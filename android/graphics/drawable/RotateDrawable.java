// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.*;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableWrapper, Drawable

public class RotateDrawable extends DrawableWrapper
{
    static final class RotateState extends DrawableWrapper.DrawableWrapperState
    {

        static int[] _2D_get0(RotateState rotatestate)
        {
            return rotatestate.mThemeAttrs;
        }

        static int[] _2D_set0(RotateState rotatestate, int ai[])
        {
            rotatestate.mThemeAttrs = ai;
            return ai;
        }

        public Drawable newDrawable(Resources resources)
        {
            return new RotateDrawable(this, resources, null);
        }

        float mCurrentDegrees;
        float mFromDegrees;
        float mPivotX;
        boolean mPivotXRel;
        float mPivotY;
        boolean mPivotYRel;
        private int mThemeAttrs[];
        float mToDegrees;

        RotateState(RotateState rotatestate, Resources resources)
        {
            super(rotatestate, resources);
            mPivotXRel = true;
            mPivotX = 0.5F;
            mPivotYRel = true;
            mPivotY = 0.5F;
            mFromDegrees = 0.0F;
            mToDegrees = 360F;
            mCurrentDegrees = 0.0F;
            if(rotatestate != null)
            {
                mPivotXRel = rotatestate.mPivotXRel;
                mPivotX = rotatestate.mPivotX;
                mPivotYRel = rotatestate.mPivotYRel;
                mPivotY = rotatestate.mPivotY;
                mFromDegrees = rotatestate.mFromDegrees;
                mToDegrees = rotatestate.mToDegrees;
                mCurrentDegrees = rotatestate.mCurrentDegrees;
            }
        }
    }


    public RotateDrawable()
    {
        this(new RotateState(null, null), null);
    }

    private RotateDrawable(RotateState rotatestate, Resources resources)
    {
        super(rotatestate, resources);
        mState = rotatestate;
    }

    RotateDrawable(RotateState rotatestate, Resources resources, RotateDrawable rotatedrawable)
    {
        this(rotatestate, resources);
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        boolean flag = true;
        RotateState rotatestate = mState;
        if(rotatestate == null)
            return;
        rotatestate.mChangingConfigurations = rotatestate.mChangingConfigurations | typedarray.getChangingConfigurations();
        RotateState._2D_set0(rotatestate, typedarray.extractThemeAttrs());
        TypedValue typedvalue;
        boolean flag1;
        float f;
        if(typedarray.hasValue(4))
        {
            typedvalue = typedarray.peekValue(4);
            if(typedvalue.type == 6)
                flag1 = true;
            else
                flag1 = false;
            rotatestate.mPivotXRel = flag1;
            if(rotatestate.mPivotXRel)
                f = typedvalue.getFraction(1.0F, 1.0F);
            else
                f = typedvalue.getFloat();
            rotatestate.mPivotX = f;
        }
        if(typedarray.hasValue(5))
        {
            typedvalue = typedarray.peekValue(5);
            if(typedvalue.type == 6)
                flag1 = flag;
            else
                flag1 = false;
            rotatestate.mPivotYRel = flag1;
            if(rotatestate.mPivotYRel)
                f = typedvalue.getFraction(1.0F, 1.0F);
            else
                f = typedvalue.getFloat();
            rotatestate.mPivotY = f;
        }
        rotatestate.mFromDegrees = typedarray.getFloat(2, rotatestate.mFromDegrees);
        rotatestate.mToDegrees = typedarray.getFloat(3, rotatestate.mToDegrees);
        rotatestate.mCurrentDegrees = rotatestate.mFromDegrees;
    }

    private void verifyRequiredAttributes(TypedArray typedarray)
        throws XmlPullParserException
    {
        if(getDrawable() == null && (RotateState._2D_get0(mState) == null || RotateState._2D_get0(mState)[1] == 0))
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <rotate> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
        else
            return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        RotateState rotatestate = mState;
        if(rotatestate == null)
            return;
        if(RotateState._2D_get0(rotatestate) == null)
            break MISSING_BLOCK_LABEL_48;
        theme = theme.resolveAttributes(RotateState._2D_get0(rotatestate), com.android.internal.R.styleable.RotateDrawable);
        updateStateFromTypedArray(theme);
        verifyRequiredAttributes(theme);
        theme.recycle();
_L2:
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
        RotateState rotatestate = mState;
        float f;
        float f1;
        if(rotatestate.mPivotXRel)
            f = (float)(i - j) * rotatestate.mPivotX;
        else
            f = rotatestate.mPivotX;
        if(rotatestate.mPivotYRel)
            f1 = (float)(k - l) * rotatestate.mPivotY;
        else
            f1 = rotatestate.mPivotY;
        j = canvas.save();
        canvas.rotate(rotatestate.mCurrentDegrees, (float)rect.left + f, (float)rect.top + f1);
        drawable.draw(canvas);
        canvas.restoreToCount(j);
    }

    public float getFromDegrees()
    {
        return mState.mFromDegrees;
    }

    public float getPivotX()
    {
        return mState.mPivotX;
    }

    public float getPivotY()
    {
        return mState.mPivotY;
    }

    public float getToDegrees()
    {
        return mState.mToDegrees;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.RotateDrawable);
        super.inflate(resources, xmlpullparser, attributeset, theme);
        updateStateFromTypedArray(typedarray);
        verifyRequiredAttributes(typedarray);
        typedarray.recycle();
    }

    public boolean isPivotXRelative()
    {
        return mState.mPivotXRel;
    }

    public boolean isPivotYRelative()
    {
        return mState.mPivotYRel;
    }

    DrawableWrapper.DrawableWrapperState mutateConstantState()
    {
        mState = new RotateState(mState, null);
        return mState;
    }

    protected boolean onLevelChange(int i)
    {
        super.onLevelChange(i);
        float f = (float)i / 10000F;
        f = MathUtils.lerp(mState.mFromDegrees, mState.mToDegrees, f);
        mState.mCurrentDegrees = f;
        invalidateSelf();
        return true;
    }

    public void setFromDegrees(float f)
    {
        if(mState.mFromDegrees != f)
        {
            mState.mFromDegrees = f;
            invalidateSelf();
        }
    }

    public void setPivotX(float f)
    {
        if(mState.mPivotX != f)
        {
            mState.mPivotX = f;
            invalidateSelf();
        }
    }

    public void setPivotXRelative(boolean flag)
    {
        if(mState.mPivotXRel != flag)
        {
            mState.mPivotXRel = flag;
            invalidateSelf();
        }
    }

    public void setPivotY(float f)
    {
        if(mState.mPivotY != f)
        {
            mState.mPivotY = f;
            invalidateSelf();
        }
    }

    public void setPivotYRelative(boolean flag)
    {
        if(mState.mPivotYRel != flag)
        {
            mState.mPivotYRel = flag;
            invalidateSelf();
        }
    }

    public void setToDegrees(float f)
    {
        if(mState.mToDegrees != f)
        {
            mState.mToDegrees = f;
            invalidateSelf();
        }
    }

    private static final int MAX_LEVEL = 10000;
    private RotateState mState;
}
