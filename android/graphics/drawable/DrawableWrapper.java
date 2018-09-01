// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public abstract class DrawableWrapper extends Drawable
    implements Drawable.Callback
{
    static abstract class DrawableWrapperState extends Drawable.ConstantState
    {

        static int[] _2D_get0(DrawableWrapperState drawablewrapperstate)
        {
            return drawablewrapperstate.mThemeAttrs;
        }

        static int[] _2D_set0(DrawableWrapperState drawablewrapperstate, int ai[])
        {
            drawablewrapperstate.mThemeAttrs = ai;
            return ai;
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs == null && (mDrawableState == null || !mDrawableState.canApplyTheme()))
                flag = super.canApplyTheme();
            else
                flag = true;
            return flag;
        }

        public boolean canConstantState()
        {
            boolean flag;
            if(mDrawableState != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public int getChangingConfigurations()
        {
            int i = mChangingConfigurations;
            int j;
            if(mDrawableState != null)
                j = mDrawableState.getChangingConfigurations();
            else
                j = 0;
            return j | i;
        }

        public Drawable newDrawable()
        {
            return newDrawable(null);
        }

        public abstract Drawable newDrawable(Resources resources);

        void onDensityChanged(int i, int j)
        {
        }

        public final void setDensity(int i)
        {
            if(mDensity != i)
            {
                int j = mDensity;
                mDensity = i;
                onDensityChanged(j, i);
            }
        }

        int mChangingConfigurations;
        int mDensity;
        Drawable.ConstantState mDrawableState;
        int mSrcDensityOverride;
        private int mThemeAttrs[];

        DrawableWrapperState(DrawableWrapperState drawablewrapperstate, Resources resources)
        {
            mDensity = 160;
            mSrcDensityOverride = 0;
            if(drawablewrapperstate != null)
            {
                mThemeAttrs = drawablewrapperstate.mThemeAttrs;
                mChangingConfigurations = drawablewrapperstate.mChangingConfigurations;
                mDrawableState = drawablewrapperstate.mDrawableState;
                mSrcDensityOverride = drawablewrapperstate.mSrcDensityOverride;
            }
            int i;
            int j;
            if(resources != null)
                i = resources.getDisplayMetrics().densityDpi;
            else
            if(drawablewrapperstate != null)
                i = drawablewrapperstate.mDensity;
            else
                i = 0;
            j = i;
            if(i == 0)
                j = 160;
            mDensity = j;
        }
    }


    public DrawableWrapper(Drawable drawable)
    {
        mState = null;
        mDrawable = drawable;
    }

    DrawableWrapper(DrawableWrapperState drawablewrapperstate, Resources resources)
    {
        mState = drawablewrapperstate;
        updateLocalState(resources);
    }

    private void inflateChildDrawable(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        Drawable drawable = null;
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() <= i)
                break;
            if(j == 2)
                drawable = Drawable.createFromXmlInnerForDensity(resources, xmlpullparser, attributeset, mState.mSrcDensityOverride, theme);
        } while(true);
        if(drawable != null)
            setDrawable(drawable);
    }

    private void updateLocalState(Resources resources)
    {
        if(mState != null && mState.mDrawableState != null)
            setDrawable(mState.mDrawableState.newDrawable(resources));
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        DrawableWrapperState drawablewrapperstate = mState;
        if(drawablewrapperstate == null)
            return;
        drawablewrapperstate.mChangingConfigurations = drawablewrapperstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        DrawableWrapperState._2D_set0(drawablewrapperstate, typedarray.extractThemeAttrs());
        if(typedarray.hasValueOrEmpty(0))
            setDrawable(typedarray.getDrawable(0));
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        if(mDrawable != null && mDrawable.canApplyTheme())
            mDrawable.applyTheme(theme);
        DrawableWrapperState drawablewrapperstate = mState;
        if(drawablewrapperstate == null)
            return;
        int i = theme.getResources().getDisplayMetrics().densityDpi;
        if(i == 0)
            i = 160;
        drawablewrapperstate.setDensity(i);
        if(DrawableWrapperState._2D_get0(drawablewrapperstate) != null)
        {
            theme = theme.resolveAttributes(DrawableWrapperState._2D_get0(drawablewrapperstate), com.android.internal.R.styleable.DrawableWrapper);
            updateStateFromTypedArray(theme);
            theme.recycle();
        }
    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(mState == null || !mState.canApplyTheme())
            flag = super.canApplyTheme();
        else
            flag = true;
        return flag;
    }

    public void clearMutated()
    {
        super.clearMutated();
        if(mDrawable != null)
            mDrawable.clearMutated();
        mMutated = false;
    }

    public void draw(Canvas canvas)
    {
        if(mDrawable != null)
            mDrawable.draw(canvas);
    }

    public int getAlpha()
    {
        int i;
        if(mDrawable != null)
            i = mDrawable.getAlpha();
        else
            i = 255;
        return i;
    }

    public int getChangingConfigurations()
    {
        int i = super.getChangingConfigurations();
        int j;
        if(mState != null)
            j = mState.getChangingConfigurations();
        else
            j = 0;
        return j | i | mDrawable.getChangingConfigurations();
    }

    public Drawable.ConstantState getConstantState()
    {
        if(mState != null && mState.canConstantState())
        {
            mState.mChangingConfigurations = getChangingConfigurations();
            return mState;
        } else
        {
            return null;
        }
    }

    public Drawable getDrawable()
    {
        return mDrawable;
    }

    public void getHotspotBounds(Rect rect)
    {
        if(mDrawable != null)
            mDrawable.getHotspotBounds(rect);
        else
            rect.set(getBounds());
    }

    public int getIntrinsicHeight()
    {
        int i;
        if(mDrawable != null)
            i = mDrawable.getIntrinsicHeight();
        else
            i = -1;
        return i;
    }

    public int getIntrinsicWidth()
    {
        int i;
        if(mDrawable != null)
            i = mDrawable.getIntrinsicWidth();
        else
            i = -1;
        return i;
    }

    public int getOpacity()
    {
        int i;
        if(mDrawable != null)
            i = mDrawable.getOpacity();
        else
            i = -2;
        return i;
    }

    public Insets getOpticalInsets()
    {
        Insets insets;
        if(mDrawable != null)
            insets = mDrawable.getOpticalInsets();
        else
            insets = Insets.NONE;
        return insets;
    }

    public void getOutline(Outline outline)
    {
        if(mDrawable != null)
            mDrawable.getOutline(outline);
        else
            super.getOutline(outline);
    }

    public boolean getPadding(Rect rect)
    {
        boolean flag;
        if(mDrawable != null)
            flag = mDrawable.getPadding(rect);
        else
            flag = false;
        return flag;
    }

    public boolean hasFocusStateSpecified()
    {
        boolean flag;
        if(mDrawable != null)
            flag = mDrawable.hasFocusStateSpecified();
        else
            flag = false;
        return flag;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        Object obj = mState;
        if(obj == null)
            return;
        int i = resources.getDisplayMetrics().densityDpi;
        if(i == 0)
            i = 160;
        ((DrawableWrapperState) (obj)).setDensity(i);
        obj.mSrcDensityOverride = mSrcDensityOverride;
        obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.DrawableWrapper);
        updateStateFromTypedArray(((TypedArray) (obj)));
        ((TypedArray) (obj)).recycle();
        inflateChildDrawable(resources, xmlpullparser, attributeset, theme);
    }

    public void invalidateDrawable(Drawable drawable)
    {
        drawable = getCallback();
        if(drawable != null)
            drawable.invalidateDrawable(this);
    }

    public boolean isStateful()
    {
        boolean flag;
        if(mDrawable != null)
            flag = mDrawable.isStateful();
        else
            flag = false;
        return flag;
    }

    public Drawable mutate()
    {
        Drawable.ConstantState constantstate = null;
        if(!mMutated && super.mutate() == this)
        {
            mState = mutateConstantState();
            if(mDrawable != null)
                mDrawable.mutate();
            if(mState != null)
            {
                DrawableWrapperState drawablewrapperstate = mState;
                if(mDrawable != null)
                    constantstate = mDrawable.getConstantState();
                drawablewrapperstate.mDrawableState = constantstate;
            }
            mMutated = true;
        }
        return this;
    }

    DrawableWrapperState mutateConstantState()
    {
        return mState;
    }

    protected void onBoundsChange(Rect rect)
    {
        if(mDrawable != null)
            mDrawable.setBounds(rect);
    }

    public boolean onLayoutDirectionChanged(int i)
    {
        boolean flag;
        if(mDrawable != null)
            flag = mDrawable.setLayoutDirection(i);
        else
            flag = false;
        return flag;
    }

    protected boolean onLevelChange(int i)
    {
        boolean flag;
        if(mDrawable != null)
            flag = mDrawable.setLevel(i);
        else
            flag = false;
        return flag;
    }

    protected boolean onStateChange(int ai[])
    {
        if(mDrawable != null && mDrawable.isStateful())
        {
            boolean flag = mDrawable.setState(ai);
            if(flag)
                onBoundsChange(getBounds());
            return flag;
        } else
        {
            return false;
        }
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        drawable = getCallback();
        if(drawable != null)
            drawable.scheduleDrawable(this, runnable, l);
    }

    public void setAlpha(int i)
    {
        if(mDrawable != null)
            mDrawable.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mDrawable != null)
            mDrawable.setColorFilter(colorfilter);
    }

    public void setDrawable(Drawable drawable)
    {
        if(mDrawable != null)
            mDrawable.setCallback(null);
        mDrawable = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            drawable.setVisible(isVisible(), true);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            drawable.setLayoutDirection(getLayoutDirection());
            if(mState != null)
                mState.mDrawableState = drawable.getConstantState();
        }
        invalidateSelf();
    }

    public void setHotspot(float f, float f1)
    {
        if(mDrawable != null)
            mDrawable.setHotspot(f, f1);
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        if(mDrawable != null)
            mDrawable.setHotspotBounds(i, j, k, l);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        if(mDrawable != null)
            mDrawable.setTintList(colorstatelist);
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mDrawable != null)
            mDrawable.setTintMode(mode);
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag2 = super.setVisible(flag, flag1);
        if(mDrawable != null)
            flag = mDrawable.setVisible(flag, flag1);
        else
            flag = false;
        return flag2 | flag;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        drawable = getCallback();
        if(drawable != null)
            drawable.unscheduleDrawable(this, runnable);
    }

    private Drawable mDrawable;
    private boolean mMutated;
    private DrawableWrapperState mState;
}
