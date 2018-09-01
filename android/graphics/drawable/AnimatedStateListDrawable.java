// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.*;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            StateListDrawable, Drawable, AnimationDrawable, AnimatedVectorDrawable, 
//            Animatable

public class AnimatedStateListDrawable extends StateListDrawable
{
    private static class AnimatableTransition extends Transition
    {

        public void start()
        {
            mA.start();
        }

        public void stop()
        {
            mA.stop();
        }

        private final Animatable mA;

        public AnimatableTransition(Animatable animatable)
        {
            super(null);
            mA = animatable;
        }
    }

    static class AnimatedStateListState extends StateListDrawable.StateListState
    {

        private static long generateTransitionKey(int i, int j)
        {
            return (long)i << 32 | (long)j;
        }

        int addStateSet(int ai[], Drawable drawable, int i)
        {
            int j = super.addStateSet(ai, drawable);
            mStateIds.put(j, i);
            return j;
        }

        int addTransition(int i, int j, Drawable drawable, boolean flag)
        {
            int k = super.addChild(drawable);
            long l = generateTransitionKey(i, j);
            long l2 = 0L;
            if(flag)
                l2 = 0x200000000L;
            mTransitions.append(l, (long)k | l2);
            if(flag)
            {
                long l1 = generateTransitionKey(j, i);
                mTransitions.append(l1, (long)k | 0x100000000L | l2);
            }
            return k;
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mAnimThemeAttrs == null)
                flag = super.canApplyTheme();
            else
                flag = true;
            return flag;
        }

        int getKeyframeIdAt(int i)
        {
            boolean flag = false;
            if(i < 0)
                i = ((flag) ? 1 : 0);
            else
                i = mStateIds.get(i, 0);
            return i;
        }

        int indexOfKeyframe(int ai[])
        {
            int i = super.indexOfStateSet(ai);
            if(i >= 0)
                return i;
            else
                return super.indexOfStateSet(StateSet.WILD_CARD);
        }

        int indexOfTransition(int i, int j)
        {
            long l = generateTransitionKey(i, j);
            return (int)mTransitions.get(l, -1L);
        }

        boolean isTransitionReversed(int i, int j)
        {
            long l = generateTransitionKey(i, j);
            boolean flag;
            if((mTransitions.get(l, -1L) & 0x100000000L) != 0L)
                flag = true;
            else
                flag = false;
            return flag;
        }

        void mutate()
        {
            mTransitions = mTransitions.clone();
            mStateIds = mStateIds.clone();
        }

        public Drawable newDrawable()
        {
            return new AnimatedStateListDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new AnimatedStateListDrawable(this, resources, null);
        }

        boolean transitionHasReversibleFlag(int i, int j)
        {
            long l = generateTransitionKey(i, j);
            boolean flag;
            if((mTransitions.get(l, -1L) & 0x200000000L) != 0L)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private static final long REVERSED_BIT = 0x100000000L;
        private static final long REVERSIBLE_FLAG_BIT = 0x200000000L;
        int mAnimThemeAttrs[];
        SparseIntArray mStateIds;
        LongSparseLongArray mTransitions;

        AnimatedStateListState(AnimatedStateListState animatedstateliststate, AnimatedStateListDrawable animatedstatelistdrawable, Resources resources)
        {
            super(animatedstateliststate, animatedstatelistdrawable, resources);
            if(animatedstateliststate != null)
            {
                mAnimThemeAttrs = animatedstateliststate.mAnimThemeAttrs;
                mTransitions = animatedstateliststate.mTransitions;
                mStateIds = animatedstateliststate.mStateIds;
            } else
            {
                mTransitions = new LongSparseLongArray();
                mStateIds = new SparseIntArray();
            }
        }
    }

    private static class AnimatedVectorDrawableTransition extends Transition
    {

        public boolean canReverse()
        {
            boolean flag;
            if(mAvd.canReverse())
                flag = mHasReversibleFlag;
            else
                flag = false;
            return flag;
        }

        public void reverse()
        {
            if(canReverse())
                mAvd.reverse();
            else
                Log.w(AnimatedStateListDrawable._2D_get0(), "Can't reverse, either the reversible is set to false, or the AnimatedVectorDrawable can't reverse");
        }

