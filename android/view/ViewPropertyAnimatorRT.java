// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.android.internal.view.animation.FallbackLUTInterpolator;
import java.util.ArrayList;

// Referenced classes of package android.view:
//            RenderNodeAnimator, ViewPropertyAnimator, View

class ViewPropertyAnimatorRT
{

    ViewPropertyAnimatorRT(View view)
    {
        mAnimators = new RenderNodeAnimator[12];
        mView = view;
    }

    private boolean canHandleAnimator(ViewPropertyAnimator viewpropertyanimator)
    {
        if(viewpropertyanimator.getUpdateListener() != null)
            return false;
        if(viewpropertyanimator.getListener() != null)
            return false;
        if(!mView.isHardwareAccelerated())
            return false;
        return !viewpropertyanimator.hasActions();
    }

    private void cancelAnimators(ArrayList arraylist)
    {
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
        {
            int k = RenderNodeAnimator.mapViewPropertyToRenderProperty(((ViewPropertyAnimator.NameValuesHolder)arraylist.get(j)).mNameConstant);
            if(mAnimators[k] != null)
            {
                mAnimators[k].cancel();
                mAnimators[k] = null;
            }
        }

    }

    private void doStartAnimation(ViewPropertyAnimator viewpropertyanimator)
    {
        int i = viewpropertyanimator.mPendingAnimations.size();
        long l = viewpropertyanimator.getStartDelay();
        long l1 = viewpropertyanimator.getDuration();
        Object obj = viewpropertyanimator.getInterpolator();
        Object obj1 = obj;
        if(obj == null)
            obj1 = sLinearInterpolator;
        obj = obj1;
        if(!RenderNodeAnimator.isNativeInterpolator(((android.animation.TimeInterpolator) (obj1))))
            obj = new FallbackLUTInterpolator(((android.animation.TimeInterpolator) (obj1)), l1);
        for(int j = 0; j < i; j++)
        {
            Object obj2 = (ViewPropertyAnimator.NameValuesHolder)viewpropertyanimator.mPendingAnimations.get(j);
            int k = RenderNodeAnimator.mapViewPropertyToRenderProperty(((ViewPropertyAnimator.NameValuesHolder) (obj2)).mNameConstant);
            obj2 = new RenderNodeAnimator(k, ((ViewPropertyAnimator.NameValuesHolder) (obj2)).mFromValue + ((ViewPropertyAnimator.NameValuesHolder) (obj2)).mDeltaValue);
            ((RenderNodeAnimator) (obj2)).setStartDelay(l);
            ((RenderNodeAnimator) (obj2)).setDuration(l1);
            ((RenderNodeAnimator) (obj2)).setInterpolator(((android.animation.TimeInterpolator) (obj)));
            ((RenderNodeAnimator) (obj2)).setTarget(mView);
            ((RenderNodeAnimator) (obj2)).start();
            mAnimators[k] = ((RenderNodeAnimator) (obj2));
        }

        viewpropertyanimator.mPendingAnimations.clear();
    }

    public void cancelAll()
    {
        for(int i = 0; i < mAnimators.length; i++)
            if(mAnimators[i] != null)
            {
                mAnimators[i].cancel();
                mAnimators[i] = null;
            }

    }

    public boolean startAnimation(ViewPropertyAnimator viewpropertyanimator)
    {
        cancelAnimators(viewpropertyanimator.mPendingAnimations);
        if(!canHandleAnimator(viewpropertyanimator))
        {
            return false;
        } else
        {
            doStartAnimation(viewpropertyanimator);
            return true;
        }
    }

    private static final Interpolator sLinearInterpolator = new LinearInterpolator();
    private RenderNodeAnimator mAnimators[];
    private final View mView;

}
