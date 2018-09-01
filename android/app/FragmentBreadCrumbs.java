// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;

// Referenced classes of package android.app:
//            BackStackRecord, Activity, FragmentManagerImpl, FragmentManager

public class FragmentBreadCrumbs extends ViewGroup
    implements FragmentManager.OnBackStackChangedListener
{
    public static interface OnBreadCrumbClickListener
    {

        public abstract boolean onBreadCrumbClick(FragmentManager.BackStackEntry backstackentry, int i);
    }


    static OnBreadCrumbClickListener _2D_get0(FragmentBreadCrumbs fragmentbreadcrumbs)
    {
        return fragmentbreadcrumbs.mOnBreadCrumbClickListener;
    }

    static android.view.View.OnClickListener _2D_get1(FragmentBreadCrumbs fragmentbreadcrumbs)
    {
        return fragmentbreadcrumbs.mParentClickListener;
    }

    public FragmentBreadCrumbs(Context context)
    {
        this(context, null);
    }

    public FragmentBreadCrumbs(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x111003b);
    }

    public FragmentBreadCrumbs(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public FragmentBreadCrumbs(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mMaxVisible = -1;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.FragmentBreadCrumbs, i, j);
        mGravity = context.getInt(0, 0x800013);
        mLayoutResId = context.getResourceId(2, 0x1090063);
        mTextColor = context.getColor(1, 0);
        context.recycle();
    }

    private BackStackRecord createBackStackEntry(CharSequence charsequence, CharSequence charsequence1)
    {
        if(charsequence == null)
        {
            return null;
        } else
        {
            BackStackRecord backstackrecord = new BackStackRecord((FragmentManagerImpl)mActivity.getFragmentManager());
            backstackrecord.setBreadCrumbTitle(charsequence);
            backstackrecord.setBreadCrumbShortTitle(charsequence1);
            return backstackrecord;
        }
    }

    private FragmentManager.BackStackEntry getPreEntry(int i)
    {
        if(mParentEntry != null)
        {
            BackStackRecord backstackrecord;
            if(i == 0)
                backstackrecord = mParentEntry;
            else
                backstackrecord = mTopEntry;
            return backstackrecord;
        } else
        {
            return mTopEntry;
        }
    }

    private int getPreEntryCount()
    {
        int i = 1;
        int j;
        if(mTopEntry != null)
            j = 1;
        else
            j = 0;
        if(mParentEntry == null)
            i = 0;
        return j + i;
    }

    public void onBackStackChanged()
    {
        updateCrumbs();
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        View view;
        int i1;
        int j1;
        int k1;
        if(getChildCount() == 0)
            return;
        view = getChildAt(0);
        l = mPaddingTop;
        i1 = mPaddingTop;
        j1 = view.getMeasuredHeight();
        k1 = mPaddingBottom;
        i = getLayoutDirection();
        Gravity.getAbsoluteGravity(mGravity & 0x800007, i);
        JVM INSTR lookupswitch 2: default 84
    //                   1: 194
    //                   5: 168;
           goto _L1 _L2 _L3
_L1:
        j = mPaddingLeft;
        i = j + view.getMeasuredWidth();
_L5:
        k = j;
        if(j < mPaddingLeft)
            k = mPaddingLeft;
        j = i;
        if(i > mRight - mLeft - mPaddingRight)
            j = mRight - mLeft - mPaddingRight;
        view.layout(k, l, j, (i1 + j1) - k1);
        return;
_L3:
        i = mRight - mLeft - mPaddingRight;
        j = i - view.getMeasuredWidth();
        continue; /* Loop/switch isn't completed */
_L2:
        j = mPaddingLeft + (mRight - mLeft - view.getMeasuredWidth()) / 2;
        i = j + view.getMeasuredWidth();
        if(true) goto _L5; else goto _L4
_L4:
    }

    protected void onMeasure(int i, int j)
    {
        int k = getChildCount();
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        for(int k1 = 0; k1 < k;)
        {
            View view = getChildAt(k1);
            int i2 = l;
            int j2 = i1;
            int k2 = j1;
            if(view.getVisibility() != 8)
            {
                measureChild(view, i, j);
                j2 = Math.max(i1, view.getMeasuredWidth());
                i2 = Math.max(l, view.getMeasuredHeight());
                k2 = combineMeasuredStates(j1, view.getMeasuredState());
            }
            k1++;
            l = i2;
            i1 = j2;
            j1 = k2;
        }

        int l2 = mPaddingLeft;
        int l1 = mPaddingRight;
        l = Math.max(l + (mPaddingTop + mPaddingBottom), getSuggestedMinimumHeight());
        setMeasuredDimension(resolveSizeAndState(Math.max(i1 + (l2 + l1), getSuggestedMinimumWidth()), i, j1), resolveSizeAndState(l, j, j1 << 16));
    }

    public void setActivity(Activity activity)
    {
        mActivity = activity;
        mInflater = (LayoutInflater)activity.getSystemService("layout_inflater");
        mContainer = (LinearLayout)mInflater.inflate(0x1090065, this, false);
        addView(mContainer);
        activity.getFragmentManager().addOnBackStackChangedListener(this);
        updateCrumbs();
        setLayoutTransition(new LayoutTransition());
    }

    public void setMaxVisible(int i)
    {
        if(i < 1)
        {
            throw new IllegalArgumentException("visibleCrumbs must be greater than zero");
        } else
        {
            mMaxVisible = i;
            return;
        }
    }

    public void setOnBreadCrumbClickListener(OnBreadCrumbClickListener onbreadcrumbclicklistener)
    {
        mOnBreadCrumbClickListener = onbreadcrumbclicklistener;
    }

    public void setParentTitle(CharSequence charsequence, CharSequence charsequence1, android.view.View.OnClickListener onclicklistener)
    {
        mParentEntry = createBackStackEntry(charsequence, charsequence1);
        mParentClickListener = onclicklistener;
        updateCrumbs();
    }

    public void setTitle(CharSequence charsequence, CharSequence charsequence1)
    {
        mTopEntry = createBackStackEntry(charsequence, charsequence1);
        updateCrumbs();
    }

    void updateCrumbs()
    {
        FragmentManager fragmentmanager = mActivity.getFragmentManager();
        int i = fragmentmanager.getBackStackEntryCount();
        int j = getPreEntryCount();
        int k = mContainer.getChildCount();
        for(int l = 0; l < i + j;)
        {
            FragmentManager.BackStackEntry backstackentry;
            int j1;
            if(l < j)
                backstackentry = getPreEntry(l);
            else
                backstackentry = fragmentmanager.getBackStackEntryAt(l - j);
            j1 = k;
            if(l < k)
            {
                j1 = k;
                if(mContainer.getChildAt(l).getTag() != backstackentry)
                {
                    for(j1 = l; j1 < k; j1++)
                        mContainer.removeViewAt(l);

                    j1 = l;
                }
            }
            if(l >= j1)
            {
                View view2 = mInflater.inflate(mLayoutResId, this, false);
                TextView textview = (TextView)view2.findViewById(0x1020016);
                textview.setText(backstackentry.getBreadCrumbTitle());
                textview.setTag(backstackentry);
                textview.setTextColor(mTextColor);
                if(l == 0)
                    view2.findViewById(0x10202d9).setVisibility(8);
                mContainer.addView(view2);
                textview.setOnClickListener(mOnClickListener);
            }
            l++;
            k = j1;
        }

        int i1;
        for(i1 = mContainer.getChildCount(); i1 > i + j; i1--)
            mContainer.removeViewAt(i1 - 1);

        k = 0;
        while(k < i1) 
        {
            View view1 = mContainer.getChildAt(k);
            View view = view1.findViewById(0x1020016);
            boolean flag;
            if(k < i1 - 1)
                flag = true;
            else
                flag = false;
            view.setEnabled(flag);
            if(mMaxVisible > 0)
            {
                int k1;
                if(k < i1 - mMaxVisible)
                    k1 = 8;
                else
                    k1 = 0;
                view1.setVisibility(k1);
                view1 = view1.findViewById(0x10202d9);
                if(k > i1 - mMaxVisible && k != 0)
                    k1 = 0;
                else
                    k1 = 8;
                view1.setVisibility(k1);
            }
            k++;
        }
    }

    private static final int DEFAULT_GRAVITY = 0x800013;
    Activity mActivity;
    LinearLayout mContainer;
    private int mGravity;
    LayoutInflater mInflater;
    private int mLayoutResId;
    int mMaxVisible;
    private OnBreadCrumbClickListener mOnBreadCrumbClickListener;
    private android.view.View.OnClickListener mOnClickListener = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            Object obj = null;
            if(view.getTag() instanceof FragmentManager.BackStackEntry)
            {
                FragmentManager.BackStackEntry backstackentry = (FragmentManager.BackStackEntry)view.getTag();
                if(backstackentry == mParentEntry)
                {
                    if(FragmentBreadCrumbs._2D_get1(FragmentBreadCrumbs.this) != null)
                        FragmentBreadCrumbs._2D_get1(FragmentBreadCrumbs.this).onClick(view);
                } else
                {
                    if(FragmentBreadCrumbs._2D_get0(FragmentBreadCrumbs.this) != null)
                    {
                        OnBreadCrumbClickListener onbreadcrumbclicklistener = FragmentBreadCrumbs._2D_get0(FragmentBreadCrumbs.this);
                        if(backstackentry == mTopEntry)
                            view = obj;
                        else
                            view = backstackentry;
                        if(onbreadcrumbclicklistener.onBreadCrumbClick(view, 0))
                            return;
                    }
                    if(backstackentry == mTopEntry)
                        mActivity.getFragmentManager().popBackStack();
                    else
                        mActivity.getFragmentManager().popBackStack(backstackentry.getId(), 0);
                }
            }
        }

        final FragmentBreadCrumbs this$0;

            
            {
                this$0 = FragmentBreadCrumbs.this;
                super();
            }
    }
;
    private android.view.View.OnClickListener mParentClickListener;
    BackStackRecord mParentEntry;
    private int mTextColor;
    BackStackRecord mTopEntry;
}
