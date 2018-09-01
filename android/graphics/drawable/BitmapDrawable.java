// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.*;
import android.view.Gravity;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class BitmapDrawable extends Drawable
{
    static final class BitmapState extends Drawable.ConstantState
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
            return new BitmapDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new BitmapDrawable(this, resources, null);
        }

        boolean mAutoMirrored;
        float mBaseAlpha;
        Bitmap mBitmap;
        int mChangingConfigurations;
        int mGravity;
        final Paint mPaint;
        boolean mRebuildShader;
        int mSrcDensityOverride;
        int mTargetDensity;
        int mThemeAttrs[];
        android.graphics.Shader.TileMode mTileModeX;
        android.graphics.Shader.TileMode mTileModeY;
        ColorStateList mTint;
        android.graphics.PorterDuff.Mode mTintMode;

        BitmapState(Bitmap bitmap)
        {
            mThemeAttrs = null;
            mBitmap = null;
            mTint = null;
            mTintMode = BitmapDrawable.DEFAULT_TINT_MODE;
            mGravity = 119;
            mBaseAlpha = 1.0F;
            mTileModeX = null;
            mTileModeY = null;
            mSrcDensityOverride = 0;
            mTargetDensity = 160;
            mAutoMirrored = false;
            mBitmap = bitmap;
            mPaint = new Paint(6);
        }

        BitmapState(BitmapState bitmapstate)
        {
            mThemeAttrs = null;
            mBitmap = null;
            mTint = null;
            mTintMode = BitmapDrawable.DEFAULT_TINT_MODE;
            mGravity = 119;
            mBaseAlpha = 1.0F;
            mTileModeX = null;
            mTileModeY = null;
            mSrcDensityOverride = 0;
            mTargetDensity = 160;
            mAutoMirrored = false;
            mBitmap = bitmapstate.mBitmap;
            mTint = bitmapstate.mTint;
            mTintMode = bitmapstate.mTintMode;
            mThemeAttrs = bitmapstate.mThemeAttrs;
            mChangingConfigurations = bitmapstate.mChangingConfigurations;
            mGravity = bitmapstate.mGravity;
            mTileModeX = bitmapstate.mTileModeX;
            mTileModeY = bitmapstate.mTileModeY;
            mSrcDensityOverride = bitmapstate.mSrcDensityOverride;
            mTargetDensity = bitmapstate.mTargetDensity;
            mBaseAlpha = bitmapstate.mBaseAlpha;
            mPaint = new Paint(bitmapstate.mPaint);
            mRebuildShader = bitmapstate.mRebuildShader;
            mAutoMirrored = bitmapstate.mAutoMirrored;
        }
    }


    public BitmapDrawable()
    {
        mDstRect = new Rect();
        mTargetDensity = 160;
        mDstRectAndInsetsDirty = true;
        mOpticalInsets = Insets.NONE;
        mBitmapState = new BitmapState((Bitmap)null);
    }

    public BitmapDrawable(Resources resources)
    {
        mDstRect = new Rect();
        mTargetDensity = 160;
        mDstRectAndInsetsDirty = true;
        mOpticalInsets = Insets.NONE;
        mBitmapState = new BitmapState((Bitmap)null);
        mBitmapState.mTargetDensity = mTargetDensity;
    }

    public BitmapDrawable(Resources resources, Bitmap bitmap)
    {
        this(new BitmapState(bitmap), resources);
        mBitmapState.mTargetDensity = mTargetDensity;
    }

    public BitmapDrawable(Resources resources, InputStream inputstream)
    {
        this(new BitmapState(BitmapFactory.decodeStream(inputstream)), ((Resources) (null)));
        mBitmapState.mTargetDensity = mTargetDensity;
        if(mBitmapState.mBitmap == null)
            Log.w("BitmapDrawable", (new StringBuilder()).append("BitmapDrawable cannot decode ").append(inputstream).toString());
    }

    public BitmapDrawable(Resources resources, String s)
    {
        this(new BitmapState(BitmapFactory.decodeFile(s)), ((Resources) (null)));
        mBitmapState.mTargetDensity = mTargetDensity;
        if(mBitmapState.mBitmap == null)
            Log.w("BitmapDrawable", (new StringBuilder()).append("BitmapDrawable cannot decode ").append(s).toString());
    }

    public BitmapDrawable(Bitmap bitmap)
    {
        this(new BitmapState(bitmap), ((Resources) (null)));
    }

    private BitmapDrawable(BitmapState bitmapstate, Resources resources)
    {
        mDstRect = new Rect();
        mTargetDensity = 160;
        mDstRectAndInsetsDirty = true;
        mOpticalInsets = Insets.NONE;
        mBitmapState = bitmapstate;
        updateLocalState(resources);
    }

    BitmapDrawable(BitmapState bitmapstate, Resources resources, BitmapDrawable bitmapdrawable)
    {
        this(bitmapstate, resources);
    }

    public BitmapDrawable(InputStream inputstream)
    {
        this(new BitmapState(BitmapFactory.decodeStream(inputstream)), ((Resources) (null)));
        if(mBitmapState.mBitmap == null)
            Log.w("BitmapDrawable", (new StringBuilder()).append("BitmapDrawable cannot decode ").append(inputstream).toString());
    }

    public BitmapDrawable(String s)
    {
        this(new BitmapState(BitmapFactory.decodeFile(s)), ((Resources) (null)));
        if(mBitmapState.mBitmap == null)
            Log.w("BitmapDrawable", (new StringBuilder()).append("BitmapDrawable cannot decode ").append(s).toString());
    }

    private void computeBitmapSize()
    {
        Bitmap bitmap = mBitmapState.mBitmap;
        if(bitmap != null)
        {
            mBitmapWidth = bitmap.getScaledWidth(mTargetDensity);
            mBitmapHeight = bitmap.getScaledHeight(mTargetDensity);
        } else
        {
            mBitmapHeight = -1;
            mBitmapWidth = -1;
        }
    }

    private Matrix getOrCreateMirrorMatrix()
    {
        if(mMirrorMatrix == null)
            mMirrorMatrix = new Matrix();
        return mMirrorMatrix;
    }

    private boolean needMirroring()
    {
        boolean flag = true;
        if(!isAutoMirrored() || getLayoutDirection() != 1)
            flag = false;
        return flag;
    }

    private static android.graphics.Shader.TileMode parseTileMode(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return android.graphics.Shader.TileMode.CLAMP;

        case 1: // '\001'
            return android.graphics.Shader.TileMode.REPEAT;

        case 2: // '\002'
            return android.graphics.Shader.TileMode.MIRROR;
        }
    }

    private void updateDstRectAndInsetsIfDirty()
    {
        if(mDstRectAndInsetsDirty)
            if(mBitmapState.mTileModeX == null && mBitmapState.mTileModeY == null)
            {
                Rect rect = getBounds();
                int i = getLayoutDirection();
                Gravity.apply(mBitmapState.mGravity, mBitmapWidth, mBitmapHeight, rect, mDstRect, i);
                mOpticalInsets = Insets.of(mDstRect.left - rect.left, mDstRect.top - rect.top, rect.right - mDstRect.right, rect.bottom - mDstRect.bottom);
            } else
            {
                copyBounds(mDstRect);
                mOpticalInsets = Insets.NONE;
            }
        mDstRectAndInsetsDirty = false;
    }

    private void updateLocalState(Resources resources)
    {
        mTargetDensity = resolveDensity(resources, mBitmapState.mTargetDensity);
        mTintFilter = updateTintFilter(mTintFilter, mBitmapState.mTint, mBitmapState.mTintMode);
        computeBitmapSize();
    }

    private void updateShaderMatrix(Bitmap bitmap, Paint paint, Shader shader, boolean flag)
    {
        int i = bitmap.getDensity();
        int j = mTargetDensity;
        boolean flag1;
        if(i != 0 && i != j)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 || flag)
        {
            bitmap = getOrCreateMirrorMatrix();
            bitmap.reset();
            if(flag)
            {
                bitmap.setTranslate(mDstRect.right - mDstRect.left, 0.0F);
                bitmap.setScale(-1F, 1.0F);
            }
            if(flag1)
            {
                float f = (float)j / (float)i;
                bitmap.postScale(f, f);
            }
            shader.setLocalMatrix(bitmap);
        } else
        {
            mMirrorMatrix = null;
            shader.setLocalMatrix(Matrix.IDENTITY_MATRIX);
        }
        paint.setShader(shader);
    }

    private void updateStateFromTypedArray(TypedArray typedarray, int i)
        throws XmlPullParserException
    {
        Object obj;
        BitmapState bitmapstate;
        int j;
        obj = typedarray.getResources();
        bitmapstate = mBitmapState;
        bitmapstate.mChangingConfigurations = bitmapstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        bitmapstate.mThemeAttrs = typedarray.extractThemeAttrs();
        bitmapstate.mSrcDensityOverride = i;
        bitmapstate.mTargetDensity = Drawable.resolveDensity(((Resources) (obj)), 0);
        j = typedarray.getResourceId(1, 0);
        if(j == 0) goto _L2; else goto _L1
_L1:
        Object obj1;
        Object obj2;
        Object obj4;
        Object obj5;
        TypedValue typedvalue = new TypedValue();
        ((Resources) (obj)).getValueForDensity(j, i, typedvalue, true);
        Object obj3;
        InputStream inputstream;
        if(i > 0 && typedvalue.density > 0 && typedvalue.density != 65535)
            if(typedvalue.density == i)
                typedvalue.density = ((Resources) (obj)).getDisplayMetrics().densityDpi;
            else
                typedvalue.density = (typedvalue.density * ((Resources) (obj)).getDisplayMetrics().densityDpi) / i;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        inputstream = ((Resources) (obj)).openRawResource(j, typedvalue);
        obj5 = inputstream;
        obj4 = inputstream;
        obj = BitmapFactory.decodeResourceStream(((Resources) (obj)), typedvalue, inputstream, null, null);
        obj4 = obj;
        obj = obj3;
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_194;
        obj5 = obj4;
        inputstream.close();
        obj = obj3;
        break MISSING_BLOCK_LABEL_194;
        obj;
          goto _L3
        obj4;
        throw obj4;
        Exception exception;
        exception;
        obj = obj5;
_L7:
        obj2 = obj4;
        if(obj == null)
            break MISSING_BLOCK_LABEL_303;
        obj5 = obj1;
        ((InputStream) (obj)).close();
        obj2 = obj4;
_L5:
        if(obj2 == null)
            break; /* Loop/switch isn't completed */
        obj5 = obj1;
        throw obj2;
        obj;
        if(obj4 == null)
        {
            obj2 = obj;
            continue; /* Loop/switch isn't completed */
        }
        obj2 = obj4;
        if(obj4 == obj)
            continue; /* Loop/switch isn't completed */
        obj5 = obj1;
        ((Throwable) (obj4)).addSuppressed(((Throwable) (obj)));
        obj2 = obj4;
        if(true) goto _L5; else goto _L4
_L4:
        obj5 = obj1;
        throw exception;
_L3:
        obj5 = obj4;
        if(obj != null)
        {
            obj5 = obj4;
            try
            {
                throw obj;
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
        }
        if(obj5 == null)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <bitmap> requires a valid 'src' attribute").toString());
        bitmapstate.mBitmap = ((Bitmap) (obj5));
_L2:
        Object obj6;
        boolean flag;
        if(bitmapstate.mBitmap != null)
            flag = bitmapstate.mBitmap.hasMipMap();
        else
            flag = false;
        setMipMap(typedarray.getBoolean(8, flag));
        bitmapstate.mAutoMirrored = typedarray.getBoolean(9, bitmapstate.mAutoMirrored);
        bitmapstate.mBaseAlpha = typedarray.getFloat(7, bitmapstate.mBaseAlpha);
        i = typedarray.getInt(10, -1);
        if(i != -1)
            bitmapstate.mTintMode = Drawable.parseTintMode(i, android.graphics.PorterDuff.Mode.SRC_IN);
        obj6 = typedarray.getColorStateList(5);
        if(obj6 != null)
            bitmapstate.mTint = ((ColorStateList) (obj6));
        obj6 = mBitmapState.mPaint;
        ((Paint) (obj6)).setAntiAlias(typedarray.getBoolean(2, ((Paint) (obj6)).isAntiAlias()));
        ((Paint) (obj6)).setFilterBitmap(typedarray.getBoolean(3, ((Paint) (obj6)).isFilterBitmap()));
        ((Paint) (obj6)).setDither(typedarray.getBoolean(4, ((Paint) (obj6)).isDither()));
        setGravity(typedarray.getInt(0, bitmapstate.mGravity));
        i = typedarray.getInt(6, -2);
        if(i != -2)
        {
            android.graphics.Shader.TileMode tilemode = parseTileMode(i);
            setTileModeXY(tilemode, tilemode);
        }
        i = typedarray.getInt(11, -2);
        if(i != -2)
            setTileModeX(parseTileMode(i));
        i = typedarray.getInt(12, -2);
        if(i != -2)
            setTileModeY(parseTileMode(i));
        return;
        exception;
        obj = obj4;
        obj4 = obj2;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private void verifyRequiredAttributes(TypedArray typedarray)
        throws XmlPullParserException
    {
        BitmapState bitmapstate = mBitmapState;
        if(bitmapstate.mBitmap == null && (bitmapstate.mThemeAttrs == null || bitmapstate.mThemeAttrs[1] == 0))
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <bitmap> requires a valid 'src' attribute").toString());
        else
            return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        BitmapState bitmapstate;
        TypedArray typedarray;
        super.applyTheme(theme);
        bitmapstate = mBitmapState;
        if(bitmapstate == null)
            return;
        if(bitmapstate.mThemeAttrs == null)
            break MISSING_BLOCK_LABEL_47;
        typedarray = theme.resolveAttributes(bitmapstate.mThemeAttrs, com.android.internal.R.styleable.BitmapDrawable);
        updateStateFromTypedArray(typedarray, bitmapstate.mSrcDensityOverride);
        typedarray.recycle();
_L2:
        if(bitmapstate.mTint != null && bitmapstate.mTint.canApplyTheme())
            bitmapstate.mTint = bitmapstate.mTint.obtainForTheme(theme);
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
        if(mBitmapState != null)
            flag = mBitmapState.canApplyTheme();
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
        Bitmap bitmap = mBitmapState.mBitmap;
        if(bitmap == null)
            return;
        BitmapState bitmapstate = mBitmapState;
        Paint paint = bitmapstate.mPaint;
        Object obj;
        int i;
        boolean flag;
        boolean flag1;
        if(bitmapstate.mRebuildShader)
        {
            android.graphics.Shader.TileMode tilemode = bitmapstate.mTileModeX;
            android.graphics.Shader.TileMode tilemode1 = bitmapstate.mTileModeY;
            if(tilemode == null && tilemode1 == null)
            {
                paint.setShader(null);
            } else
            {
                obj = tilemode;
                if(tilemode == null)
                    obj = android.graphics.Shader.TileMode.CLAMP;
                tilemode = tilemode1;
                if(tilemode1 == null)
                    tilemode = android.graphics.Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(bitmap, ((android.graphics.Shader.TileMode) (obj)), tilemode));
            }
            bitmapstate.mRebuildShader = false;
        }
        if(bitmapstate.mBaseAlpha != 1.0F)
        {
            obj = getPaint();
            i = ((Paint) (obj)).getAlpha();
            ((Paint) (obj)).setAlpha((int)((float)i * bitmapstate.mBaseAlpha + 0.5F));
        } else
        {
            i = -1;
        }
        if(mTintFilter != null && paint.getColorFilter() == null)
        {
            paint.setColorFilter(mTintFilter);
            flag = true;
        } else
        {
            flag = false;
        }
        updateDstRectAndInsetsIfDirty();
        obj = paint.getShader();
        flag1 = needMirroring();
        if(obj == null)
        {
            if(flag1)
            {
                canvas.save();
                canvas.translate(mDstRect.right - mDstRect.left, 0.0F);
                canvas.scale(-1F, 1.0F);
            }
            canvas.drawBitmap(bitmap, null, mDstRect, paint);
            if(flag1)
                canvas.restore();
        } else
        {
            updateShaderMatrix(bitmap, paint, ((Shader) (obj)), flag1);
            canvas.drawRect(mDstRect, paint);
        }
        if(flag)
            paint.setColorFilter(null);
        if(i >= 0)
            paint.setAlpha(i);
    }

    public int getAlpha()
    {
        return mBitmapState.mPaint.getAlpha();
    }

    public final Bitmap getBitmap()
    {
        return mBitmapState.mBitmap;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mBitmapState.getChangingConfigurations();
    }

    public ColorFilter getColorFilter()
    {
        return mBitmapState.mPaint.getColorFilter();
    }

    public final Drawable.ConstantState getConstantState()
    {
        BitmapState bitmapstate = mBitmapState;
        bitmapstate.mChangingConfigurations = bitmapstate.mChangingConfigurations | getChangingConfigurations();
        return mBitmapState;
    }

    public int getGravity()
    {
        return mBitmapState.mGravity;
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
        byte byte0 = -3;
        if(mBitmapState.mGravity != 119)
            return -3;
        Bitmap bitmap = mBitmapState.mBitmap;
        byte byte1 = byte0;
        if(bitmap != null)
        {
            byte1 = byte0;
            if(!bitmap.hasAlpha())
                if(mBitmapState.mPaint.getAlpha() < 255)
                    byte1 = byte0;
                else
                    byte1 = -1;
        }
        return byte1;
    }

    public Insets getOpticalInsets()
    {
        updateDstRectAndInsetsIfDirty();
        return mOpticalInsets;
    }

    public void getOutline(Outline outline)
    {
        updateDstRectAndInsetsIfDirty();
        outline.setRect(mDstRect);
        boolean flag;
        float f;
        if(mBitmapState.mBitmap != null)
            flag = mBitmapState.mBitmap.hasAlpha() ^ true;
        else
            flag = false;
        if(flag)
            f = (float)getAlpha() / 255F;
        else
            f = 0.0F;
        outline.setAlpha(f);
    }

    public final Paint getPaint()
    {
        return mBitmapState.mPaint;
    }

    public android.graphics.Shader.TileMode getTileModeX()
    {
        return mBitmapState.mTileModeX;
    }

    public android.graphics.Shader.TileMode getTileModeY()
    {
        return mBitmapState.mTileModeY;
    }

    public ColorStateList getTint()
    {
        return mBitmapState.mTint;
    }

    public android.graphics.PorterDuff.Mode getTintMode()
    {
        return mBitmapState.mTintMode;
    }

    public boolean hasAntiAlias()
    {
        return mBitmapState.mPaint.isAntiAlias();
    }

    public boolean hasFocusStateSpecified()
    {
        boolean flag;
        if(mBitmapState.mTint != null)
            flag = mBitmapState.mTint.hasFocusStateSpecified();
        else
            flag = false;
        return flag;
    }

    public boolean hasMipMap()
    {
        boolean flag;
        if(mBitmapState.mBitmap != null)
            flag = mBitmapState.mBitmap.hasMipMap();
        else
            flag = false;
        return flag;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        xmlpullparser = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.BitmapDrawable);
        updateStateFromTypedArray(xmlpullparser, mSrcDensityOverride);
        verifyRequiredAttributes(xmlpullparser);
        xmlpullparser.recycle();
        updateLocalState(resources);
    }

    public final boolean isAutoMirrored()
    {
        return mBitmapState.mAutoMirrored;
    }

    public boolean isFilterBitmap()
    {
        return mBitmapState.mPaint.isFilterBitmap();
    }

    public boolean isStateful()
    {
        boolean flag;
        if(mBitmapState.mTint == null || !mBitmapState.mTint.isStateful())
            flag = super.isStateful();
        else
            flag = true;
        return flag;
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mBitmapState = new BitmapState(mBitmapState);
            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        mDstRectAndInsetsDirty = true;
        rect = mBitmapState.mBitmap;
        Shader shader = mBitmapState.mPaint.getShader();
        if(rect != null && shader != null)
            updateShaderMatrix(rect, mBitmapState.mPaint, shader, needMirroring());
    }

    protected boolean onStateChange(int ai[])
    {
        ai = mBitmapState;
        if(((BitmapState) (ai)).mTint != null && ((BitmapState) (ai)).mTintMode != null)
        {
            mTintFilter = updateTintFilter(mTintFilter, ((BitmapState) (ai)).mTint, ((BitmapState) (ai)).mTintMode);
            return true;
        } else
        {
            return false;
        }
    }

    public void setAlpha(int i)
    {
        if(i != mBitmapState.mPaint.getAlpha())
        {
            mBitmapState.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setAntiAlias(boolean flag)
    {
        mBitmapState.mPaint.setAntiAlias(flag);
        invalidateSelf();
    }

    public void setAutoMirrored(boolean flag)
    {
        if(mBitmapState.mAutoMirrored != flag)
        {
            mBitmapState.mAutoMirrored = flag;
            invalidateSelf();
        }
    }

    public void setBitmap(Bitmap bitmap)
    {
        if(mBitmapState.mBitmap != bitmap)
        {
            mBitmapState.mBitmap = bitmap;
            computeBitmapSize();
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mBitmapState.mPaint.setColorFilter(colorfilter);
        invalidateSelf();
    }

    public void setDither(boolean flag)
    {
        mBitmapState.mPaint.setDither(flag);
        invalidateSelf();
    }

    public void setFilterBitmap(boolean flag)
    {
        mBitmapState.mPaint.setFilterBitmap(flag);
        invalidateSelf();
    }

    public void setGravity(int i)
    {
        if(mBitmapState.mGravity != i)
        {
            mBitmapState.mGravity = i;
            mDstRectAndInsetsDirty = true;
            invalidateSelf();
        }
    }

    public void setMipMap(boolean flag)
    {
        if(mBitmapState.mBitmap != null)
        {
            mBitmapState.mBitmap.setHasMipMap(flag);
            invalidateSelf();
        }
    }

    public void setTargetDensity(int i)
    {
        if(mTargetDensity != i)
        {
            int j = i;
            if(i == 0)
                j = 160;
            mTargetDensity = j;
            if(mBitmapState.mBitmap != null)
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

    public void setTileModeX(android.graphics.Shader.TileMode tilemode)
    {
        setTileModeXY(tilemode, mBitmapState.mTileModeY);
    }

    public void setTileModeXY(android.graphics.Shader.TileMode tilemode, android.graphics.Shader.TileMode tilemode1)
    {
        BitmapState bitmapstate = mBitmapState;
        if(bitmapstate.mTileModeX != tilemode || bitmapstate.mTileModeY != tilemode1)
        {
            bitmapstate.mTileModeX = tilemode;
            bitmapstate.mTileModeY = tilemode1;
            bitmapstate.mRebuildShader = true;
            mDstRectAndInsetsDirty = true;
            invalidateSelf();
        }
    }

    public final void setTileModeY(android.graphics.Shader.TileMode tilemode)
    {
        setTileModeXY(mBitmapState.mTileModeX, tilemode);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        BitmapState bitmapstate = mBitmapState;
        if(bitmapstate.mTint != colorstatelist)
        {
            bitmapstate.mTint = colorstatelist;
            mTintFilter = updateTintFilter(mTintFilter, colorstatelist, mBitmapState.mTintMode);
            invalidateSelf();
        }
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        BitmapState bitmapstate = mBitmapState;
        if(bitmapstate.mTintMode != mode)
        {
            bitmapstate.mTintMode = mode;
            mTintFilter = updateTintFilter(mTintFilter, mBitmapState.mTint, mode);
            invalidateSelf();
        }
    }

    public void setXfermode(Xfermode xfermode)
    {
        mBitmapState.mPaint.setXfermode(xfermode);
        invalidateSelf();
    }

    private static final int DEFAULT_PAINT_FLAGS = 6;
    private static final int TILE_MODE_CLAMP = 0;
    private static final int TILE_MODE_DISABLED = -1;
    private static final int TILE_MODE_MIRROR = 2;
    private static final int TILE_MODE_REPEAT = 1;
    private static final int TILE_MODE_UNDEFINED = -2;
    private int mBitmapHeight;
    private BitmapState mBitmapState;
    private int mBitmapWidth;
    private final Rect mDstRect;
    private boolean mDstRectAndInsetsDirty;
    private Matrix mMirrorMatrix;
    private boolean mMutated;
    private Insets mOpticalInsets;
    private int mTargetDensity;
    private PorterDuffColorFilter mTintFilter;
}
