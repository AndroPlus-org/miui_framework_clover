// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;

public final class WindowInsets
{

    public WindowInsets(Rect rect)
    {
        this(rect, null, null, false, false);
    }

    public WindowInsets(Rect rect, Rect rect1, Rect rect2, boolean flag, boolean flag1)
    {
        boolean flag2 = true;
        super();
        mSystemWindowInsetsConsumed = false;
        mWindowDecorInsetsConsumed = false;
        mStableInsetsConsumed = false;
        boolean flag3;
        if(rect == null)
            flag3 = true;
        else
            flag3 = false;
        mSystemWindowInsetsConsumed = flag3;
        if(mSystemWindowInsetsConsumed)
            rect = EMPTY_RECT;
        mSystemWindowInsets = rect;
        if(rect1 == null)
            flag3 = true;
        else
            flag3 = false;
        mWindowDecorInsetsConsumed = flag3;
        if(mWindowDecorInsetsConsumed)
            rect1 = EMPTY_RECT;
        mWindowDecorInsets = rect1;
        if(rect2 == null)
            flag3 = flag2;
        else
            flag3 = false;
        mStableInsetsConsumed = flag3;
        if(mStableInsetsConsumed)
            rect2 = EMPTY_RECT;
        mStableInsets = rect2;
        mIsRound = flag;
        mAlwaysConsumeNavBar = flag1;
    }

    public WindowInsets(WindowInsets windowinsets)
    {
        mSystemWindowInsetsConsumed = false;
        mWindowDecorInsetsConsumed = false;
        mStableInsetsConsumed = false;
        mSystemWindowInsets = windowinsets.mSystemWindowInsets;
        mWindowDecorInsets = windowinsets.mWindowDecorInsets;
        mStableInsets = windowinsets.mStableInsets;
        mSystemWindowInsetsConsumed = windowinsets.mSystemWindowInsetsConsumed;
        mWindowDecorInsetsConsumed = windowinsets.mWindowDecorInsetsConsumed;
        mStableInsetsConsumed = windowinsets.mStableInsetsConsumed;
        mIsRound = windowinsets.mIsRound;
        mAlwaysConsumeNavBar = windowinsets.mAlwaysConsumeNavBar;
    }

    public WindowInsets consumeStableInsets()
    {
        WindowInsets windowinsets = new WindowInsets(this);
        windowinsets.mStableInsets = EMPTY_RECT;
        windowinsets.mStableInsetsConsumed = true;
        return windowinsets;
    }

    public WindowInsets consumeSystemWindowInsets()
    {
        WindowInsets windowinsets = new WindowInsets(this);
        windowinsets.mSystemWindowInsets = EMPTY_RECT;
        windowinsets.mSystemWindowInsetsConsumed = true;
        return windowinsets;
    }

    public WindowInsets consumeSystemWindowInsets(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        int i = 0;
        if(flag || flag1 || flag2 || flag3)
        {
            WindowInsets windowinsets = new WindowInsets(this);
            int j;
            int k;
            int l;
            if(flag)
                j = 0;
            else
                j = mSystemWindowInsets.left;
            if(flag1)
                k = 0;
            else
                k = mSystemWindowInsets.top;
            if(flag2)
                l = 0;
            else
                l = mSystemWindowInsets.right;
            if(!flag3)
                i = mSystemWindowInsets.bottom;
            windowinsets.mSystemWindowInsets = new Rect(j, k, l, i);
            return windowinsets;
        } else
        {
            return this;
        }
    }

    public WindowInsets consumeWindowDecorInsets()
    {
        WindowInsets windowinsets = new WindowInsets(this);
        windowinsets.mWindowDecorInsets.set(0, 0, 0, 0);
        windowinsets.mWindowDecorInsetsConsumed = true;
        return windowinsets;
    }

    public WindowInsets consumeWindowDecorInsets(boolean flag, boolean flag1, boolean flag2, boolean flag3)
    {
        int i = 0;
        if(flag || flag1 || flag2 || flag3)
        {
            WindowInsets windowinsets = new WindowInsets(this);
            int j;
            int k;
            int l;
            if(flag)
                j = 0;
            else
                j = mWindowDecorInsets.left;
            if(flag1)
                k = 0;
            else
                k = mWindowDecorInsets.top;
            if(flag2)
                l = 0;
            else
                l = mWindowDecorInsets.right;
            if(!flag3)
                i = mWindowDecorInsets.bottom;
            windowinsets.mWindowDecorInsets = new Rect(j, k, l, i);
            return windowinsets;
        } else
        {
            return this;
        }
    }

