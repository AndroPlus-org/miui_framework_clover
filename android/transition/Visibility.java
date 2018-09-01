// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.*;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues, TransitionUtils, TransitionListenerAdapter

public abstract class Visibility extends Transition
{
    private static class DisappearListener extends TransitionListenerAdapter
        implements android.animation.Animator.AnimatorListener, android.animation.Animator.AnimatorPauseListener
    {

        private void hideViewWhenNotCanceled()
        {
            if(!mCanceled)
            {
                mView.setTransitionVisibility(mFinalVisibility);
                if(mParent != null)
                    mParent.invalidate();
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean flag)
        {
            if(mSuppressLayout && mLayoutSuppressed != flag && mParent != null)
            {
                mLayoutSuppressed = flag;
                mParent.suppressLayout(flag);
            }
        }

        public void onAnimationCancel(Animator animator)
        {
            mCanceled = true;
        }

        public void onAnimationEnd(Animator animator)
        {
            hideViewWhenNotCanceled();
        }

        public void onAnimationPause(Animator animator)
        {
            if(!mCanceled)
                mView.setTransitionVisibility(mFinalVisibility);
        }

        public void onAnimationRepeat(Animator animator)
        {
        }

        public void onAnimationResume(Animator animator)
        {
            if(!mCanceled)
                mView.setTransitionVisibility(0);
        }

        public void onAnimationStart(Animator animator)
        {
        }

        public void onTransitionEnd(Transition transition)
        {
            hideViewWhenNotCanceled();
            transition.removeListener(this);
        }

        public void onTransitionPause(Transition transition)
        {
            suppressLayout(false);
        }

        public void onTransitionResume(Transition transition)
        {
            suppressLayout(true);
        }

        boolean mCanceled;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        public DisappearListener(View view, int i, boolean flag)
        {
            mCanceled = false;
            mView = view;
            mFinalVisibility = i;
            mParent = (ViewGroup)view.getParent();
            mSuppressLayout = flag;
            suppressLayout(true);
        }
    }

    private static class VisibilityInfo
    {

        ViewGroup endParent;
        int endVisibility;
        boolean fadeIn;
        ViewGroup startParent;
        int startVisibility;
        boolean visibilityChange;

        private VisibilityInfo()
        {
        }

        VisibilityInfo(VisibilityInfo visibilityinfo)
        {
            this();
        }
    }


    public Visibility()
    {
        mMode = 3;
        mSuppressLayout = true;
    }

    public Visibility(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mMode = 3;
        mSuppressLayout = true;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.VisibilityTransition);
        int i = context.getInt(0, 0);
        context.recycle();
        if(i != 0)
            setMode(i);
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        int i = transitionvalues.view.getVisibility();
        transitionvalues.values.put("android:visibility:visibility", Integer.valueOf(i));
        transitionvalues.values.put("android:visibility:parent", transitionvalues.view.getParent());
        int ai[] = new int[2];
        transitionvalues.view.getLocationOnScreen(ai);
        transitionvalues.values.put("android:visibility:screenLocation", ai);
    }

