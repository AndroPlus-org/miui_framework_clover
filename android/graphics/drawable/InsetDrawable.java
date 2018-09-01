// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.*;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableWrapper, Drawable

public class InsetDrawable extends DrawableWrapper
{
    static final class InsetState extends DrawableWrapper.DrawableWrapperState
    {

        static int[] _2D_get0(InsetState insetstate)
        {
            return insetstate.mThemeAttrs;
        }

        static int[] _2D_set0(InsetState insetstate, int ai[])
        {
            insetstate.mThemeAttrs = ai;
            return ai;
        }

        private void applyDensityScaling(int i, int j)
        {
            mInsetLeft.scaleFromDensity(i, j);
            mInsetTop.scaleFromDensity(i, j);
            mInsetRight.scaleFromDensity(i, j);
            mInsetBottom.scaleFromDensity(i, j);
        }

        public Drawable newDrawable(Resources resources)
        {
            InsetState insetstate;
            if(resources != null)
            {
                int i = resources.getDisplayMetrics().densityDpi;
                if(i == 0)
                    i = 160;
                if(i != mDensity)
                    insetstate = new InsetState(this, resources);
                else
                    insetstate = this;
            } else
            {
                insetstate = this;
            }
            return new InsetDrawable(insetstate, resources, null);
        }

        void onDensityChanged(int i, int j)
        {
            super.onDensityChanged(i, j);
            applyDensityScaling(i, j);
        }

        InsetValue mInsetBottom;
        InsetValue mInsetLeft;
        InsetValue mInsetRight;
        InsetValue mInsetTop;
        private int mThemeAttrs[];

        InsetState(InsetState insetstate, Resources resources)
        {
            super(insetstate, resources);
            if(insetstate != null)
            {
                mInsetLeft = insetstate.mInsetLeft.clone();
                mInsetTop = insetstate.mInsetTop.clone();
                mInsetRight = insetstate.mInsetRight.clone();
                mInsetBottom = insetstate.mInsetBottom.clone();
                if(insetstate.mDensity != mDensity)
                    applyDensityScaling(insetstate.mDensity, mDensity);
            } else
            {
                mInsetLeft = new InsetValue();
                mInsetTop = new InsetValue();
                mInsetRight = new InsetValue();
                mInsetBottom = new InsetValue();
            }
        }
    }

