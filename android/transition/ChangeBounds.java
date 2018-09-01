// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.*;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues, PathMotion, TransitionUtils, 
//            TransitionListenerAdapter

public class ChangeBounds extends Transition
{
    private static class ViewBounds
    {

        private void setLeftTopRightBottom()
        {
            mView.setLeftTopRightBottom(mLeft, mTop, mRight, mBottom);
            mTopLeftCalls = 0;
            mBottomRightCalls = 0;
        }

        public void setBottomRight(PointF pointf)
        {
            mRight = Math.round(pointf.x);
            mBottom = Math.round(pointf.y);
            mBottomRightCalls = mBottomRightCalls + 1;
            if(mTopLeftCalls == mBottomRightCalls)
                setLeftTopRightBottom();
        }

        public void setTopLeft(PointF pointf)
        {
            mLeft = Math.round(pointf.x);
            mTop = Math.round(pointf.y);
            mTopLeftCalls = mTopLeftCalls + 1;
            if(mTopLeftCalls == mBottomRightCalls)
                setLeftTopRightBottom();
        }

        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private View mView;

        public ViewBounds(View view)
        {
            mView = view;
        }
    }


    public ChangeBounds()
    {
        tempLocation = new int[2];
        mResizeClip = false;
        mReparent = false;
    }

