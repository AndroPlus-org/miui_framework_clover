// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableContainer, Animatable, Drawable

public class AnimationDrawable extends DrawableContainer
    implements Runnable, Animatable
{
    private static final class AnimationState extends DrawableContainer.DrawableContainerState
    {

        static int[] _2D_get0(AnimationState animationstate)
        {
            return animationstate.mDurations;
        }

        static boolean _2D_get1(AnimationState animationstate)
        {
            return animationstate.mOneShot;
        }

        static boolean _2D_set0(AnimationState animationstate, boolean flag)
        {
            animationstate.mOneShot = flag;
            return flag;
        }

        static void _2D_wrap0(AnimationState animationstate)
        {
            animationstate.mutate();
        }

        private void mutate()
        {
            mDurations = (int[])mDurations.clone();
        }

        public void addFrame(Drawable drawable, int i)
        {
            int j = super.addChild(drawable);
            mDurations[j] = i;
        }

        public void growArray(int i, int j)
        {
            super.growArray(i, j);
            int ai[] = new int[j];
            System.arraycopy(mDurations, 0, ai, 0, i);
            mDurations = ai;
        }

        public Drawable newDrawable()
        {
            return new AnimationDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new AnimationDrawable(this, resources, null);
        }

        private int mDurations[];
        private boolean mOneShot;

        AnimationState(AnimationState animationstate, AnimationDrawable animationdrawable, Resources resources)
        {
            super(animationstate, animationdrawable, resources);
            mOneShot = false;
            if(animationstate != null)
            {
                mDurations = animationstate.mDurations;
                mOneShot = animationstate.mOneShot;
            } else
            {
                mDurations = new int[getCapacity()];
                mOneShot = false;
            }
        }
    }


    public AnimationDrawable()
    {
        this(null, null);
    }

    private AnimationDrawable(AnimationState animationstate, Resources resources)
    {
        mCurFrame = 0;
        setConstantState(new AnimationState(animationstate, this, resources));
        if(animationstate != null)
            setFrame(0, true, false);
    }

    AnimationDrawable(AnimationState animationstate, Resources resources, AnimationDrawable animationdrawable)
    {
        this(animationstate, resources);
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
            int l = xmlpullparser.getDepth();
            if(l < i && j == 3)
                break;
            if(j == 2 && l <= i && !(xmlpullparser.getName().equals("item") ^ true))
            {
                Object obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimationDrawableItem);
                int i1 = ((TypedArray) (obj)).getInt(0, -1);
                if(i1 < 0)
                    throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'duration' attribute").toString());
                Drawable drawable = ((TypedArray) (obj)).getDrawable(1);
                ((TypedArray) (obj)).recycle();
                obj = drawable;
                if(drawable == null)
                {
                    int k;
                    do
                        k = xmlpullparser.next();
                    while(k == 4);
                    if(k != 2)
                        throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'drawable' attribute or child tag").append(" defining a drawable").toString());
                    obj = Drawable.createFromXmlInner(resources, xmlpullparser, attributeset, theme);
                }
                mAnimationState.addFrame(((Drawable) (obj)), i1);
                if(obj != null)
                    ((Drawable) (obj)).setCallback(this);
            }
        } while(true);
    }

    private void nextFrame(boolean flag)
    {
        int i = mCurFrame + 1;
        int j = mAnimationState.getChildCount();
        boolean flag1;
        int k;
        if(AnimationState._2D_get1(mAnimationState) && i >= j - 1)
            flag1 = true;
        else
            flag1 = false;
        k = i;
        if(!AnimationState._2D_get1(mAnimationState))
        {
            k = i;
            if(i >= j)
                k = 0;
        }
        setFrame(k, flag, flag1 ^ true);
    }

    private void setFrame(int i, boolean flag, boolean flag1)
    {
        if(i >= mAnimationState.getChildCount())
            return;
        mAnimating = flag1;
        mCurFrame = i;
        selectDrawable(i);
        if(flag || flag1)
            unscheduleSelf(this);
        if(flag1)
        {
            mCurFrame = i;
            mRunning = true;
            scheduleSelf(this, SystemClock.uptimeMillis() + (long)AnimationState._2D_get0(mAnimationState)[i]);
        }
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        mAnimationState.mVariablePadding = typedarray.getBoolean(1, mAnimationState.mVariablePadding);
        AnimationState._2D_set0(mAnimationState, typedarray.getBoolean(2, AnimationState._2D_get1(mAnimationState)));
    }

    public void addFrame(Drawable drawable, int i)
    {
        mAnimationState.addFrame(drawable, i);
        if(!mRunning)
            setFrame(0, true, false);
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    AnimationState cloneConstantState()
    {
        return new AnimationState(mAnimationState, this, null);
    }

    volatile DrawableContainer.DrawableContainerState cloneConstantState()
    {
        return cloneConstantState();
    }

    public int getDuration(int i)
    {
        return AnimationState._2D_get0(mAnimationState)[i];
    }

    public Drawable getFrame(int i)
    {
        return mAnimationState.getChild(i);
    }

    public int getNumberOfFrames()
    {
        return mAnimationState.getChildCount();
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimationDrawable);
        super.inflateWithAttributes(resources, xmlpullparser, typedarray, 0);
        updateStateFromTypedArray(typedarray);
        updateDensity(resources);
        typedarray.recycle();
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
        setFrame(0, true, false);
    }

    public boolean isOneShot()
    {
        return AnimationState._2D_get1(mAnimationState);
    }

    public boolean isRunning()
    {
        return mRunning;
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            AnimationState._2D_wrap0(mAnimationState);
            mMutated = true;
        }
        return this;
    }

    public void run()
    {
        nextFrame(false);
    }

    protected void setConstantState(DrawableContainer.DrawableContainerState drawablecontainerstate)
    {
        super.setConstantState(drawablecontainerstate);
        if(drawablecontainerstate instanceof AnimationState)
            mAnimationState = (AnimationState)drawablecontainerstate;
    }

    public void setOneShot(boolean flag)
    {
        AnimationState._2D_set0(mAnimationState, flag);
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag2 = super.setVisible(flag, flag1);
        if(flag)
        {
            if(flag1 || flag2)
            {
                int i;
                if(!flag1 && (mRunning || !(AnimationState._2D_get1(mAnimationState) ^ true)))
                {
                    if(mCurFrame >= mAnimationState.getChildCount())
                        i = 1;
                    else
                        i = 0;
                } else
                {
                    i = 1;
                }
                if(i != 0)
                    i = 0;
                else
                    i = mCurFrame;
                setFrame(i, true, mAnimating);
            }
        } else
        {
            unscheduleSelf(this);
        }
        return flag2;
    }

    public void start()
    {
        boolean flag = true;
        mAnimating = true;
        if(!isRunning())
        {
            if(mAnimationState.getChildCount() <= 1)
                flag = AnimationState._2D_get1(mAnimationState) ^ true;
            setFrame(0, false, flag);
        }
    }

    public void stop()
    {
        mAnimating = false;
        if(isRunning())
        {
            mCurFrame = 0;
            unscheduleSelf(this);
        }
    }

    public void unscheduleSelf(Runnable runnable)
    {
        mRunning = false;
        super.unscheduleSelf(runnable);
    }

    private boolean mAnimating;
    private AnimationState mAnimationState;
    private int mCurFrame;
    private boolean mMutated;
    private boolean mRunning;
}