    static final class InsetValue
        implements Cloneable
    {

        public InsetValue clone()
        {
            return new InsetValue(mFraction, mDimension);
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        int getDimension(int i)
        {
            return (int)((float)i * mFraction) + mDimension;
        }

        void scaleFromDensity(int i, int j)
        {
            if(mDimension != 0)
                mDimension = Bitmap.scaleFromDensity(mDimension, i, j);
        }

        int mDimension;
        final float mFraction;

        public InsetValue()
        {
            this(0.0F, 0);
        }

        public InsetValue(float f, int i)
        {
            mFraction = f;
            mDimension = i;
        }
    }


    InsetDrawable()
    {
        this(new InsetState(null, null), ((Resources) (null)));
    }

    public InsetDrawable(Drawable drawable, float f)
    {
        this(drawable, f, f, f, f);
    }

    public InsetDrawable(Drawable drawable, float f, float f1, float f2, float f3)
    {
        this(new InsetState(null, null), ((Resources) (null)));
        mState.mInsetLeft = new InsetValue(f, 0);
        mState.mInsetTop = new InsetValue(f1, 0);
        mState.mInsetRight = new InsetValue(f2, 0);
        mState.mInsetBottom = new InsetValue(f3, 0);
        setDrawable(drawable);
    }

    public InsetDrawable(Drawable drawable, int i)
    {
        this(drawable, i, i, i, i);
    }

    public InsetDrawable(Drawable drawable, int i, int j, int k, int l)
    {
        this(new InsetState(null, null), ((Resources) (null)));
        mState.mInsetLeft = new InsetValue(0.0F, i);
        mState.mInsetTop = new InsetValue(0.0F, j);
        mState.mInsetRight = new InsetValue(0.0F, k);
        mState.mInsetBottom = new InsetValue(0.0F, l);
        setDrawable(drawable);
    }

    private InsetDrawable(InsetState insetstate, Resources resources)
    {
        super(insetstate, resources);
        mTmpRect = new Rect();
        mTmpInsetRect = new Rect();
        mState = insetstate;
    }

    InsetDrawable(InsetState insetstate, Resources resources, InsetDrawable insetdrawable)
    {
        this(insetstate, resources);
    }

    private InsetValue getInset(TypedArray typedarray, int i, InsetValue insetvalue)
    {
        if(typedarray.hasValue(i))
        {
            TypedValue typedvalue = typedarray.peekValue(i);
            if(typedvalue.type == 6)
            {
                float f = typedvalue.getFraction(1.0F, 1.0F);
                if(f >= 1.0F)
                    throw new IllegalStateException("Fraction cannot be larger than 1");
                else
                    return new InsetValue(f, 0);
            }
            i = typedarray.getDimensionPixelOffset(i, 0);
            if(i != 0)
                return new InsetValue(0.0F, i);
        }
        return insetvalue;
    }

    private void getInsets(Rect rect)
    {
        Rect rect1 = getBounds();
        rect.left = mState.mInsetLeft.getDimension(rect1.width());
        rect.right = mState.mInsetRight.getDimension(rect1.width());
        rect.top = mState.mInsetTop.getDimension(rect1.height());
        rect.bottom = mState.mInsetBottom.getDimension(rect1.height());
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        InsetState insetstate = mState;
        if(insetstate == null)
            return;
        insetstate.mChangingConfigurations = insetstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        InsetState._2D_set0(insetstate, typedarray.extractThemeAttrs());
        if(typedarray.hasValue(6))
        {
            InsetValue insetvalue = getInset(typedarray, 6, new InsetValue());
            insetstate.mInsetLeft = insetvalue;
            insetstate.mInsetTop = insetvalue;
            insetstate.mInsetRight = insetvalue;
            insetstate.mInsetBottom = insetvalue;
        }
        insetstate.mInsetLeft = getInset(typedarray, 2, insetstate.mInsetLeft);
        insetstate.mInsetTop = getInset(typedarray, 4, insetstate.mInsetTop);
        insetstate.mInsetRight = getInset(typedarray, 3, insetstate.mInsetRight);
        insetstate.mInsetBottom = getInset(typedarray, 5, insetstate.mInsetBottom);
    }

    private void verifyRequiredAttributes(TypedArray typedarray)
        throws XmlPullParserException
    {
        if(getDrawable() == null && (InsetState._2D_get0(mState) == null || InsetState._2D_get0(mState)[1] == 0))
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <inset> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
        else
            return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        InsetState insetstate = mState;
        if(insetstate == null)
            return;
        if(InsetState._2D_get0(insetstate) == null)
            break MISSING_BLOCK_LABEL_48;
        theme = theme.resolveAttributes(InsetState._2D_get0(insetstate), com.android.internal.R.styleable.InsetDrawable);
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

    public int getIntrinsicHeight()
    {
        int i = getDrawable().getIntrinsicHeight();
        float f = mState.mInsetTop.mFraction + mState.mInsetBottom.mFraction;
        if(i < 0 || f >= 1.0F)
            return -1;
        else
            return (int)((float)i / (1.0F - f)) + mState.mInsetTop.mDimension + mState.mInsetBottom.mDimension;
    }

    public int getIntrinsicWidth()
    {
        int i = getDrawable().getIntrinsicWidth();
        float f = mState.mInsetLeft.mFraction + mState.mInsetRight.mFraction;
        if(i < 0 || f >= 1.0F)
            return -1;
        else
            return (int)((float)i / (1.0F - f)) + mState.mInsetLeft.mDimension + mState.mInsetRight.mDimension;
    }

    public int getOpacity()
    {
        InsetState insetstate = mState;
        int i = getDrawable().getOpacity();
        getInsets(mTmpInsetRect);
        while(i == -1 && (mTmpInsetRect.left > 0 || mTmpInsetRect.top > 0 || mTmpInsetRect.right > 0 || mTmpInsetRect.bottom > 0)) 
            return -3;
        return i;
    }

    public Insets getOpticalInsets()
    {
        Insets insets = super.getOpticalInsets();
        getInsets(mTmpInsetRect);
        return Insets.of(insets.left + mTmpInsetRect.left, insets.top + mTmpInsetRect.top, insets.right + mTmpInsetRect.right, insets.bottom + mTmpInsetRect.bottom);
    }

    public void getOutline(Outline outline)
    {
        getDrawable().getOutline(outline);
    }

    public boolean getPadding(Rect rect)
    {
        boolean flag = true;
        boolean flag1 = super.getPadding(rect);
        getInsets(mTmpInsetRect);
        rect.left = rect.left + mTmpInsetRect.left;
        rect.right = rect.right + mTmpInsetRect.right;
        rect.top = rect.top + mTmpInsetRect.top;
        rect.bottom = rect.bottom + mTmpInsetRect.bottom;
        boolean flag2 = flag;
        if(!flag1)
            if((mTmpInsetRect.left | mTmpInsetRect.right | mTmpInsetRect.top | mTmpInsetRect.bottom) != 0)
                flag2 = flag;
            else
                flag2 = false;
        return flag2;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.InsetDrawable);
        super.inflate(resources, xmlpullparser, attributeset, theme);
        updateStateFromTypedArray(typedarray);
        verifyRequiredAttributes(typedarray);
        typedarray.recycle();
    }

    DrawableWrapper.DrawableWrapperState mutateConstantState()
    {
        mState = new InsetState(mState, null);
        return mState;
    }

    protected void onBoundsChange(Rect rect)
    {
        Rect rect1 = mTmpRect;
        rect1.set(rect);
        rect1.left = rect1.left + mState.mInsetLeft.getDimension(rect.width());
        rect1.top = rect1.top + mState.mInsetTop.getDimension(rect.height());
        rect1.right = rect1.right - mState.mInsetRight.getDimension(rect.width());
        rect1.bottom = rect1.bottom - mState.mInsetBottom.getDimension(rect.height());
        super.onBoundsChange(rect1);
    }

    private InsetState mState;
    private final Rect mTmpInsetRect;
    private final Rect mTmpRect;
}
