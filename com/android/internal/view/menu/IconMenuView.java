// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.*;
import java.util.ArrayList;

// Referenced classes of package com.android.internal.view.menu:
//            MenuView, IconMenuItemView, MenuBuilder, MenuItemImpl

public final class IconMenuView extends ViewGroup
    implements MenuBuilder.ItemInvoker, MenuView, Runnable
{
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams
    {

        int bottom;
        int desiredWidth;
        int left;
        int maxNumItemsOnRow;
        int right;
        int top;

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }

    private static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(focusedPosition);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int focusedPosition;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            focusedPosition = parcel.readInt();
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        public SavedState(Parcelable parcelable, int i)
        {
            super(parcelable);
            focusedPosition = i;
        }
    }


    static MenuBuilder _2D_get0(IconMenuView iconmenuview)
    {
        return iconmenuview.mMenu;
    }

    public IconMenuView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMenuBeingLongpressed = false;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.IconMenuView, 0, 0);
        mRowHeight = typedarray.getDimensionPixelSize(0, 64);
        mMaxRows = typedarray.getInt(1, 2);
        mMaxItems = typedarray.getInt(4, 6);
        mMaxItemsPerRow = typedarray.getInt(2, 3);
        mMoreIcon = typedarray.getDrawable(3);
        typedarray.recycle();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.MenuView, 0, 0);
        mItemBackground = context.getDrawable(5);
        mHorizontalDivider = context.getDrawable(2);
        mHorizontalDividerRects = new ArrayList();
        mVerticalDivider = context.getDrawable(3);
        mVerticalDividerRects = new ArrayList();
        mAnimations = context.getResourceId(0, 0);
        context.recycle();
        if(mHorizontalDivider != null)
        {
            mHorizontalDividerHeight = mHorizontalDivider.getIntrinsicHeight();
            if(mHorizontalDividerHeight == -1)
                mHorizontalDividerHeight = 1;
        }
        if(mVerticalDivider != null)
        {
            mVerticalDividerWidth = mVerticalDivider.getIntrinsicWidth();
            if(mVerticalDividerWidth == -1)
                mVerticalDividerWidth = 1;
        }
        mLayout = new int[mMaxRows];
        setWillNotDraw(false);
        setFocusableInTouchMode(true);
        setDescendantFocusability(0x40000);
    }

    private void calculateItemFittingMetadata(int i)
    {
        int j = mMaxItemsPerRow;
        int k = getChildCount();
        int l = 0;
label0:
        do
        {
            if(l < k)
            {
                LayoutParams layoutparams = (LayoutParams)getChildAt(l).getLayoutParams();
                layoutparams.maxNumItemsOnRow = 1;
                int i1 = j;
                do
                {
label1:
                    {
                        if(i1 > 0)
                        {
                            if(layoutparams.desiredWidth >= i / i1)
                                break label1;
                            layoutparams.maxNumItemsOnRow = i1;
                        }
                        l++;
                        continue label0;
                    }
                    i1--;
                } while(true);
            }
            return;
        } while(true);
    }

    private boolean doItemsFit()
    {
        int i = 0;
        int ai[] = mLayout;
        int j = mLayoutNumRows;
        int k = 0;
        while(k < j) 
        {
            int l = ai[k];
            if(l == 1)
            {
                i++;
            } else
            {
                int i1 = l;
                while(i1 > 0) 
                {
                    if(((LayoutParams)getChildAt(i).getLayoutParams()).maxNumItemsOnRow < l)
                        return false;
                    i1--;
                    i++;
                }
            }
            k++;
        }
        return true;
    }

    private void layoutItems(int i)
    {
        int j = getChildCount();
        if(j == 0)
        {
            mLayoutNumRows = 0;
            return;
        }
        i = Math.min((int)Math.ceil((float)j / (float)mMaxItemsPerRow), mMaxRows);
        do
        {
            if(i <= mMaxRows)
            {
                layoutItemsUsingGravity(i, j);
                break MISSING_BLOCK_LABEL_50;
            }
            do
                return;
            while(i >= j || doItemsFit());
            i++;
        } while(true);
    }

    private void layoutItemsUsingGravity(int i, int j)
    {
        int k = j / i;
        int ai[] = mLayout;
        for(int l = 0; l < i; l++)
        {
            ai[l] = k;
            if(l >= i - j % i)
                ai[l] = ai[l] + 1;
        }

        mLayoutNumRows = i;
    }

    private void positionChildren(int i, int j)
    {
        if(mHorizontalDivider != null)
            mHorizontalDividerRects.clear();
        if(mVerticalDivider != null)
            mVerticalDividerRects.clear();
        int k = mLayoutNumRows;
        int ai[] = mLayout;
        int l = 0;
        Object obj = null;
        float f = 0.0F;
        float f1 = (float)(j - mHorizontalDividerHeight * (k - 1)) / (float)k;
        for(j = 0; j < k; j++)
        {
            float f2 = 0.0F;
            float f3 = (float)(i - mVerticalDividerWidth * (ai[j] - 1)) / (float)ai[j];
            for(int i1 = 0; i1 < ai[j]; i1++)
            {
                obj = getChildAt(l);
                ((View) (obj)).measure(android.view.View.MeasureSpec.makeMeasureSpec((int)f3, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec((int)f1, 0x40000000));
                obj = (LayoutParams)((View) (obj)).getLayoutParams();
                obj.left = (int)f2;
                obj.right = (int)(f2 + f3);
                obj.top = (int)f;
                obj.bottom = (int)(f + f1);
                f2 += f3;
                l++;
                if(mVerticalDivider != null)
                    mVerticalDividerRects.add(new Rect((int)f2, (int)f, (int)((float)mVerticalDividerWidth + f2), (int)(f + f1)));
                f2 += mVerticalDividerWidth;
            }

            if(obj != null)
                obj.right = i;
            f2 = f + f1;
            f = f2;
            if(mHorizontalDivider == null)
                continue;
            f = f2;
            if(j < k - 1)
            {
                mHorizontalDividerRects.add(new Rect(0, (int)f2, i, (int)((float)mHorizontalDividerHeight + f2)));
                f = f2 + (float)mHorizontalDividerHeight;
            }
        }

    }

    private void setChildrenCaptionMode(boolean flag)
    {
        mLastChildrenCaptionMode = flag;
        for(int i = getChildCount() - 1; i >= 0; i--)
            ((IconMenuItemView)getChildAt(i)).setCaptionMode(flag);

    }

    private void setCycleShortcutCaptionMode(boolean flag)
    {
        if(!flag)
        {
            removeCallbacks(this);
            setChildrenCaptionMode(false);
            mMenuBeingLongpressed = false;
        } else
        {
            setChildrenCaptionMode(true);
        }
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    IconMenuItemView createMoreItemView()
    {
        Context context = getContext();
        IconMenuItemView iconmenuitemview = (IconMenuItemView)LayoutInflater.from(context).inflate(0x109006a, null);
        iconmenuitemview.initialize(context.getResources().getText(0x10403b9), mMoreIcon);
        iconmenuitemview.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                IconMenuView._2D_get0(IconMenuView.this).changeMenuMode();
            }

            final IconMenuView this$0;

            
            {
                this$0 = IconMenuView.this;
                super();
            }
        }
);
        return iconmenuitemview;
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() != 82) goto _L2; else goto _L1
_L1:
        if(keyevent.getAction() != 0 || keyevent.getRepeatCount() != 0) goto _L4; else goto _L3
