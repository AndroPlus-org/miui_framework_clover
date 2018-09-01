// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;


class RtlSpacingHelper
{

    RtlSpacingHelper()
    {
        mLeft = 0;
        mRight = 0;
        mStart = 0x80000000;
        mEnd = 0x80000000;
        mExplicitLeft = 0;
        mExplicitRight = 0;
        mIsRtl = false;
        mIsRelative = false;
    }

    public int getEnd()
    {
        int i;
        if(mIsRtl)
            i = mLeft;
        else
            i = mRight;
        return i;
    }

    public int getLeft()
    {
        return mLeft;
    }

    public int getRight()
    {
        return mRight;
    }

    public int getStart()
    {
        int i;
        if(mIsRtl)
            i = mRight;
        else
            i = mLeft;
        return i;
    }

    public void setAbsolute(int i, int j)
    {
        mIsRelative = false;
        if(i != 0x80000000)
        {
            mExplicitLeft = i;
            mLeft = i;
        }
        if(j != 0x80000000)
        {
            mExplicitRight = j;
            mRight = j;
        }
    }

    public void setDirection(boolean flag)
    {
        if(flag == mIsRtl)
            return;
        mIsRtl = flag;
        if(mIsRelative)
        {
            if(flag)
            {
                int i;
                if(mEnd != 0x80000000)
                    i = mEnd;
                else
                    i = mExplicitLeft;
                mLeft = i;
                if(mStart != 0x80000000)
                    i = mStart;
                else
                    i = mExplicitRight;
                mRight = i;
            } else
            {
                int j;
                if(mStart != 0x80000000)
                    j = mStart;
                else
                    j = mExplicitLeft;
                mLeft = j;
                if(mEnd != 0x80000000)
                    j = mEnd;
                else
                    j = mExplicitRight;
                mRight = j;
            }
        } else
        {
            mLeft = mExplicitLeft;
            mRight = mExplicitRight;
        }
    }

    public void setRelative(int i, int j)
    {
        mStart = i;
        mEnd = j;
        mIsRelative = true;
        if(!mIsRtl) goto _L2; else goto _L1
_L1:
        if(j != 0x80000000)
            mLeft = j;
        if(i != 0x80000000)
            mRight = i;
_L4:
        return;
_L2:
        if(i != 0x80000000)
            mLeft = i;
        if(j != 0x80000000)
            mRight = j;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final int UNDEFINED = 0x80000000;
    private int mEnd;
    private int mExplicitLeft;
    private int mExplicitRight;
    private boolean mIsRelative;
    private boolean mIsRtl;
    private int mLeft;
    private int mRight;
    private int mStart;
}
