// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableWrapper, Animatable, Drawable, BitmapDrawable

public class AnimatedRotateDrawable extends DrawableWrapper
    implements Animatable
{
    static final class AnimatedRotateState extends DrawableWrapper.DrawableWrapperState
    {

        static int[] _2D_get0(AnimatedRotateState animatedrotatestate)
        {
            return animatedrotatestate.mThemeAttrs;
        }

        static int[] _2D_set0(AnimatedRotateState animatedrotatestate, int ai[])
        {
            animatedrotatestate.mThemeAttrs = ai;
            return ai;
        }

        public Drawable newDrawable(Resources resources)
        {
            return new AnimatedRotateDrawable(this, resources, null);
        }

        int mFrameDuration;
        int mFramesCount;
        float mPivotX;
        boolean mPivotXRel;
        float mPivotY;
        boolean mPivotYRel;
        private int mThemeAttrs[];

        public AnimatedRotateState(AnimatedRotateState animatedrotatestate, Resources resources)
        {
            super(animatedrotatestate, resources);
            mPivotXRel = false;
            mPivotX = 0.0F;
            mPivotYRel = false;
            mPivotY = 0.0F;
            mFrameDuration = 150;
            mFramesCount = 12;
            if(animatedrotatestate != null)
            {
                mPivotXRel = animatedrotatestate.mPivotXRel;
                mPivotX = animatedrotatestate.mPivotX;
                mPivotYRel = animatedrotatestate.mPivotYRel;
                mPivotY = animatedrotatestate.mPivotY;
                mFramesCount = animatedrotatestate.mFramesCount;
                mFrameDuration = animatedrotatestate.mFrameDuration;
            }
        }
    }


    static float _2D_get0(AnimatedRotateDrawable animatedrotatedrawable)
    {
        return animatedrotatedrawable.mCurrentDegrees;
    }

    static float _2D_get1(AnimatedRotateDrawable animatedrotatedrawable)
    {
        return animatedrotatedrawable.mIncrement;
    }

    static float _2D_set0(AnimatedRotateDrawable animatedrotatedrawable, float f)
    {
        animatedrotatedrawable.mCurrentDegrees = f;
        return f;
    }

    static void _2D_wrap0(AnimatedRotateDrawable animatedrotatedrawable)
    {
        animatedrotatedrawable.nextFrame();
    }

    public AnimatedRotateDrawable()
    {
        this(new AnimatedRotateState(null, null), null);
    }

    private AnimatedRotateDrawable(AnimatedRotateState animatedrotatestate, Resources resources)
    {
        super(animatedrotatestate, resources);
        mNextFrame = new Runnable() {

            public void run()
            {
                AnimatedRotateDrawable animatedrotatedrawable = AnimatedRotateDrawable.this;
                AnimatedRotateDrawable._2D_set0(animatedrotatedrawable, AnimatedRotateDrawable._2D_get0(animatedrotatedrawable) + AnimatedRotateDrawable._2D_get1(AnimatedRotateDrawable.this));
                if(AnimatedRotateDrawable._2D_get0(AnimatedRotateDrawable.this) > 360F - AnimatedRotateDrawable._2D_get1(AnimatedRotateDrawable.this))
                    AnimatedRotateDrawable._2D_set0(AnimatedRotateDrawable.this, 0.0F);
                invalidateSelf();
                AnimatedRotateDrawable._2D_wrap0(AnimatedRotateDrawable.this);
            }

            final AnimatedRotateDrawable this$0;

            
            {
                this$0 = AnimatedRotateDrawable.this;
                super();
            }
        }
;
        mState = animatedrotatestate;
        updateLocalState();
    }

    AnimatedRotateDrawable(AnimatedRotateState animatedrotatestate, Resources resources, AnimatedRotateDrawable animatedrotatedrawable)
    {
        this(animatedrotatestate, resources);
    }

    private void nextFrame()
    {
        unscheduleSelf(mNextFrame);
        scheduleSelf(mNextFrame, SystemClock.uptimeMillis() + (long)mState.mFrameDuration);
    }

    private void updateLocalState()
    {
        mIncrement = 360F / (float)mState.mFramesCount;
        Drawable drawable = getDrawable();
        if(drawable != null)
        {
            drawable.setFilterBitmap(true);
            if(drawable instanceof BitmapDrawable)
                ((BitmapDrawable)drawable).setAntiAlias(true);
        }
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        boolean flag = true;
        AnimatedRotateState animatedrotatestate = mState;
        if(animatedrotatestate == null)
            return;
        animatedrotatestate.mChangingConfigurations = animatedrotatestate.mChangingConfigurations | typedarray.getChangingConfigurations();
        AnimatedRotateState._2D_set0(animatedrotatestate, typedarray.extractThemeAttrs());
        TypedValue typedvalue;
        boolean flag1;
        float f;
        if(typedarray.hasValue(2))
        {
            typedvalue = typedarray.peekValue(2);
            if(typedvalue.type == 6)
                flag1 = true;
            else
                flag1 = false;
            animatedrotatestate.mPivotXRel = flag1;
            if(animatedrotatestate.mPivotXRel)
                f = typedvalue.getFraction(1.0F, 1.0F);
            else
                f = typedvalue.getFloat();
            animatedrotatestate.mPivotX = f;
        }
        if(typedarray.hasValue(3))
        {
            typedvalue = typedarray.peekValue(3);
            if(typedvalue.type == 6)
                flag1 = flag;
            else
                flag1 = false;
            animatedrotatestate.mPivotYRel = flag1;
            if(animatedrotatestate.mPivotYRel)
                f = typedvalue.getFraction(1.0F, 1.0F);
            else
                f = typedvalue.getFloat();
            animatedrotatestate.mPivotY = f;
        }
        setFramesCount(typedarray.getInt(5, animatedrotatestate.mFramesCount));
        setFramesDuration(typedarray.getInt(4, animatedrotatestate.mFrameDuration));
    }

    private void verifyRequiredAttributes(TypedArray typedarray)
        throws XmlPullParserException
    {
        if(getDrawable() == null && (AnimatedRotateState._2D_get0(mState) == null || AnimatedRotateState._2D_get0(mState)[1] == 0))
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <animated-rotate> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
        else
            return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        AnimatedRotateState animatedrotatestate = mState;
        if(animatedrotatestate == null)
            return;
        if(AnimatedRotateState._2D_get0(animatedrotatestate) == null)
            break MISSING_BLOCK_LABEL_48;
        theme = theme.resolveAttributes(AnimatedRotateState._2D_get0(animatedrotatestate), com.android.internal.R.styleable.AnimatedRotateDrawable);
        updateStateFromTypedArray(theme);
        verifyRequiredAttributes(theme);
        theme.recycle();
_L2:
        updateLocalState();
        return;
        Object obj;
        obj;
        rethrowAsRuntimeException(((Exception) (obj)));
        theme.recycle();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        theme.recycle();
        throw obj;
    }

    public void draw(Canvas canvas)
    {
        Drawable drawable = getDrawable();
        Rect rect = drawable.getBounds();
        int i = rect.right;
        int j = rect.left;
        int k = rect.bottom;
        int l = rect.top;
        AnimatedRotateState animatedrotatestate = mState;
        float f;
        float f1;
        if(animatedrotatestate.mPivotXRel)
            f = (float)(i - j) * animatedrotatestate.mPivotX;
        else
            f = animatedrotatestate.mPivotX;
        if(animatedrotatestate.mPivotYRel)
            f1 = (float)(k - l) * animatedrotatestate.mPivotY;
        else
            f1 = animatedrotatestate.mPivotY;
        j = canvas.save();
        canvas.rotate(mCurrentDegrees, (float)rect.left + f, (float)rect.top + f1);
        drawable.draw(canvas);
        canvas.restoreToCount(j);
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimatedRotateDrawable);
        super.inflate(resources, xmlpullparser, attributeset, theme);
        updateStateFromTypedArray(typedarray);
        verifyRequiredAttributes(typedarray);
        typedarray.recycle();
        updateLocalState();
    }

    public boolean isRunning()
    {
        return mRunning;
    }

    DrawableWrapper.DrawableWrapperState mutateConstantState()
    {
        mState = new AnimatedRotateState(mState, null);
        return mState;
    }

    public void setFramesCount(int i)
    {
        mState.mFramesCount = i;
        mIncrement = 360F / (float)mState.mFramesCount;
    }

    public void setFramesDuration(int i)
    {
        mState.mFrameDuration = i;
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag2 = super.setVisible(flag, flag1);
        if(flag)
        {
            if(flag2 || flag1)
            {
                mCurrentDegrees = 0.0F;
                nextFrame();
            }
        } else
        {
            unscheduleSelf(mNextFrame);
        }
        return flag2;
    }

    public void start()
    {
        if(!mRunning)
        {
            mRunning = true;
            nextFrame();
        }
    }

    public void stop()
    {
        mRunning = false;
        unscheduleSelf(mNextFrame);
    }

    private float mCurrentDegrees;
    private float mIncrement;
    private final Runnable mNextFrame;
    private boolean mRunning;
    private AnimatedRotateState mState;
}
