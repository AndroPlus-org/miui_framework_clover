// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.*;

// Referenced classes of package android.widget:
//            LinearLayout

public class TableRow extends LinearLayout
{
    private class ChildrenTracker
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        static void _2D_wrap0(ChildrenTracker childrentracker, android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
        {
            childrentracker.setOnHierarchyChangeListener(onhierarchychangelistener);
        }

        private void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
        {
            listener = onhierarchychangelistener;
        }

        public void onChildViewAdded(View view, View view1)
        {
            TableRow._2D_set0(TableRow.this, null);
            if(listener != null)
                listener.onChildViewAdded(view, view1);
        }

        public void onChildViewRemoved(View view, View view1)
        {
            TableRow._2D_set0(TableRow.this, null);
            if(listener != null)
                listener.onChildViewRemoved(view, view1);
        }

        private android.view.ViewGroup.OnHierarchyChangeListener listener;
        final TableRow this$0;

        private ChildrenTracker()
        {
            this$0 = TableRow.this;
            super();
        }

        ChildrenTracker(ChildrenTracker childrentracker)
        {
            this();
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams
    {

        static int[] _2D_get0(LayoutParams layoutparams)
        {
            return layoutparams.mOffset;
        }

        protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
        {
            super.encodeProperties(viewhierarchyencoder);
            viewhierarchyencoder.addProperty("layout:column", column);
            viewhierarchyencoder.addProperty("layout:span", span);
        }

        protected void setBaseAttributes(TypedArray typedarray, int i, int j)
        {
            if(typedarray.hasValue(i))
                width = typedarray.getLayoutDimension(i, "layout_width");
            else
                width = -1;
            if(typedarray.hasValue(j))
                height = typedarray.getLayoutDimension(j, "layout_height");
            else
                height = -2;
        }

        private static final int LOCATION = 0;
        private static final int LOCATION_NEXT = 1;
        public int column;
        private int mOffset[];
        public int span;

        public LayoutParams()
        {
            super(-1, -2);
            mOffset = new int[2];
            column = -1;
            span = 1;
        }

        public LayoutParams(int i)
        {
            this();
            column = i;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
            mOffset = new int[2];
            column = -1;
            span = 1;
        }

        public LayoutParams(int i, int j, float f)
        {
            super(i, j, f);
            mOffset = new int[2];
            column = -1;
            span = 1;
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mOffset = new int[2];
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.TableRow_Cell);
            column = context.getInt(0, -1);
            span = context.getInt(1, 1);
            if(span <= 1)
                span = 1;
            context.recycle();
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
            mOffset = new int[2];
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
            mOffset = new int[2];
        }
    }


    static SparseIntArray _2D_set0(TableRow tablerow, SparseIntArray sparseintarray)
    {
        tablerow.mColumnToChildIndex = sparseintarray;
        return sparseintarray;
    }

    public TableRow(Context context)
    {
        super(context);
        mNumColumns = 0;
        initTableRow();
    }

    public TableRow(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mNumColumns = 0;
        initTableRow();
    }

    private void initTableRow()
    {
        android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener = mOnHierarchyChangeListener;
        mChildrenTracker = new ChildrenTracker(null);
        if(onhierarchychangelistener != null)
            ChildrenTracker._2D_wrap0(mChildrenTracker, onhierarchychangelistener);
        super.setOnHierarchyChangeListener(mChildrenTracker);
    }

    private void mapIndexAndColumns()
    {
        if(mColumnToChildIndex == null)
        {
            int i = 0;
            int j = getChildCount();
            mColumnToChildIndex = new SparseIntArray();
            SparseIntArray sparseintarray = mColumnToChildIndex;
            for(int k = 0; k < j;)
            {
                LayoutParams layoutparams = (LayoutParams)getChildAt(k).getLayoutParams();
                int l = i;
                if(layoutparams.column >= i)
                    l = layoutparams.column;
                for(i = 0; i < layoutparams.span;)
                {
                    sparseintarray.put(l, k);
                    i++;
                    l++;
                }

                k++;
                i = l;
            }

            mNumColumns = i;
        }
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
        return android/widget/TableRow.getName();
    }

    int getChildrenSkipCount(View view, int i)
    {
        return ((LayoutParams)view.getLayoutParams()).span - 1;
    }

    int[] getColumnsWidths(int i, int j)
    {
        int k;
        int ai[];
        int l;
        k = getVirtualChildCount();
        if(mColumnWidths == null || k != mColumnWidths.length)
            mColumnWidths = new int[k];
        ai = mColumnWidths;
        l = 0;
_L9:
        View view;
        LayoutParams layoutparams;
        if(l >= k)
            break MISSING_BLOCK_LABEL_204;
        view = getVirtualChildAt(l);
        if(view == null || view.getVisibility() == 8)
            break MISSING_BLOCK_LABEL_195;
        layoutparams = (LayoutParams)view.getLayoutParams();
        if(layoutparams.span != 1) goto _L2; else goto _L1
_L1:
        layoutparams.width;
        JVM INSTR tableswitch -2 -1: default 112
    //                   -2 161
    //                   -1 173;
           goto _L3 _L4 _L5
_L3:
        int i1 = android.view.View.MeasureSpec.makeMeasureSpec(layoutparams.width, 0x40000000);
_L6:
        view.measure(i1, i1);
        ai[l] = view.getMeasuredWidth() + layoutparams.leftMargin + layoutparams.rightMargin;
_L7:
        l++;
        continue; /* Loop/switch isn't completed */
_L4:
        i1 = getChildMeasureSpec(i, 0, -2);
          goto _L6
_L5:
        i1 = android.view.View.MeasureSpec.makeSafeMeasureSpec(android.view.View.MeasureSpec.getSize(j), 0);
          goto _L6
_L2:
        ai[l] = 0;
          goto _L7
        ai[l] = 0;
          goto _L7
        return ai;
        if(true) goto _L9; else goto _L8
_L8:
    }

    int getLocationOffset(View view)
    {
        return LayoutParams._2D_get0((LayoutParams)view.getLayoutParams())[0];
    }

    int getNextLocationOffset(View view)
    {
        return LayoutParams._2D_get0((LayoutParams)view.getLayoutParams())[1];
    }

    public View getVirtualChildAt(int i)
    {
        if(mColumnToChildIndex == null)
            mapIndexAndColumns();
        i = mColumnToChildIndex.get(i, -1);
        if(i != -1)
            return getChildAt(i);
        else
            return null;
    }

    public int getVirtualChildCount()
    {
        if(mColumnToChildIndex == null)
            mapIndexAndColumns();
        return mNumColumns;
    }

    void measureChildBeforeLayout(View view, int i, int j, int k, int l, int i1)
    {
        if(mConstrainedColumnWidths == null) goto _L2; else goto _L1
_L1:
        LayoutParams layoutparams;
        boolean flag;
        layoutparams = (LayoutParams)view.getLayoutParams();
        int j1 = 0x40000000;
        j = 0;
        int k1 = layoutparams.span;
        int ai[] = mConstrainedColumnWidths;
        for(k = 0; k < k1; k++)
            j += ai[i + k];

        k = layoutparams.gravity;
        flag = Gravity.isHorizontal(k);
        i = j1;
        if(flag)
            i = 0x80000000;
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, j - layoutparams.leftMargin - layoutparams.rightMargin), i), getChildMeasureSpec(l, mPaddingTop + mPaddingBottom + layoutparams.topMargin + layoutparams.bottomMargin + i1, layoutparams.height));
        if(!flag) goto _L4; else goto _L3