        public void start()
        {
            if(mReversed)
                reverse();
            else
                mAvd.start();
        }

        public void stop()
        {
            mAvd.stop();
        }

        private final AnimatedVectorDrawable mAvd;
        private final boolean mHasReversibleFlag;
        private final boolean mReversed;

        public AnimatedVectorDrawableTransition(AnimatedVectorDrawable animatedvectordrawable, boolean flag, boolean flag1)
        {
            super(null);
            mAvd = animatedvectordrawable;
            mReversed = flag;
            mHasReversibleFlag = flag1;
        }
    }

    private static class AnimationDrawableTransition extends Transition
    {

        public boolean canReverse()
        {
            return mHasReversibleFlag;
        }

        public void reverse()
        {
            mAnim.reverse();
        }

        public void start()
        {
            mAnim.start();
        }

        public void stop()
        {
            mAnim.cancel();
        }

        private final ObjectAnimator mAnim;
        private final boolean mHasReversibleFlag;

        public AnimationDrawableTransition(AnimationDrawable animationdrawable, boolean flag, boolean flag1)
        {
            super(null);
            int i = animationdrawable.getNumberOfFrames();
            int j;
            FrameInterpolator frameinterpolator;
            if(flag)
                j = i - 1;
            else
                j = 0;
            if(flag)
                i = 0;
            else
                i--;
            frameinterpolator = new FrameInterpolator(animationdrawable, flag);
            animationdrawable = ObjectAnimator.ofInt(animationdrawable, "currentIndex", new int[] {
                j, i
            });
            animationdrawable.setAutoCancel(true);
            animationdrawable.setDuration(frameinterpolator.getTotalDuration());
            animationdrawable.setInterpolator(frameinterpolator);
            mHasReversibleFlag = flag1;
            mAnim = animationdrawable;
        }
    }

    private static class FrameInterpolator
        implements TimeInterpolator
    {

        public float getInterpolation(float f)
        {
            int i = (int)((float)mTotalDuration * f + 0.5F);
            int j = mFrames;
            int ai[] = mFrameTimes;
            int k;
            for(k = 0; k < j && i >= ai[k]; k++)
                i -= ai[k];

            if(k < j)
                f = (float)i / (float)mTotalDuration;
            else
                f = 0.0F;
            return (float)k / (float)j + f;
        }

        public int getTotalDuration()
        {
            return mTotalDuration;
        }

        public int updateFrames(AnimationDrawable animationdrawable, boolean flag)
        {
            int i = animationdrawable.getNumberOfFrames();
            mFrames = i;
            if(mFrameTimes == null || mFrameTimes.length < i)
                mFrameTimes = new int[i];
            int ai[] = mFrameTimes;
            int j = 0;
            int k = 0;
            while(k < i) 
            {
                int l;
                if(flag)
                    l = i - k - 1;
                else
                    l = k;
                l = animationdrawable.getDuration(l);
                ai[k] = l;
                j += l;
                k++;
            }
            mTotalDuration = j;
            return j;
        }

        private int mFrameTimes[];
        private int mFrames;
        private int mTotalDuration;

        public FrameInterpolator(AnimationDrawable animationdrawable, boolean flag)
        {
            updateFrames(animationdrawable, flag);
        }
    }

    private static abstract class Transition
    {

        public boolean canReverse()
        {
            return false;
        }

        public void reverse()
        {
        }

        public abstract void start();

        public abstract void stop();

        private Transition()
        {
        }

        Transition(Transition transition)
        {
            this();
        }
    }


    static String _2D_get0()
    {
        return LOGTAG;
    }

    public AnimatedStateListDrawable()
    {
        this(null, null);
    }