    private static VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo;
        visibilityinfo = new VisibilityInfo(null);
        visibilityinfo.visibilityChange = false;
        visibilityinfo.fadeIn = false;
        if(transitionvalues != null && transitionvalues.values.containsKey("android:visibility:visibility"))
        {
            visibilityinfo.startVisibility = ((Integer)transitionvalues.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.startParent = (ViewGroup)transitionvalues.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.startVisibility = -1;
            visibilityinfo.startParent = null;
        }
        if(transitionvalues1 != null && transitionvalues1.values.containsKey("android:visibility:visibility"))
        {
            visibilityinfo.endVisibility = ((Integer)transitionvalues1.values.get("android:visibility:visibility")).intValue();
            visibilityinfo.endParent = (ViewGroup)transitionvalues1.values.get("android:visibility:parent");
        } else
        {
            visibilityinfo.endVisibility = -1;
            visibilityinfo.endParent = null;
        }
        if(transitionvalues == null || transitionvalues1 == null) goto _L2; else goto _L1
_L1:
        if(visibilityinfo.startVisibility == visibilityinfo.endVisibility && visibilityinfo.startParent == visibilityinfo.endParent)
            return visibilityinfo;
        if(visibilityinfo.startVisibility == visibilityinfo.endVisibility) goto _L4; else goto _L3
_L3:
        if(visibilityinfo.startVisibility != 0) goto _L6; else goto _L5
_L5:
        visibilityinfo.fadeIn = false;
        visibilityinfo.visibilityChange = true;
_L8:
        return visibilityinfo;
_L6:
        if(visibilityinfo.endVisibility == 0)
        {
            visibilityinfo.fadeIn = true;
            visibilityinfo.visibilityChange = true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(visibilityinfo.startParent != visibilityinfo.endParent)
            if(visibilityinfo.endParent == null)
            {
                visibilityinfo.fadeIn = false;
                visibilityinfo.visibilityChange = true;
            } else
            if(visibilityinfo.startParent == null)
            {
                visibilityinfo.fadeIn = true;
                visibilityinfo.visibilityChange = true;
            }
        continue; /* Loop/switch isn't completed */
_L2:
        if(transitionvalues == null && visibilityinfo.endVisibility == 0)
        {
            visibilityinfo.fadeIn = true;
            visibilityinfo.visibilityChange = true;
        } else
        if(transitionvalues1 == null && visibilityinfo.startVisibility == 0)
        {
            visibilityinfo.fadeIn = false;
            visibilityinfo.visibilityChange = true;
        }
        if(true) goto _L8; else goto _L7
_L7:
    }

    public void captureEndValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public void captureStartValues(TransitionValues transitionvalues)
    {
        captureValues(transitionvalues);
    }

    public Animator createAnimator(ViewGroup viewgroup, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        VisibilityInfo visibilityinfo = getVisibilityChangeInfo(transitionvalues, transitionvalues1);
        if(visibilityinfo.visibilityChange && (visibilityinfo.startParent != null || visibilityinfo.endParent != null))
        {
            if(visibilityinfo.fadeIn)
                return onAppear(viewgroup, transitionvalues, visibilityinfo.startVisibility, transitionvalues1, visibilityinfo.endVisibility);
            else
                return onDisappear(viewgroup, transitionvalues, visibilityinfo.startVisibility, transitionvalues1, visibilityinfo.endVisibility);
        } else
        {
            return null;
        }
    }

    public int getMode()
    {
        return mMode;
    }

    public String[] getTransitionProperties()
    {
        return sTransitionProperties;
    }

    public boolean isTransitionRequired(TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        boolean flag = true;
        if(transitionvalues == null && transitionvalues1 == null)
            return false;
        if(transitionvalues != null && transitionvalues1 != null && transitionvalues1.values.containsKey("android:visibility:visibility") != transitionvalues.values.containsKey("android:visibility:visibility"))
            return false;
        transitionvalues = getVisibilityChangeInfo(transitionvalues, transitionvalues1);
        boolean flag1;
        if(((VisibilityInfo) (transitionvalues)).visibilityChange)
        {
            flag1 = flag;
            if(((VisibilityInfo) (transitionvalues)).startVisibility != 0)
                if(((VisibilityInfo) (transitionvalues)).endVisibility == 0)
                    flag1 = flag;
                else
                    flag1 = false;
        } else
        {
            flag1 = false;
        }
        return flag1;
    }

    public boolean isVisible(TransitionValues transitionvalues)
    {
        if(transitionvalues == null)
            return false;
        int i = ((Integer)transitionvalues.values.get("android:visibility:visibility")).intValue();
        transitionvalues = (View)transitionvalues.values.get("android:visibility:parent");
        boolean flag;
        if(i == 0 && transitionvalues != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public Animator onAppear(ViewGroup viewgroup, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        if((mMode & 1) != 1 || transitionvalues1 == null)
            return null;
        if(transitionvalues == null)
        {
            View view = (View)transitionvalues1.view.getParent();
            if(getVisibilityChangeInfo(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).visibilityChange)
                return null;
        }
        return onAppear(viewgroup, transitionvalues1.view, transitionvalues, transitionvalues1);
    }

    public Animator onAppear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return null;
    }

    public Animator onDisappear(final ViewGroup finalSceneRoot, TransitionValues transitionvalues, int i, TransitionValues transitionvalues1, int j)
    {
        if((mMode & 2) != 2)
            return null;
        View view;
        final Object finalOverlayView;
        Object obj;
        Object obj1;
        Object obj2;
        if(transitionvalues != null)
            view = transitionvalues.view;
        else
            view = null;
        if(transitionvalues1 != null)
            finalOverlayView = transitionvalues1.view;
        else
            finalOverlayView = null;
        obj = null;
        obj1 = null;
        if(finalOverlayView == null || ((View) (finalOverlayView)).getParent() == null)
        {
            if(finalOverlayView != null)
            {
                obj2 = obj1;
            } else
            {
                finalOverlayView = obj;
                obj2 = obj1;
                if(view != null)
                    if(view.getParent() == null)
                    {
                        finalOverlayView = view;
                        obj2 = obj1;
                    } else
                    {
                        finalOverlayView = obj;
                        obj2 = obj1;
                        if(view.getParent() instanceof View)
                        {
                            View view1 = (View)view.getParent();
                            if(!getVisibilityChangeInfo(getTransitionValues(view1, true), getMatchedTransitionValues(view1, true)).visibilityChange)
                            {
                                finalOverlayView = TransitionUtils.copyViewImage(finalSceneRoot, view, view1);
                                obj2 = obj1;
                            } else
                            {
                                finalOverlayView = obj;
                                obj2 = obj1;
                                if(view1.getParent() == null)
                                {
                                    i = view1.getId();
                                    finalOverlayView = obj;
                                    obj2 = obj1;
                                    if(i != -1)
                                    {
                                        finalOverlayView = obj;
                                        obj2 = obj1;
                                        if(finalSceneRoot.findViewById(i) != null)
                                        {
                                            finalOverlayView = obj;
                                            obj2 = obj1;
                                            if(mCanRemoveViews)
                                            {
                                                finalOverlayView = view;
                                                obj2 = obj1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
            }
        } else
        if(j == 4)
        {
            obj2 = finalOverlayView;
            finalOverlayView = obj;
        } else
        if(view == finalOverlayView)
        {
            obj2 = finalOverlayView;
            finalOverlayView = obj;
        } else
        {
            finalOverlayView = view;
            obj2 = obj1;
        }
        if(finalOverlayView != null)
        {
            view = (int[])transitionvalues.values.get("android:visibility:screenLocation");
            j = view[0];
            i = view[1];
            view = new int[2];
            finalSceneRoot.getLocationOnScreen(view);
            ((View) (finalOverlayView)).offsetLeftAndRight(j - view[0] - ((View) (finalOverlayView)).getLeft());
            ((View) (finalOverlayView)).offsetTopAndBottom(i - view[1] - ((View) (finalOverlayView)).getTop());
            finalSceneRoot.getOverlay().add(((View) (finalOverlayView)));
            transitionvalues = onDisappear(finalSceneRoot, ((View) (finalOverlayView)), transitionvalues, transitionvalues1);
            if(transitionvalues == null)
                finalSceneRoot.getOverlay().remove(((View) (finalOverlayView)));
            else
                addListener(new TransitionListenerAdapter() {

                    public void onTransitionEnd(Transition transition)
                    {
                        finalSceneRoot.getOverlay().remove(finalOverlayView);
                        transition.removeListener(this);
                    }

                    final Visibility this$0;
                    final View val$finalOverlayView;
                    final ViewGroup val$finalSceneRoot;

            
            {
                this$0 = Visibility.this;
                finalSceneRoot = viewgroup;
                finalOverlayView = view;
                super();
            }
                }
);
            return transitionvalues;
        }
        if(obj2 != null)
        {
            i = ((View) (obj2)).getVisibility();
            ((View) (obj2)).setTransitionVisibility(0);
            finalSceneRoot = onDisappear(finalSceneRoot, ((View) (obj2)), transitionvalues, transitionvalues1);
            if(finalSceneRoot != null)
            {
                transitionvalues = new DisappearListener(((View) (obj2)), j, mSuppressLayout);
                finalSceneRoot.addListener(transitionvalues);
                finalSceneRoot.addPauseListener(transitionvalues);
                addListener(transitionvalues);
            } else
            {
                ((View) (obj2)).setTransitionVisibility(i);
            }
            return finalSceneRoot;
        } else
        {
            return null;
        }
    }

    public Animator onDisappear(ViewGroup viewgroup, View view, TransitionValues transitionvalues, TransitionValues transitionvalues1)
    {
        return null;
    }

    public void setMode(int i)
    {
        if((i & -4) != 0)
        {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        } else
        {
            mMode = i;
            return;
        }
    }

    public void setSuppressLayout(boolean flag)
    {
        mSuppressLayout = flag;
    }

    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String sTransitionProperties[] = {
        "android:visibility:visibility", "android:visibility:parent"
    };
    private int mMode;
    private boolean mSuppressLayout;

}
