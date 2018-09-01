// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.graphics.drawable;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.*;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimationScaleListDrawable extends DrawableContainer
    implements Animatable
{
    static class AnimationScaleListState extends android.graphics.drawable.DrawableContainer.DrawableContainerState
    {

        int addDrawable(Drawable drawable)
        {
            int i = addChild(drawable);
            if(drawable instanceof Animatable)
                mAnimatableDrawableIndex = i;
            else
                mStaticDrawableIndex = i;
            return i;
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs == null)
                flag = super.canApplyTheme();
            else
                flag = true;
            return flag;
        }

        public int getCurrentDrawableIndexBasedOnScale()
        {
            if(ValueAnimator.getDurationScale() == 0.0F)
                return mStaticDrawableIndex;
            else
                return mAnimatableDrawableIndex;
        }

        void mutate()
        {
            int ai[] = null;
            if(mThemeAttrs != null)
                ai = (int[])mThemeAttrs.clone();
            mThemeAttrs = ai;
        }

        public Drawable newDrawable()
        {
            return new AnimationScaleListDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new AnimationScaleListDrawable(this, resources, null);
        }

        int mAnimatableDrawableIndex;
        int mStaticDrawableIndex;
        int mThemeAttrs[];

        AnimationScaleListState(AnimationScaleListState animationscaleliststate, AnimationScaleListDrawable animationscalelistdrawable, Resources resources)
        {
            super(animationscaleliststate, animationscalelistdrawable, resources);
            mThemeAttrs = null;
            mStaticDrawableIndex = -1;
            mAnimatableDrawableIndex = -1;
            if(animationscaleliststate != null)
            {
                mThemeAttrs = animationscaleliststate.mThemeAttrs;
                mStaticDrawableIndex = animationscaleliststate.mStaticDrawableIndex;
                mAnimatableDrawableIndex = animationscaleliststate.mAnimatableDrawableIndex;
            }
        }
    }


    public AnimationScaleListDrawable()
    {
        this(null, null);
    }

    private AnimationScaleListDrawable(AnimationScaleListState animationscaleliststate, Resources resources)
    {
        setConstantState(new AnimationScaleListState(animationscaleliststate, this, resources));
        onStateChange(getState());
    }

    AnimationScaleListDrawable(AnimationScaleListState animationscaleliststate, Resources resources, AnimationScaleListDrawable animationscalelistdrawable)
    {
        this(animationscaleliststate, resources);
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        AnimationScaleListState animationscaleliststate = mAnimationScaleListState;
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
                Object obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimationScaleListDrawableItem);
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
                        throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
                    obj = Drawable.createFromXmlInner(resources, xmlpullparser, attributeset, theme);
                }
                animationscaleliststate.addDrawable(((Drawable) (obj)));
            }
        } while(true);
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimationScaleListDrawable);
        updateDensity(resources);
        typedarray.recycle();
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
        onStateChange(getState());
    }

    public boolean isRunning()
    {
        boolean flag = false;
        Drawable drawable = getCurrent();
        boolean flag1 = flag;
        if(drawable != null)
        {
            flag1 = flag;
            if(drawable instanceof Animatable)
                flag1 = ((Animatable)drawable).isRunning();
        }
        return flag1;
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mAnimationScaleListState.mutate();
            mMutated = true;
        }
        return this;
    }

    protected boolean onStateChange(int ai[])
    {
        boolean flag = super.onStateChange(ai);
        if(selectDrawable(mAnimationScaleListState.getCurrentDrawableIndexBasedOnScale()))
            flag = true;
        return flag;
    }

    protected void setConstantState(android.graphics.drawable.DrawableContainer.DrawableContainerState drawablecontainerstate)
    {
        super.setConstantState(drawablecontainerstate);
        if(drawablecontainerstate instanceof AnimationScaleListState)
            mAnimationScaleListState = (AnimationScaleListState)drawablecontainerstate;
    }

    public void start()
    {
        Drawable drawable = getCurrent();
        if(drawable != null && (drawable instanceof Animatable))
            ((Animatable)drawable).start();
    }

    public void stop()
    {
        Drawable drawable = getCurrent();
        if(drawable != null && (drawable instanceof Animatable))
            ((Animatable)drawable).stop();
    }

    private static final String TAG = "AnimationScaleListDrawable";
    private AnimationScaleListState mAnimationScaleListState;
    private boolean mMutated;
}
