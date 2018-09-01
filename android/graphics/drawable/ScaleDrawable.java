// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            DrawableWrapper, Drawable

public class ScaleDrawable extends DrawableWrapper
{
    static final class ScaleState extends DrawableWrapper.DrawableWrapperState
    {

        static int[] _2D_get0(ScaleState scalestate)
        {
            return scalestate.mThemeAttrs;
        }

        static int[] _2D_set0(ScaleState scalestate, int ai[])
        {
            scalestate.mThemeAttrs = ai;
            return ai;
        }

        public Drawable newDrawable(Resources resources)
        {
            return new ScaleDrawable(this, resources, null);
        }

        private static final float DO_NOT_SCALE = -1F;
        int mGravity;
        int mInitialLevel;
        float mScaleHeight;
        float mScaleWidth;
        private int mThemeAttrs[];
        boolean mUseIntrinsicSizeAsMin;

        ScaleState(ScaleState scalestate, Resources resources)
        {
            super(scalestate, resources);
            mScaleWidth = -1F;
            mScaleHeight = -1F;
            mGravity = 3;
            mUseIntrinsicSizeAsMin = false;
            mInitialLevel = 0;
            if(scalestate != null)
            {
                mScaleWidth = scalestate.mScaleWidth;
                mScaleHeight = scalestate.mScaleHeight;
                mGravity = scalestate.mGravity;
                mUseIntrinsicSizeAsMin = scalestate.mUseIntrinsicSizeAsMin;
                mInitialLevel = scalestate.mInitialLevel;
            }
        }
    }


    ScaleDrawable()
    {
        this(new ScaleState(null, null), null);
    }

    public ScaleDrawable(Drawable drawable, int i, float f, float f1)
    {
        this(new ScaleState(null, null), null);
        mState.mGravity = i;
        mState.mScaleWidth = f;
        mState.mScaleHeight = f1;
        setDrawable(drawable);
    }

    private ScaleDrawable(ScaleState scalestate, Resources resources)
    {
        super(scalestate, resources);
        mTmpRect = new Rect();
        mState = scalestate;
        updateLocalState();
    }

    ScaleDrawable(ScaleState scalestate, Resources resources, ScaleDrawable scaledrawable)
    {
        this(scalestate, resources);
    }

    private static float getPercent(TypedArray typedarray, int i, float f)
    {
        int j = typedarray.getType(i);
        if(j == 6 || j == 0)
            return typedarray.getFraction(i, 1, 1, f);
        typedarray = typedarray.getString(i);
        if(typedarray != null && typedarray.endsWith("%"))
            return Float.parseFloat(typedarray.substring(0, typedarray.length() - 1)) / 100F;
        else
            return f;
    }

    private void updateLocalState()
    {
        setLevel(mState.mInitialLevel);
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        ScaleState scalestate = mState;
        if(scalestate == null)
        {
            return;
        } else
        {
            scalestate.mChangingConfigurations = scalestate.mChangingConfigurations | typedarray.getChangingConfigurations();
            ScaleState._2D_set0(scalestate, typedarray.extractThemeAttrs());
            scalestate.mScaleWidth = getPercent(typedarray, 1, scalestate.mScaleWidth);
            scalestate.mScaleHeight = getPercent(typedarray, 2, scalestate.mScaleHeight);
            scalestate.mGravity = typedarray.getInt(3, scalestate.mGravity);
            scalestate.mUseIntrinsicSizeAsMin = typedarray.getBoolean(4, scalestate.mUseIntrinsicSizeAsMin);
            scalestate.mInitialLevel = typedarray.getInt(5, scalestate.mInitialLevel);
            return;
        }
    }

    private void verifyRequiredAttributes(TypedArray typedarray)
        throws XmlPullParserException
    {
        if(getDrawable() == null && (ScaleState._2D_get0(mState) == null || ScaleState._2D_get0(mState)[0] == 0))
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <scale> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
        else
            return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        ScaleState scalestate = mState;
        if(scalestate == null)
            return;
        if(ScaleState._2D_get0(scalestate) == null)
            break MISSING_BLOCK_LABEL_48;
        theme = theme.resolveAttributes(ScaleState._2D_get0(scalestate), com.android.internal.R.styleable.ScaleDrawable);
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
        if(drawable != null && drawable.getLevel() != 0)
            drawable.draw(canvas);
    }

    public int getOpacity()
    {
        Drawable drawable = getDrawable();
        if(drawable.getLevel() == 0)
            return -2;
        int i = drawable.getOpacity();
        if(i == -1 && drawable.getLevel() < 10000)
            return -3;
        else
            return i;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.ScaleDrawable);
        super.inflate(resources, xmlpullparser, attributeset, theme);
        updateStateFromTypedArray(typedarray);
        verifyRequiredAttributes(typedarray);
        typedarray.recycle();
        updateLocalState();
    }

    DrawableWrapper.DrawableWrapperState mutateConstantState()
    {
        mState = new ScaleState(mState, null);
        return mState;
    }

    protected void onBoundsChange(Rect rect)
    {
        Drawable drawable = getDrawable();
        Rect rect1 = mTmpRect;
        boolean flag = mState.mUseIntrinsicSizeAsMin;
        int i = getLevel();
        int j = rect.width();
        int k = j;
        if(mState.mScaleWidth > 0.0F)
        {
            int l;
            if(flag)
                k = drawable.getIntrinsicWidth();
            else
                k = 0;
            k = j - (int)(((float)((j - k) * (10000 - i)) * mState.mScaleWidth) / 10000F);
        }
        l = rect.height();
        j = l;
        if(mState.mScaleHeight > 0.0F)
        {
            if(flag)
                j = drawable.getIntrinsicHeight();
            else
                j = 0;
            j = l - (int)(((float)((l - j) * (10000 - i)) * mState.mScaleHeight) / 10000F);
        }
        l = getLayoutDirection();
        Gravity.apply(mState.mGravity, k, j, rect, rect1, l);
        if(k > 0 && j > 0)
            drawable.setBounds(rect1.left, rect1.top, rect1.right, rect1.bottom);
    }

    protected boolean onLevelChange(int i)
    {
        super.onLevelChange(i);
        onBoundsChange(getBounds());
        invalidateSelf();
        return true;
    }

    private static final int MAX_LEVEL = 10000;
    private ScaleState mState;
    private final Rect mTmpRect;
}
