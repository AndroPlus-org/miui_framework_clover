// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class ShapeDrawable extends Drawable
{
    public static abstract class ShaderFactory
    {

        public abstract Shader resize(int i, int j);

        public ShaderFactory()
        {
        }
    }

    static final class ShapeState extends Drawable.ConstantState
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
            return new ShapeDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new ShapeDrawable(this, resources, null);
        }

        int mAlpha;
        int mChangingConfigurations;
        int mIntrinsicHeight;
        int mIntrinsicWidth;
        Rect mPadding;
        final Paint mPaint;
        ShaderFactory mShaderFactory;
        Shape mShape;
        int mThemeAttrs[];
        ColorStateList mTint;
        android.graphics.PorterDuff.Mode mTintMode;

        ShapeState()
        {
            mTintMode = ShapeDrawable.DEFAULT_TINT_MODE;
            mAlpha = 255;
            mPaint = new Paint(1);
        }

        ShapeState(ShapeState shapestate)
        {
            mTintMode = ShapeDrawable.DEFAULT_TINT_MODE;
            mAlpha = 255;
            mChangingConfigurations = shapestate.mChangingConfigurations;
            mPaint = new Paint(shapestate.mPaint);
            mThemeAttrs = shapestate.mThemeAttrs;
            if(shapestate.mShape != null)
                try
                {
                    mShape = shapestate.mShape.clone();
                }
                catch(CloneNotSupportedException clonenotsupportedexception)
                {
                    mShape = shapestate.mShape;
                }
            mTint = shapestate.mTint;
            mTintMode = shapestate.mTintMode;
            if(shapestate.mPadding != null)
                mPadding = new Rect(shapestate.mPadding);
            mIntrinsicWidth = shapestate.mIntrinsicWidth;
            mIntrinsicHeight = shapestate.mIntrinsicHeight;
            mAlpha = shapestate.mAlpha;
            mShaderFactory = shapestate.mShaderFactory;
        }
    }


    public ShapeDrawable()
    {
        this(new ShapeState(), null);
    }

    private ShapeDrawable(ShapeState shapestate, Resources resources)
    {
        mShapeState = shapestate;
        updateLocalState();
    }

    ShapeDrawable(ShapeState shapestate, Resources resources, ShapeDrawable shapedrawable)
    {
        this(shapestate, resources);
    }

    public ShapeDrawable(Shape shape)
    {
        this(new ShapeState(), null);
        mShapeState.mShape = shape;
    }

    private static int modulateAlpha(int i, int j)
    {
        return i * (j + (j >>> 7)) >>> 8;
    }

    private void updateLocalState()
    {
        mTintFilter = updateTintFilter(mTintFilter, mShapeState.mTint, mShapeState.mTintMode);
    }

    private void updateShape()
    {
        if(mShapeState.mShape != null)
        {
            Rect rect = getBounds();
            int i = rect.width();
            int j = rect.height();
            mShapeState.mShape.resize(i, j);
            if(mShapeState.mShaderFactory != null)
                mShapeState.mPaint.setShader(mShapeState.mShaderFactory.resize(i, j));
        }
        invalidateSelf();
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        ShapeState shapestate = mShapeState;
        Paint paint = shapestate.mPaint;
        shapestate.mChangingConfigurations = shapestate.mChangingConfigurations | typedarray.getChangingConfigurations();
        shapestate.mThemeAttrs = typedarray.extractThemeAttrs();
        paint.setColor(typedarray.getColor(4, paint.getColor()));
        paint.setDither(typedarray.getBoolean(0, paint.isDither()));
        shapestate.mIntrinsicWidth = (int)typedarray.getDimension(3, shapestate.mIntrinsicWidth);
        shapestate.mIntrinsicHeight = (int)typedarray.getDimension(2, shapestate.mIntrinsicHeight);
        int i = typedarray.getInt(5, -1);
        if(i != -1)
            shapestate.mTintMode = Drawable.parseTintMode(i, android.graphics.PorterDuff.Mode.SRC_IN);
        typedarray = typedarray.getColorStateList(1);
        if(typedarray != null)
            shapestate.mTint = typedarray;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        ShapeState shapestate = mShapeState;
        if(shapestate == null)
            return;
        if(shapestate.mThemeAttrs != null)
        {
            TypedArray typedarray = theme.resolveAttributes(shapestate.mThemeAttrs, com.android.internal.R.styleable.ShapeDrawable);
            updateStateFromTypedArray(typedarray);
            typedarray.recycle();
        }
        if(shapestate.mTint != null && shapestate.mTint.canApplyTheme())
            shapestate.mTint = shapestate.mTint.obtainForTheme(theme);
        updateLocalState();
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    public void draw(Canvas canvas)
    {
        Rect rect;
        ShapeState shapestate;
        Paint paint;
        int i;
        rect = getBounds();
        shapestate = mShapeState;
        paint = shapestate.mPaint;
        i = paint.getAlpha();
        paint.setAlpha(modulateAlpha(i, shapestate.mAlpha));
        break MISSING_BLOCK_LABEL_37;
        if(paint.getAlpha() != 0 || paint.getXfermode() != null || paint.hasShadowLayer())
        {
            boolean flag;
            if(mTintFilter != null && paint.getColorFilter() == null)
            {
                paint.setColorFilter(mTintFilter);
                flag = true;
            } else
            {
                flag = false;
            }
            if(shapestate.mShape != null)
            {
                int j = canvas.save();
                canvas.translate(rect.left, rect.top);
                onDraw(shapestate.mShape, canvas, paint);
                canvas.restoreToCount(j);
            } else
            {
                canvas.drawRect(rect, paint);
            }
            if(flag)
                paint.setColorFilter(null);
        }
        paint.setAlpha(i);
        return;
    }

    public int getAlpha()
    {
        return mShapeState.mAlpha;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mShapeState.getChangingConfigurations();
    }

    public Drawable.ConstantState getConstantState()
    {
        mShapeState.mChangingConfigurations = getChangingConfigurations();
        return mShapeState;
    }

    public int getIntrinsicHeight()
    {
        return mShapeState.mIntrinsicHeight;
    }

    public int getIntrinsicWidth()
    {
        return mShapeState.mIntrinsicWidth;
    }

    public int getOpacity()
    {
        if(mShapeState.mShape == null)
        {
            Paint paint = mShapeState.mPaint;
            if(paint.getXfermode() == null)
            {
                int i = paint.getAlpha();
                if(i == 0)
                    return -2;
                if(i == 255)
                    return -1;
            }
        }
        return -3;
    }

    public void getOutline(Outline outline)
    {
        if(mShapeState.mShape != null)
        {
            mShapeState.mShape.getOutline(outline);
            outline.setAlpha((float)getAlpha() / 255F);
        }
    }

    public boolean getPadding(Rect rect)
    {
        if(mShapeState.mPadding != null)
        {
            rect.set(mShapeState.mPadding);
            return true;
        } else
        {
            return super.getPadding(rect);
        }
    }

    public Paint getPaint()
    {
        return mShapeState.mPaint;
    }

    public ShaderFactory getShaderFactory()
    {
        return mShapeState.mShaderFactory;
    }

    public Shape getShape()
    {
        return mShapeState.mShape;
    }

    public boolean hasFocusStateSpecified()
    {
        boolean flag;
        if(mShapeState.mTint != null)
            flag = mShapeState.mTint.hasFocusStateSpecified();
        else
            flag = false;
        return flag;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        theme = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.ShapeDrawable);
        updateStateFromTypedArray(theme);
        theme.recycle();
        int i = xmlpullparser.getDepth();
        do
        {
            int j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() <= i)
                break;
            if(j == 2)
            {
                theme = xmlpullparser.getName();
                if(!inflateTag(theme, resources, xmlpullparser, attributeset))
                    Log.w("drawable", (new StringBuilder()).append("Unknown element: ").append(theme).append(" for ShapeDrawable ").append(this).toString());
            }
        } while(true);
        updateLocalState();
    }

    protected boolean inflateTag(String s, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
    {
        if("padding".equals(s))
        {
            s = resources.obtainAttributes(attributeset, com.android.internal.R.styleable.ShapeDrawablePadding);
            setPadding(s.getDimensionPixelOffset(0, 0), s.getDimensionPixelOffset(1, 0), s.getDimensionPixelOffset(2, 0), s.getDimensionPixelOffset(3, 0));
            s.recycle();
            return true;
        } else
        {
            return false;
        }
    }

    public boolean isStateful()
    {
        ShapeState shapestate = mShapeState;
        boolean flag;
        if(!super.isStateful())
        {
            if(shapestate.mTint != null)
                flag = shapestate.mTint.isStateful();
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mShapeState = new ShapeState(mShapeState);
            updateLocalState();
            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        updateShape();
    }

    protected void onDraw(Shape shape, Canvas canvas, Paint paint)
    {
        shape.draw(canvas, paint);
    }

    protected boolean onStateChange(int ai[])
    {
        ai = mShapeState;
        if(((ShapeState) (ai)).mTint != null && ((ShapeState) (ai)).mTintMode != null)
        {
            mTintFilter = updateTintFilter(mTintFilter, ((ShapeState) (ai)).mTint, ((ShapeState) (ai)).mTintMode);
            return true;
        } else
        {
            return false;
        }
    }

    public void setAlpha(int i)
    {
        mShapeState.mAlpha = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mShapeState.mPaint.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public void setDither(boolean flag)
    {
        mShapeState.mPaint.setDither(flag);
        invalidateSelf();
    }

    public void setIntrinsicHeight(int i)
    {
        mShapeState.mIntrinsicHeight = i;
        invalidateSelf();
    }

    public void setIntrinsicWidth(int i)
    {
        mShapeState.mIntrinsicWidth = i;
        invalidateSelf();
    }

    public void setPadding(int i, int j, int k, int l)
    {
        if((i | j | k | l) == 0)
        {
            mShapeState.mPadding = null;
        } else
        {
            if(mShapeState.mPadding == null)
                mShapeState.mPadding = new Rect();
            mShapeState.mPadding.set(i, j, k, l);
        }
        invalidateSelf();
    }

    public void setPadding(Rect rect)
    {
        if(rect == null)
        {
            mShapeState.mPadding = null;
        } else
        {
            if(mShapeState.mPadding == null)
                mShapeState.mPadding = new Rect();
            mShapeState.mPadding.set(rect);
        }
        invalidateSelf();
    }

    public void setShaderFactory(ShaderFactory shaderfactory)
    {
        mShapeState.mShaderFactory = shaderfactory;
    }

    public void setShape(Shape shape)
    {
        mShapeState.mShape = shape;
        updateShape();
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mShapeState.mTint = colorstatelist;
        mTintFilter = updateTintFilter(mTintFilter, colorstatelist, mShapeState.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mShapeState.mTintMode = mode;
        mTintFilter = updateTintFilter(mTintFilter, mShapeState.mTint, mode);
        invalidateSelf();
    }

    public void setXfermode(Xfermode xfermode)
    {
        mShapeState.mPaint.setXfermode(xfermode);
        invalidateSelf();
    }

    private boolean mMutated;
    private ShapeState mShapeState;
    private PorterDuffColorFilter mTintFilter;
}
