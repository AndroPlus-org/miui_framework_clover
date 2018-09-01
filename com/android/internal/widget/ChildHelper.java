// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

class ChildHelper
{
    static class Bucket
    {

        private void ensureNext()
        {
            if(mNext == null)
                mNext = new Bucket();
        }

        void clear(int i)
        {
            if(i >= 64)
            {
                if(mNext != null)
                    mNext.clear(i - 64);
            } else
            {
                mData = mData & 1L << i;
            }
        }

        int countOnesBefore(int i)
        {
            if(mNext == null)
                if(i >= 64)
                    return Long.bitCount(mData);
                else
                    return Long.bitCount(mData & (1L << i) - 1L);
            if(i < 64)
                return Long.bitCount(mData & (1L << i) - 1L);
            else
                return mNext.countOnesBefore(i - 64) + Long.bitCount(mData);
        }

        boolean get(int i)
        {
            if(i >= 64)
            {
                ensureNext();
                return mNext.get(i - 64);
            }
            boolean flag;
            if((mData & 1L << i) != 0L)
                flag = true;
            else
                flag = false;
            return flag;
        }

        void insert(int i, boolean flag)
        {
            if(i < 64) goto _L2; else goto _L1
_L1:
            ensureNext();
            mNext.insert(i - 64, flag);
_L4:
            return;
_L2:
            boolean flag1;
            long l;
            if((mData & 0x8000000000000000L) != 0L)
                flag1 = true;
            else
                flag1 = false;
            l = (1L << i) - 1L;
            mData = mData & l | (mData & l) << 1;
            if(flag)
                set(i);
            else
                clear(i);
            if(flag1 || mNext != null)
            {
                ensureNext();
                mNext.insert(0, flag1);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        boolean remove(int i)
        {
            if(i >= 64)
            {
                ensureNext();
                return mNext.remove(i - 64);
            }
            long l = 1L << i;
            boolean flag;
            if((mData & l) != 0L)
                flag = true;
            else
                flag = false;
            mData = mData & l;
            l--;
            mData = mData & l | Long.rotateRight(mData & l, 1);
            if(mNext != null)
            {
                if(mNext.get(0))
                    set(63);
                mNext.remove(0);
            }
            return flag;
        }

        void reset()
        {
            mData = 0L;
            if(mNext != null)
                mNext.reset();
        }

        void set(int i)
        {
            if(i >= 64)
            {
                ensureNext();
                mNext.set(i - 64);
            } else
            {
                mData = mData | 1L << i;
            }
        }

        public String toString()
        {
            String s;
            if(mNext == null)
                s = Long.toBinaryString(mData);
            else
                s = (new StringBuilder()).append(mNext.toString()).append("xx").append(Long.toBinaryString(mData)).toString();
            return s;
        }

        static final int BITS_PER_WORD = 64;
        static final long LAST_BIT = 0x8000000000000000L;
        long mData;
        Bucket mNext;

        Bucket()
        {
            mData = 0L;
        }
    }

    static interface Callback
    {

        public abstract void addView(View view, int i);

        public abstract void attachViewToParent(View view, int i, android.view.ViewGroup.LayoutParams layoutparams);

        public abstract void detachViewFromParent(int i);

        public abstract View getChildAt(int i);

        public abstract int getChildCount();

        public abstract RecyclerView.ViewHolder getChildViewHolder(View view);

        public abstract int indexOfChild(View view);

        public abstract void onEnteredHiddenState(View view);

        public abstract void onLeftHiddenState(View view);

        public abstract void removeAllViews();

        public abstract void removeViewAt(int i);
    }


    ChildHelper(Callback callback)
    {
        mCallback = callback;
    }

    private int getOffset(int i)
    {
        if(i < 0)
            return -1;
        int j = mCallback.getChildCount();
        int l;
        for(int k = i; k < j; k += l)
        {
            l = i - (k - mBucket.countOnesBefore(k));
            if(l == 0)
            {
                for(; mBucket.get(k); k++);
                return k;
            }
        }

        return -1;
    }

    private void hideViewInternal(View view)
    {
        mHiddenViews.add(view);
        mCallback.onEnteredHiddenState(view);
    }

    private boolean unhideViewInternal(View view)
    {
        if(mHiddenViews.remove(view))
        {
            mCallback.onLeftHiddenState(view);
            return true;
        } else
        {
            return false;
        }
    }

    void addView(View view, int i, boolean flag)
    {
        if(i < 0)
            i = mCallback.getChildCount();
        else
            i = getOffset(i);
        mBucket.insert(i, flag);
        if(flag)
            hideViewInternal(view);
        mCallback.addView(view, i);
    }

    void addView(View view, boolean flag)
    {
        addView(view, -1, flag);
    }

    void attachViewToParent(View view, int i, android.view.ViewGroup.LayoutParams layoutparams, boolean flag)
    {
        if(i < 0)
            i = mCallback.getChildCount();
        else
            i = getOffset(i);
        mBucket.insert(i, flag);
        if(flag)
            hideViewInternal(view);
        mCallback.attachViewToParent(view, i, layoutparams);
    }

    void detachViewFromParent(int i)
    {
        i = getOffset(i);
        mBucket.remove(i);
        mCallback.detachViewFromParent(i);
    }

    View findHiddenNonRemovedView(int i)
    {
        int j = mHiddenViews.size();
        for(int k = 0; k < j; k++)
        {
            View view = (View)mHiddenViews.get(k);
            RecyclerView.ViewHolder viewholder = mCallback.getChildViewHolder(view);
            if(viewholder.getLayoutPosition() == i && viewholder.isInvalid() ^ true && viewholder.isRemoved() ^ true)
                return view;
        }

        return null;
    }

    View getChildAt(int i)
    {
        i = getOffset(i);
        return mCallback.getChildAt(i);
    }

    int getChildCount()
    {
        return mCallback.getChildCount() - mHiddenViews.size();
    }

    View getUnfilteredChildAt(int i)
    {
        return mCallback.getChildAt(i);
    }

    int getUnfilteredChildCount()
    {
        return mCallback.getChildCount();
    }

    void hide(View view)
    {
        int i = mCallback.indexOfChild(view);
        if(i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("view is not a child, cannot hide ").append(view).toString());
        } else
        {
            mBucket.set(i);
            hideViewInternal(view);
            return;
        }
    }

