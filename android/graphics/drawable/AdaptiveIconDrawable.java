// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.PathParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class AdaptiveIconDrawable extends Drawable
    implements Drawable.Callback
{
    static class ChildDrawable
    {

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs == null)
            {
                if(mDrawable != null)
                    flag = mDrawable.canApplyTheme();
                else
                    flag = false;
            } else
            {
                flag = true;
            }
            return flag;
        }

        public final void setDensity(int i)
        {
            if(mDensity != i)
                mDensity = i;
        }

        public int mDensity;
        public Drawable mDrawable;
        public int mThemeAttrs[];

        ChildDrawable(int i)
        {
            mDensity = 160;
            mDensity = i;
        }

        ChildDrawable(ChildDrawable childdrawable, AdaptiveIconDrawable adaptiveicondrawable, Resources resources)
        {
            mDensity = 160;
            Drawable drawable = childdrawable.mDrawable;
            Object obj;
            if(drawable != null)
            {
                obj = drawable.getConstantState();
                if(obj == null)
                    obj = drawable;
                else
                if(resources != null)
                    obj = ((Drawable.ConstantState) (obj)).newDrawable(resources);
                else
                    obj = ((Drawable.ConstantState) (obj)).newDrawable();
                ((Drawable) (obj)).setCallback(adaptiveicondrawable);
                ((Drawable) (obj)).setBounds(drawable.getBounds());
                ((Drawable) (obj)).setLevel(drawable.getLevel());
            } else
            {
                obj = null;
            }
            mDrawable = ((Drawable) (obj));
            mThemeAttrs = childdrawable.mThemeAttrs;
            mDensity = Drawable.resolveDensity(resources, childdrawable.mDensity);
        }
    }

    static class LayerState extends Drawable.ConstantState
    {

        static boolean _2D_get0(LayerState layerstate)
        {
            return layerstate.mAutoMirrored;
        }

        static boolean _2D_set0(LayerState layerstate, boolean flag)
        {
            layerstate.mAutoMirrored = flag;
            return flag;
        }

        public boolean canApplyTheme()
        {
            if(mThemeAttrs != null || super.canApplyTheme())
                return true;
            ChildDrawable achilddrawable[] = mChildren;
            for(int i = 0; i < 2; i++)
                if(achilddrawable[i].canApplyTheme())
                    return true;

            return false;
        }

        public final boolean canConstantState()
        {
            ChildDrawable achilddrawable[] = mChildren;
            for(int i = 0; i < 2; i++)
            {
                Drawable drawable = achilddrawable[i].mDrawable;
                if(drawable != null && drawable.getConstantState() == null)
                    return false;
            }

            return true;
        }

        public int getChangingConfigurations()
        {
            return mChangingConfigurations | mChildrenChangingConfigurations;
        }

        public final int getOpacity()
        {
            if(mCheckedOpacity)
                return mOpacity;
            ChildDrawable achilddrawable[] = mChildren;
            int i = -1;
            int j = 0;
label0:
            do
            {
label1:
                {
                    int k = i;
                    if(j < 2)
                    {
                        if(achilddrawable[j].mDrawable == null)
                            break label1;
                        k = j;
                    }
                    if(k >= 0)
                        j = achilddrawable[k].mDrawable.getOpacity();
                    else
                        j = -2;
                    for(i = k + 1; i < 2;)
                    {
                        Drawable drawable = achilddrawable[i].mDrawable;
                        int l = j;
                        if(drawable != null)
                            l = Drawable.resolveOpacity(j, drawable.getOpacity());
                        i++;
                        j = l;
                    }

                    break label0;
                }
                j++;
            } while(true);
            mOpacity = j;
            mCheckedOpacity = true;
            return j;
        }

        public final boolean hasFocusStateSpecified()
        {
            ChildDrawable achilddrawable[] = mChildren;
            for(int i = 0; i < 2; i++)
            {
                Drawable drawable = achilddrawable[i].mDrawable;
                if(drawable != null && drawable.hasFocusStateSpecified())
                    return true;
            }

            return false;
        }

        public void invalidateCache()
        {
            mCheckedOpacity = false;
            mCheckedStateful = false;
        }

        public final boolean isStateful()
        {
            if(mCheckedStateful)
                return mIsStateful;
            ChildDrawable achilddrawable[] = mChildren;
            boolean flag = false;
            int i = 0;
            do
            {
label0:
                {
                    boolean flag1 = flag;
                    if(i < 2)
                    {
                        Drawable drawable = achilddrawable[i].mDrawable;
                        if(drawable == null || !drawable.isStateful())
                            break label0;
                        flag1 = true;
                    }
                    mIsStateful = flag1;
                    mCheckedStateful = true;
                    return flag1;
                }
                i++;
            } while(true);
        }

        public Drawable newDrawable()
        {
            return new AdaptiveIconDrawable(this, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new AdaptiveIconDrawable(this, resources);
        }

        public final void setDensity(int i)
        {
            if(mDensity != i)
                mDensity = i;
        }

        static final int N_CHILDREN = 2;
        private boolean mAutoMirrored;
        int mChangingConfigurations;
        private boolean mCheckedOpacity;
        private boolean mCheckedStateful;
        ChildDrawable mChildren[];
        int mChildrenChangingConfigurations;
        int mDensity;
        private boolean mIsStateful;
        private int mOpacity;
        int mOpacityOverride;
        int mSrcDensityOverride;
        private int mThemeAttrs[];

        LayerState(LayerState layerstate, AdaptiveIconDrawable adaptiveicondrawable, Resources resources)
        {
            int i = 0;
            super();
            mSrcDensityOverride = 0;
            mOpacityOverride = 0;
            mAutoMirrored = false;
            if(layerstate != null)
                i = layerstate.mDensity;
            mDensity = Drawable.resolveDensity(resources, i);
            mChildren = new ChildDrawable[2];
            if(layerstate != null)
            {
                ChildDrawable achilddrawable[] = layerstate.mChildren;
                mChangingConfigurations = layerstate.mChangingConfigurations;
                mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations;
                for(int j = 0; j < 2; j++)
                {
                    ChildDrawable childdrawable = achilddrawable[j];
                    mChildren[j] = new ChildDrawable(childdrawable, adaptiveicondrawable, resources);
                }

                mCheckedOpacity = layerstate.mCheckedOpacity;
                mOpacity = layerstate.mOpacity;
                mCheckedStateful = layerstate.mCheckedStateful;
                mIsStateful = layerstate.mIsStateful;
                mAutoMirrored = layerstate.mAutoMirrored;
                mThemeAttrs = layerstate.mThemeAttrs;
                mOpacityOverride = layerstate.mOpacityOverride;
                mSrcDensityOverride = layerstate.mSrcDensityOverride;
            } else
            {
                int k = 0;
                while(k < 2) 
                {
                    mChildren[k] = new ChildDrawable(mDensity);
                    k++;
                }
            }
        }
    }


    AdaptiveIconDrawable()
    {
        this((LayerState)null, ((Resources) (null)));
    }

    AdaptiveIconDrawable(LayerState layerstate, Resources resources)
    {
        mTmpOutRect = new Rect();
        mPaint = new Paint(7);
        mLayerState = createConstantState(layerstate, resources);
        if(sMask == null)
            sMask = PathParser.createPathFromPathData(Resources.getSystem().getString(0x104014a));
        mMask = PathParser.createPathFromPathData(Resources.getSystem().getString(0x104014a));
        mMaskMatrix = new Matrix();
        mCanvas = new Canvas();
        mTransparentRegion = new Region();
    }

    public AdaptiveIconDrawable(Drawable drawable, Drawable drawable1)
    {
        this((LayerState)null, ((Resources) (null)));
        if(drawable != null)
            addLayer(0, createChildDrawable(drawable));
        if(drawable1 != null)
            addLayer(1, createChildDrawable(drawable1));
    }

    private void addLayer(int i, ChildDrawable childdrawable)
    {
        mLayerState.mChildren[i] = childdrawable;
        mLayerState.invalidateCache();
    }

    private ChildDrawable createChildDrawable(Drawable drawable)
    {
        ChildDrawable childdrawable = new ChildDrawable(mLayerState.mDensity);
        childdrawable.mDrawable = drawable;
        childdrawable.mDrawable.setCallback(this);
        drawable = mLayerState;
        drawable.mChildrenChangingConfigurations = ((LayerState) (drawable)).mChildrenChangingConfigurations | childdrawable.mDrawable.getChangingConfigurations();
        return childdrawable;
    }

    public static float getExtraInsetFraction()
    {
        return 0.25F;
    }

    public static float getExtraInsetPercentage()
    {
        return 0.25F;
    }

    private int getMaxIntrinsicHeight()
    {
        byte byte0 = -1;
        int i = 0;
        while(i < 2) 
        {
            ChildDrawable childdrawable = mLayerState.mChildren[i];
            int j;
            if(childdrawable.mDrawable == null)
            {
                j = byte0;
            } else
            {
                int k = childdrawable.mDrawable.getIntrinsicHeight();
                j = byte0;
                if(k > byte0)
                    j = k;
            }
            i++;
            byte0 = j;
        }
        return byte0;
    }

    private int getMaxIntrinsicWidth()
    {
        byte byte0 = -1;
        int i = 0;
        while(i < 2) 
        {
            ChildDrawable childdrawable = mLayerState.mChildren[i];
            int j;
            if(childdrawable.mDrawable == null)
            {
                j = byte0;
            } else
            {
                int k = childdrawable.mDrawable.getIntrinsicWidth();
                j = byte0;
                if(k > byte0)
                    j = k;
            }
            i++;
            byte0 = j;
        }
        return byte0;
    }

    private void inflateLayers(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        LayerState layerstate = mLayerState;
        int i = xmlpullparser.getDepth() + 1;
        do
        {
            int j = xmlpullparser.next();
            if(j == 1)
                break;
            int k = xmlpullparser.getDepth();
            if(k < i && j == 3)
                break;
            if(j != 2 || k > i)
                continue;
            Object obj = xmlpullparser.getName();
            ChildDrawable childdrawable;
            if(((String) (obj)).equals("background"))
            {
                j = 0;
            } else
            {
                if(!((String) (obj)).equals("foreground"))
                    continue;
                j = 1;
            }
            childdrawable = new ChildDrawable(layerstate.mDensity);
            obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AdaptiveIconDrawableLayer);
            updateLayerFromTypedArray(childdrawable, ((TypedArray) (obj)));
            ((TypedArray) (obj)).recycle();
            if(childdrawable.mDrawable == null && childdrawable.mThemeAttrs == null)
            {
                int l;
                do
                    l = xmlpullparser.next();
                while(l == 4);
                if(l != 2)
                    throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <foreground> or <background> tag requires a 'drawable'").append("attribute or child tag defining a drawable").toString());
                childdrawable.mDrawable = Drawable.createFromXmlInnerForDensity(resources, xmlpullparser, attributeset, mLayerState.mSrcDensityOverride, theme);
                childdrawable.mDrawable.setCallback(this);
                layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | childdrawable.mDrawable.getChangingConfigurations();
            }
            addLayer(j, childdrawable);
        } while(true);
    }

    private void resumeChildInvalidation()
    {
        mSuspendChildInvalidation = false;
        if(mChildRequestedInvalidation)
        {
            mChildRequestedInvalidation = false;
            invalidateSelf();
        }
    }

    private void suspendChildInvalidation()
    {
        mSuspendChildInvalidation = true;
    }

    private void updateLayerBounds(Rect rect)
    {
        if(rect.isEmpty())
            return;
        suspendChildInvalidation();
        updateLayerBoundsInternal(rect);
        updateMaskBoundsInternal(rect);
        resumeChildInvalidation();
        return;
        rect;
        resumeChildInvalidation();
        throw rect;
    }

    private void updateLayerBoundsInternal(Rect rect)
    {
        int i = rect.width() / 2;
        int j = rect.height() / 2;
        int k = 0;
        while(k < 2) 
        {
            Object obj = mLayerState.mChildren[k];
            if(obj != null)
            {
                obj = ((ChildDrawable) (obj)).mDrawable;
                if(obj != null)
                {
                    int l = (int)((float)rect.width() / 1.333333F);
                    int i1 = (int)((float)rect.height() / 1.333333F);
                    Rect rect1 = mTmpOutRect;
                    rect1.set(i - l, j - i1, i + l, j + i1);
                    ((Drawable) (obj)).setBounds(rect1);
                }
            }
            k++;
        }
    }

    private void updateLayerFromTypedArray(ChildDrawable childdrawable, TypedArray typedarray)
    {
        LayerState layerstate = mLayerState;
        layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | typedarray.getChangingConfigurations();
        childdrawable.mThemeAttrs = typedarray.extractThemeAttrs();
        typedarray = typedarray.getDrawableForDensity(0, layerstate.mSrcDensityOverride);
        if(typedarray != null)
        {
            if(childdrawable.mDrawable != null)
                childdrawable.mDrawable.setCallback(null);
            childdrawable.mDrawable = typedarray;
            childdrawable.mDrawable.setCallback(this);
            layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | childdrawable.mDrawable.getChangingConfigurations();
        }
    }

    private void updateMaskBoundsInternal(Rect rect)
    {
        mMaskMatrix.setScale((float)rect.width() / 100F, (float)rect.height() / 100F);
        sMask.transform(mMaskMatrix, mMask);
        break MISSING_BLOCK_LABEL_37;
        if(mMaskBitmap == null || mMaskBitmap.getWidth() != rect.width() || mMaskBitmap.getHeight() != rect.height())
        {
            mMaskBitmap = Bitmap.createBitmap(rect.width(), rect.height(), android.graphics.Bitmap.Config.ALPHA_8);
            mLayersBitmap = Bitmap.createBitmap(rect.width(), rect.height(), android.graphics.Bitmap.Config.ARGB_8888);
        }
        mCanvas.setBitmap(mMaskBitmap);
        mPaint.setShader(null);
        mCanvas.drawPath(mMask, mPaint);
        mMaskMatrix.postTranslate(rect.left, rect.top);
        mMask.reset();
        sMask.transform(mMaskMatrix, mMask);
        mTransparentRegion.setEmpty();
        mLayersShader = null;
        return;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        LayerState layerstate = mLayerState;
        if(layerstate == null)
            return;
        int i = Drawable.resolveDensity(theme.getResources(), 0);
        layerstate.setDensity(i);
        ChildDrawable achilddrawable[] = layerstate.mChildren;
        for(int j = 0; j < 2; j++)
        {
            ChildDrawable childdrawable = achilddrawable[j];
            childdrawable.setDensity(i);
            if(childdrawable.mThemeAttrs != null)
            {
                TypedArray typedarray = theme.resolveAttributes(childdrawable.mThemeAttrs, com.android.internal.R.styleable.AdaptiveIconDrawableLayer);
                updateLayerFromTypedArray(childdrawable, typedarray);
                typedarray.recycle();
            }
            Drawable drawable = childdrawable.mDrawable;
            if(drawable != null && drawable.canApplyTheme())
            {
                drawable.applyTheme(theme);
                layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | drawable.getChangingConfigurations();
            }
        }

    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(mLayerState == null || !mLayerState.canApplyTheme())
            flag = super.canApplyTheme();
        else
            flag = true;
        return flag;
    }

    public void clearMutated()
    {
        super.clearMutated();
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.clearMutated();
        }

        mMutated = false;
    }

    LayerState createConstantState(LayerState layerstate, Resources resources)
    {
        return new LayerState(layerstate, this, resources);
    }

    public void draw(Canvas canvas)
    {
        if(mLayersBitmap == null)
            return;
        if(mLayersShader == null)
        {
            mCanvas.setBitmap(mLayersBitmap);
            mCanvas.drawColor(0xff000000);
            int i = 0;
            while(i < 2) 
            {
                if(mLayerState.mChildren[i] != null)
                {
                    Drawable drawable = mLayerState.mChildren[i].mDrawable;
                    if(drawable != null)
                        drawable.draw(mCanvas);
                }
                i++;
            }
            mLayersShader = new BitmapShader(mLayersBitmap, android.graphics.Shader.TileMode.CLAMP, android.graphics.Shader.TileMode.CLAMP);
            mPaint.setShader(mLayersShader);
        }
        if(mMaskBitmap != null)
        {
            Rect rect = getBounds();
            canvas.drawBitmap(mMaskBitmap, rect.left, rect.top, mPaint);
        }
    }

    public int getAlpha()
    {
        return -3;
    }

    public Drawable getBackground()
    {
        return mLayerState.mChildren[0].mDrawable;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mLayerState.getChangingConfigurations();
    }

    public Drawable.ConstantState getConstantState()
    {
        if(mLayerState.canConstantState())
        {
            mLayerState.mChangingConfigurations = getChangingConfigurations();
            return mLayerState;
        } else
        {
            return null;
        }
    }

    public Drawable getForeground()
    {
        return mLayerState.mChildren[1].mDrawable;
    }

    public void getHotspotBounds(Rect rect)
    {
        if(mHotspotBounds != null)
            rect.set(mHotspotBounds);
        else
            super.getHotspotBounds(rect);
    }

    public Path getIconMask()
    {
        return mMask;
    }

    public int getIntrinsicHeight()
    {
        return (int)((float)getMaxIntrinsicHeight() * 0.6666667F);
    }

    public int getIntrinsicWidth()
    {
        return (int)((float)getMaxIntrinsicWidth() * 0.6666667F);
    }

    public int getOpacity()
    {
        if(mLayerState.mOpacityOverride != 0)
            return mLayerState.mOpacityOverride;
        else
            return mLayerState.getOpacity();
    }

    public void getOutline(Outline outline)
    {
        outline.setConvexPath(mMask);
    }

    public Region getSafeZone()
    {
        mMaskMatrix.reset();
        mMaskMatrix.setScale(0.9166667F, 0.9166667F, getBounds().centerX(), getBounds().centerY());
        Path path = new Path();
        mMask.transform(mMaskMatrix, path);
        Region region = new Region(getBounds());
        region.setPath(path, region);
        return region;
    }

    public Region getTransparentRegion()
    {
        if(mTransparentRegion.isEmpty())
        {
            mMask.toggleInverseFillType();
            mTransparentRegion.set(getBounds());
            mTransparentRegion.setPath(mMask, mTransparentRegion);
            mMask.toggleInverseFillType();
        }
        return mTransparentRegion;
    }

    public boolean hasFocusStateSpecified()
    {
        return mLayerState.hasFocusStateSpecified();
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        LayerState layerstate = mLayerState;
        if(layerstate == null)
            return;
        int i = Drawable.resolveDensity(resources, 0);
        layerstate.setDensity(i);
        layerstate.mSrcDensityOverride = mSrcDensityOverride;
        ChildDrawable achilddrawable[] = layerstate.mChildren;
        for(int j = 0; j < layerstate.mChildren.length; j++)
            achilddrawable[j].setDensity(i);

        inflateLayers(resources, xmlpullparser, attributeset, theme);
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if(mSuspendChildInvalidation)
            mChildRequestedInvalidation = true;
        else
            invalidateSelf();
    }

    public void invalidateSelf()
    {
        mLayersShader = null;
        super.invalidateSelf();
    }

    public boolean isAutoMirrored()
    {
        return LayerState._2D_get0(mLayerState);
    }

    public boolean isProjected()
    {
        if(super.isProjected())
            return true;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
            if(achilddrawable[i].mDrawable.isProjected())
                return true;

        return false;
    }

    public boolean isStateful()
    {
        return mLayerState.isStateful();
    }

    public void jumpToCurrentState()
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.jumpToCurrentState();
        }

    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mLayerState = createConstantState(mLayerState, null);
            for(int i = 0; i < 2; i++)
            {
                Drawable drawable = mLayerState.mChildren[i].mDrawable;
                if(drawable != null)
                    drawable.mutate();
            }

            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        if(rect.isEmpty())
        {
            return;
        } else
        {
            updateLayerBounds(rect);
            return;
        }
    }

    protected boolean onLevelChange(int i)
    {
        boolean flag = false;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int j = 0; j < 2;)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            boolean flag1 = flag;
            if(drawable != null)
            {
                flag1 = flag;
                if(drawable.setLevel(i))
                    flag1 = true;
            }
            j++;
            flag = flag1;
        }

        if(flag)
            updateLayerBounds(getBounds());
        return flag;
    }

    protected boolean onStateChange(int ai[])
    {
        boolean flag = false;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2;)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            boolean flag1 = flag;
            if(drawable != null)
            {
                flag1 = flag;
                if(drawable.isStateful())
                {
                    flag1 = flag;
                    if(drawable.setState(ai))
                        flag1 = true;
                }
            }
            i++;
            flag = flag1;
        }

        if(flag)
            updateLayerBounds(getBounds());
        return flag;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public void setAlpha(int i)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int j = 0; j < 2; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setAlpha(i);
        }

    }

    public void setAutoMirrored(boolean flag)
    {
        LayerState._2D_set0(mLayerState, flag);
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.setAutoMirrored(flag);
        }

    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.setColorFilter(colorfilter);
        }

    }

    public void setDither(boolean flag)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.setDither(flag);
        }

    }

    public void setHotspot(float f, float f1)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.setHotspot(f, f1);
        }

    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i1 = 0; i1 < 2; i1++)
        {
            Drawable drawable = achilddrawable[i1].mDrawable;
            if(drawable != null)
                drawable.setHotspotBounds(i, j, k, l);
        }

        if(mHotspotBounds == null)
            mHotspotBounds = new Rect(i, j, k, l);
        else
            mHotspotBounds.set(i, j, k, l);
    }

    public void setOpacity(int i)
    {
        mLayerState.mOpacityOverride = i;
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.setTintList(colorstatelist);
        }

    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.setTintMode(mode);
        }

    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag2 = super.setVisible(flag, flag1);
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int i = 0; i < 2; i++)
        {
            Drawable drawable = achilddrawable[i].mDrawable;
            if(drawable != null)
                drawable.setVisible(flag, flag1);
        }

        return flag2;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    private static final int BACKGROUND_ID = 0;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667F;
    private static final float EXTRA_INSET_PERCENTAGE = 0.25F;
    private static final int FOREGROUND_ID = 1;
    public static final float MASK_SIZE = 100F;
    private static final float SAFEZONE_SCALE = 0.9166667F;
    private static Path sMask;
    private final Canvas mCanvas;
    private boolean mChildRequestedInvalidation;
    private Rect mHotspotBounds;
    LayerState mLayerState;
    private Bitmap mLayersBitmap;
    private Shader mLayersShader;
    private final Path mMask;
    private Bitmap mMaskBitmap;
    private final Matrix mMaskMatrix;
    private boolean mMutated;
    private Paint mPaint;
    private boolean mSuspendChildInvalidation;
    private final Rect mTmpOutRect;
    private final Region mTransparentRegion;
}
