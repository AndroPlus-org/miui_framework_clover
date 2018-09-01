// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.view.View;

public abstract class SimpleItemAnimator extends RecyclerView.ItemAnimator
{

    public SimpleItemAnimator()
    {
        mSupportsChangeAnimations = true;
    }

    public abstract boolean animateAdd(RecyclerView.ViewHolder viewholder);

    public boolean animateAppearance(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        if(itemholderinfo != null && (itemholderinfo.left != itemholderinfo1.left || itemholderinfo.top != itemholderinfo1.top))
            return animateMove(viewholder, itemholderinfo.left, itemholderinfo.top, itemholderinfo1.left, itemholderinfo1.top);
        else
            return animateAdd(viewholder);
    }

    public abstract boolean animateChange(RecyclerView.ViewHolder viewholder, RecyclerView.ViewHolder viewholder1, int i, int j, int k, int l);

    public boolean animateChange(RecyclerView.ViewHolder viewholder, RecyclerView.ViewHolder viewholder1, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        int i = itemholderinfo.left;
        int j = itemholderinfo.top;
        int k;
        int l;
        if(viewholder1.shouldIgnore())
        {
            k = itemholderinfo.left;
            l = itemholderinfo.top;
        } else
        {
            k = itemholderinfo1.left;
            l = itemholderinfo1.top;
        }
        return animateChange(viewholder, viewholder1, i, j, k, l);
    }

    public boolean animateDisappearance(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        int i = itemholderinfo.left;
        int j = itemholderinfo.top;
        itemholderinfo = viewholder.itemView;
        int k;
        int l;
        if(itemholderinfo1 == null)
            k = itemholderinfo.getLeft();
        else
            k = itemholderinfo1.left;
        if(itemholderinfo1 == null)
            l = itemholderinfo.getTop();
        else
            l = itemholderinfo1.top;
        if(!viewholder.isRemoved() && (i != k || j != l))
        {
            itemholderinfo.layout(k, l, itemholderinfo.getWidth() + k, itemholderinfo.getHeight() + l);
            return animateMove(viewholder, i, j, k, l);
        } else
        {
            return animateRemove(viewholder);
        }
    }

    public abstract boolean animateMove(RecyclerView.ViewHolder viewholder, int i, int j, int k, int l);

    public boolean animatePersistence(RecyclerView.ViewHolder viewholder, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo, RecyclerView.ItemAnimator.ItemHolderInfo itemholderinfo1)
    {
        if(itemholderinfo.left != itemholderinfo1.left || itemholderinfo.top != itemholderinfo1.top)
        {
            return animateMove(viewholder, itemholderinfo.left, itemholderinfo.top, itemholderinfo1.left, itemholderinfo1.top);
        } else
        {
            dispatchMoveFinished(viewholder);
            return false;
        }
    }

    public abstract boolean animateRemove(RecyclerView.ViewHolder viewholder);

    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewholder)
    {
        boolean flag;
        if(mSupportsChangeAnimations)
            flag = viewholder.isInvalid();
        else
            flag = true;
        return flag;
    }

    public final void dispatchAddFinished(RecyclerView.ViewHolder viewholder)
    {
        onAddFinished(viewholder);
        dispatchAnimationFinished(viewholder);
    }

    public final void dispatchAddStarting(RecyclerView.ViewHolder viewholder)
    {
        onAddStarting(viewholder);
    }

    public final void dispatchChangeFinished(RecyclerView.ViewHolder viewholder, boolean flag)
    {
        onChangeFinished(viewholder, flag);
        dispatchAnimationFinished(viewholder);
    }

    public final void dispatchChangeStarting(RecyclerView.ViewHolder viewholder, boolean flag)
    {
        onChangeStarting(viewholder, flag);
    }

    public final void dispatchMoveFinished(RecyclerView.ViewHolder viewholder)
    {
        onMoveFinished(viewholder);
        dispatchAnimationFinished(viewholder);
    }

    public final void dispatchMoveStarting(RecyclerView.ViewHolder viewholder)
    {
        onMoveStarting(viewholder);
    }

    public final void dispatchRemoveFinished(RecyclerView.ViewHolder viewholder)
    {
        onRemoveFinished(viewholder);
        dispatchAnimationFinished(viewholder);
    }

    public final void dispatchRemoveStarting(RecyclerView.ViewHolder viewholder)
    {
        onRemoveStarting(viewholder);
    }

    public boolean getSupportsChangeAnimations()
    {
        return mSupportsChangeAnimations;
    }

    public void onAddFinished(RecyclerView.ViewHolder viewholder)
    {
    }

    public void onAddStarting(RecyclerView.ViewHolder viewholder)
    {
    }

    public void onChangeFinished(RecyclerView.ViewHolder viewholder, boolean flag)
    {
    }

    public void onChangeStarting(RecyclerView.ViewHolder viewholder, boolean flag)
    {
    }

    public void onMoveFinished(RecyclerView.ViewHolder viewholder)
    {
    }

    public void onMoveStarting(RecyclerView.ViewHolder viewholder)
    {
    }

    public void onRemoveFinished(RecyclerView.ViewHolder viewholder)
    {
    }

    public void onRemoveStarting(RecyclerView.ViewHolder viewholder)
    {
    }

    public void setSupportsChangeAnimations(boolean flag)
    {
        mSupportsChangeAnimations = flag;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "SimpleItemAnimator";
    boolean mSupportsChangeAnimations;
}
