// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class ColorDrawable extends Drawable
{
    static final class ColorState extends Drawable.ConstantState
    {

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs == null)
            {
                if(mTint != null)
                    flag = mTint.canApplyTheme();
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        public int getChangingConfigurations()
        {
            int i = mChangingConfigurations;
            int j;
            if(mTint != null)
                j = mTint.getChangingConfigurations();
            else
                j = 0;
            return j | i;
        }

        public Drawable newDrawable()
        {
            return new ColorDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new ColorDrawable(this, resources, null);
        }

        int mBaseColor;
        int mChangingConfigurations;
        int mThemeAttrs[];
        ColorStateList mTint;
        android.graphics.PorterDuff.Mode mTintMode;
        int mUseColor;

        ColorState()
        {
            mTint = null;
            mTintMode = ColorDrawable.DEFAULT_TINT_MODE;
        }

        ColorState(ColorState colorstate)
        {
            mTint = null;
            mTintMode = ColorDrawable.DEFAULT_TINT_MODE;
            mThemeAttrs = colorstate.mThemeAttrs;
            mBaseColor = colorstate.mBaseColor;
            mUseColor = colorstate.mUseColor;
            mChangingConfigurations = colorstate.mChangingConfigurations;
            mTint = colorstate.mTint;
            mTintMode = colorstate.mTintMode;
        }
    }


    public ColorDrawable()
    {
        mPaint = new Paint(1);
        mColorState = new ColorState();
    }

    public ColorDrawable(int i)
    {
        mPaint = new Paint(1);
        mColorState = new ColorState();
        setColor(i);
    }

    private ColorDrawable(ColorState colorstate, Resources resources)
    {
        mPaint = new Paint(1);
        mColorState = colorstate;
        updateLocalState(resources);
    }

    ColorDrawable(ColorState colorstate, Resources resources, ColorDrawable colordrawable)
    {
        this(colorstate, resources);
    }

    private void updateLocalState(Resources resources)
    {
        mTintFilter = updateTintFilter(mTintFilter, mColorState.mTint, mColorState.mTintMode);
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        ColorState colorstate = mColorState;
        colorstate.mChangingConfigurations = colorstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        colorstate.mThemeAttrs = typedarray.extractThemeAttrs();
        colorstate.mBaseColor = typedarray.getColor(0, colorstate.mBaseColor);
        colorstate.mUseColor = colorstate.mBaseColor;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        ColorState colorstate = mColorState;
        if(colorstate == null)
            return;
        if(colorstate.mThemeAttrs != null)
        {
            TypedArray typedarray = theme.resolveAttributes(colorstate.mThemeAttrs, com.android.internal.R.styleable.ColorDrawable);
            updateStateFromTypedArray(typedarray);
            typedarray.recycle();
        }
        if(colorstate.mTint != null && colorstate.mTint.canApplyTheme())
            colorstate.mTint = colorstate.mTint.obtainForTheme(theme);
        updateLocalState(theme.getResources());
    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(!mColorState.canApplyTheme())
            flag = super.canApplyTheme();
        else
            flag = true;
        return flag;
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    public void draw(Canvas canvas)
    {
        ColorFilter colorfilter;
        colorfilter = mPaint.getColorFilter();
        break MISSING_BLOCK_LABEL_8;
        if(mColorState.mUseColor >>> 24 != 0 || colorfilter != null || mTintFilter != null)
        {
            if(colorfilter == null)
                mPaint.setColorFilter(mTintFilter);
            mPaint.setColor(mColorState.mUseColor);
            canvas.drawRect(getBounds(), mPaint);
            mPaint.setColorFilter(colorfilter);
        }
        return;
    }

    public int getAlpha()
    {
        return mColorState.mUseColor >>> 24;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mColorState.getChangingConfigurations();
    }

    public int getColor()
    {
        return mColorState.mUseColor;
    }

    public Drawable.ConstantState getConstantState()
    {
        return mColorState;
    }

    public int getOpacity()
    {
        if(mTintFilter != null || mPaint.getColorFilter() != null)
            return -3;
        switch(mColorState.mUseColor >>> 24)
        {
        default:
            return -3;

        case 255: 
            return -1;

        case 0: // '\0'
            return -2;
        }
    }

    public void getOutline(Outline outline)
    {
        outline.setRect(getBounds());
        outline.setAlpha((float)getAlpha() / 255F);
    }

    public Xfermode getXfermode()
    {
        return mPaint.getXfermode();
    }

    public boolean hasFocusStateSpecified()
    {
        boolean flag;
        if(mColorState.mTint != null)
            flag = mColorState.mTint.hasFocusStateSpecified();
        else
            flag = false;
        return flag;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        xmlpullparser = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.ColorDrawable);
        updateStateFromTypedArray(xmlpullparser);
        xmlpullparser.recycle();
        updateLocalState(resources);
    }

    public boolean isStateful()
    {
        boolean flag;
        if(mColorState.mTint != null)
            flag = mColorState.mTint.isStateful();
        else
            flag = false;
        return flag;
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mColorState = new ColorState(mColorState);
            mMutated = true;
        }
        return this;
    }

    protected boolean onStateChange(int ai[])
    {
        ai = mColorState;
        if(((ColorState) (ai)).mTint != null && ((ColorState) (ai)).mTintMode != null)
        {
            mTintFilter = updateTintFilter(mTintFilter, ((ColorState) (ai)).mTint, ((ColorState) (ai)).mTintMode);
            return true;
        } else
        {
            return false;
        }
    }

    public void setAlpha(int i)
    {
        int j = mColorState.mBaseColor;
        i = (mColorState.mBaseColor << 8) >>> 8 | ((j >>> 24) * (i + (i >> 7)) >> 8) << 24;
        if(mColorState.mUseColor != i)
        {
            mColorState.mUseColor = i;
            invalidateSelf();
        }
    }

    public void setColor(int i)
    {
        if(mColorState.mBaseColor != i || mColorState.mUseColor != i)
        {
            ColorState colorstate = mColorState;
            mColorState.mUseColor = i;
            colorstate.mBaseColor = i;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mPaint.setColorFilter(colorfilter);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mColorState.mTint = colorstatelist;
        mTintFilter = updateTintFilter(mTintFilter, colorstatelist, mColorState.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mColorState.mTintMode = mode;
        mTintFilter = updateTintFilter(mTintFilter, mColorState.mTint, mode);
        invalidateSelf();
    }

    public void setXfermode(Xfermode xfermode)
    {
        mPaint.setXfermode(xfermode);
        invalidateSelf();
    }

    private ColorState mColorState;
    private boolean mMutated;
    private final Paint mPaint;
    private PorterDuffColorFilter mTintFilter;
}
