// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            LayerDrawable, Drawable, RippleForeground, RippleBackground

public class RippleDrawable extends LayerDrawable
{
    static class RippleState extends LayerDrawable.LayerState
    {

        private void applyDensityScaling(int i, int j)
        {
            if(mMaxRadius != -1)
                mMaxRadius = Drawable.scaleFromDensity(mMaxRadius, i, j, true);
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mTouchThemeAttrs == null && (mColor == null || !mColor.canApplyTheme()))
                flag = super.canApplyTheme();
            else
                flag = true;
            return flag;
        }

        public int getChangingConfigurations()
        {
            int i = super.getChangingConfigurations();
            int j;
            if(mColor != null)
                j = mColor.getChangingConfigurations();
            else
                j = 0;
            return j | i;
        }

        public Drawable newDrawable()
        {
            return new RippleDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new RippleDrawable(this, resources, null);
        }

        protected void onDensityChanged(int i, int j)
        {
            super.onDensityChanged(i, j);
            applyDensityScaling(i, j);
        }

        ColorStateList mColor;
        int mMaxRadius;
        int mTouchThemeAttrs[];

        public RippleState(LayerDrawable.LayerState layerstate, RippleDrawable rippledrawable, Resources resources)
        {
            super(layerstate, rippledrawable, resources);
            mColor = ColorStateList.valueOf(-65281);
            mMaxRadius = -1;
            if(layerstate != null && (layerstate instanceof RippleState))
            {
                rippledrawable = (RippleState)layerstate;
                mTouchThemeAttrs = ((RippleState) (rippledrawable)).mTouchThemeAttrs;
                mColor = ((RippleState) (rippledrawable)).mColor;
                mMaxRadius = ((RippleState) (rippledrawable)).mMaxRadius;
                if(((RippleState) (rippledrawable)).mDensity != mDensity)
                    applyDensityScaling(layerstate.mDensity, mDensity);
            }
        }
    }


    RippleDrawable()
    {
        this(new RippleState(null, null, null), null);
    }

    public RippleDrawable(ColorStateList colorstatelist, Drawable drawable, Drawable drawable1)
    {
        this(new RippleState(null, null, null), null);
        if(colorstatelist == null)
            throw new IllegalArgumentException("RippleDrawable requires a non-null color");
        if(drawable != null)
            addLayer(drawable, null, 0, 0, 0, 0, 0);
        if(drawable1 != null)
            addLayer(drawable1, null, 0x102002e, 0, 0, 0, 0);
        setColor(colorstatelist);
        ensurePadding();
        refreshPadding();
        updateLocalState();
    }

    private RippleDrawable(RippleState ripplestate, Resources resources)
    {
        mTempRect = new Rect();
        mHotspotBounds = new Rect();
        mDrawingBounds = new Rect();
        mDirtyBounds = new Rect();
        mExitingRipplesCount = 0;
        mState = new RippleState(ripplestate, this, resources);
        mLayerState = mState;
        mDensity = Drawable.resolveDensity(resources, mState.mDensity);
        if(mState.mNumChildren > 0)
        {
            ensurePadding();
            refreshPadding();
        }
        updateLocalState();
    }

    RippleDrawable(RippleState ripplestate, Resources resources, RippleDrawable rippledrawable)
    {
        this(ripplestate, resources);
    }

    private void cancelExitingRipples()
    {
        int i = mExitingRipplesCount;
        RippleForeground arippleforeground[] = mExitingRipples;
        for(int j = 0; j < i; j++)
            arippleforeground[j].end();

        if(arippleforeground != null)
            Arrays.fill(arippleforeground, 0, i, null);
        mExitingRipplesCount = 0;
        invalidateSelf(false);
    }

    private void clearHotspots()
    {
        if(mRipple != null)
        {
            mRipple.end();
            mRipple = null;
            mRippleActive = false;
        }
        if(mBackground != null)
        {
            mBackground.end();
            mBackground = null;
            mBackgroundActive = false;
        }
        cancelExitingRipples();
    }

    private void drawBackgroundAndRipples(Canvas canvas)
    {
        RippleForeground rippleforeground = mRipple;
        RippleBackground ripplebackground = mBackground;
        int i = mExitingRipplesCount;
        if(rippleforeground == null && i <= 0 && (ripplebackground == null || ripplebackground.isVisible() ^ true))
            return;
        float f = mHotspotBounds.exactCenterX();
        float f1 = mHotspotBounds.exactCenterY();
        canvas.translate(f, f1);
        updateMaskShaderIfNeeded();
        if(mMaskShader != null)
        {
            Rect rect = getBounds();
            mMaskMatrix.setTranslate((float)rect.left - f, (float)rect.top - f1);
            mMaskShader.setLocalMatrix(mMaskMatrix);
        }
        int j = mState.mColor.getColorForState(getState(), 0xff000000);
        int k = Color.alpha(j) / 2 << 24;
        Paint paint = getRipplePaint();
        if(mMaskColorFilter != null)
        {
            mMaskColorFilter.setColor(j | 0xff000000);
            paint.setColor(k);
            paint.setColorFilter(mMaskColorFilter);
            paint.setShader(mMaskShader);
        } else
        {
            paint.setColor(0xffffff & j | k);
            paint.setColorFilter(null);
            paint.setShader(null);
        }
        if(ripplebackground != null && ripplebackground.isVisible())
            ripplebackground.draw(canvas, paint);
        if(i > 0)
        {
            RippleForeground arippleforeground[] = mExitingRipples;
            for(k = 0; k < i; k++)
                arippleforeground[k].draw(canvas, paint);

        }
        if(rippleforeground != null)
            rippleforeground.draw(canvas, paint);
        canvas.translate(-f, -f1);
    }

    private void drawContent(Canvas canvas)
    {
        LayerDrawable.ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
            if(achilddrawable[j].mId != 0x102002e)
                achilddrawable[j].mDrawable.draw(canvas);

    }

    private void drawMask(Canvas canvas)
    {
        mMask.draw(canvas);
    }

    private int getMaskType()
    {
        if(mRipple == null && mExitingRipplesCount <= 0 && (mBackground == null || mBackground.isVisible() ^ true))
            return -1;
        if(mMask != null)
            return mMask.getOpacity() != -1 ? 2 : 0;
        LayerDrawable.ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
            if(achilddrawable[j].mDrawable.getOpacity() != -1)
                return 1;

        return 0;
    }

    private Paint getRipplePaint()
    {
        if(mRipplePaint == null)
        {
            mRipplePaint = new Paint();
            mRipplePaint.setAntiAlias(true);
            mRipplePaint.setStyle(android.graphics.Paint.Style.FILL);
        }
        return mRipplePaint;
    }

    private boolean isBounded()
    {
        boolean flag = false;
        if(getNumberOfLayers() > 0)
            flag = true;
        return flag;
    }

    private void onHotspotBoundsChanged()
    {
        int i = mExitingRipplesCount;
        RippleForeground arippleforeground[] = mExitingRipples;
        for(int j = 0; j < i; j++)
            arippleforeground[j].onHotspotBoundsChanged();

        if(mRipple != null)
            mRipple.onHotspotBoundsChanged();
        if(mBackground != null)
            mBackground.onHotspotBoundsChanged();
    }

    private void pruneRipples()
    {
        RippleForeground arippleforeground[] = mExitingRipples;
        int i = mExitingRipplesCount;
        int j = 0;
        int l = 0;
        for(; j < i; j++)
            if(!arippleforeground[j].hasFinishedExit())
            {
                int i1 = l + 1;
                arippleforeground[l] = arippleforeground[j];
                l = i1;
            }

        for(int k = l; k < i; k++)
            arippleforeground[k] = null;

        mExitingRipplesCount = l;
    }

    private void setBackgroundActive(boolean flag, boolean flag1)
    {
        if(mBackgroundActive != flag)
        {
            mBackgroundActive = flag;
            if(flag)
                tryBackgroundEnter(flag1);
            else
                tryBackgroundExit();
        }
    }

    private void setRippleActive(boolean flag)
    {
        if(mRippleActive != flag)
        {
            mRippleActive = flag;
            if(flag)
                tryRippleEnter();
            else
                tryRippleExit();
        }
    }

    private void tryBackgroundEnter(boolean flag)
    {
        if(mBackground == null)
        {
            boolean flag1 = isBounded();
            mBackground = new RippleBackground(this, mHotspotBounds, flag1, mForceSoftware);
        }
        mBackground.setup(mState.mMaxRadius, mDensity);
        mBackground.enter(flag);
    }

    private void tryBackgroundExit()
    {
        if(mBackground != null)
            mBackground.exit();
    }

    private void tryRippleEnter()
    {
        if(mExitingRipplesCount >= 10)
            return;
        if(mRipple == null)
        {
            float f;
            float f1;
            boolean flag;
            if(mHasPending)
            {
                mHasPending = false;
                f = mPendingX;
                f1 = mPendingY;
            } else
            {
                f = mHotspotBounds.exactCenterX();
                f1 = mHotspotBounds.exactCenterY();
            }
            flag = isBounded();
            mRipple = new RippleForeground(this, mHotspotBounds, f, f1, flag, mForceSoftware);
        }
        mRipple.setup(mState.mMaxRadius, mDensity);
        mRipple.enter(false);
    }

    private void tryRippleExit()
    {
        if(mRipple != null)
        {
            if(mExitingRipples == null)
                mExitingRipples = new RippleForeground[10];
            RippleForeground arippleforeground[] = mExitingRipples;
            int i = mExitingRipplesCount;
            mExitingRipplesCount = i + 1;
            arippleforeground[i] = mRipple;
            mRipple.exit();
            mRipple = null;
        }
    }

    private void updateLocalState()
    {
        mMask = findDrawableByLayerId(0x102002e);
    }

    private void updateMaskShaderIfNeeded()
    {
        int i;
        Rect rect;
        if(mHasValidMask)
            return;
        i = getMaskType();
        if(i == -1)
            return;
        mHasValidMask = true;
        rect = getBounds();
        if(i == 0 || rect.isEmpty())
        {
            if(mMaskBuffer != null)
            {
                mMaskBuffer.recycle();
                mMaskBuffer = null;
                mMaskShader = null;
                mMaskCanvas = null;
            }
            mMaskMatrix = null;
            mMaskColorFilter = null;
            return;
        }
        break MISSING_BLOCK_LABEL_80;
        int j;
        int k;
        if(mMaskBuffer == null || mMaskBuffer.getWidth() != rect.width() || mMaskBuffer.getHeight() != rect.height())
        {
            if(mMaskBuffer != null)
                mMaskBuffer.recycle();
            mMaskBuffer = Bitmap.createBitmap(rect.width(), rect.height(), android.graphics.Bitmap.Config.ALPHA_8);
            mMaskShader = new BitmapShader(mMaskBuffer, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
            mMaskCanvas = new Canvas(mMaskBuffer);
        } else
        {
            mMaskBuffer.eraseColor(0);
        }
        if(mMaskMatrix == null)
            mMaskMatrix = new Matrix();
        else
            mMaskMatrix.reset();
        if(mMaskColorFilter == null)
            mMaskColorFilter = new PorterDuffColorFilter(0, android.graphics.PorterDuff.Mode.SRC_IN);
        j = rect.left;
        k = rect.top;
        mMaskCanvas.translate(-j, -k);
        if(i == 2)
            drawMask(mMaskCanvas);
        else
        if(i == 1)
            drawContent(mMaskCanvas);
        mMaskCanvas.translate(j, k);
        return;
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
        throws XmlPullParserException
    {
        Object obj = mState;
        obj.mChangingConfigurations = ((RippleState) (obj)).mChangingConfigurations | typedarray.getChangingConfigurations();
        obj.mTouchThemeAttrs = typedarray.extractThemeAttrs();
        obj = typedarray.getColorStateList(0);
        if(obj != null)
            mState.mColor = ((ColorStateList) (obj));
        mState.mMaxRadius = typedarray.getDimensionPixelSize(1, mState.mMaxRadius);
    }

    private void verifyRequiredAttributes(TypedArray typedarray)
        throws XmlPullParserException
    {
        if(mState.mColor == null && (mState.mTouchThemeAttrs == null || mState.mTouchThemeAttrs[0] == 0))
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append(": <ripple> requires a valid color attribute").toString());
        else
            return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        RippleState ripplestate;
        TypedArray typedarray;
        super.applyTheme(theme);
        ripplestate = mState;
        if(ripplestate == null)
            return;
        if(ripplestate.mTouchThemeAttrs == null)
            break MISSING_BLOCK_LABEL_48;
        typedarray = theme.resolveAttributes(ripplestate.mTouchThemeAttrs, com.android.internal.R.styleable.RippleDrawable);
        updateStateFromTypedArray(typedarray);
        verifyRequiredAttributes(typedarray);
        typedarray.recycle();
_L2:
        if(ripplestate.mColor != null && ripplestate.mColor.canApplyTheme())
            ripplestate.mColor = ripplestate.mColor.obtainForTheme(theme);
        updateLocalState();
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
        if(mState == null || !mState.canApplyTheme())
            flag = super.canApplyTheme();
        else
            flag = true;
        return flag;
    }

    volatile LayerDrawable.LayerState createConstantState(LayerDrawable.LayerState layerstate, Resources resources)
    {
        return createConstantState(layerstate, resources);
    }

    RippleState createConstantState(LayerDrawable.LayerState layerstate, Resources resources)
    {
        return new RippleState(layerstate, this, resources);
    }

    public void draw(Canvas canvas)
    {
        pruneRipples();
        Rect rect = getDirtyBounds();
        int i = canvas.save(2);
        canvas.clipRect(rect);
        drawContent(canvas);
        drawBackgroundAndRipples(canvas);
        canvas.restoreToCount(i);
    }

    public Drawable.ConstantState getConstantState()
    {
        return mState;
    }

    public Rect getDirtyBounds()
    {
        if(!isBounded())
        {
            Rect rect = mDrawingBounds;
            Rect rect1 = mDirtyBounds;
            rect1.set(rect);
            rect.setEmpty();
            int i = (int)mHotspotBounds.exactCenterX();
            int j = (int)mHotspotBounds.exactCenterY();
            Rect rect2 = mTempRect;
            RippleForeground arippleforeground[] = mExitingRipples;
            int k = mExitingRipplesCount;
            for(int l = 0; l < k; l++)
            {
                arippleforeground[l].getBounds(rect2);
                rect2.offset(i, j);
                rect.union(rect2);
            }

            RippleBackground ripplebackground = mBackground;
            if(ripplebackground != null)
            {
                ripplebackground.getBounds(rect2);
                rect2.offset(i, j);
                rect.union(rect2);
            }
            rect1.union(rect);
            rect1.union(super.getDirtyBounds());
            return rect1;
        } else
        {
            return getBounds();
        }
    }

    public void getHotspotBounds(Rect rect)
    {
        rect.set(mHotspotBounds);
    }

    public int getOpacity()
    {
        return -3;
    }

    public void getOutline(Outline outline)
    {
        LayerDrawable.LayerState layerstate = mLayerState;
        LayerDrawable.ChildDrawable achilddrawable[] = layerstate.mChildren;
        int i = layerstate.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            if(achilddrawable[j].mId == 0x102002e)
                continue;
            achilddrawable[j].mDrawable.getOutline(outline);
            if(!outline.isEmpty())
                return;
        }

    }

    public int getRadius()
    {
        return mState.mMaxRadius;
    }

    public boolean hasFocusStateSpecified()
    {
        return true;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.RippleDrawable);
        setPaddingMode(1);
        super.inflate(resources, xmlpullparser, attributeset, theme);
        updateStateFromTypedArray(typedarray);
        verifyRequiredAttributes(typedarray);
        typedarray.recycle();
        updateLocalState();
    }

    public void invalidateSelf()
    {
        invalidateSelf(true);
    }

    void invalidateSelf(boolean flag)
    {
        super.invalidateSelf();
        if(flag)
            mHasValidMask = false;
    }

    public boolean isProjected()
    {
        if(isBounded())
            return false;
        int i = mState.mMaxRadius;
        Rect rect = getBounds();
        Rect rect1 = mHotspotBounds;
        return i == -1 || i > rect1.width() / 2 || i > rect1.height() / 2 || !rect.equals(rect1) && !rect.contains(rect1);
    }

    public boolean isStateful()
    {
        return true;
    }

    public void jumpToCurrentState()
    {
        super.jumpToCurrentState();
        if(mRipple != null)
            mRipple.end();
        if(mBackground != null)
            mBackground.end();
        cancelExitingRipples();
    }

    public Drawable mutate()
    {
        super.mutate();
        mState = (RippleState)mLayerState;
        mMask = findDrawableByLayerId(0x102002e);
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        if(!mOverrideBounds)
        {
            mHotspotBounds.set(rect);
            onHotspotBoundsChanged();
        }
        if(mBackground != null)
            mBackground.onBoundsChange();
        if(mRipple != null)
            mRipple.onBoundsChange();
        invalidateSelf();
    }

    protected boolean onStateChange(int ai[])
    {
        boolean flag = super.onStateChange(ai);
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        int i = ai.length;
        int j = 0;
        while(j < i) 
        {
            int k = ai[j];
            boolean flag5;
            boolean flag6;
            boolean flag8;
            if(k == 0x101009e)
            {
                flag5 = true;
                flag6 = flag2;
                flag8 = flag3;
            } else
            if(k == 0x101009c)
            {
                flag8 = true;
                flag5 = flag1;
                flag6 = flag2;
            } else
            if(k == 0x10100a7)
            {
                flag6 = true;
                flag5 = flag1;
                flag8 = flag3;
            } else
            {
                flag5 = flag1;
                flag8 = flag3;
                flag6 = flag2;
                if(k == 0x1010367)
                {
                    flag4 = true;
                    flag5 = flag1;
                    flag8 = flag3;
                    flag6 = flag2;
                }
            }
            j++;
            flag1 = flag5;
            flag3 = flag8;
            flag2 = flag6;
        }
        boolean flag7;
        if(flag1)
            flag7 = flag2;
        else
            flag7 = false;
        setRippleActive(flag7);
        if(!flag4 && !flag3)
        {
            if(!flag1)
                flag2 = false;
        } else
        {
            flag2 = true;
        }
        if(flag3)
            flag4 = true;
        setBackgroundActive(flag2, flag4);
        return flag;
    }

    public void setColor(ColorStateList colorstatelist)
    {
        mState.mColor = colorstatelist;
        invalidateSelf(false);
    }

    public boolean setDrawableByLayerId(int i, Drawable drawable)
    {
        if(super.setDrawableByLayerId(i, drawable))
        {
            if(i == 0x102002e)
            {
                mMask = drawable;
                mHasValidMask = false;
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void setForceSoftware(boolean flag)
    {
        mForceSoftware = flag;
    }

    public void setHotspot(float f, float f1)
    {
        if(mRipple == null || mBackground == null)
        {
            mPendingX = f;
            mPendingY = f1;
            mHasPending = true;
        }
        if(mRipple != null)
            mRipple.move(f, f1);
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        mOverrideBounds = true;
        mHotspotBounds.set(i, j, k, l);
        onHotspotBoundsChanged();
    }

    public void setPaddingMode(int i)
    {
        super.setPaddingMode(i);
    }

    public void setRadius(int i)
    {
        mState.mMaxRadius = i;
        invalidateSelf(false);
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        flag1 = super.setVisible(flag, flag1);
        if(flag) goto _L2; else goto _L1
_L1:
        clearHotspots();
_L4:
        return flag1;
_L2:
        if(flag1)
        {
            if(mRippleActive)
                tryRippleEnter();
            if(mBackgroundActive)
                tryBackgroundEnter(false);
            jumpToCurrentState();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int MASK_CONTENT = 1;
    private static final int MASK_EXPLICIT = 2;
    private static final int MASK_NONE = 0;
    private static final int MASK_UNKNOWN = -1;
    private static final int MAX_RIPPLES = 10;
    public static final int RADIUS_AUTO = -1;
    private RippleBackground mBackground;
    private boolean mBackgroundActive;
    private int mDensity;
    private final Rect mDirtyBounds;
    private final Rect mDrawingBounds;
    private RippleForeground mExitingRipples[];
    private int mExitingRipplesCount;
    private boolean mForceSoftware;
    private boolean mHasPending;
    private boolean mHasValidMask;
    private final Rect mHotspotBounds;
    private Drawable mMask;
    private Bitmap mMaskBuffer;
    private Canvas mMaskCanvas;
    private PorterDuffColorFilter mMaskColorFilter;
    private Matrix mMaskMatrix;
    private BitmapShader mMaskShader;
    private boolean mOverrideBounds;
    private float mPendingX;
    private float mPendingY;
    private RippleForeground mRipple;
    private boolean mRippleActive;
    private Paint mRipplePaint;
    private RippleState mState;
    private final Rect mTempRect;
}
