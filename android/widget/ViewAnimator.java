// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// Referenced classes of package android.widget:
//            FrameLayout

public class ViewAnimator extends FrameLayout
{

    public ViewAnimator(Context context)
    {
        super(context);
        mWhichChild = 0;
        mFirstTime = true;
        mAnimateFirstTime = true;
        initViewAnimator(context, null);
    }

    public ViewAnimator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mWhichChild = 0;
        mFirstTime = true;
        mAnimateFirstTime = true;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ViewAnimator);
        int i = typedarray.getResourceId(0, 0);
        if(i > 0)
            setInAnimation(context, i);
        i = typedarray.getResourceId(1, 0);
        if(i > 0)
            setOutAnimation(context, i);
        setAnimateFirstView(typedarray.getBoolean(2, true));
        typedarray.recycle();
        initViewAnimator(context, attributeset);
    }

    private void initViewAnimator(Context context, AttributeSet attributeset)
    {
        if(attributeset == null)
        {
            mMeasureAllChildren = true;
            return;
        } else
        {
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.FrameLayout);
            setMeasureAllChildren(context.getBoolean(0, true));
            context.recycle();
            return;
        }
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        super.addView(view, i, layoutparams);
        if(getChildCount() == 1)
            view.setVisibility(0);
        else
            view.setVisibility(8);
        if(i >= 0 && mWhichChild >= i)
            setDisplayedChild(mWhichChild + 1);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ViewAnimator.getName();
    }

    public boolean getAnimateFirstView()
    {
        return mAnimateFirstTime;
    }

    public int getBaseline()
    {
        int i;
        if(getCurrentView() != null)
            i = getCurrentView().getBaseline();
        else
            i = super.getBaseline();
        return i;
    }

    public View getCurrentView()
    {
        return getChildAt(mWhichChild);
    }

    public int getDisplayedChild()
    {
        return mWhichChild;
    }

    public Animation getInAnimation()
    {
        return mInAnimation;
    }

    public Animation getOutAnimation()
    {
        return mOutAnimation;
    }

    public void removeAllViews()
    {
        super.removeAllViews();
        mWhichChild = 0;
        mFirstTime = true;
    }

    public void removeView(View view)
    {
        int i = indexOfChild(view);
        if(i >= 0)
            removeViewAt(i);
    }

    public void removeViewAt(int i)
    {
        int j;
        super.removeViewAt(i);
        j = getChildCount();
        if(j != 0) goto _L2; else goto _L1
_L1:
        mWhichChild = 0;
        mFirstTime = true;
_L4:
        return;
_L2:
        if(mWhichChild >= j)
            setDisplayedChild(j - 1);
        else
        if(mWhichChild == i)
            setDisplayedChild(mWhichChild);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void removeViewInLayout(View view)
    {
        removeView(view);
    }

    public void removeViews(int i, int j)
    {
        super.removeViews(i, j);
        if(getChildCount() != 0) goto _L2; else goto _L1
_L1:
        mWhichChild = 0;
        mFirstTime = true;
_L4:
        return;
_L2:
        if(mWhichChild >= i && mWhichChild < i + j)
            setDisplayedChild(mWhichChild);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void removeViewsInLayout(int i, int j)
    {
        removeViews(i, j);
    }

    public void setAnimateFirstView(boolean flag)
    {
        mAnimateFirstTime = flag;
    }

    public void setDisplayedChild(int i)
    {
        mWhichChild = i;
        if(i >= getChildCount())
            mWhichChild = 0;
        else
        if(i < 0)
            mWhichChild = getChildCount() - 1;
        if(getFocusedChild() != null)
            i = 1;
        else
            i = 0;
        showOnly(mWhichChild);
        if(i != 0)
            requestFocus(2);
    }

    public void setInAnimation(Context context, int i)
    {
        setInAnimation(AnimationUtils.loadAnimation(context, i));
    }

    public void setInAnimation(Animation animation)
    {
        mInAnimation = animation;
    }

    public void setOutAnimation(Context context, int i)
    {
        setOutAnimation(AnimationUtils.loadAnimation(context, i));
    }

    public void setOutAnimation(Animation animation)
    {
        mOutAnimation = animation;
    }

    public void showNext()
    {
        setDisplayedChild(mWhichChild + 1);
    }

    void showOnly(int i)
    {
        boolean flag;
        if(mFirstTime)
            flag = mAnimateFirstTime;
        else
            flag = true;
        showOnly(i, flag);
    }

    void showOnly(int i, boolean flag)
    {
        int j = getChildCount();
        int k = 0;
        while(k < j) 
        {
            View view = getChildAt(k);
            if(k == i)
            {
                if(flag && mInAnimation != null)
                    view.startAnimation(mInAnimation);
                view.setVisibility(0);
                mFirstTime = false;
            } else
            {
                if(flag && mOutAnimation != null && view.getVisibility() == 0)
                    view.startAnimation(mOutAnimation);
                else
                if(view.getAnimation() == mInAnimation)
                    view.clearAnimation();
                view.setVisibility(8);
            }
            k++;
        }
    }

    public void showPrevious()
    {
        setDisplayedChild(mWhichChild - 1);
    }

    boolean mAnimateFirstTime;
    boolean mFirstTime;
    Animation mInAnimation;
    Animation mOutAnimation;
    int mWhichChild;
}