_L3:
        i = view.getMeasuredWidth();
        LayoutParams._2D_get0(layoutparams)[1] = j - i;
        Gravity.getAbsoluteGravity(k, getLayoutDirection()) & 7;
        JVM INSTR tableswitch 1 5: default 212
    //                   1 230
    //                   2 212
    //                   3 212
    //                   4 212
    //                   5 213;
           goto _L5 _L6 _L5 _L5 _L5 _L7
_L5:
        return;
_L7:
        LayoutParams._2D_get0(layoutparams)[0] = LayoutParams._2D_get0(layoutparams)[1];
        continue; /* Loop/switch isn't completed */
_L6:
        LayoutParams._2D_get0(layoutparams)[0] = LayoutParams._2D_get0(layoutparams)[1] / 2;
        continue; /* Loop/switch isn't completed */
_L4:
        view = LayoutParams._2D_get0(layoutparams);
        LayoutParams._2D_get0(layoutparams)[1] = 0;
        view[0] = 0;
        continue; /* Loop/switch isn't completed */
_L2:
        super.measureChildBeforeLayout(view, i, j, k, l, i1);
        if(true) goto _L5; else goto _L8
_L8:
    }

    int measureNullChild(int i)
    {
        return mConstrainedColumnWidths[i];
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        layoutHorizontal(i, j, k, l);
    }

    protected void onMeasure(int i, int j)
    {
        measureHorizontal(i, j);
    }

    void setColumnCollapsed(int i, boolean flag)
    {
        View view = getVirtualChildAt(i);
        if(view != null)
        {
            if(flag)
                i = 8;
            else
                i = 0;
            view.setVisibility(i);
        }
    }

    void setColumnsWidthConstraints(int ai[])
    {
        if(ai == null || ai.length < getVirtualChildCount())
        {
            throw new IllegalArgumentException("columnWidths should be >= getVirtualChildCount()");
        } else
        {
            mConstrainedColumnWidths = ai;
            return;
        }
    }

    public void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        ChildrenTracker._2D_wrap0(mChildrenTracker, onhierarchychangelistener);
    }

    private ChildrenTracker mChildrenTracker;
    private SparseIntArray mColumnToChildIndex;
    private int mColumnWidths[];
    private int mConstrainedColumnWidths[];
    private int mNumColumns;
}
