// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.view.animation:
//            Animation, Transformation, Interpolator

public class AnimationSet extends Animation
{

    public AnimationSet(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mFlags = 0;
        mAnimations = new ArrayList();
        mTempTransformation = new Transformation();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AnimationSet);
        setFlag(16, attributeset.getBoolean(1, true));
        init();
        if(context.getApplicationInfo().targetSdkVersion >= 14)
        {
            if(attributeset.hasValue(0))
                mFlags = mFlags | 0x20;
            if(attributeset.hasValue(2))
                mFlags = mFlags | 2;
            if(attributeset.hasValue(3))
                mFlags = mFlags | 1;
            if(attributeset.hasValue(5))
                mFlags = mFlags | 4;
            if(attributeset.hasValue(4))
                mFlags = mFlags | 8;
        }
        attributeset.recycle();
    }

    public AnimationSet(boolean flag)
    {
        mFlags = 0;
        mAnimations = new ArrayList();
        mTempTransformation = new Transformation();
        setFlag(16, flag);
        init();
    }

    private void init()
    {
        mStartTime = 0L;
    }

    private void setFlag(int i, boolean flag)
    {
        if(flag)
            mFlags = mFlags | i;
        else
            mFlags = mFlags & i;
    }

    public void addAnimation(Animation animation)
    {
        mAnimations.add(animation);
        boolean flag;
        if((mFlags & 0x40) == 0)
            flag = true;
        else
            flag = false;
        if(flag && animation.willChangeTransformationMatrix())
            mFlags = mFlags | 0x40;
        if((mFlags & 0x80) == 0)
            flag = true;
        else
            flag = false;
        if(flag && animation.willChangeBounds())
            mFlags = mFlags | 0x80;
        if((mFlags & 0x20) == 32)
            mLastEnd = mStartOffset + mDuration;
        else
        if(mAnimations.size() == 1)
        {
            mDuration = animation.getStartOffset() + animation.getDuration();
            mLastEnd = mStartOffset + mDuration;
        } else
        {
            mLastEnd = Math.max(mLastEnd, mStartOffset + animation.getStartOffset() + animation.getDuration());
            mDuration = mLastEnd - mStartOffset;
        }
        mDirty = true;
    }

    protected volatile Animation clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    protected AnimationSet clone()
        throws CloneNotSupportedException
    {
        AnimationSet animationset = (AnimationSet)super.clone();
        animationset.mTempTransformation = new Transformation();
        animationset.mAnimations = new ArrayList();
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for(int j = 0; j < i; j++)
            animationset.mAnimations.add(((Animation)arraylist.get(j)).clone());

        return animationset;
    }

    protected volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public long computeDurationHint()
    {
        long l = 0L;
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for(i--; i >= 0;)
        {
            long l1 = ((Animation)arraylist.get(i)).computeDurationHint();
            long l2 = l;
            if(l1 > l)
                l2 = l1;
            i--;
            l = l2;
        }

        return l;
    }

    public List getAnimations()
    {
        return mAnimations;
    }

    public long getDuration()
    {
        ArrayList arraylist;
        int i;
        long l;
        long l1;
        arraylist = mAnimations;
        i = arraylist.size();
        l = 0L;
        boolean flag;
        if((mFlags & 0x20) == 32)
            flag = true;
        else
            flag = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        l1 = mDuration;
_L4:
        return l1;
_L2:
        int j = 0;
        do
        {
            l1 = l;
            if(j >= i)
                continue;
            l = Math.max(l, ((Animation)arraylist.get(j)).getDuration());
            j++;
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public long getStartTime()
    {
        long l = 0x7fffffffffffffffL;
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for(int j = 0; j < i; j++)
            l = Math.min(l, ((Animation)arraylist.get(j)).getStartTime());

        return l;
    }

    public boolean getTransformation(long l, Transformation transformation)
    {
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        Transformation transformation1 = mTempTransformation;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = true;
        transformation.clear();
        i--;
        while(i >= 0) 
        {
            Animation animation = (Animation)arraylist.get(i);
            transformation1.clear();
            if(animation.getTransformation(l, transformation1, getScaleFactor()))
                flag = true;
            transformation.compose(transformation1);
            if(!flag1)
                flag1 = animation.hasStarted();
            else
                flag1 = true;
            if(!animation.hasEnded())
                flag2 = false;
            i--;
        }
        if(flag1 && mStarted ^ true)
        {
            if(mListener != null)
                mListener.onAnimationStart(this);
            mStarted = true;
        }
        if(flag2 != mEnded)
        {
            if(mListener != null)
                mListener.onAnimationEnd(this);
            mEnded = flag2;
        }
        return flag;
    }

    public boolean hasAlpha()
    {
        if(!mDirty) goto _L2; else goto _L1
_L1:
        int i;
        ArrayList arraylist;
        int j;
        mHasAlpha = false;
        mDirty = false;
        i = mAnimations.size();
        arraylist = mAnimations;
        j = 0;
_L7:
        if(j >= i) goto _L2; else goto _L3
_L3:
        if(!((Animation)arraylist.get(j)).hasAlpha()) goto _L5; else goto _L4
_L4:
        mHasAlpha = true;
_L2:
        return mHasAlpha;
_L5:
        j++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void initialize(int i, int j, int k, int l)
    {
        Object obj;
        long al[];
        super.initialize(i, j, k, l);
        boolean flag;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        ArrayList arraylist;
        int i1;
        long l1;
        boolean flag6;
        boolean flag7;
        int j1;
        Interpolator interpolator;
        long l2;
        int k1;
        long l3;
        if((mFlags & 0x20) == 32)
            flag = true;
        else
            flag = false;
        if((mFlags & 1) == 1)
            flag1 = true;
        else
            flag1 = false;
        if((mFlags & 2) == 2)
            flag2 = true;
        else
            flag2 = false;
        if((mFlags & 4) == 4)
            flag3 = true;
        else
            flag3 = false;
        if((mFlags & 0x10) == 16)
            flag4 = true;
        else
            flag4 = false;
        if((mFlags & 8) == 8)
            flag5 = true;
        else
            flag5 = false;
        if(flag4)
            ensureInterpolator();
        arraylist = mAnimations;
        i1 = arraylist.size();
        l1 = mDuration;
        flag6 = mFillAfter;
        flag7 = mFillBefore;
        j1 = mRepeatMode;
        interpolator = mInterpolator;
        l2 = mStartOffset;
        obj = mStoredOffsets;
        if(!flag5) goto _L2; else goto _L1
_L1:
label0:
        {
            if(obj != null)
            {
                al = ((long []) (obj));
                if(obj.length == i1)
                    break label0;
            }
            al = new long[i1];
            mStoredOffsets = al;
        }
_L4:
        for(k1 = 0; k1 < i1; k1++)
        {
            obj = (Animation)arraylist.get(k1);
            if(flag)
                ((Animation) (obj)).setDuration(l1);
            if(flag1)
                ((Animation) (obj)).setFillAfter(flag6);
            if(flag2)
                ((Animation) (obj)).setFillBefore(flag7);
            if(flag3)
                ((Animation) (obj)).setRepeatMode(j1);
            if(flag4)
                ((Animation) (obj)).setInterpolator(interpolator);
            if(flag5)
            {
                l3 = ((Animation) (obj)).getStartOffset();
                ((Animation) (obj)).setStartOffset(l3 + l2);
                al[k1] = l3;
            }
            ((Animation) (obj)).initialize(i, j, k, l);
        }

        break; /* Loop/switch isn't completed */
_L2:
        al = ((long []) (obj));
        if(obj != null)
        {
            mStoredOffsets = null;
            al = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void initializeInvalidateRegion(int i, int j, int k, int l)
    {
        RectF rectf = mPreviousRegion;
        rectf.set(i, j, k, l);
        rectf.inset(-1F, -1F);
        if(mFillBefore)
        {
            i = mAnimations.size();
            ArrayList arraylist = mAnimations;
            Transformation transformation = mTempTransformation;
            Transformation transformation1 = mPreviousTransformation;
            i--;
            while(i >= 0) 
            {
                Animation animation = (Animation)arraylist.get(i);
                if(!animation.isFillEnabled() || animation.getFillBefore() || animation.getStartOffset() == 0L)
                {
                    transformation.clear();
                    Interpolator interpolator = animation.mInterpolator;
                    float f;
                    if(interpolator != null)
                        f = interpolator.getInterpolation(0.0F);
                    else
                        f = 0.0F;
                    animation.applyTransformation(f, transformation);
                    transformation1.compose(transformation);
                }
                i--;
            }
        }
    }

    public void reset()
    {
        super.reset();
        restoreChildrenStartOffset();
    }

    void restoreChildrenStartOffset()
    {
        long al[] = mStoredOffsets;
        if(al == null)
            return;
        ArrayList arraylist = mAnimations;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            ((Animation)arraylist.get(j)).setStartOffset(al[j]);

    }

    public void restrictDuration(long l)
    {
        super.restrictDuration(l);
        ArrayList arraylist = mAnimations;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            ((Animation)arraylist.get(j)).restrictDuration(l);

    }

    public void scaleCurrentDuration(float f)
    {
        ArrayList arraylist = mAnimations;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            ((Animation)arraylist.get(j)).scaleCurrentDuration(f);

    }

    public void setDuration(long l)
    {
        mFlags = mFlags | 0x20;
        super.setDuration(l);
        mLastEnd = mStartOffset + mDuration;
    }

    public void setFillAfter(boolean flag)
    {
        mFlags = mFlags | 1;
        super.setFillAfter(flag);
    }

    public void setFillBefore(boolean flag)
    {
        mFlags = mFlags | 2;
        super.setFillBefore(flag);
    }

    public void setRepeatMode(int i)
    {
        mFlags = mFlags | 4;
        super.setRepeatMode(i);
    }

    public void setStartOffset(long l)
    {
        mFlags = mFlags | 8;
        super.setStartOffset(l);
    }

    public void setStartTime(long l)
    {
        super.setStartTime(l);
        int i = mAnimations.size();
        ArrayList arraylist = mAnimations;
        for(int j = 0; j < i; j++)
            ((Animation)arraylist.get(j)).setStartTime(l);

    }

    public boolean willChangeBounds()
    {
        boolean flag;
        if((mFlags & 0x80) == 128)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean willChangeTransformationMatrix()
    {
        boolean flag;
        if((mFlags & 0x40) == 64)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private static final int PROPERTY_CHANGE_BOUNDS_MASK = 128;
    private static final int PROPERTY_DURATION_MASK = 32;
    private static final int PROPERTY_FILL_AFTER_MASK = 1;
    private static final int PROPERTY_FILL_BEFORE_MASK = 2;
    private static final int PROPERTY_MORPH_MATRIX_MASK = 64;
    private static final int PROPERTY_REPEAT_MODE_MASK = 4;
    private static final int PROPERTY_SHARE_INTERPOLATOR_MASK = 16;
    private static final int PROPERTY_START_OFFSET_MASK = 8;
    private ArrayList mAnimations;
    private boolean mDirty;
    private int mFlags;
    private boolean mHasAlpha;
    private long mLastEnd;
    private long mStoredOffsets[];
    private Transformation mTempTransformation;
}