    int indexOfChild(View view)
    {
        int i = mCallback.indexOfChild(view);
        if(i == -1)
            return -1;
        if(mBucket.get(i))
            return -1;
        else
            return i - mBucket.countOnesBefore(i);
    }

    boolean isHidden(View view)
    {
        return mHiddenViews.contains(view);
    }

    void removeAllViewsUnfiltered()
    {
        mBucket.reset();
        for(int i = mHiddenViews.size() - 1; i >= 0; i--)
        {
            mCallback.onLeftHiddenState((View)mHiddenViews.get(i));
            mHiddenViews.remove(i);
        }

        mCallback.removeAllViews();
    }

    void removeView(View view)
    {
        int i = mCallback.indexOfChild(view);
        if(i < 0)
            return;
        if(mBucket.remove(i))
            unhideViewInternal(view);
        mCallback.removeViewAt(i);
    }

    void removeViewAt(int i)
    {
        i = getOffset(i);
        View view = mCallback.getChildAt(i);
        if(view == null)
            return;
        if(mBucket.remove(i))
            unhideViewInternal(view);
        mCallback.removeViewAt(i);
    }

    boolean removeViewIfHidden(View view)
    {
        int i = mCallback.indexOfChild(view);
        if(i == -1)
        {
            unhideViewInternal(view);
            return true;
        }
        if(mBucket.get(i))
        {
            mBucket.remove(i);
            unhideViewInternal(view);
            mCallback.removeViewAt(i);
            return true;
        } else
        {
            return false;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append(mBucket.toString()).append(", hidden list:").append(mHiddenViews.size()).toString();
    }

    void unhide(View view)
    {
        int i = mCallback.indexOfChild(view);
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("view is not a child, cannot hide ").append(view).toString());
        if(!mBucket.get(i))
        {
            throw new RuntimeException((new StringBuilder()).append("trying to unhide a view that was not hidden").append(view).toString());
        } else
        {
            mBucket.clear(i);
            unhideViewInternal(view);
            return;
        }
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    final Bucket mBucket = new Bucket();
    final Callback mCallback;
    final List mHiddenViews = new ArrayList();
}
