// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.view:
//            View, ViewGroup, RenderNode, ViewParent

public class ViewOverlay
{
    static class OverlayViewGroup extends ViewGroup
    {

        public void add(Drawable drawable)
        {
            if(drawable == null)
                throw new IllegalArgumentException("drawable must be non-null");
            if(mDrawables == null)
                mDrawables = new ArrayList();
            if(!mDrawables.contains(drawable))
            {
                mDrawables.add(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(this);
            }
        }

        public void add(View view)
        {
            if(view == null)
                throw new IllegalArgumentException("view must be non-null");
            if(view.getParent() instanceof ViewGroup)
            {
                ViewGroup viewgroup = (ViewGroup)view.getParent();
                if(viewgroup != mHostView && viewgroup.getParent() != null && viewgroup.mAttachInfo != null)
                {
                    int ai[] = new int[2];
                    int ai1[] = new int[2];
                    viewgroup.getLocationOnScreen(ai);
                    mHostView.getLocationOnScreen(ai1);
                    view.offsetLeftAndRight(ai[0] - ai1[0]);
                    view.offsetTopAndBottom(ai[1] - ai1[1]);
                }
                viewgroup.removeView(view);
                if(viewgroup.getLayoutTransition() != null)
                    viewgroup.getLayoutTransition().cancel(3);
                if(view.getParent() != null)
                    view.mParent = null;
            }
            super.addView(view);
        }

        public void clear()
        {
            removeAllViews();
            if(mDrawables != null)
            {
                for(Iterator iterator = mDrawables.iterator(); iterator.hasNext(); ((Drawable)iterator.next()).setCallback(null));
                mDrawables.clear();
            }
        }

        protected void dispatchDraw(Canvas canvas)
        {
            canvas.insertReorderBarrier();
            super.dispatchDraw(canvas);
            canvas.insertInorderBarrier();
            int i;
            int j;
            if(mDrawables == null)
                i = 0;
            else
                i = mDrawables.size();
            for(j = 0; j < i; j++)
                ((Drawable)mDrawables.get(j)).draw(canvas);

        }

        public void invalidate()
        {
            super.invalidate();
            if(mHostView != null)
                mHostView.invalidate();
        }

        public void invalidate(int i, int j, int k, int l)
        {
            super.invalidate(i, j, k, l);
            if(mHostView != null)
                mHostView.invalidate(i, j, k, l);
        }

        public void invalidate(Rect rect)
        {
            super.invalidate(rect);
            if(mHostView != null)
                mHostView.invalidate(rect);
        }

        public void invalidate(boolean flag)
        {
            super.invalidate(flag);
            if(mHostView != null)
                mHostView.invalidate(flag);
        }

        public ViewParent invalidateChildInParent(int ai[], Rect rect)
        {
            if(mHostView != null)
            {
                rect.offset(ai[0], ai[1]);
                if(mHostView instanceof ViewGroup)
                {
                    ai[0] = 0;
                    ai[1] = 0;
                    super.invalidateChildInParent(ai, rect);
                    return ((ViewGroup)mHostView).invalidateChildInParent(ai, rect);
                }
                invalidate(rect);
            }
            return null;
        }

        public void invalidateDrawable(Drawable drawable)
        {
            invalidate(drawable.getBounds());
        }

        protected void invalidateParentCaches()
        {
            super.invalidateParentCaches();
            if(mHostView != null)
                mHostView.invalidateParentCaches();
        }

        protected void invalidateParentIfNeeded()
        {
            super.invalidateParentIfNeeded();
            if(mHostView != null)
                mHostView.invalidateParentIfNeeded();
        }

        void invalidateViewProperty(boolean flag, boolean flag1)
        {
            super.invalidateViewProperty(flag, flag1);
            if(mHostView != null)
                mHostView.invalidateViewProperty(flag, flag1);
        }

        boolean isEmpty()
        {
            return getChildCount() == 0 && (mDrawables == null || mDrawables.size() == 0);
        }

        public void onDescendantInvalidated(View view, View view1)
        {
            if(mHostView != null)
                if(mHostView instanceof ViewGroup)
                {
                    ((ViewGroup)mHostView).onDescendantInvalidated(mHostView, view1);
                    super.onDescendantInvalidated(view, view1);
                } else
                {
                    invalidate();
                }
        }

        protected void onLayout(boolean flag, int i, int j, int k, int l)
        {
        }

        public void remove(Drawable drawable)
        {
            if(drawable == null)
                throw new IllegalArgumentException("drawable must be non-null");
            if(mDrawables != null)
            {
                mDrawables.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
            }
        }

        public void remove(View view)
        {
            if(view == null)
            {
                throw new IllegalArgumentException("view must be non-null");
            } else
            {
                super.removeView(view);
                return;
            }
        }

        protected boolean verifyDrawable(Drawable drawable)
        {
            boolean flag;
            if(!super.verifyDrawable(drawable))
            {
                if(mDrawables != null)
                    flag = mDrawables.contains(drawable);
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        ArrayList mDrawables;
        final View mHostView;

        OverlayViewGroup(Context context, View view)
        {
            super(context);
            mDrawables = null;
            mHostView = view;
            mAttachInfo = mHostView.mAttachInfo;
            mRight = view.getWidth();
            mBottom = view.getHeight();
            mRenderNode.setLeftTopRightBottom(0, 0, mRight, mBottom);
        }
    }


    ViewOverlay(Context context, View view)
    {
        mOverlayViewGroup = new OverlayViewGroup(context, view);
    }

    public void add(Drawable drawable)
    {
        mOverlayViewGroup.add(drawable);
    }

    public void clear()
    {
        mOverlayViewGroup.clear();
    }

    ViewGroup getOverlayView()
    {
        return mOverlayViewGroup;
    }

    boolean isEmpty()
    {
        return mOverlayViewGroup.isEmpty();
    }

    public void remove(Drawable drawable)
    {
        mOverlayViewGroup.remove(drawable);
    }

    OverlayViewGroup mOverlayViewGroup;
}
