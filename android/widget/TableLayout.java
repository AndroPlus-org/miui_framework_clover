// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import java.util.regex.Pattern;

// Referenced classes of package android.widget:
//            LinearLayout, TableRow

public class TableLayout extends LinearLayout
{
    public static class LayoutParams extends LinearLayout.LayoutParams
    {

        protected void setBaseAttributes(TypedArray typedarray, int i, int j)
        {
            width = -1;
            if(typedarray.hasValue(j))
                height = typedarray.getLayoutDimension(j, "layout_height");
            else
                height = -2;
        }

        public LayoutParams()
        {
            super(-1, -2);
        }

        public LayoutParams(int i, int j)
        {
            super(-1, j);
        }

        public LayoutParams(int i, int j, float f)
        {
            super(-1, j, f);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            width = -1;
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            width = -1;
            if(marginlayoutparams instanceof LayoutParams)
                weight = ((LayoutParams)marginlayoutparams).weight;
        }
    }

    private class PassThroughHierarchyChangeListener
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        static android.view.ViewGroup.OnHierarchyChangeListener _2D_set0(PassThroughHierarchyChangeListener passthroughhierarchychangelistener, android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
        {
            passthroughhierarchychangelistener.mOnHierarchyChangeListener = onhierarchychangelistener;
            return onhierarchychangelistener;
        }

        public void onChildViewAdded(View view, View view1)
        {
            TableLayout._2D_wrap0(TableLayout.this, view1);
            if(mOnHierarchyChangeListener != null)
                mOnHierarchyChangeListener.onChildViewAdded(view, view1);
        }

        public void onChildViewRemoved(View view, View view1)
        {
            if(mOnHierarchyChangeListener != null)
                mOnHierarchyChangeListener.onChildViewRemoved(view, view1);
        }

        private android.view.ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
        final TableLayout this$0;

        private PassThroughHierarchyChangeListener()
        {
            this$0 = TableLayout.this;
            super();
        }

        PassThroughHierarchyChangeListener(PassThroughHierarchyChangeListener passthroughhierarchychangelistener)
        {
            this();
        }
    }


    static void _2D_wrap0(TableLayout tablelayout, View view)
    {
        tablelayout.trackCollapsedColumns(view);
    }

    public TableLayout(Context context)
    {
        super(context);
        initTableLayout();
    }

