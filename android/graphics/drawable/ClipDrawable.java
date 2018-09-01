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

public class ClipDrawable extends DrawableWrapper
{
    static final class ClipState extends DrawableWrapper.DrawableWrapperState
    {

        static int[] _2D_get0(ClipState clipstate)
        {
            return clipstate.mThemeAttrs;
        }

        static int[] _2D_set0(ClipState clipstate, int ai[])
        {
            clipstate.mThemeAttrs = ai;
            return ai;
        }

        public Drawable newDrawable(Resources resources)
        {
            return new ClipDrawable(this, resources, null);
        }

        int mGravity;
        int mOrientation;
        private int mThemeAttrs[];

        ClipState(ClipState clipstate, Resources resources)
        {
            super(clipstate, resources);
            mOrientation = 1;
            mGravity = 3;
            if(clipstate != null)
            {
                mOrientation = clipstate.mOrientation;
                mGravity = clipstate.mGravity;
            }
        }
    }


    ClipDrawable()
    {
        this(new ClipState(null, null), null);
    }

    private ClipDrawable(ClipState clipstate, Resources resources)
    {
        super(clipstate, resources);
        mTmpRect = new Rect();
        mState = clipstate;
    }

    ClipDrawable(ClipState clipstate, Resources resources, ClipDrawable clipdrawable)
    {
        this(clipstate, resources);
    }

    public ClipDrawable(Drawable drawable, int i, int j)
    {
        this(new ClipState(null, null), null);
        mState.mGravity = i;
        mState.mOrientation = j;
        setDrawable(drawable);
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        ClipState clipstate = mState;
        if(clipstate == null)
        {
            return;
        } else
        {
            clipstate.mChangingConfigurations = clipstate.mChangingConfigurations | typedarray.getChangingConfigurations();
            ClipState._2D_set0(clipstate, typedarray.extractThemeAttrs());
            clipstate.mOrientation = typedarray.getInt(2, clipstate.mOrientation);
            clipstate.mGravity = typedarray.getInt(0, clipstate.mGravity);
            return;
        }
    }

    private void verifyRequiredAttributes(TypedArray typedarray)
        throws XmlPullParserException
    {
        if(getDrawable() == null && (ClipState._2D_get0(mState) == null || ClipState._2D_get0(mState)[1] == 0))
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <clip> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
        else
            return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        ClipState clipstate = mState;
        if(clipstate == null)
            return;
        if(ClipState._2D_get0(clipstate) == null)
            break MISSING_BLOCK_LABEL_48;
        theme = theme.resolveAttributes(ClipState._2D_get0(clipstate), com.android.internal.R.styleable.ClipDrawable);
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
        if(drawable.getLevel() == 0)
            return;
        Rect rect = mTmpRect;
        Rect rect1 = getBounds();
        int i = getLevel();
        int j = rect1.width();
        int k = j;
        if((mState.mOrientation & 1) != 0)
            k = j - ((j + 0) * (10000 - i)) / 10000;
        int l = rect1.height();
        j = l;
        if((mState.mOrientation & 2) != 0)
            j = l - ((l + 0) * (10000 - i)) / 10000;
        l = getLayoutDirection();
        Gravity.apply(mState.mGravity, k, j, rect1, rect, l);
        if(k > 0 && j > 0)
        {
            canvas.save();
            canvas.clipRect(rect);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    public int getOpacity()
    {
        Drawable drawable = getDrawable();
        if(drawable.getOpacity() == -2 || drawable.getLevel() == 0)
            return -2;
        if(getLevel() >= 10000)
            return drawable.getOpacity();
        else
            return -3;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.ClipDrawable);
        super.inflate(resources, xmlpullparser, attributeset, theme);
        updateStateFromTypedArray(typedarray);
        verifyRequiredAttributes(typedarray);
        typedarray.recycle();
    }

    DrawableWrapper.DrawableWrapperState mutateConstantState()
    {
        mState = new ClipState(mState, null);
        return mState;
    }

    protected boolean onLevelChange(int i)
    {
        super.onLevelChange(i);
        invalidateSelf();
        return true;
    }

    public static final int HORIZONTAL = 1;
    private static final int MAX_LEVEL = 10000;
    public static final int VERTICAL = 2;
    private ClipState mState;
    private final Rect mTmpRect;
}
