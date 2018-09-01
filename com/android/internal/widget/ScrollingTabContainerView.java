// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.animation.*;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;
import com.android.internal.view.ActionBarPolicy;

public class ScrollingTabContainerView extends HorizontalScrollView
    implements android.widget.AdapterView.OnItemClickListener
{
    private class TabAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return ScrollingTabContainerView._2D_get1(ScrollingTabContainerView.this).getChildCount();
        }

        public View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
                view = ScrollingTabContainerView._2D_wrap0(ScrollingTabContainerView.this, mDropDownContext, (android.app.ActionBar.Tab)getItem(i), true);
            else
                ((TabView)view).bindTab((android.app.ActionBar.Tab)getItem(i));
            return view;
        }

        public Object getItem(int i)
        {
            return ((TabView)ScrollingTabContainerView._2D_get1(ScrollingTabContainerView.this).getChildAt(i)).getTab();
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            if(view == null)
                view = ScrollingTabContainerView._2D_wrap0(ScrollingTabContainerView.this, ScrollingTabContainerView._2D_get0(ScrollingTabContainerView.this), (android.app.ActionBar.Tab)getItem(i), true);
            else
                ((TabView)view).bindTab((android.app.ActionBar.Tab)getItem(i));
            return view;
        }

        public void setDropDownViewContext(Context context)
        {
            mDropDownContext = context;
        }

        private Context mDropDownContext;
        final ScrollingTabContainerView this$0;

        public TabAdapter(Context context)
        {
            this$0 = ScrollingTabContainerView.this;
            super();
            setDropDownViewContext(context);
        }
    }

    private class TabClickListener
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            ((TabView)view).getTab().select();
            int i = ScrollingTabContainerView._2D_get1(ScrollingTabContainerView.this).getChildCount();
            int j = 0;
            while(j < i) 
            {
                View view1 = ScrollingTabContainerView._2D_get1(ScrollingTabContainerView.this).getChildAt(j);
                boolean flag;
                if(view1 == view)
                    flag = true;
                else
                    flag = false;
                view1.setSelected(flag);
                j++;
            }
        }

        final ScrollingTabContainerView this$0;

        private TabClickListener()
        {
            this$0 = ScrollingTabContainerView.this;
            super();
        }

        TabClickListener(TabClickListener tabclicklistener)
        {
            this();
        }
    }

    private class TabView extends LinearLayout
    {

        public void bindTab(android.app.ActionBar.Tab tab)
        {
            mTab = tab;
            update();
        }

        public CharSequence getAccessibilityClassName()
        {
            return android/app/ActionBar$Tab.getName();
        }

        public android.app.ActionBar.Tab getTab()
        {
            return mTab;
        }

        public void onMeasure(int i, int j)
        {
            super.onMeasure(i, j);
            if(mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth)
                super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(mMaxTabWidth, 0x40000000), j);
        }

        public void setSelected(boolean flag)
        {
            boolean flag1;
            if(isSelected() != flag)
                flag1 = true;
            else
                flag1 = false;
            super.setSelected(flag);
            if(flag1 && flag)
                sendAccessibilityEvent(4);
        }

        public void update()
        {
            Object obj;
            android.app.ActionBar.Tab tab;
            View view;
            obj = null;
            tab = mTab;
            view = tab.getCustomView();
            if(view == null) goto _L2; else goto _L1
_L1:
            obj = view.getParent();
            if(obj != this)
            {
                if(obj != null)
                    ((ViewGroup)obj).removeView(view);
                addView(view);
            }
            mCustomView = view;
            if(mTextView != null)
                mTextView.setVisibility(8);
            if(mIconView != null)
            {
                mIconView.setVisibility(8);
                mIconView.setImageDrawable(null);
            }
_L5:
            return;
_L2:
            if(mCustomView != null)
            {
                removeView(mCustomView);
                mCustomView = null;
            }
            android.graphics.drawable.Drawable drawable = tab.getIcon();
            CharSequence charsequence = tab.getText();
            android.widget.LinearLayout.LayoutParams layoutparams;
            TextView textview;
            boolean flag;
            if(drawable != null)
            {
                if(mIconView == null)
                {
                    ImageView imageview = new ImageView(getContext());
                    android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-2, -2);
                    layoutparams1.gravity = 16;
                    imageview.setLayoutParams(layoutparams1);
                    addView(imageview, 0);
                    mIconView = imageview;
                }
                mIconView.setImageDrawable(drawable);
                mIconView.setVisibility(0);
            } else
            if(mIconView != null)
            {
                mIconView.setVisibility(8);
                mIconView.setImageDrawable(null);
            }
            flag = TextUtils.isEmpty(charsequence) ^ true;
            if(!flag) goto _L4; else goto _L3
