// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.*;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class NinePatchDrawable extends Drawable
{
    static final class NinePatchState extends Drawable.ConstantState
    {

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs == null && (mTint == null || !mTint.canApplyTheme()))
                flag = super.canApplyTheme();
            else
                flag = true;
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
            return new NinePatchDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new NinePatchDrawable(this, resources, null);
        }

        boolean mAutoMirrored;
        float mBaseAlpha;
        int mChangingConfigurations;
        boolean mDither;
        NinePatch mNinePatch;
        Insets mOpticalInsets;
        Rect mPadding;
        int mThemeAttrs[];
        ColorStateList mTint;
        android.graphics.PorterDuff.Mode mTintMode;

        NinePatchState()
        {
            mNinePatch = null;
            mTint = null;
            mTintMode = NinePatchDrawable.DEFAULT_TINT_MODE;
            mPadding = null;
            mOpticalInsets = Insets.NONE;
            mBaseAlpha = 1.0F;
            mDither = false;
            mAutoMirrored = false;
        }

        NinePatchState(NinePatch ninepatch, Rect rect)
        {
            this(ninepatch, rect, null, false, false);
        }

        NinePatchState(NinePatch ninepatch, Rect rect, Rect rect1)
        {
            this(ninepatch, rect, rect1, false, false);
        }

        NinePatchState(NinePatch ninepatch, Rect rect, Rect rect1, boolean flag, boolean flag1)
        {
            mNinePatch = null;
            mTint = null;
            mTintMode = NinePatchDrawable.DEFAULT_TINT_MODE;
            mPadding = null;
            mOpticalInsets = Insets.NONE;
            mBaseAlpha = 1.0F;
            mDither = false;
            mAutoMirrored = false;
            mNinePatch = ninepatch;
            mPadding = rect;
            mOpticalInsets = Insets.of(rect1);
            mDither = flag;
            mAutoMirrored = flag1;
        }

        NinePatchState(NinePatchState ninepatchstate)
        {
            mNinePatch = null;
            mTint = null;
            mTintMode = NinePatchDrawable.DEFAULT_TINT_MODE;
            mPadding = null;
            mOpticalInsets = Insets.NONE;
            mBaseAlpha = 1.0F;
            mDither = false;
            mAutoMirrored = false;
            mChangingConfigurations = ninepatchstate.mChangingConfigurations;
            mNinePatch = ninepatchstate.mNinePatch;
            mTint = ninepatchstate.mTint;
            mTintMode = ninepatchstate.mTintMode;
            mPadding = ninepatchstate.mPadding;
            mOpticalInsets = ninepatchstate.mOpticalInsets;
            mBaseAlpha = ninepatchstate.mBaseAlpha;
            mDither = ninepatchstate.mDither;
            mAutoMirrored = ninepatchstate.mAutoMirrored;
            mThemeAttrs = ninepatchstate.mThemeAttrs;
        }
    }


    NinePatchDrawable()
    {
        mOpticalInsets = Insets.NONE;
        mTargetDensity = 160;
        mBitmapWidth = -1;
        mBitmapHeight = -1;
        mNinePatchState = new NinePatchState();
    }

    public NinePatchDrawable(Resources resources, Bitmap bitmap, byte abyte0[], Rect rect, Rect rect1, String s)
    {
        this(new NinePatchState(new NinePatch(bitmap, abyte0, s), rect, rect1), resources);
    }

    public NinePatchDrawable(Resources resources, Bitmap bitmap, byte abyte0[], Rect rect, String s)
    {
        this(new NinePatchState(new NinePatch(bitmap, abyte0, s), rect), resources);
    }

    public NinePatchDrawable(Resources resources, NinePatch ninepatch)
    {
        this(new NinePatchState(ninepatch, new Rect()), resources);
    }

    public NinePatchDrawable(Bitmap bitmap, byte abyte0[], Rect rect, String s)
    {
        this(new NinePatchState(new NinePatch(bitmap, abyte0, s), rect), ((Resources) (null)));
    }

    public NinePatchDrawable(NinePatch ninepatch)
    {
        this(new NinePatchState(ninepatch, new Rect()), ((Resources) (null)));
    }

    private NinePatchDrawable(NinePatchState ninepatchstate, Resources resources)
    {
        mOpticalInsets = Insets.NONE;
        mTargetDensity = 160;
        mBitmapWidth = -1;
        mBitmapHeight = -1;
        mNinePatchState = ninepatchstate;
        updateLocalState(resources);
    }

    NinePatchDrawable(NinePatchState ninepatchstate, Resources resources, NinePatchDrawable ninepatchdrawable)
    {
        this(ninepatchstate, resources);
    }

    private void computeBitmapSize()
    {
        NinePatch ninepatch = mNinePatchState.mNinePatch;
        if(ninepatch == null)
            return;
        int i = ninepatch.getDensity();
        int j = mTargetDensity;
        Object obj = mNinePatchState.mOpticalInsets;
        if(obj != Insets.NONE)
            mOpticalInsets = Insets.of(Drawable.scaleFromDensity(((Insets) (obj)).left, i, j, true), Drawable.scaleFromDensity(((Insets) (obj)).top, i, j, true), Drawable.scaleFromDensity(((Insets) (obj)).right, i, j, true), Drawable.scaleFromDensity(((Insets) (obj)).bottom, i, j, true));
        else
            mOpticalInsets = Insets.NONE;
        obj = mNinePatchState.mPadding;
        if(obj != null)
        {
            if(mPadding == null)
                mPadding = new Rect();
            mPadding.left = Drawable.scaleFromDensity(((Rect) (obj)).left, i, j, false);
            mPadding.top = Drawable.scaleFromDensity(((Rect) (obj)).top, i, j, false);
            mPadding.right = Drawable.scaleFromDensity(((Rect) (obj)).right, i, j, false);
            mPadding.bottom = Drawable.scaleFromDensity(((Rect) (obj)).bottom, i, j, false);
        } else
        {
            mPadding = null;
        }
        mBitmapHeight = Drawable.scaleFromDensity(ninepatch.getHeight(), i, j, true);
        mBitmapWidth = Drawable.scaleFromDensity(ninepatch.getWidth(), i, j, true);
        obj = ninepatch.getBitmap().getNinePatchInsets();
        if(obj != null)
        {
            Rect rect = ((android.graphics.NinePatch.InsetStruct) (obj)).outlineRect;
            mOutlineInsets = android.graphics.NinePatch.InsetStruct.scaleInsets(rect.left, rect.top, rect.right, rect.bottom, (float)j / (float)i);
            mOutlineRadius = Drawable.scaleFromDensity(((android.graphics.NinePatch.InsetStruct) (obj)).outlineRadius, i, j);
        } else
        {
            mOutlineInsets = null;
        }
    }

    private boolean needsMirroring()
    {
        boolean flag = true;
        if(!isAutoMirrored() || getLayoutDirection() != 1)
            flag = false;
        return flag;
    }

    private void updateLocalState(Resources resources)
    {
        NinePatchState ninepatchstate = mNinePatchState;
        if(ninepatchstate.mDither)
            setDither(ninepatchstate.mDither);
        if(resources == null && ninepatchstate.mNinePatch != null)
            mTargetDensity = ninepatchstate.mNinePatch.getDensity();
        else
            mTargetDensity = Drawable.resolveDensity(resources, mTargetDensity);
        mTintFilter = updateTintFilter(mTintFilter, ninepatchstate.mTint, ninepatchstate.mTintMode);
        computeBitmapSize();
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
        throws XmlPullParserException
    {
        Resources resources;
        NinePatchState ninepatchstate;
        int i;
        resources = typedarray.getResources();
        ninepatchstate = mNinePatchState;
        ninepatchstate.mChangingConfigurations = ninepatchstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        ninepatchstate.mThemeAttrs = typedarray.extractThemeAttrs();
        ninepatchstate.mDither = typedarray.getBoolean(1, ninepatchstate.mDither);
        i = typedarray.getResourceId(0, 0);
        if(i == 0) goto _L2; else goto _L1
_L1:
        android.graphics.BitmapFactory.Options options;
        Rect rect;
        Rect rect1;
        Bitmap bitmap;
        Bitmap bitmap1;
        options = new android.graphics.BitmapFactory.Options();
        options.inDither = ninepatchstate.mDither ^ true;
        options.inScreenDensity = resources.getDisplayMetrics().noncompatDensityDpi;
        rect = new Rect();
        rect1 = new Rect();
        bitmap = null;
        bitmap1 = bitmap;
        TypedValue typedvalue = JVM INSTR new #242 <Class TypedValue>;
        bitmap1 = bitmap;
        typedvalue.TypedValue();
        bitmap1 = bitmap;
        InputStream inputstream = resources.openRawResource(i, typedvalue);
        bitmap1 = bitmap;
        bitmap = BitmapFactory.decodeResourceStream(resources, typedvalue, inputstream, rect, options);
        bitmap1 = bitmap;
        inputstream.close();
        bitmap1 = bitmap;
_L4:
        if(bitmap1 == null)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <nine-patch> requires a valid src attribute").toString());
        if(bitmap1.getNinePatchChunk() == null)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <nine-patch> requires a valid 9-patch source image").toString());
        bitmap1.getOpticalInsets(rect1);
        ninepatchstate.mNinePatch = new NinePatch(bitmap1, bitmap1.getNinePatchChunk());
        ninepatchstate.mPadding = rect;
        ninepatchstate.mOpticalInsets = Insets.of(rect1);
_L2:
        ninepatchstate.mAutoMirrored = typedarray.getBoolean(4, ninepatchstate.mAutoMirrored);
        ninepatchstate.mBaseAlpha = typedarray.getFloat(3, ninepatchstate.mBaseAlpha);
        int j = typedarray.getInt(5, -1);
        if(j != -1)
            ninepatchstate.mTintMode = Drawable.parseTintMode(j, android.graphics.PorterDuff.Mode.SRC_IN);
        typedarray = typedarray.getColorStateList(2);
        if(typedarray != null)
            ninepatchstate.mTint = typedarray;
        return;
        IOException ioexception;
        ioexception;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        NinePatchState ninepatchstate;
        TypedArray typedarray;
        super.applyTheme(theme);
        ninepatchstate = mNinePatchState;
        if(ninepatchstate == null)
            return;
        if(ninepatchstate.mThemeAttrs == null)
            break MISSING_BLOCK_LABEL_43;
        typedarray = theme.resolveAttributes(ninepatchstate.mThemeAttrs, com.android.internal.R.styleable.NinePatchDrawable);
        updateStateFromTypedArray(typedarray);
        typedarray.recycle();
_L2:
        if(ninepatchstate.mTint != null && ninepatchstate.mTint.canApplyTheme())
            ninepatchstate.mTint = ninepatchstate.mTint.obtainForTheme(theme);
        updateLocalState(theme.getResources());
        return;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        rethrowAsRuntimeException(xmlpullparserexception);
        typedarray.recycle();
        if(true) goto _L2; else goto _L1
_L1:
        theme;
        typedarray.recycle();
        throw theme;
    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(mNinePatchState != null)
            flag = mNinePatchState.canApplyTheme();
        else
            flag = false;
        return flag;
    }

    public void clearMutated()
    {
        super.clearMutated();
        mMutated = false;
    }

    public void draw(Canvas canvas)
    {
        NinePatchState ninepatchstate = mNinePatchState;
        Rect rect = getBounds();
        int i = -1;
        boolean flag;
        int j;
        int k;
        Rect rect1;
        if(mTintFilter != null && getPaint().getColorFilter() == null)
        {
            mPaint.setColorFilter(mTintFilter);
            flag = true;
        } else
        {
            flag = false;
        }
        if(ninepatchstate.mBaseAlpha != 1.0F)
        {
            j = getPaint().getAlpha();
            mPaint.setAlpha((int)((float)j * ninepatchstate.mBaseAlpha + 0.5F));
        } else
        {
            j = -1;
        }
        if(canvas.getDensity() == 0)
            k = 1;
        else
            k = 0;
        rect1 = rect;
        if(k != 0)
        {
            i = canvas.save();
            float f = (float)mTargetDensity / (float)ninepatchstate.mNinePatch.getDensity();
            canvas.scale(f, f, rect.left, rect.top);
            if(mTempRect == null)
                mTempRect = new Rect();
            rect1 = mTempRect;
            rect1.left = rect.left;
            rect1.top = rect.top;
            rect1.right = rect.left + Math.round((float)rect.width() / f);
            rect1.bottom = rect.top + Math.round((float)rect.height() / f);
        }
        k = i;
        if(needsMirroring())
        {
            if(i < 0)
                i = canvas.save();
            canvas.scale(-1F, 1.0F, (float)(rect1.left + rect1.right) / 2.0F, (float)(rect1.top + rect1.bottom) / 2.0F);
            k = i;
        }
        ninepatchstate.mNinePatch.draw(canvas, rect1, mPaint);
        if(k >= 0)
            canvas.restoreToCount(k);
        if(flag)
            mPaint.setColorFilter(null);
        if(j >= 0)
            mPaint.setAlpha(j);
    }

    public int getAlpha()
    {
        if(mPaint == null)
            return 255;
        else
            return getPaint().getAlpha();
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mNinePatchState.getChangingConfigurations();
    }

    public Drawable.ConstantState getConstantState()
    {
        mNinePatchState.mChangingConfigurations = getChangingConfigurations();
        return mNinePatchState;
    }

    public int getIntrinsicHeight()
    {
        return mBitmapHeight;
    }

    public int getIntrinsicWidth()
    {
        return mBitmapWidth;
    }

    public int getOpacity()
    {
        byte byte0;
        if(mNinePatchState.mNinePatch.hasAlpha() || mPaint != null && mPaint.getAlpha() < 255)
            byte0 = -3;
        else
            byte0 = -1;
        return byte0;
    }

    public Insets getOpticalInsets()
    {
        Insets insets = mOpticalInsets;
        if(needsMirroring())
            return Insets.of(insets.right, insets.top, insets.left, insets.bottom);
        else
            return insets;
    }

    public void getOutline(Outline outline)
    {
        Rect rect = getBounds();
        if(rect.isEmpty())
            return;
        if(mNinePatchState != null && mOutlineInsets != null)
        {
            android.graphics.NinePatch.InsetStruct insetstruct = mNinePatchState.mNinePatch.getBitmap().getNinePatchInsets();
            if(insetstruct != null)
            {
                int i = rect.left;
                int j = mOutlineInsets.left;
                int k = rect.top;
                outline.setRoundRect(j + i, mOutlineInsets.top + k, rect.right - mOutlineInsets.right, rect.bottom - mOutlineInsets.bottom, mOutlineRadius);
                outline.setAlpha(insetstruct.outlineAlpha * ((float)getAlpha() / 255F));
                return;
            }
        }
        super.getOutline(outline);
    }

    public boolean getPadding(Rect rect)
    {
        boolean flag = false;
        if(mPadding != null)
        {
            rect.set(mPadding);
            if((rect.left | rect.top | rect.right | rect.bottom) != 0)
                flag = true;
            return flag;
        } else
        {
            return super.getPadding(rect);
        }
    }

    public Paint getPaint()
    {
        if(mPaint == null)
        {
            mPaint = new Paint();
            mPaint.setDither(false);
        }
        return mPaint;
    }

    public Region getTransparentRegion()
    {
        return mNinePatchState.mNinePatch.getTransparentRegion(getBounds());
    }

    public boolean hasFocusStateSpecified()
    {
        boolean flag;
        if(mNinePatchState.mTint != null)
            flag = mNinePatchState.mTint.hasFocusStateSpecified();
        else
            flag = false;
        return flag;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        xmlpullparser = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.NinePatchDrawable);
        updateStateFromTypedArray(xmlpullparser);
        xmlpullparser.recycle();
        updateLocalState(resources);
    }

    public boolean isAutoMirrored()
    {
        return mNinePatchState.mAutoMirrored;
    }

    public boolean isFilterBitmap()
    {
        boolean flag;
        if(mPaint != null)
            flag = getPaint().isFilterBitmap();
        else
            flag = false;
        return flag;
    }

    public boolean isStateful()
    {
        NinePatchState ninepatchstate = mNinePatchState;
        boolean flag;
        if(!super.isStateful())
        {
            if(ninepatchstate.mTint != null)
                flag = ninepatchstate.mTint.isStateful();
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
            mNinePatchState = new NinePatchState(mNinePatchState);
            mMutated = true;
        }
        return this;
    }

    protected boolean onStateChange(int ai[])
    {
        ai = mNinePatchState;
        if(((NinePatchState) (ai)).mTint != null && ((NinePatchState) (ai)).mTintMode != null)
        {
            mTintFilter = updateTintFilter(mTintFilter, ((NinePatchState) (ai)).mTint, ((NinePatchState) (ai)).mTintMode);
            return true;
        } else
        {
            return false;
        }
    }

    public void setAlpha(int i)
    {
        if(mPaint == null && i == 255)
        {
            return;
        } else
        {
            getPaint().setAlpha(i);
            invalidateSelf();
            return;
        }
    }

    public void setAutoMirrored(boolean flag)
    {
        mNinePatchState.mAutoMirrored = flag;
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mPaint == null && colorfilter == null)
        {
            return;
        } else
        {
            getPaint().setColorFilter(colorfilter);
            invalidateSelf();
            return;
        }
    }

    public void setDither(boolean flag)
    {
        if(mPaint == null && !flag)
        {
            return;
        } else
        {
            getPaint().setDither(flag);
            invalidateSelf();
            return;
        }
    }

    public void setFilterBitmap(boolean flag)
    {
        getPaint().setFilterBitmap(flag);
        invalidateSelf();
    }

    public void setTargetDensity(int i)
    {
        int j = i;
        if(i == 0)
            j = 160;
        if(mTargetDensity != j)
        {
            mTargetDensity = j;
            computeBitmapSize();
            invalidateSelf();
        }
    }

    public void setTargetDensity(Canvas canvas)
    {
        setTargetDensity(canvas.getDensity());
    }

    public void setTargetDensity(DisplayMetrics displaymetrics)
    {
        setTargetDensity(displaymetrics.densityDpi);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mNinePatchState.mTint = colorstatelist;
        mTintFilter = updateTintFilter(mTintFilter, colorstatelist, mNinePatchState.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mNinePatchState.mTintMode = mode;
        mTintFilter = updateTintFilter(mTintFilter, mNinePatchState.mTint, mode);
        invalidateSelf();
    }

    private static final boolean DEFAULT_DITHER = false;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private boolean mMutated;
    private NinePatchState mNinePatchState;
    private Insets mOpticalInsets;
    private Rect mOutlineInsets;
    private float mOutlineRadius;
    private Rect mPadding;
    private Paint mPaint;
    private int mTargetDensity;
    private Rect mTempRect;
    private PorterDuffColorFilter mTintFilter;
}