    private AnimatedStateListDrawable(AnimatedStateListState animatedstateliststate, Resources resources)
    {
        super(null);
        mTransitionToIndex = -1;
        mTransitionFromIndex = -1;
        setConstantState(new AnimatedStateListState(animatedstateliststate, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    AnimatedStateListDrawable(AnimatedStateListState animatedstateliststate, Resources resources, AnimatedStateListDrawable animatedstatelistdrawable)
    {
        this(animatedstateliststate, resources);
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth() + 1;
        do
        {
            int j = xmlpullparser.next();
            if(j == 1)
                break;
            int k = xmlpullparser.getDepth();
            if(k < i && j == 3)
                break;
            if(j == 2 && k <= i)
                if(xmlpullparser.getName().equals("item"))
                    parseItem(resources, xmlpullparser, attributeset, theme);
                else
                if(xmlpullparser.getName().equals("transition"))
                    parseTransition(resources, xmlpullparser, attributeset, theme);
        } while(true);
    }

    private void init()
    {
        onStateChange(getState());
    }

    private int parseItem(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        Object obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimatedStateListDrawableItem);
        int i = ((TypedArray) (obj)).getResourceId(0, 0);
        Drawable drawable = ((TypedArray) (obj)).getDrawable(1);
        ((TypedArray) (obj)).recycle();
        int ai[] = extractStateSet(attributeset);
        obj = drawable;
        if(drawable == null)
        {
            int j;
            do
                j = xmlpullparser.next();
            while(j == 4);
            if(j != 2)
                throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
            obj = Drawable.createFromXmlInner(resources, xmlpullparser, attributeset, theme);
        }
        return mState.addStateSet(ai, ((Drawable) (obj)), i);
    }

    private int parseTransition(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        Object obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimatedStateListDrawableTransition);
        int i = ((TypedArray) (obj)).getResourceId(2, 0);
        int j = ((TypedArray) (obj)).getResourceId(1, 0);
        boolean flag = ((TypedArray) (obj)).getBoolean(3, false);
        Drawable drawable = ((TypedArray) (obj)).getDrawable(0);
        ((TypedArray) (obj)).recycle();
        obj = drawable;
        if(drawable == null)
        {
            int k;
            do
                k = xmlpullparser.next();
            while(k == 4);
            if(k != 2)
                throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <transition> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
            obj = Drawable.createFromXmlInner(resources, xmlpullparser, attributeset, theme);
        }
        return mState.addTransition(i, j, ((Drawable) (obj)), flag);
    }

    private boolean selectTransition(int i)
    {
        Object obj = mTransition;
        int j;
        int k;
        int l;
        if(obj != null)
        {
            if(i == mTransitionToIndex)
                return true;
            if(i == mTransitionFromIndex && ((Transition) (obj)).canReverse())
            {
                ((Transition) (obj)).reverse();
                mTransitionToIndex = mTransitionFromIndex;
                mTransitionFromIndex = i;
                return true;
            }
            j = mTransitionToIndex;
            ((Transition) (obj)).stop();
        } else
        {
            j = getCurrentIndex();
        }
        mTransition = null;
        mTransitionFromIndex = -1;
        mTransitionToIndex = -1;
        obj = mState;
        k = ((AnimatedStateListState) (obj)).getKeyframeIdAt(j);
        l = ((AnimatedStateListState) (obj)).getKeyframeIdAt(i);
        if(l == 0 || k == 0)
            return false;
        int i1 = ((AnimatedStateListState) (obj)).indexOfTransition(k, l);
        if(i1 < 0)
            return false;
        boolean flag = ((AnimatedStateListState) (obj)).transitionHasReversibleFlag(k, l);
        selectDrawable(i1);
        Drawable drawable = getCurrent();
        if(drawable instanceof AnimationDrawable)
        {
            boolean flag1 = ((AnimatedStateListState) (obj)).isTransitionReversed(k, l);
            obj = new AnimationDrawableTransition((AnimationDrawable)drawable, flag1, flag);
        } else
        if(drawable instanceof AnimatedVectorDrawable)
        {
            boolean flag2 = ((AnimatedStateListState) (obj)).isTransitionReversed(k, l);
            obj = new AnimatedVectorDrawableTransition((AnimatedVectorDrawable)drawable, flag2, flag);
        } else
        if(drawable instanceof Animatable)
            obj = new AnimatableTransition((Animatable)drawable);
        else
            return false;
        ((Transition) (obj)).start();
        mTransition = ((Transition) (obj));
        mTransitionFromIndex = j;
        mTransitionToIndex = i;
        return true;
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        AnimatedStateListState animatedstateliststate = mState;
        animatedstateliststate.mChangingConfigurations = animatedstateliststate.mChangingConfigurations | typedarray.getChangingConfigurations();
        animatedstateliststate.mAnimThemeAttrs = typedarray.extractThemeAttrs();
        animatedstateliststate.setVariablePadding(typedarray.getBoolean(2, animatedstateliststate.mVariablePadding));
        animatedstateliststate.setConstantSize(typedarray.getBoolean(3, animatedstateliststate.mConstantSize));
        animatedstateliststate.setEnterFadeDuration(typedarray.getInt(4, animatedstateliststate.mEnterFadeDuration));
        animatedstateliststate.setExitFadeDuration(typedarray.getInt(5, animatedstateliststate.mExitFadeDuration));
        setDither(typedarray.getBoolean(0, animatedstateliststate.mDither));
        setAutoMirrored(typedarray.getBoolean(6, animatedstateliststate.mAutoMirrored));
    }

    public void addState(int ai[], Drawable drawable, int i)
    {
        if(drawable == null)
        {
            throw new IllegalArgumentException("Drawable must not be null");
        } else
        {
            mState.addStateSet(ai, drawable, i);
            onStateChange(getState());
            return;
        }
    }

    public void addTransition(int i, int j, Drawable drawable, boolean flag)
    {
        if(drawable == null)
        {
            throw new IllegalArgumentException("Transition drawable must not be null");
        } else
        {
            mState.addTransition(i, j, drawable, flag);
            return;
        }
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        AnimatedStateListState animatedstateliststate = mState;
        if(animatedstateliststate == null || animatedstateliststate.mAnimThemeAttrs == null)
        {
            return;
        } else
        {
            theme = theme.resolveAttributes(animatedstateliststate.mAnimThemeAttrs, com.android.internal.R.styleable.AnimatedRotateDrawable);
            updateStateFromTypedArray(theme);
            theme.recycle();
            init();
            return;
        }
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    AnimatedStateListState cloneConstantState()
    {
        return new AnimatedStateListState(mState, this, null);
    }

    volatile DrawableContainer.DrawableContainerState cloneConstantState()
    {
        return cloneConstantState();
    }

    volatile StateListDrawable.StateListState cloneConstantState()
    {
        return cloneConstantState();
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimatedStateListDrawable);
        super.inflateWithAttributes(resources, xmlpullparser, typedarray, 1);
        updateStateFromTypedArray(typedarray);
        updateDensity(resources);
        typedarray.recycle();
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
        init();
    }

    public boolean isStateful()
    {
        return true;
    }

    public void jumpToCurrentState()
    {
        super.jumpToCurrentState();
        if(mTransition != null)
        {
            mTransition.stop();
            mTransition = null;
            selectDrawable(mTransitionToIndex);
            mTransitionToIndex = -1;
            mTransitionFromIndex = -1;
        }
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mState.mutate();
            mMutated = true;
        }
        return this;
    }

