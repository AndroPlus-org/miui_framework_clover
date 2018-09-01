// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.animation.*;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import java.util.Map;

// Referenced classes of package android.transition:
//            Transition, TransitionValues

public class Crossfade extends Transition
{

    static int _2D_get0(Crossfade crossfade)
    {
        return crossfade.mFadeBehavior;
    }

    public Crossfade()
    {
        mFadeBehavior = 1;
        mResizeBehavior = 1;
    }

    private void captureValues(TransitionValues transitionvalues)
    {
        View view = transitionvalues.view;
        Rect rect = new Rect(0, 0, view.getWidth(), view.getHeight());
        if(mFadeBehavior != 1)
            rect.offset(view.getLeft(), view.getTop());
        transitionvalues.values.put("android:crossfade:bounds", rect);
        Object obj = Bitmap.createBitmap(view.getWidth(), view.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        if(view instanceof TextureView)
            obj = ((TextureView)view).getBitmap();
        else
            view.draw(new Canvas(((Bitmap) (obj))));
        transitionvalues.values.put("android:crossfade:bitmap", obj);
        obj = new BitmapDrawable(((Bitmap) (obj)));
        ((BitmapDrawable) (obj)).setBounds(rect);
        transitionvalues.values.put("android:crossfade:drawable", obj);
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
        final Object view;
        final BitmapDrawable startDrawable;
        if(transitionvalues == null || transitionvalues1 == null)
            return null;
        final boolean useParentOverlay;
        final BitmapDrawable endDrawable;
        Rect rect;
        if(mFadeBehavior != 1)
            useParentOverlay = true;
        else
            useParentOverlay = false;
        view = transitionvalues1.view;
        startDrawable = transitionvalues.values;
        endDrawable = transitionvalues1.values;
        rect = (Rect)startDrawable.get("android:crossfade:bounds");
        transitionvalues1 = (Rect)endDrawable.get("android:crossfade:bounds");
        viewgroup = (Bitmap)startDrawable.get("android:crossfade:bitmap");
        transitionvalues = (Bitmap)endDrawable.get("android:crossfade:bitmap");
        startDrawable = (BitmapDrawable)startDrawable.get("android:crossfade:drawable");
        endDrawable = (BitmapDrawable)endDrawable.get("android:crossfade:drawable");
        if(startDrawable == null || endDrawable == null || !(viewgroup.sameAs(transitionvalues) ^ true)) goto _L2; else goto _L1
_L1:
        if(useParentOverlay)
            viewgroup = ((ViewGroup)((View) (view)).getParent()).getOverlay();
        else
            viewgroup = ((View) (view)).getOverlay();
        if(mFadeBehavior == 1)
            viewgroup.add(endDrawable);
        viewgroup.add(startDrawable);
        if(mFadeBehavior == 2)
            transitionvalues = ObjectAnimator.ofInt(startDrawable, "alpha", new int[] {
                255, 0, 0
            });
        else
            transitionvalues = ObjectAnimator.ofInt(startDrawable, "alpha", new int[] {
                0
            });
        transitionvalues.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueanimator)
            {
                view.invalidate(startDrawable.getBounds());
            }

            final Crossfade this$0;
            final BitmapDrawable val$startDrawable;
            final View val$view;

            
            {
                this$0 = Crossfade.this;
                view = view1;
                startDrawable = bitmapdrawable;
                super();
            }
        }
);
        viewgroup = null;
        if(mFadeBehavior != 2) goto _L4; else goto _L3
_L3:
        viewgroup = ObjectAnimator.ofFloat(view, View.ALPHA, new float[] {
            0.0F, 0.0F, 1.0F
        });
_L5:
        transitionvalues.addListener(new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                if(useParentOverlay)
                    animator = ((ViewGroup)view.getParent()).getOverlay();
                else
                    animator = view.getOverlay();
                animator.remove(startDrawable);
                if(Crossfade._2D_get0(Crossfade.this) == 1)
                    animator.remove(endDrawable);
            }

            final Crossfade this$0;
            final BitmapDrawable val$endDrawable;
            final BitmapDrawable val$startDrawable;
            final boolean val$useParentOverlay;
            final View val$view;

            
            {
                this$0 = Crossfade.this;
                useParentOverlay = flag;
                view = view1;
                startDrawable = bitmapdrawable;
                endDrawable = bitmapdrawable1;
                super();
            }
        }
);
        view = new AnimatorSet();
        ((AnimatorSet) (view)).playTogether(new Animator[] {
            transitionvalues
        });
        if(viewgroup != null)
            ((AnimatorSet) (view)).playTogether(new Animator[] {
                viewgroup
            });
        if(mResizeBehavior == 1 && rect.equals(transitionvalues1) ^ true)
        {
            ((AnimatorSet) (view)).playTogether(new Animator[] {
                ObjectAnimator.ofObject(startDrawable, "bounds", sRectEvaluator, new Object[] {
                    rect, transitionvalues1
                })
            });
            if(mResizeBehavior == 1)
                ((AnimatorSet) (view)).playTogether(new Animator[] {
                    ObjectAnimator.ofObject(endDrawable, "bounds", sRectEvaluator, new Object[] {
                        rect, transitionvalues1
                    })
                });
        }
        return ((Animator) (view));
_L4:
        if(mFadeBehavior == 0)
            viewgroup = ObjectAnimator.ofFloat(view, View.ALPHA, new float[] {
                0.0F, 1.0F
            });
        if(true) goto _L5; else goto _L2
_L2:
        return null;
    }

    public int getFadeBehavior()
    {
        return mFadeBehavior;
    }

    public int getResizeBehavior()
    {
        return mResizeBehavior;
    }

    public Crossfade setFadeBehavior(int i)
    {
        if(i >= 0 && i <= 2)
            mFadeBehavior = i;
        return this;
    }

    public Crossfade setResizeBehavior(int i)
    {
        if(i >= 0 && i <= 1)
            mResizeBehavior = i;
        return this;
    }

    public static final int FADE_BEHAVIOR_CROSSFADE = 0;
    public static final int FADE_BEHAVIOR_OUT_IN = 2;
    public static final int FADE_BEHAVIOR_REVEAL = 1;
    private static final String LOG_TAG = "Crossfade";
    private static final String PROPNAME_BITMAP = "android:crossfade:bitmap";
    private static final String PROPNAME_BOUNDS = "android:crossfade:bounds";
    private static final String PROPNAME_DRAWABLE = "android:crossfade:drawable";
    public static final int RESIZE_BEHAVIOR_NONE = 0;
    public static final int RESIZE_BEHAVIOR_SCALE = 1;
    private static RectEvaluator sRectEvaluator = new RectEvaluator();
    private int mFadeBehavior;
    private int mResizeBehavior;

}