    public TableLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TableLayout);
        attributeset = context.getString(0);
        if(attributeset != null)
            if(attributeset.charAt(0) == '*')
                mStretchAllColumns = true;
            else
                mStretchableColumns = parseColumns(attributeset);
        attributeset = context.getString(1);
        if(attributeset != null)
            if(attributeset.charAt(0) == '*')
                mShrinkAllColumns = true;
            else
                mShrinkableColumns = parseColumns(attributeset);
        attributeset = context.getString(2);
        if(attributeset != null)
            mCollapsedColumns = parseColumns(attributeset);
        context.recycle();
        initTableLayout();
    }

    private void findLargestCells(int i, int j)
    {
        boolean flag;
        int k;
        int l;
        flag = true;
        k = getChildCount();
        l = 0;
_L2:
        Object obj;
        int i1;
        if(l >= k)
            break MISSING_BLOCK_LABEL_254;
        obj = getChildAt(l);
        if(((View) (obj)).getVisibility() != 8)
            break; /* Loop/switch isn't completed */
        i1 = ((flag) ? 1 : 0);
_L3:
        l++;
        flag = i1;
        if(true) goto _L2; else goto _L1
_L1:
        int ai[];
        i1 = ((flag) ? 1 : 0);
        if(obj instanceof TableRow)
        {
label0:
            {
                obj = (TableRow)obj;
                ((TableRow) (obj)).getLayoutParams().height = -2;
                ai = ((TableRow) (obj)).getColumnsWidths(i, j);
                i1 = ai.length;
                if(!flag)
                    break label0;
                if(mMaxWidths == null || mMaxWidths.length != i1)
                    mMaxWidths = new int[i1];
                System.arraycopy(ai, 0, mMaxWidths, 0, i1);
                i1 = 0;
            }
        }
          goto _L3
        int j1;
        int k1;
        int ai2[];
        j1 = mMaxWidths.length;
        k1 = i1 - j1;
        if(k1 > 0)
        {
            int ai1[] = mMaxWidths;
            mMaxWidths = new int[i1];
            System.arraycopy(ai1, 0, mMaxWidths, 0, ai1.length);
            System.arraycopy(ai, ai1.length, mMaxWidths, ai1.length, k1);
        }
        ai2 = mMaxWidths;
        j1 = Math.min(j1, i1);
        k1 = 0;
_L5:
        i1 = ((flag) ? 1 : 0);
        if(k1 >= j1) goto _L3; else goto _L4
_L4:
        ai2[k1] = Math.max(ai2[k1], ai[k1]);
        k1++;
          goto _L5
          goto _L3
    }

    private void initTableLayout()
    {
        if(mCollapsedColumns == null)
            mCollapsedColumns = new SparseBooleanArray();
        if(mStretchableColumns == null)
            mStretchableColumns = new SparseBooleanArray();
        if(mShrinkableColumns == null)
            mShrinkableColumns = new SparseBooleanArray();
        setOrientation(1);
        mPassThroughListener = new PassThroughHierarchyChangeListener(null);
        super.setOnHierarchyChangeListener(mPassThroughListener);
        mInitialized = true;
    }

    private void mutateColumnsWidth(SparseBooleanArray sparsebooleanarray, boolean flag, int i, int j)
    {
        int k = 0;
        int ai[] = mMaxWidths;
        int i1 = ai.length;
        int j1;
        int k1;
        if(flag)
            j1 = i1;
        else
            j1 = sparsebooleanarray.size();
        k1 = (i - j) / j1;
        j = getChildCount();
        for(i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            if(view instanceof TableRow)
                view.forceLayout();
        }

        if(!flag)
        {
            j = 0;
            i = k;
            k = j;
            while(k < j1) 
            {
                int l1 = sparsebooleanarray.keyAt(k);
                j = i;
                if(sparsebooleanarray.valueAt(k))
                    if(l1 < i1)
                    {
                        ai[l1] = ai[l1] + k1;
                        j = i;
                    } else
                    {
                        j = i + 1;
                    }
                k++;
                i = j;
            }
        } else
        {
            for(i = 0; i < j1; i++)
                ai[i] = ai[i] + k1;

            return;
        }
        if(i > 0 && i < j1)
        {
            j = (i * k1) / (j1 - i);
            i = 0;
            while(i < j1) 
            {
                int l = sparsebooleanarray.keyAt(i);
                if(sparsebooleanarray.valueAt(i) && l < i1)
                    if(j > ai[l])
                        ai[l] = 0;
                    else
                        ai[l] = ai[l] + j;
                i++;
            }
        }
    }

    private static SparseBooleanArray parseColumns(String s)
    {
        int i;
        SparseBooleanArray sparsebooleanarray;
        int j;
        i = 0;
        sparsebooleanarray = new SparseBooleanArray();
        s = Pattern.compile("\\s*,\\s*").split(s);
        j = s.length;
_L3:
        if(i >= j) goto _L2; else goto _L1
_L1:
        String s1 = s[i];
        int k = Integer.parseInt(s1);
        if(k >= 0)
            try
            {
                sparsebooleanarray.put(k, true);
            }
            catch(NumberFormatException numberformatexception) { }
        i++;
          goto _L3
_L2:
        return sparsebooleanarray;
    }

    private void requestRowsLayout()
    {
        if(mInitialized)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
                getChildAt(j).requestLayout();

        }
    }

    private void shrinkAndStretchColumns(int i)
    {
        int k;
        int j = 0;
        if(mMaxWidths == null)
            return;
        k = 0;
        int ai[] = mMaxWidths;
        for(int l = ai.length; j < l; j++)
            k += ai[j];

        i = android.view.View.MeasureSpec.getSize(i) - mPaddingLeft - mPaddingRight;
        if(k <= i || !mShrinkAllColumns && mShrinkableColumns.size() <= 0) goto _L2; else goto _L1
_L1:
        mutateColumnsWidth(mShrinkableColumns, mShrinkAllColumns, i, k);
_L4:
        return;
_L2:
        if(k < i && (mStretchAllColumns || mStretchableColumns.size() > 0))
            mutateColumnsWidth(mStretchableColumns, mStretchAllColumns, i, k);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void trackCollapsedColumns(View view)
    {
        if(view instanceof TableRow)
        {
            view = (TableRow)view;
            SparseBooleanArray sparsebooleanarray = mCollapsedColumns;
            int i = sparsebooleanarray.size();
            for(int j = 0; j < i; j++)
            {
                int k = sparsebooleanarray.keyAt(j);
                boolean flag = sparsebooleanarray.valueAt(j);
                if(flag)
                    view.setColumnCollapsed(k, flag);
            }

        }
    }

    public void addView(View view)
    {
        super.addView(view);
        requestRowsLayout();
    }

    public void addView(View view, int i)
    {
        super.addView(view, i);
        requestRowsLayout();
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        requestRowsLayout();
    }

    public void addView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, layoutparams);
        requestRowsLayout();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams();
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return generateLayoutParams(layoutparams);
    }

    public volatile LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    protected LinearLayout.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return new LayoutParams(layoutparams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/TableLayout.getName();
    }

    public boolean isColumnCollapsed(int i)
    {
        return mCollapsedColumns.get(i);
    }

    public boolean isColumnShrinkable(int i)
    {
        boolean flag;
        if(!mShrinkAllColumns)
            flag = mShrinkableColumns.get(i);
        else
            flag = true;
        return flag;
    }

    public boolean isColumnStretchable(int i)
    {
        boolean flag;
        if(!mStretchAllColumns)
            flag = mStretchableColumns.get(i);
        else
            flag = true;
        return flag;
    }

    public boolean isShrinkAllColumns()
    {
        return mShrinkAllColumns;
    }

    public boolean isStretchAllColumns()
    {
        return mStretchAllColumns;
    }

    void measureChildBeforeLayout(View view, int i, int j, int k, int l, int i1)
    {
        if(view instanceof TableRow)
            ((TableRow)view).setColumnsWidthConstraints(mMaxWidths);
        super.measureChildBeforeLayout(view, i, j, k, l, i1);
    }

    void measureVertical(int i, int j)
    {
        findLargestCells(i, j);
        shrinkAndStretchColumns(i);
        super.measureVertical(i, j);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        layoutVertical(i, j, k, l);
    }

    protected void onMeasure(int i, int j)
    {
        measureVertical(i, j);
    }

    public void requestLayout()
    {
        if(mInitialized)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
                getChildAt(j).forceLayout();

        }
        super.requestLayout();
    }

    public void setColumnCollapsed(int i, boolean flag)
    {
        mCollapsedColumns.put(i, flag);
        int j = getChildCount();
        for(int k = 0; k < j; k++)
        {
            View view = getChildAt(k);
            if(view instanceof TableRow)
                ((TableRow)view).setColumnCollapsed(i, flag);
        }

        requestRowsLayout();
    }

    public void setColumnShrinkable(int i, boolean flag)
    {
        mShrinkableColumns.put(i, flag);
        requestRowsLayout();
    }

    public void setColumnStretchable(int i, boolean flag)
    {
        mStretchableColumns.put(i, flag);
        requestRowsLayout();
    }

    public void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        PassThroughHierarchyChangeListener._2D_set0(mPassThroughListener, onhierarchychangelistener);
    }

    public void setShrinkAllColumns(boolean flag)
    {
        mShrinkAllColumns = flag;
    }

    public void setStretchAllColumns(boolean flag)
    {
        mStretchAllColumns = flag;
    }

    private SparseBooleanArray mCollapsedColumns;
    private boolean mInitialized;
    private int mMaxWidths[];
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private boolean mShrinkAllColumns;
    private SparseBooleanArray mShrinkableColumns;
    private boolean mStretchAllColumns;
    private SparseBooleanArray mStretchableColumns;
}