    protected boolean onStateChange(int ai[])
    {
        int i = mState.indexOfKeyframe(ai);
        boolean flag;
        Drawable drawable;
        boolean flag1;
        if(i != getCurrentIndex())
        {
            if(!selectTransition(i))
                flag = selectDrawable(i);
            else
                flag = true;
        } else
        {
            flag = false;
        }
        drawable = getCurrent();
        flag1 = flag;
        if(drawable != null)
            flag1 = flag | drawable.setState(ai);
        return flag1;
    }

    protected void setConstantState(DrawableContainer.DrawableContainerState drawablecontainerstate)
    {
        super.setConstantState(drawablecontainerstate);
        if(drawablecontainerstate instanceof AnimatedStateListState)
            mState = (AnimatedStateListState)drawablecontainerstate;
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag2 = super.setVisible(flag, flag1);
        if(mTransition != null && (flag2 || flag1))
            if(flag)
                mTransition.start();
            else
                jumpToCurrentState();
        return flag2;
    }

    private static final String ELEMENT_ITEM = "item";
    private static final String ELEMENT_TRANSITION = "transition";
    private static final String LOGTAG = android/graphics/drawable/AnimatedStateListDrawable.getSimpleName();
    private boolean mMutated;
    private AnimatedStateListState mState;
    private Transition mTransition;
    private int mTransitionFromIndex;
    private int mTransitionToIndex;

}