_L3:
            if(mTextView == null)
            {
                textview = new TextView(getContext(), null, 0x10102f5);
                textview.setEllipsize(android.text.TextUtils.TruncateAt.END);
                layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2);
                layoutparams.gravity = 16;
                textview.setLayoutParams(layoutparams);
                addView(textview);
                mTextView = textview;
            }
            mTextView.setText(charsequence);
            mTextView.setVisibility(0);
_L6:
            if(mIconView != null)
                mIconView.setContentDescription(tab.getContentDescription());
            if(!flag)
                obj = tab.getContentDescription();
            setTooltipText(((CharSequence) (obj)));
            if(true) goto _L5; else goto _L4
_L4:
            if(mTextView != null)
            {
                mTextView.setVisibility(8);
                mTextView.setText(null);
            }
              goto _L6
        }

        private View mCustomView;
        private ImageView mIconView;
        private android.app.ActionBar.Tab mTab;
        private TextView mTextView;
        final ScrollingTabContainerView this$0;

        public TabView(Context context, android.app.ActionBar.Tab tab, boolean flag)
        {
            this$0 = ScrollingTabContainerView.this;
            super(context, null, 0x10102f3);
            mTab = tab;
            if(flag)
                setGravity(0x800013);
            update();
        }
    }

    protected class VisibilityAnimListener
        implements android.animation.Animator.AnimatorListener
    {

        public void onAnimationCancel(Animator animator)
        {
            mCanceled = true;
        }

        public void onAnimationEnd(Animator animator)
        {
            if(mCanceled)
            {
                return;
            } else
            {
                mVisibilityAnim = null;
                setVisibility(mFinalVisibility);
                return;
            }
        }

        public void onAnimationRepeat(Animator animator)
        {
        }

        public void onAnimationStart(Animator animator)
        {
            setVisibility(0);
            mVisibilityAnim = animator;
            mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(int i)
        {
            mFinalVisibility = i;
            return this;
        }

        private boolean mCanceled;
        private int mFinalVisibility;
        final ScrollingTabContainerView this$0;

        protected VisibilityAnimListener()
        {
            this$0 = ScrollingTabContainerView.this;
            super();
            mCanceled = false;
        }
    }


    static Context _2D_get0(ScrollingTabContainerView scrollingtabcontainerview)
    {
        return scrollingtabcontainerview.mContext;
    }

    static LinearLayout _2D_get1(ScrollingTabContainerView scrollingtabcontainerview)
    {
        return scrollingtabcontainerview.mTabLayout;
    }

    static TabView _2D_wrap0(ScrollingTabContainerView scrollingtabcontainerview, Context context, android.app.ActionBar.Tab tab, boolean flag)
    {
        return scrollingtabcontainerview.createTabView(context, tab, flag);
    }

    public ScrollingTabContainerView(Context context)
    {
        super(context);
        setHorizontalScrollBarEnabled(false);
        context = ActionBarPolicy.get(context);
        setContentHeight(context.getTabContainerHeight());
        mStackedTabMaxWidth = context.getStackedTabMaxWidth();
        mTabLayout = createTabLayout();
        addView(mTabLayout, new android.view.ViewGroup.LayoutParams(-2, -1));
    }

    private Spinner createSpinner()
    {
        Spinner spinner = new Spinner(getContext(), null, 0x10102d7);
        spinner.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -1));
        spinner.setOnItemClickListenerInt(this);
        return spinner;
    }

    private LinearLayout createTabLayout()
    {
        LinearLayout linearlayout = new LinearLayout(getContext(), null, 0x10102f4);
        linearlayout.setMeasureWithLargestChildEnabled(true);
        linearlayout.setGravity(17);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -1));
        return linearlayout;
    }

    private TabView createTabView(Context context, android.app.ActionBar.Tab tab, boolean flag)
    {
        context = new TabView(context, tab, flag);
        if(flag)
        {
            context.setBackgroundDrawable(null);
            context.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, mContentHeight));
        } else
        {
            context.setFocusable(true);
            if(mTabClickListener == null)
                mTabClickListener = new TabClickListener(null);
            context.setOnClickListener(mTabClickListener);
        }
        return context;
    }

    private boolean isCollapsed()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mTabSpinner != null)
        {
            flag1 = flag;
            if(mTabSpinner.getParent() == this)
                flag1 = true;
        }
        return flag1;
    }

    private void performCollapse()
    {
        if(isCollapsed())
            return;
        if(mTabSpinner == null)
            mTabSpinner = createSpinner();
        removeView(mTabLayout);
        addView(mTabSpinner, new android.view.ViewGroup.LayoutParams(-2, -1));
        if(mTabSpinner.getAdapter() == null)
        {
            TabAdapter tabadapter = new TabAdapter(mContext);
            tabadapter.setDropDownViewContext(mTabSpinner.getPopupContext());
            mTabSpinner.setAdapter(tabadapter);
        }
        if(mTabSelector != null)
        {
            removeCallbacks(mTabSelector);
            mTabSelector = null;
        }
        mTabSpinner.setSelection(mSelectedTabIndex);
    }

    private boolean performExpand()
    {
        if(!isCollapsed())
        {
            return false;
        } else
        {
            removeView(mTabSpinner);
            addView(mTabLayout, new android.view.ViewGroup.LayoutParams(-2, -1));
            setTabSelected(mTabSpinner.getSelectedItemPosition());
            return false;
        }
    }

    public void addTab(android.app.ActionBar.Tab tab, int i, boolean flag)
    {
        tab = createTabView(mContext, tab, false);
        mTabLayout.addView(tab, i, new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(flag)
            tab.setSelected(true);
        if(mAllowCollapse)
            requestLayout();
    }

    public void addTab(android.app.ActionBar.Tab tab, boolean flag)
    {
        tab = createTabView(mContext, tab, false);
        mTabLayout.addView(tab, new android.widget.LinearLayout.LayoutParams(0, -1, 1.0F));
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(flag)
            tab.setSelected(true);
        if(mAllowCollapse)
            requestLayout();
    }

    public void animateToTab(int i)
    {
        final View tabView = mTabLayout.getChildAt(i);
        if(mTabSelector != null)
            removeCallbacks(mTabSelector);
        mTabSelector = new Runnable() {

            public void run()
            {
                int j = tabView.getLeft();
                int k = (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(j - k, 0);
                mTabSelector = null;
            }

            final ScrollingTabContainerView this$0;
            final View val$tabView;

            
            {
                this$0 = ScrollingTabContainerView.this;
                tabView = view;
                super();
            }
        }
;
        post(mTabSelector);
    }

    public void animateToVisibility(int i)
    {
        if(mVisibilityAnim != null)
            mVisibilityAnim.cancel();
        if(i == 0)
        {
            if(getVisibility() != 0)
                setAlpha(0.0F);
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, "alpha", new float[] {
                1.0F
            });
            objectanimator.setDuration(200L);
            objectanimator.setInterpolator(sAlphaInterpolator);
            objectanimator.addListener(mVisAnimListener.withFinalVisibility(i));
            objectanimator.start();
        } else
        {
            ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(this, "alpha", new float[] {
                0.0F
            });
            objectanimator1.setDuration(200L);
            objectanimator1.setInterpolator(sAlphaInterpolator);
            objectanimator1.addListener(mVisAnimListener.withFinalVisibility(i));
            objectanimator1.start();
        }
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mTabSelector != null)
            post(mTabSelector);
    }

    protected void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        configuration = ActionBarPolicy.get(getContext());
        setContentHeight(configuration.getTabContainerHeight());
        mStackedTabMaxWidth = configuration.getStackedTabMaxWidth();
    }

    public void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mTabSelector != null)
            removeCallbacks(mTabSelector);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        ((TabView)view).getTab().select();
    }

    public void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        boolean flag;
        boolean flag1;
        if(k == 0x40000000)
            flag = true;
        else
            flag = false;
        setFillViewport(flag);
        j = mTabLayout.getChildCount();
        if(j > 1 && (k == 0x40000000 || k == 0x80000000))
        {
            int l;
            if(j > 2)
                mMaxTabWidth = (int)((float)android.view.View.MeasureSpec.getSize(i) * 0.4F);
            else
                mMaxTabWidth = android.view.View.MeasureSpec.getSize(i) / 2;
            mMaxTabWidth = Math.min(mMaxTabWidth, mStackedTabMaxWidth);
        } else
        {
            mMaxTabWidth = -1;
        }
        l = android.view.View.MeasureSpec.makeMeasureSpec(mContentHeight, 0x40000000);
        if(!flag)
            flag1 = mAllowCollapse;
        else
            flag1 = false;
        if(flag1)
        {
            mTabLayout.measure(0, l);
            if(mTabLayout.getMeasuredWidth() > android.view.View.MeasureSpec.getSize(i))
                performCollapse();
            else
                performExpand();
        } else
        {
            performExpand();
        }
        j = getMeasuredWidth();
        super.onMeasure(i, l);
        i = getMeasuredWidth();
        if(flag && j != i)
            setTabSelected(mSelectedTabIndex);
    }

    public void removeAllTabs()
    {
        mTabLayout.removeAllViews();
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(mAllowCollapse)
            requestLayout();
    }

    public void removeTabAt(int i)
    {
        mTabLayout.removeViewAt(i);
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(mAllowCollapse)
            requestLayout();
    }

    public void setAllowCollapse(boolean flag)
    {
        mAllowCollapse = flag;
    }

    public void setContentHeight(int i)
    {
        mContentHeight = i;
        requestLayout();
    }

    public void setTabSelected(int i)
    {
        mSelectedTabIndex = i;
        int j = mTabLayout.getChildCount();
        int k = 0;
        while(k < j) 
        {
            View view = mTabLayout.getChildAt(k);
            boolean flag;
            if(k == i)
                flag = true;
            else
                flag = false;
            view.setSelected(flag);
            if(flag)
                animateToTab(i);
            k++;
        }
        if(mTabSpinner != null && i >= 0)
            mTabSpinner.setSelection(i);
    }

    public void updateTab(int i)
    {
        ((TabView)mTabLayout.getChildAt(i)).update();
        if(mTabSpinner != null)
            ((TabAdapter)mTabSpinner.getAdapter()).notifyDataSetChanged();
        if(mAllowCollapse)
            requestLayout();
    }

    private static final int FADE_DURATION = 200;
    private static final String TAG = "ScrollingTabContainerView";
    private static final TimeInterpolator sAlphaInterpolator = new DecelerateInterpolator();
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    private LinearLayout mTabLayout;
    Runnable mTabSelector;
    private Spinner mTabSpinner;
    protected final VisibilityAnimListener mVisAnimListener = new VisibilityAnimListener();
    protected Animator mVisibilityAnim;

}