_L3:
        removeCallbacks(this);
        postDelayed(this, ViewConfiguration.getLongPressTimeout());
_L2:
        return super.dispatchKeyEvent(keyevent);
_L4:
        if(keyevent.getAction() == 1)
        {
            if(mMenuBeingLongpressed)
            {
                setCycleShortcutCaptionMode(false);
                return true;
            }
            removeCallbacks(this);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    Drawable getItemBackgroundDrawable()
    {
        return mItemBackground.getConstantState().newDrawable(getContext().getResources());
    }

    public int[] getLayout()
    {
        return mLayout;
    }

    public int getLayoutNumRows()
    {
        return mLayoutNumRows;
    }

    int getMaxItems()
    {
        return mMaxItems;
    }

    int getNumActualItemsShown()
    {
        return mNumActualItemsShown;
    }

    public int getWindowAnimations()
    {
        return mAnimations;
    }

    public void initialize(MenuBuilder menubuilder)
    {
        mMenu = menubuilder;
    }

    public boolean invokeItem(MenuItemImpl menuitemimpl)
    {
        return mMenu.performItemAction(menuitemimpl, 0);
    }

    void markStaleChildren()
    {
        if(!mHasStaleChildren)
        {
            mHasStaleChildren = true;
            requestLayout();
        }
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        requestFocus();
    }

    protected void onDetachedFromWindow()
    {
        setCycleShortcutCaptionMode(false);
        super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas canvas)
    {
        Drawable drawable = mHorizontalDivider;
        if(drawable != null)
        {
            ArrayList arraylist = mHorizontalDividerRects;
            for(int i = arraylist.size() - 1; i >= 0; i--)
            {
                drawable.setBounds((Rect)arraylist.get(i));
                drawable.draw(canvas);
            }

        }
        drawable = mVerticalDivider;
        if(drawable != null)
        {
            ArrayList arraylist1 = mVerticalDividerRects;
            for(int j = arraylist1.size() - 1; j >= 0; j--)
            {
                drawable.setBounds((Rect)arraylist1.get(j));
                drawable.draw(canvas);
            }

        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        for(i = getChildCount() - 1; i >= 0; i--)
        {
            View view = getChildAt(i);
            LayoutParams layoutparams = (LayoutParams)view.getLayoutParams();
            view.layout(layoutparams.left, layoutparams.top, layoutparams.right, layoutparams.bottom);
        }

    }

    protected void onMeasure(int i, int j)
    {
        i = resolveSize(0x7fffffff, i);
        calculateItemFittingMetadata(i);
        layoutItems(i);
        int k = mLayoutNumRows;
        setMeasuredDimension(i, resolveSize((mRowHeight + mHorizontalDividerHeight) * k - mHorizontalDividerHeight, j));
        if(k > 0)
            positionChildren(getMeasuredWidth(), getMeasuredHeight());
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        if(((SavedState) (parcelable)).focusedPosition >= getChildCount())
            return;
        parcelable = getChildAt(((SavedState) (parcelable)).focusedPosition);
        if(parcelable != null)
            parcelable.requestFocus();
    }

    protected Parcelable onSaveInstanceState()
    {
        Parcelable parcelable = super.onSaveInstanceState();
        View view = getFocusedChild();
        for(int i = getChildCount() - 1; i >= 0; i--)
            if(getChildAt(i) == view)
                return new SavedState(parcelable, i);

        return new SavedState(parcelable, -1);
    }

    public void onWindowFocusChanged(boolean flag)
    {
        if(!flag)
            setCycleShortcutCaptionMode(false);
        super.onWindowFocusChanged(flag);
    }

    public void run()
    {
        if(mMenuBeingLongpressed)
        {
            setChildrenCaptionMode(mLastChildrenCaptionMode ^ true);
        } else
        {
            mMenuBeingLongpressed = true;
            setCycleShortcutCaptionMode(true);
        }
        postDelayed(this, 1000L);
    }

    void setNumActualItemsShown(int i)
    {
        mNumActualItemsShown = i;
    }

    private static final int ITEM_CAPTION_CYCLE_DELAY = 1000;
    private int mAnimations;
    private boolean mHasStaleChildren;
    private Drawable mHorizontalDivider;
    private int mHorizontalDividerHeight;
    private ArrayList mHorizontalDividerRects;
    private Drawable mItemBackground;
    private boolean mLastChildrenCaptionMode;
    private int mLayout[];
    private int mLayoutNumRows;
    private int mMaxItems;
    private int mMaxItemsPerRow;
    private int mMaxRows;
    private MenuBuilder mMenu;
    private boolean mMenuBeingLongpressed;
    private Drawable mMoreIcon;
    private int mNumActualItemsShown;
    private int mRowHeight;
    private Drawable mVerticalDivider;
    private ArrayList mVerticalDividerRects;
    private int mVerticalDividerWidth;
}
