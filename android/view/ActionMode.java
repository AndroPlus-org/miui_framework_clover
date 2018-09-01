// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Rect;

// Referenced classes of package android.view:
//            View, Menu, MenuInflater, MenuItem

public abstract class ActionMode
{
    public static interface Callback
    {

        public abstract boolean onActionItemClicked(ActionMode actionmode, MenuItem menuitem);

        public abstract boolean onCreateActionMode(ActionMode actionmode, Menu menu);

        public abstract void onDestroyActionMode(ActionMode actionmode);

        public abstract boolean onPrepareActionMode(ActionMode actionmode, Menu menu);
    }

    public static abstract class Callback2
        implements Callback
    {

        public void onGetContentRect(ActionMode actionmode, View view, Rect rect)
        {
            if(view != null)
                rect.set(0, 0, view.getWidth(), view.getHeight());
            else
                rect.set(0, 0, 0, 0);
        }

        public Callback2()
        {
        }
    }


    public ActionMode()
    {
        mType = 0;
    }

    public abstract void finish();

    public abstract View getCustomView();

    public abstract Menu getMenu();

    public abstract MenuInflater getMenuInflater();

    public abstract CharSequence getSubtitle();

    public Object getTag()
    {
        return mTag;
    }

    public abstract CharSequence getTitle();

    public boolean getTitleOptionalHint()
    {
        return mTitleOptionalHint;
    }

    public int getType()
    {
        return mType;
    }

    public void hide(long l)
    {
    }

    public abstract void invalidate();

    public void invalidateContentRect()
    {
    }

    public boolean isTitleOptional()
    {
        return false;
    }

    public boolean isUiFocusable()
    {
        return true;
    }

    public void onWindowFocusChanged(boolean flag)
    {
    }

    public abstract void setCustomView(View view);

    public abstract void setSubtitle(int i);

    public abstract void setSubtitle(CharSequence charsequence);

    public void setTag(Object obj)
    {
        mTag = obj;
    }

    public abstract void setTitle(int i);

    public abstract void setTitle(CharSequence charsequence);

    public void setTitleOptionalHint(boolean flag)
    {
        mTitleOptionalHint = flag;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public static final int DEFAULT_HIDE_DURATION = -1;
    public static final int TYPE_FLOATING = 1;
    public static final int TYPE_PRIMARY = 0;
    private Object mTag;
    private boolean mTitleOptionalHint;
    private int mType;
}