    public ChangeBounds(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        tempLocation = new int[2];
        mResizeClip = false;
        mReparent = false;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ChangeBounds);
        boolean flag = context.getBoolean(0, false);
        context.recycle();
        setResizeClip(flag);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        View view;
        view = transitionvalues.view;
        break MISSING_BLOCK_LABEL_5;
        if(view.isLaidOut() || view.getWidth() != 0 || view.getHeight() != 0)
        {
            transitionvalues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionvalues.values.put("android:changeBounds:parent", transitionvalues.view.getParent());
            if(mReparent)
            {
                transitionvalues.view.getLocationInWindow(tempLocation);
                transitionvalues.values.put("android:changeBounds:windowX", Integer.valueOf(tempLocation[0]));
                transitionvalues.values.put("android:changeBounds:windowY", Integer.valueOf(tempLocation[1]));
            }
            if(mResizeClip)
                transitionvalues.values.put("android:changeBounds:clip", view.getClipBounds());
        }
        return;
    }

    private boolean parentMatches(View view, View view1)
    {
        boolean flag = true;
        if(mReparent)
        {
            TransitionValues transitionvalues = getMatchedTransitionValues(view, true);
            if(transitionvalues == null)
            {
                if(view == view1)
                    flag = true;
                else
                    flag = false;
            } else
            if(view1 == transitionvalues.view)
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(final ViewGroup sceneRoot, final TransitionValues parent, final TransitionValues viewBounds)
    {
        Object obj;
        final Rect finalClip;
        final View view;
        if(parent == null || viewBounds == null)
            return null;
        obj = parent.values;
        finalClip = viewBounds.values;
        obj = (ViewGroup)((Map) (obj)).get("android:changeBounds:parent");
        finalClip = (ViewGroup)finalClip.get("android:changeBounds:parent");
        if(obj == null || finalClip == null)
            return null;
        view = viewBounds.view;
        if(!parentMatches(((View) (obj)), finalClip)) goto _L2; else goto _L1
_L1:
        int i;
        final int endLeft;
        int j;
        final int endTop;
        int k;
        final int endRight;
        int l;
        final int endBottom;
        int i1;
        int j1;
        int k1;
        int l1;
        boolean flag;
        int j2;
        sceneRoot = (Rect)parent.values.get("android:changeBounds:bounds");
        obj = (Rect)viewBounds.values.get("android:changeBounds:bounds");
        i = ((Rect) (sceneRoot)).left;
        endLeft = ((Rect) (obj)).left;
        j = ((Rect) (sceneRoot)).top;
        endTop = ((Rect) (obj)).top;
        k = ((Rect) (sceneRoot)).right;
        endRight = ((Rect) (obj)).right;
        l = ((Rect) (sceneRoot)).bottom;
        endBottom = ((Rect) (obj)).bottom;
        i1 = k - i;
        j1 = l - j;
        k1 = endRight - endLeft;
        l1 = endBottom - endTop;
        obj = (Rect)parent.values.get("android:changeBounds:clip");
        finalClip = (Rect)viewBounds.values.get("android:changeBounds:clip");
        flag = false;
        j2 = 0;
        if(i1 == 0 || j1 == 0) goto _L4; else goto _L3
_L3:
        int i3;
label0:
        {
            if(i != endLeft || j != endTop)
                j2 = 1;
            if(k == endRight)
            {
                i3 = j2;
                if(l == endBottom)
                    break label0;
            }
            i3 = j2 + 1;
        }
_L6:
        int k2;
label1:
        {
            if(obj == null || !(((Rect) (obj)).equals(finalClip) ^ true))
            {
                k2 = i3;
                if(obj != null)
                    break label1;
                k2 = i3;
                if(finalClip == null)
                    break label1;
            }
            k2 = i3 + 1;
        }
        if(k2 > 0)
        {
            if(!mResizeClip)
            {
                view.setLeftTopRightBottom(i, j, k, l);
                if(k2 == 2)
                {
                    if(i1 == k1 && j1 == l1)
                    {
                        sceneRoot = getPathMotion().getPath(i, j, endLeft, endTop);
                        sceneRoot = ObjectAnimator.ofObject(view, POSITION_PROPERTY, null, sceneRoot);
                    } else
                    {
                        viewBounds = new ViewBounds(view);
                        sceneRoot = getPathMotion().getPath(i, j, endLeft, endTop);
                        sceneRoot = ObjectAnimator.ofObject(viewBounds, TOP_LEFT_PROPERTY, null, sceneRoot);
                        parent = getPathMotion().getPath(k, l, endRight, endBottom);
                        obj = ObjectAnimator.ofObject(viewBounds, BOTTOM_RIGHT_PROPERTY, null, parent);
                        parent = new AnimatorSet();
                        parent.playTogether(new Animator[] {
                            sceneRoot, obj
                        });
                        sceneRoot = parent;
                        parent.addListener(new AnimatorListenerAdapter() {

                            private ViewBounds mViewBounds;
                            final ChangeBounds this$0;
                            final ViewBounds val$viewBounds;

            
            {
                this$0 = ChangeBounds.this;
                viewBounds = viewbounds;
                super();
                mViewBounds = viewBounds;
            }
                        }
);
                    }
                } else
                if(i != endLeft || j != endTop)
                {
                    sceneRoot = getPathMotion().getPath(i, j, endLeft, endTop);
                    sceneRoot = ObjectAnimator.ofObject(view, TOP_LEFT_ONLY_PROPERTY, null, sceneRoot);
                } else
                {
                    sceneRoot = getPathMotion().getPath(k, l, endRight, endBottom);
                    sceneRoot = ObjectAnimator.ofObject(view, BOTTOM_RIGHT_ONLY_PROPERTY, null, sceneRoot);
                }
            } else
            {
                view.setLeftTopRightBottom(i, j, i + Math.max(i1, k1), j + Math.max(j1, l1));
                sceneRoot = null;
                if(i != endLeft || j != endTop)
                {
                    sceneRoot = getPathMotion().getPath(i, j, endLeft, endTop);
                    sceneRoot = ObjectAnimator.ofObject(view, POSITION_PROPERTY, null, sceneRoot);
                }
                parent = ((TransitionValues) (obj));
                if(obj == null)
                    parent = new Rect(0, 0, i1, j1);
                int i2;
                int l2;
                int j3;
                final float transitionAlpha;
                if(finalClip == null)
                    viewBounds = new Rect(0, 0, k1, l1);
                else
                    viewBounds = finalClip;
                obj = null;
                if(!parent.equals(viewBounds))
                {
                    view.setClipBounds(parent);
                    obj = ObjectAnimator.ofObject(view, "clipBounds", sRectEvaluator, new Object[] {
                        parent, viewBounds
                    });
                    ((ObjectAnimator) (obj)).addListener(new AnimatorListenerAdapter() {

                        public void onAnimationCancel(Animator animator)
                        {
                            mIsCanceled = true;
                        }

                        public void onAnimationEnd(Animator animator)
                        {
                            if(!mIsCanceled)
                            {
                                view.setClipBounds(finalClip);
                                view.setLeftTopRightBottom(endLeft, endTop, endRight, endBottom);
                            }
                        }

                        private boolean mIsCanceled;
                        final ChangeBounds this$0;
                        final int val$endBottom;
                        final int val$endLeft;
                        final int val$endRight;
                        final int val$endTop;
                        final Rect val$finalClip;
                        final View val$view;

            
            {
                this$0 = ChangeBounds.this;
                view = view1;
                finalClip = rect;
                endLeft = i;
                endTop = j;
                endRight = k;
                endBottom = l;
                super();
            }
                    }
);
                }
                sceneRoot = TransitionUtils.mergeAnimators(sceneRoot, ((Animator) (obj)));
            }
            if(view.getParent() instanceof ViewGroup)
            {
                parent = (ViewGroup)view.getParent();
                parent.suppressLayout(true);
                addListener(new TransitionListenerAdapter() {

                    public void onTransitionCancel(Transition transition)
                    {
                        parent.suppressLayout(false);
                        mCanceled = true;
                    }

                    public void onTransitionEnd(Transition transition)
                    {
                        if(!mCanceled)
                            parent.suppressLayout(false);
                        transition.removeListener(this);
                    }

                    public void onTransitionPause(Transition transition)
                    {
                        parent.suppressLayout(false);
                    }

                    public void onTransitionResume(Transition transition)
                    {
                        parent.suppressLayout(true);
                    }

                    boolean mCanceled;
                    final ChangeBounds this$0;
                    final ViewGroup val$parent;

            
            {
                this$0 = ChangeBounds.this;
                parent = viewgroup;
                super();
                mCanceled = false;
            }
                }
);
            }
            return sceneRoot;
        }
        break MISSING_BLOCK_LABEL_1120;
_L4:
        i3 = ((flag) ? 1 : 0);
        if(k1 == 0) goto _L6; else goto _L5
_L5:
        i3 = ((flag) ? 1 : 0);
        if(l1 == 0) goto _L6; else goto _L3
_L2:
        sceneRoot.getLocationInWindow(tempLocation);
        l2 = ((Integer)parent.values.get("android:changeBounds:windowX")).intValue() - tempLocation[0];
        i2 = ((Integer)parent.values.get("android:changeBounds:windowY")).intValue() - tempLocation[1];
        j1 = ((Integer)viewBounds.values.get("android:changeBounds:windowX")).intValue() - tempLocation[0];
        endTop = ((Integer)viewBounds.values.get("android:changeBounds:windowY")).intValue() - tempLocation[1];
        if(l2 != j1 || i2 != endTop)
        {
            j3 = view.getWidth();
            endBottom = view.getHeight();
            parent = Bitmap.createBitmap(j3, endBottom, android.graphics.Bitmap.Config.ARGB_8888);
            view.draw(new Canvas(parent));
            parent = new BitmapDrawable(parent);
            parent.setBounds(l2, i2, l2 + j3, i2 + endBottom);
            transitionAlpha = view.getTransitionAlpha();
            view.setTransitionAlpha(0.0F);
            sceneRoot.getOverlay().add(parent);
            viewBounds = getPathMotion().getPath(l2, i2, j1, endTop);
            viewBounds = ObjectAnimator.ofPropertyValuesHolder(parent, new PropertyValuesHolder[] {
                PropertyValuesHolder.ofObject(DRAWABLE_ORIGIN_PROPERTY, null, viewBounds)
            });
            viewBounds.addListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    sceneRoot.getOverlay().remove(drawable);
                    view.setTransitionAlpha(transitionAlpha);
                }

                final ChangeBounds this$0;
                final BitmapDrawable val$drawable;
                final ViewGroup val$sceneRoot;
                final float val$transitionAlpha;
                final View val$view;

            
            {
                this$0 = ChangeBounds.this;
                sceneRoot = viewgroup;
                drawable = bitmapdrawable;
                view = view1;
                transitionAlpha = f;
                super();
            }
            }
);
            return viewBounds;
        }
        return null;
    }

    public boolean getResizeClip()
    {
        return mResizeClip;
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public void setReparent(boolean flag)
    {
        mReparent = flag;
    }

    public void setResizeClip(boolean flag)
    {
        mResizeClip = flag;
    }

    private static final Property BOTTOM_RIGHT_ONLY_PROPERTY = new Property(android/graphics/PointF, "bottomRight") {

        public PointF get(View view)
        {
            return null;
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void set(View view, PointF pointf)
        {
            view.setLeftTopRightBottom(view.getLeft(), view.getTop(), Math.round(pointf.x), Math.round(pointf.y));
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((View)obj, (PointF)obj1);
        }

    }
;
    private static final Property BOTTOM_RIGHT_PROPERTY = new Property(android/graphics/PointF, "bottomRight") {

        public PointF get(ViewBounds viewbounds)
        {
            return null;
        }

        public volatile Object get(Object obj)
        {
            return get((ViewBounds)obj);
        }

        public void set(ViewBounds viewbounds, PointF pointf)
        {
            viewbounds.setBottomRight(pointf);
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((ViewBounds)obj, (PointF)obj1);
        }

    }
;
    private static final Property DRAWABLE_ORIGIN_PROPERTY = new Property(android/graphics/PointF, "boundsOrigin") {

        public PointF get(Drawable drawable)
        {
            drawable.copyBounds(mBounds);
            return new PointF(mBounds.left, mBounds.top);
        }

        public volatile Object get(Object obj)
        {
            return get((Drawable)obj);
        }

        public void set(Drawable drawable, PointF pointf)
        {
            drawable.copyBounds(mBounds);
            mBounds.offsetTo(Math.round(pointf.x), Math.round(pointf.y));
            drawable.setBounds(mBounds);
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((Drawable)obj, (PointF)obj1);
        }

        private Rect mBounds;

            
            {
                mBounds = new Rect();
            }
    }
;
    private static final String LOG_TAG = "ChangeBounds";
    private static final Property POSITION_PROPERTY = new Property(android/graphics/PointF, "position") {

        public PointF get(View view)
        {
            return null;
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void set(View view, PointF pointf)
        {
            int i = Math.round(pointf.x);
            int j = Math.round(pointf.y);
            view.setLeftTopRightBottom(i, j, i + view.getWidth(), j + view.getHeight());
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((View)obj, (PointF)obj1);
        }

    }
;
    private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
    private static final String PROPNAME_CLIP = "android:changeBounds:clip";
    private static final String PROPNAME_PARENT = "android:changeBounds:parent";
    private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
    private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
    private static final Property TOP_LEFT_ONLY_PROPERTY = new Property(android/graphics/PointF, "topLeft") {

        public PointF get(View view)
        {
            return null;
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void set(View view, PointF pointf)
        {
            view.setLeftTopRightBottom(Math.round(pointf.x), Math.round(pointf.y), view.getRight(), view.getBottom());
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((View)obj, (PointF)obj1);
        }

    }
;
    private static final Property TOP_LEFT_PROPERTY = new Property(android/graphics/PointF, "topLeft") {

        public PointF get(ViewBounds viewbounds)
        {
            return null;
        }

        public volatile Object get(Object obj)
        {
            return get((ViewBounds)obj);
        }

        public void set(ViewBounds viewbounds, PointF pointf)
        {
            viewbounds.setTopLeft(pointf);
        }

        public volatile void set(Object obj, Object obj1)
        {
            set((ViewBounds)obj, (PointF)obj1);
        }

    }
;
    private static RectEvaluator sRectEvaluator = new RectEvaluator();
    private static final String sTransitionProperties[] = {
        "android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"
    };
    boolean mReparent;
    boolean mResizeClip;
    int tempLocation[];

}