    public int getStableInsetBottom()
    {
        return mStableInsets.bottom;
    }

    public int getStableInsetLeft()
    {
        return mStableInsets.left;
    }

    public int getStableInsetRight()
    {
        return mStableInsets.right;
    }

    public int getStableInsetTop()
    {
        return mStableInsets.top;
    }

    public int getSystemWindowInsetBottom()
    {
        return mSystemWindowInsets.bottom;
    }

    public int getSystemWindowInsetLeft()
    {
        return mSystemWindowInsets.left;
    }

    public int getSystemWindowInsetRight()
    {
        return mSystemWindowInsets.right;
    }

    public int getSystemWindowInsetTop()
    {
        return mSystemWindowInsets.top;
    }

    public Rect getSystemWindowInsets()
    {
        if(mTempRect == null)
            mTempRect = new Rect();
        if(mSystemWindowInsets != null)
            mTempRect.set(mSystemWindowInsets);
        else
            mTempRect.setEmpty();
        return mTempRect;
    }

    public int getWindowDecorInsetBottom()
    {
        return mWindowDecorInsets.bottom;
    }

    public int getWindowDecorInsetLeft()
    {
        return mWindowDecorInsets.left;
    }

    public int getWindowDecorInsetRight()
    {
        return mWindowDecorInsets.right;
    }

    public int getWindowDecorInsetTop()
    {
        return mWindowDecorInsets.top;
    }

    public boolean hasInsets()
    {
        boolean flag;
        if(!hasSystemWindowInsets() && !hasWindowDecorInsets())
            flag = hasStableInsets();
        else
            flag = true;
        return flag;
    }

    public boolean hasStableInsets()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mStableInsets.top != 0) goto _L2; else goto _L1
_L1:
        if(mStableInsets.left == 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mStableInsets.right == 0)
        {
            flag1 = flag;
            if(mStableInsets.bottom == 0)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean hasSystemWindowInsets()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mSystemWindowInsets.left != 0) goto _L2; else goto _L1
_L1:
        if(mSystemWindowInsets.top == 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mSystemWindowInsets.right == 0)
        {
            flag1 = flag;
            if(mSystemWindowInsets.bottom == 0)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean hasWindowDecorInsets()
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(mWindowDecorInsets.left != 0) goto _L2; else goto _L1
_L1:
        if(mWindowDecorInsets.top == 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(mWindowDecorInsets.right == 0)
        {
            flag1 = flag;
            if(mWindowDecorInsets.bottom == 0)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean isConsumed()
    {
        boolean flag;
        if(mSystemWindowInsetsConsumed && mWindowDecorInsetsConsumed)
            flag = mStableInsetsConsumed;
        else
            flag = false;
        return flag;
    }

    public boolean isRound()
    {
        return mIsRound;
    }

    public WindowInsets replaceSystemWindowInsets(int i, int j, int k, int l)
    {
        WindowInsets windowinsets = new WindowInsets(this);
        windowinsets.mSystemWindowInsets = new Rect(i, j, k, l);
        return windowinsets;
    }

    public WindowInsets replaceSystemWindowInsets(Rect rect)
    {
        WindowInsets windowinsets = new WindowInsets(this);
        windowinsets.mSystemWindowInsets = new Rect(rect);
        return windowinsets;
    }

    public WindowInsets replaceWindowDecorInsets(int i, int j, int k, int l)
    {
        WindowInsets windowinsets = new WindowInsets(this);
        windowinsets.mWindowDecorInsets = new Rect(i, j, k, l);
        return windowinsets;
    }

    public boolean shouldAlwaysConsumeNavBar()
    {
        return mAlwaysConsumeNavBar;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("WindowInsets{systemWindowInsets=").append(mSystemWindowInsets).append(" windowDecorInsets=").append(mWindowDecorInsets).append(" stableInsets=").append(mStableInsets);
        String s;
        if(isRound())
            s = " round}";
        else
            s = "}";
        return stringbuilder.append(s).toString();
    }

    public static final WindowInsets CONSUMED = new WindowInsets(null, null, null, false, false);
    private static final Rect EMPTY_RECT = new Rect(0, 0, 0, 0);
    private boolean mAlwaysConsumeNavBar;
    private boolean mIsRound;
    private Rect mStableInsets;
    private boolean mStableInsetsConsumed;
    private Rect mSystemWindowInsets;
    private boolean mSystemWindowInsetsConsumed;
    private Rect mTempRect;
    private Rect mWindowDecorInsets;
    private boolean mWindowDecorInsetsConsumed;

}
