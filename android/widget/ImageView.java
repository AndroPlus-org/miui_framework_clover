// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewHierarchyEncoder;
import android.view.accessibility.AccessibilityEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImageView extends View
{
    private class ImageDrawableCallback
        implements Runnable
    {

        public void run()
        {
            setImageDrawable(drawable);
            ImageView._2D_set1(ImageView.this, uri);
            ImageView._2D_set0(ImageView.this, resource);
        }

        private final Drawable drawable;
        private final int resource;
        final ImageView this$0;
        private final Uri uri;

        ImageDrawableCallback(Drawable drawable1, Uri uri1, int i)
        {
            this$0 = ImageView.this;
            super();
            drawable = drawable1;
            uri = uri1;
            resource = i;
        }
    }

    public static final class ScaleType extends Enum
    {

        public static ScaleType valueOf(String s)
        {
            return (ScaleType)Enum.valueOf(android/widget/ImageView$ScaleType, s);
        }

        public static ScaleType[] values()
        {
            return $VALUES;
        }

        private static final ScaleType $VALUES[];
        public static final ScaleType CENTER;
        public static final ScaleType CENTER_CROP;
        public static final ScaleType CENTER_INSIDE;
        public static final ScaleType FIT_CENTER;
        public static final ScaleType FIT_END;
        public static final ScaleType FIT_START;
        public static final ScaleType FIT_XY;
        public static final ScaleType MATRIX;
        final int nativeInt;

        static 
        {
            MATRIX = new ScaleType("MATRIX", 0, 0);
            FIT_XY = new ScaleType("FIT_XY", 1, 1);
            FIT_START = new ScaleType("FIT_START", 2, 2);
            FIT_CENTER = new ScaleType("FIT_CENTER", 3, 3);
            FIT_END = new ScaleType("FIT_END", 4, 4);
            CENTER = new ScaleType("CENTER", 5, 5);
            CENTER_CROP = new ScaleType("CENTER_CROP", 6, 6);
            CENTER_INSIDE = new ScaleType("CENTER_INSIDE", 7, 7);
            $VALUES = (new ScaleType[] {
                MATRIX, FIT_XY, FIT_START, FIT_CENTER, FIT_END, CENTER, CENTER_CROP, CENTER_INSIDE
            });
        }

        private ScaleType(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    static int _2D_set0(ImageView imageview, int i)
    {
        imageview.mResource = i;
        return i;
    }

    static Uri _2D_set1(ImageView imageview, Uri uri)
    {
        imageview.mUri = uri;
        return uri;
    }

    public ImageView(Context context)
    {
        super(context);
        mResource = 0;
        mHaveFrame = false;
        mAdjustViewBounds = false;
        mMaxWidth = 0x7fffffff;
        mMaxHeight = 0x7fffffff;
        mColorFilter = null;
        mHasColorFilter = false;
        mAlpha = 255;
        mViewAlphaScale = 256;
        mColorMod = false;
        mDrawable = null;
        mRecycleableBitmapDrawable = null;
        mDrawableTintList = null;
        mDrawableTintMode = null;
        mHasDrawableTint = false;
        mHasDrawableTintMode = false;
        mState = null;
        mMergeState = false;
        mLevel = 0;
        mDrawMatrix = null;
        mTempSrc = new RectF();
        mTempDst = new RectF();
        mBaseline = -1;
        mBaselineAlignBottom = false;
        initImageView();
    }

    public ImageView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ImageView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ImageView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mResource = 0;
        mHaveFrame = false;
        mAdjustViewBounds = false;
        mMaxWidth = 0x7fffffff;
        mMaxHeight = 0x7fffffff;
        mColorFilter = null;
        mHasColorFilter = false;
        mAlpha = 255;
        mViewAlphaScale = 256;
        mColorMod = false;
        mDrawable = null;
        mRecycleableBitmapDrawable = null;
        mDrawableTintList = null;
        mDrawableTintMode = null;
        mHasDrawableTint = false;
        mHasDrawableTintMode = false;
        mState = null;
        mMergeState = false;
        mLevel = 0;
        mDrawMatrix = null;
        mTempSrc = new RectF();
        mTempDst = new RectF();
        mBaseline = -1;
        mBaselineAlignBottom = false;
        initImageView();
        if(getImportantForAutofill() == 0)
            setImportantForAutofill(2);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ImageView, i, j);
        attributeset = context.getDrawable(0);
        if(attributeset != null)
            setImageDrawable(attributeset);
        mBaselineAlignBottom = context.getBoolean(6, false);
        mBaseline = context.getDimensionPixelSize(8, -1);
        setAdjustViewBounds(context.getBoolean(2, false));
        setMaxWidth(context.getDimensionPixelSize(3, 0x7fffffff));
        setMaxHeight(context.getDimensionPixelSize(4, 0x7fffffff));
        i = context.getInt(1, -1);
        if(i >= 0)
            setScaleType(sScaleTypeArray[i]);
        if(context.hasValue(5))
        {
            mDrawableTintList = context.getColorStateList(5);
            mHasDrawableTint = true;
            mDrawableTintMode = android.graphics.PorterDuff.Mode.SRC_ATOP;
            mHasDrawableTintMode = true;
        }
        if(context.hasValue(9))
        {
            mDrawableTintMode = Drawable.parseTintMode(context.getInt(9, -1), mDrawableTintMode);
            mHasDrawableTintMode = true;
        }
        applyImageTint();
        i = context.getInt(10, 255);
        if(i != 255)
            setImageAlpha(i);
        mCropToPadding = context.getBoolean(7, false);
        context.recycle();
    }

    private void applyColorMod()
    {
        if(mDrawable != null && mColorMod)
        {
            mDrawable = mDrawable.mutate();
            if(mHasColorFilter)
                mDrawable.setColorFilter(mColorFilter);
            mDrawable.setXfermode(mXfermode);
            mDrawable.setAlpha(mAlpha * 256 >> 8);
        }
    }

    private void applyImageTint()
    {
        if(mDrawable != null && (mHasDrawableTint || mHasDrawableTintMode))
        {
            mDrawable = mDrawable.mutate();
            if(mHasDrawableTint)
                mDrawable.setTintList(mDrawableTintList);
            if(mHasDrawableTintMode)
                mDrawable.setTintMode(mDrawableTintMode);
            if(mDrawable.isStateful())
                mDrawable.setState(getDrawableState());
        }
    }

    private void configureBounds()
    {
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        if(mDrawable == null || mHaveFrame ^ true)
            return;
        i = mDrawableWidth;
        j = mDrawableHeight;
        k = getWidth() - mPaddingLeft - mPaddingRight;
        l = getHeight() - mPaddingTop - mPaddingBottom;
        if(i < 0 || k == i)
        {
            if(j < 0 || l == j)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        break MISSING_BLOCK_LABEL_80;
        if(i <= 0 || j <= 0 || ScaleType.FIT_XY == mScaleType)
        {
            mDrawable.setBounds(0, 0, k, l);
            mDrawMatrix = null;
        } else
        {
            mDrawable.setBounds(0, 0, i, j);
            if(ScaleType.MATRIX == mScaleType)
            {
                if(mMatrix.isIdentity())
                    mDrawMatrix = null;
                else
                    mDrawMatrix = mMatrix;
            } else
            if(flag)
                mDrawMatrix = null;
            else
            if(ScaleType.CENTER == mScaleType)
            {
                mDrawMatrix = mMatrix;
                mDrawMatrix.setTranslate(Math.round((float)(k - i) * 0.5F), Math.round((float)(l - j) * 0.5F));
            } else
            if(ScaleType.CENTER_CROP == mScaleType)
            {
                mDrawMatrix = mMatrix;
                float f = 0.0F;
                float f2 = 0.0F;
                float f4;
                if(i * l > k * j)
                {
                    f4 = (float)l / (float)j;
                    f = ((float)k - (float)i * f4) * 0.5F;
                } else
                {
                    f4 = (float)k / (float)i;
                    f2 = ((float)l - (float)j * f4) * 0.5F;
                }
                mDrawMatrix.setScale(f4, f4);
                mDrawMatrix.postTranslate(Math.round(f), Math.round(f2));
            } else
            if(ScaleType.CENTER_INSIDE == mScaleType)
            {
                mDrawMatrix = mMatrix;
                float f1;
                float f3;
                float f5;
                if(i <= k && j <= l)
                    f5 = 1.0F;
                else
                    f5 = Math.min((float)k / (float)i, (float)l / (float)j);
                f1 = Math.round(((float)k - (float)i * f5) * 0.5F);
                f3 = Math.round(((float)l - (float)j * f5) * 0.5F);
                mDrawMatrix.setScale(f5, f5);
                mDrawMatrix.postTranslate(f1, f3);
            } else
            {
                mTempSrc.set(0.0F, 0.0F, i, j);
                mTempDst.set(0.0F, 0.0F, k, l);
                mDrawMatrix = mMatrix;
                mDrawMatrix.setRectToRect(mTempSrc, mTempDst, scaleTypeToScaleToFit(mScaleType));
            }
        }
        return;
    }

    private Drawable getDrawableFromUri(Uri uri)
    {
        Object obj = uri.getScheme();
        if(!"android.resource".equals(obj)) goto _L2; else goto _L1
_L1:
        obj = mContext.getContentResolver().getResourceId(uri);
        obj = ((android.content.ContentResolver.OpenResourceIdResult) (obj)).r.getDrawable(((android.content.ContentResolver.OpenResourceIdResult) (obj)).id, mContext.getTheme());
        return ((Drawable) (obj));
        obj;
        Log.w("ImageView", (new StringBuilder()).append("Unable to open content: ").append(uri).toString(), ((Throwable) (obj)));
_L8:
        return null;
_L2:
        InputStream inputstream;
        if(!"content".equals(obj) && !"file".equals(obj))
            break; /* Loop/switch isn't completed */
        obj = null;
        inputstream = null;
        InputStream inputstream1 = mContext.getContentResolver().openInputStream(uri);
        inputstream = inputstream1;
        obj = inputstream1;
        if(!sCompatUseCorrectStreamDensity) goto _L4; else goto _L3
_L3:
        inputstream = inputstream1;
        obj = inputstream1;
        Object obj1 = getResources();
_L6:
        inputstream = inputstream1;
        obj = inputstream1;
        obj1 = Drawable.createFromResourceStream(((Resources) (obj1)), null, inputstream1, null);
        if(inputstream1 != null)
            try
            {
                inputstream1.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.w("ImageView", (new StringBuilder()).append("Unable to close content: ").append(uri).toString(), ((Throwable) (obj)));
            }
        return ((Drawable) (obj1));
_L4:
        obj1 = null;
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception1;
        exception1;
        obj = inputstream;
        obj1 = JVM INSTR new #431 <Class StringBuilder>;
        obj = inputstream;
        ((StringBuilder) (obj1)).StringBuilder();
        obj = inputstream;
        Log.w("ImageView", ((StringBuilder) (obj1)).append("Unable to open content: ").append(uri).toString(), exception1);
        if(inputstream != null)
            try
            {
                inputstream.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.w("ImageView", (new StringBuilder()).append("Unable to close content: ").append(uri).toString(), ((Throwable) (obj)));
            }
        if(true) goto _L8; else goto _L7
        Exception exception;
        exception;
        if(obj != null)
            try
            {
                ((InputStream) (obj)).close();
            }
            catch(IOException ioexception)
            {
                Log.w("ImageView", (new StringBuilder()).append("Unable to close content: ").append(uri).toString(), ioexception);
            }
        throw exception;
_L7:
        return Drawable.createFromPath(uri.toString());
    }

    private void initImageView()
    {
        boolean flag = false;
        mMatrix = new Matrix();
        mScaleType = ScaleType.FIT_CENTER;
        if(!sCompatDone)
        {
            int i = mContext.getApplicationInfo().targetSdkVersion;
            boolean flag1;
            if(i <= 17)
                flag1 = true;
            else
                flag1 = false;
            sCompatAdjustViewBounds = flag1;
            if(i > 23)
                flag1 = true;
            else
                flag1 = false;
            sCompatUseCorrectStreamDensity = flag1;
            flag1 = flag;
            if(i < 24)
                flag1 = true;
            sCompatDrawableVisibilityDispatch = flag1;
            sCompatDone = true;
        }
    }

    private boolean isFilledByImage()
    {
        boolean flag = true;
        boolean flag1 = true;
        if(mDrawable == null)
            return false;
        Rect rect = mDrawable.getBounds();
        Matrix matrix = mDrawMatrix;
        if(matrix == null)
        {
            if(rect.left <= 0 && rect.top <= 0 && rect.right >= getWidth())
            {
                if(rect.bottom < getHeight())
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        }
        if(matrix.rectStaysRect())
        {
            RectF rectf = mTempSrc;
            RectF rectf1 = mTempDst;
            rectf.set(rect);
            matrix.mapRect(rectf1, rectf);
            boolean flag2;
            if(rectf1.left <= 0.0F && rectf1.top <= 0.0F && rectf1.right >= (float)getWidth())
            {
                if(rectf1.bottom >= (float)getHeight())
                    flag2 = flag;
                else
                    flag2 = false;
            } else
            {
                flag2 = false;
            }
            return flag2;
        } else
        {
            return false;
        }
    }

    private void resizeFromDrawable()
    {
        Drawable drawable = mDrawable;
        if(drawable != null)
        {
            int i = drawable.getIntrinsicWidth();
            int j = i;
            if(i < 0)
                j = mDrawableWidth;
            int k = drawable.getIntrinsicHeight();
            i = k;
            if(k < 0)
                i = mDrawableHeight;
            if(j != mDrawableWidth || i != mDrawableHeight)
            {
                mDrawableWidth = j;
                mDrawableHeight = i;
                requestLayout();
            }
        }
    }

    private int resolveAdjustedSize(int i, int j, int k)
    {
        int l;
        int i1;
        l = i;
        i1 = android.view.View.MeasureSpec.getMode(k);
        k = android.view.View.MeasureSpec.getSize(k);
        i1;
        JVM INSTR lookupswitch 3: default 52
    //                   -2147483648: 66
    //                   0: 57
    //                   1073741824: 79;
           goto _L1 _L2 _L3 _L4
_L1:
        i = l;
_L6:
        return i;
_L3:
        i = Math.min(i, j);
        continue; /* Loop/switch isn't completed */
_L2:
        i = Math.min(Math.min(i, k), j);
        continue; /* Loop/switch isn't completed */
_L4:
        i = k;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void resolveUri()
    {
        Drawable drawable;
        if(mDrawable != null)
            return;
        if(getResources() == null)
            return;
        drawable = null;
        if(mResource == 0) goto _L2; else goto _L1
_L1:
        Drawable drawable1;
        try
        {
            drawable1 = mContext.getDrawable(mResource);
        }
        catch(Exception exception)
        {
            Log.w("ImageView", (new StringBuilder()).append("Unable to find resource: ").append(mResource).toString(), exception);
            mResource = 0;
            continue; /* Loop/switch isn't completed */
        }
        drawable = drawable1;
_L4:
        updateDrawable(drawable);
        return;
_L2:
        if(mUri == null)
            break; /* Loop/switch isn't completed */
        Drawable drawable2 = getDrawableFromUri(mUri);
        drawable = drawable2;
        if(drawable2 == null)
        {
            Log.w("ImageView", (new StringBuilder()).append("resolveUri failed on bad bitmap uri: ").append(mUri).toString());
            mUri = null;
            drawable = drawable2;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static android.graphics.Matrix.ScaleToFit scaleTypeToScaleToFit(ScaleType scaletype)
    {
        return sS2FArray[scaletype.nativeInt - 1];
    }

    private void updateDrawable(Drawable drawable)
    {
        if(drawable != mRecycleableBitmapDrawable && mRecycleableBitmapDrawable != null)
            mRecycleableBitmapDrawable.setBitmap(null);
        boolean flag = false;
        if(mDrawable != null)
        {
            boolean flag1;
            if(mDrawable == drawable)
                flag1 = true;
            else
                flag1 = false;
            mDrawable.setCallback(null);
            unscheduleDrawable(mDrawable);
            flag = flag1;
            if(!sCompatDrawableVisibilityDispatch)
            {
                flag = flag1;
                if(flag1 ^ true)
                {
                    flag = flag1;
                    if(isAttachedToWindow())
                    {
                        mDrawable.setVisible(false, false);
                        flag = flag1;
                    }
                }
            }
        }
        mDrawable = drawable;
        if(drawable != null)
        {
            drawable.setCallback(this);
            drawable.setLayoutDirection(getLayoutDirection());
            if(drawable.isStateful())
                drawable.setState(getDrawableState());
            if(!flag || sCompatDrawableVisibilityDispatch)
            {
                boolean flag2;
                if(sCompatDrawableVisibilityDispatch)
                {
                    if(getVisibility() == 0)
                        flag2 = true;
                    else
                        flag2 = false;
                } else
                if(isAttachedToWindow() && getWindowVisibility() == 0)
                    flag2 = isShown();
                else
                    flag2 = false;
                drawable.setVisible(flag2, true);
            }
            drawable.setLevel(mLevel);
            mDrawableWidth = drawable.getIntrinsicWidth();
            mDrawableHeight = drawable.getIntrinsicHeight();
            applyImageTint();
            applyColorMod();
            configureBounds();
        } else
        {
            mDrawableHeight = -1;
            mDrawableWidth = -1;
        }
    }

    public void animateTransform(Matrix matrix)
    {
        if(mDrawable == null)
            return;
        if(matrix == null)
        {
            mDrawable.setBounds(0, 0, getWidth(), getHeight());
        } else
        {
            mDrawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
            if(mDrawMatrix == null)
                mDrawMatrix = new Matrix();
            mDrawMatrix.set(matrix);
        }
        invalidate();
    }

    public final void clearColorFilter()
    {
        setColorFilter(((ColorFilter) (null)));
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mDrawable != null)
            mDrawable.setHotspot(f, f1);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        Drawable drawable = mDrawable;
        if(drawable != null && drawable.isStateful() && drawable.setState(getDrawableState()))
            invalidateDrawable(drawable);
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("layout:baseline", getBaseline());
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ImageView.getName();
    }

    public boolean getAdjustViewBounds()
    {
        return mAdjustViewBounds;
    }

    public int getBaseline()
    {
        if(mBaselineAlignBottom)
            return getMeasuredHeight();
        else
            return mBaseline;
    }

    public boolean getBaselineAlignBottom()
    {
        return mBaselineAlignBottom;
    }

    public ColorFilter getColorFilter()
    {
        return mColorFilter;
    }

    public boolean getCropToPadding()
    {
        return mCropToPadding;
    }

    public Drawable getDrawable()
    {
        if(mDrawable == mRecycleableBitmapDrawable)
            mRecycleableBitmapDrawable = null;
        return mDrawable;
    }

    public int getImageAlpha()
    {
        return mAlpha;
    }

    public Matrix getImageMatrix()
    {
        if(mDrawMatrix == null)
            return new Matrix(Matrix.IDENTITY_MATRIX);
        else
            return mDrawMatrix;
    }

    public ColorStateList getImageTintList()
    {
        return mDrawableTintList;
    }

    public android.graphics.PorterDuff.Mode getImageTintMode()
    {
        return mDrawableTintMode;
    }

    public int getMaxHeight()
    {
        return mMaxHeight;
    }

    public int getMaxWidth()
    {
        return mMaxWidth;
    }

    public ScaleType getScaleType()
    {
        return mScaleType;
    }

    public boolean hasOverlappingRendering()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(getBackground() != null)
        {
            flag1 = flag;
            if(getBackground().getCurrent() != null)
                flag1 = true;
        }
        return flag1;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if(drawable == mDrawable)
        {
            if(drawable != null)
            {
                int i = drawable.getIntrinsicWidth();
                int j = drawable.getIntrinsicHeight();
                if(i != mDrawableWidth || j != mDrawableHeight)
                {
                    mDrawableWidth = i;
                    mDrawableHeight = j;
                    configureBounds();
                }
            }
            invalidate();
        } else
        {
            super.invalidateDrawable(drawable);
        }
    }

    public boolean isDefaultFocusHighlightNeeded(Drawable drawable, Drawable drawable1)
    {
        boolean flag;
        if(mDrawable != null && !(mDrawable.isStateful() ^ true))
            flag = mDrawable.hasFocusStateSpecified() ^ true;
        else
            flag = true;
        if(!super.isDefaultFocusHighlightNeeded(drawable, drawable1))
            flag = false;
        return flag;
    }

    public boolean isOpaque()
    {
        boolean flag;
        if(!super.isOpaque())
        {
            if(mDrawable != null && mXfermode == null && mDrawable.getOpacity() == -1 && mAlpha * 256 >> 8 == 255)
                flag = isFilledByImage();
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mDrawable != null)
            mDrawable.jumpToCurrentState();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mDrawable != null && sCompatDrawableVisibilityDispatch)
        {
            Drawable drawable = mDrawable;
            boolean flag;
            if(getVisibility() == 0)
                flag = true;
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
    }

    public int[] onCreateDrawableState(int i)
    {
        if(mState == null)
            return super.onCreateDrawableState(i);
        if(!mMergeState)
            return mState;
        else
            return mergeDrawableStates(super.onCreateDrawableState(mState.length + i), mState);
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mDrawable != null && sCompatDrawableVisibilityDispatch)
            mDrawable.setVisible(false, false);
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(mDrawable == null)
            return;
        if(mDrawableWidth == 0 || mDrawableHeight == 0)
            return;
        if(mDrawMatrix == null && mPaddingTop == 0 && mPaddingLeft == 0)
        {
            mDrawable.draw(canvas);
        } else
        {
            int i = canvas.getSaveCount();
            canvas.save();
            if(mCropToPadding)
            {
                int j = mScrollX;
                int k = mScrollY;
                canvas.clipRect(mPaddingLeft + j, mPaddingTop + k, (mRight + j) - mLeft - mPaddingRight, (mBottom + k) - mTop - mPaddingBottom);
            }
            canvas.translate(mPaddingLeft, mPaddingTop);
            if(mDrawMatrix != null)
                canvas.concat(mDrawMatrix);
            mDrawable.draw(canvas);
            canvas.restoreToCount(i);
        }
    }

    protected void onMeasure(int i, int j)
    {
        resolveUri();
        float f = 0.0F;
        int k = 0;
        boolean flag = false;
        int l = android.view.View.MeasureSpec.getMode(i);
        int i1 = android.view.View.MeasureSpec.getMode(j);
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        if(mDrawable == null)
        {
            mDrawableWidth = -1;
            mDrawableHeight = -1;
            j1 = 0;
            k1 = 0;
        } else
        {
            l2 = mDrawableWidth;
            k1 = mDrawableHeight;
            int j3 = l2;
            if(l2 <= 0)
                j3 = 1;
            l2 = k1;
            if(k1 <= 0)
                l2 = 1;
            j1 = l2;
            k1 = j3;
            if(mAdjustViewBounds)
            {
                if(l != 0x40000000)
                    k1 = 1;
                else
                    k1 = 0;
                if(i1 != 0x40000000)
                    i1 = 1;
                else
                    i1 = 0;
                f = (float)j3 / (float)l2;
                j1 = l2;
                flag = i1;
                k = k1;
                k1 = j3;
            }
        }
        l1 = mPaddingLeft;
        i2 = mPaddingRight;
        j2 = mPaddingTop;
        k2 = mPaddingBottom;
        if(k != 0 || flag)
        {
            k1 = resolveAdjustedSize(k1 + l1 + i2, mMaxWidth, i);
            j1 = resolveAdjustedSize(j1 + j2 + k2, mMaxHeight, j);
            l2 = j1;
            i1 = k1;
            if(f != 0.0F)
            {
                l2 = j1;
                i1 = k1;
                if((double)Math.abs((float)(k1 - l1 - i2) / (float)(j1 - j2 - k2) - f) > 9.9999999999999995E-008D)
                {
                    boolean flag1 = false;
                    l = ((flag1) ? 1 : 0);
                    int i3 = k1;
                    if(k != 0)
                    {
                        i1 = (int)((float)(j1 - j2 - k2) * f) + l1 + i2;
                        l2 = k1;
                        if(!flag)
                        {
                            l2 = k1;
                            if(sCompatAdjustViewBounds ^ true)
                                l2 = resolveAdjustedSize(i1, mMaxWidth, i);
                        }
                        l = ((flag1) ? 1 : 0);
                        i3 = l2;
                        if(i1 <= l2)
                        {
                            i3 = i1;
                            l = 1;
                        }
                    }
                    l2 = j1;
                    i1 = i3;
                    if(l == 0)
                    {
                        l2 = j1;
                        i1 = i3;
                        if(flag)
                        {
                            k1 = (int)((float)(i3 - l1 - i2) / f) + j2 + k2;
                            i = j1;
                            if(k == 0)
                            {
                                i = j1;
                                if(sCompatAdjustViewBounds ^ true)
                                    i = resolveAdjustedSize(k1, mMaxHeight, j);
                            }
                            l2 = i;
                            i1 = i3;
                            if(k1 <= i)
                            {
                                l2 = k1;
                                i1 = i3;
                            }
                        }
                    }
                }
            }
        } else
        {
            int k3 = Math.max(k1 + (l1 + i2), getSuggestedMinimumWidth());
            l2 = Math.max(j1 + (j2 + k2), getSuggestedMinimumHeight());
            i1 = resolveSizeAndState(k3, i, 0);
            l2 = resolveSizeAndState(l2, j, 0);
        }
        setMeasuredDimension(i1, l2);
    }

    public void onPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onPopulateAccessibilityEventInternal(accessibilityevent);
        CharSequence charsequence = getContentDescription();
        if(!TextUtils.isEmpty(charsequence))
            accessibilityevent.getText().add(charsequence);
    }

    public void onRtlPropertiesChanged(int i)
    {
        super.onRtlPropertiesChanged(i);
        if(mDrawable != null)
            mDrawable.setLayoutDirection(i);
    }

    public void onVisibilityAggregated(boolean flag)
    {
        super.onVisibilityAggregated(flag);
        if(mDrawable != null && sCompatDrawableVisibilityDispatch ^ true)
            mDrawable.setVisible(flag, false);
    }

    public void setAdjustViewBounds(boolean flag)
    {
        mAdjustViewBounds = flag;
        if(flag)
            setScaleType(ScaleType.FIT_CENTER);
    }

    public void setAlpha(int i)
    {
        i &= 0xff;
        if(mAlpha != i)
        {
            mAlpha = i;
            mColorMod = true;
            applyColorMod();
            invalidate();
        }
    }

    public void setBaseline(int i)
    {
        if(mBaseline != i)
        {
            mBaseline = i;
            requestLayout();
        }
    }

    public void setBaselineAlignBottom(boolean flag)
    {
        if(mBaselineAlignBottom != flag)
        {
            mBaselineAlignBottom = flag;
            requestLayout();
        }
    }

    public final void setColorFilter(int i)
    {
        setColorFilter(i, android.graphics.PorterDuff.Mode.SRC_ATOP);
    }

    public final void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        setColorFilter(((ColorFilter) (new PorterDuffColorFilter(i, mode))));
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mColorFilter != colorfilter)
        {
            mColorFilter = colorfilter;
            mHasColorFilter = true;
            mColorMod = true;
            applyColorMod();
            invalidate();
        }
    }

    public void setCropToPadding(boolean flag)
    {
        if(mCropToPadding != flag)
        {
            mCropToPadding = flag;
            requestLayout();
            invalidate();
        }
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag = super.setFrame(i, j, k, l);
        mHaveFrame = true;
        configureBounds();
        return flag;
    }

    public void setImageAlpha(int i)
    {
        setAlpha(i);
    }

    public void setImageBitmap(Bitmap bitmap)
    {
        mDrawable = null;
        if(mRecycleableBitmapDrawable == null)
            mRecycleableBitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
        else
            mRecycleableBitmapDrawable.setBitmap(bitmap);
        setImageDrawable(mRecycleableBitmapDrawable);
    }

    public void setImageDrawable(Drawable drawable)
    {
        if(mDrawable != drawable)
        {
            mResource = 0;
            mUri = null;
            int i = mDrawableWidth;
            int j = mDrawableHeight;
            updateDrawable(drawable);
            if(i != mDrawableWidth || j != mDrawableHeight)
                requestLayout();
            invalidate();
        }
    }

    public void setImageIcon(Icon icon)
    {
        Object obj = null;
        if(icon == null)
            icon = obj;
        else
            icon = icon.loadDrawable(mContext);
        setImageDrawable(icon);
    }

    public Runnable setImageIconAsync(Icon icon)
    {
        if(icon == null)
            icon = null;
        else
            icon = icon.loadDrawable(mContext);
        return new ImageDrawableCallback(icon, null, 0);
    }

    public void setImageLevel(int i)
    {
        mLevel = i;
        if(mDrawable != null)
        {
            mDrawable.setLevel(i);
            resizeFromDrawable();
        }
    }

    public void setImageMatrix(Matrix matrix)
    {
        Matrix matrix1 = matrix;
        if(matrix != null)
        {
            matrix1 = matrix;
            if(matrix.isIdentity())
                matrix1 = null;
        }
        if(matrix1 == null && mMatrix.isIdentity() ^ true || matrix1 != null && mMatrix.equals(matrix1) ^ true)
        {
            mMatrix.set(matrix1);
            configureBounds();
            invalidate();
        }
    }

    public void setImageResource(int i)
    {
        int j = mDrawableWidth;
        int k = mDrawableHeight;
        updateDrawable(null);
        mResource = i;
        mUri = null;
        resolveUri();
        if(j != mDrawableWidth || k != mDrawableHeight)
            requestLayout();
        invalidate();
    }

    public Runnable setImageResourceAsync(int i)
    {
        Object obj;
        Drawable drawable;
        int j;
        obj = null;
        drawable = obj;
        j = i;
        if(i == 0)
            break MISSING_BLOCK_LABEL_23;
        drawable = getContext().getDrawable(i);
        j = i;
_L2:
        return new ImageDrawableCallback(drawable, null, j);
        Exception exception;
        exception;
        Log.w("ImageView", (new StringBuilder()).append("Unable to find resource: ").append(i).toString(), exception);
        j = 0;
        exception = obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setImageState(int ai[], boolean flag)
    {
        mState = ai;
        mMergeState = flag;
        if(mDrawable != null)
        {
            refreshDrawableState();
            resizeFromDrawable();
        }
    }

    public void setImageTintList(ColorStateList colorstatelist)
    {
        mDrawableTintList = colorstatelist;
        mHasDrawableTint = true;
        applyImageTint();
    }

    public void setImageTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mDrawableTintMode = mode;
        mHasDrawableTintMode = true;
        applyImageTint();
    }

    public void setImageURI(Uri uri)
    {
        if(mResource != 0 || mUri != uri && (uri == null || mUri == null || uri.equals(mUri) ^ true))
        {
            updateDrawable(null);
            mResource = 0;
            mUri = uri;
            int i = mDrawableWidth;
            int j = mDrawableHeight;
            resolveUri();
            if(i != mDrawableWidth || j != mDrawableHeight)
                requestLayout();
            invalidate();
        }
    }

    public Runnable setImageURIAsync(Uri uri)
    {
        while(mResource != 0 || mUri != uri && (uri == null || mUri == null || uri.equals(mUri) ^ true)) 
        {
            Drawable drawable;
            if(uri == null)
                drawable = null;
            else
                drawable = getDrawableFromUri(uri);
            if(drawable == null)
                uri = null;
            return new ImageDrawableCallback(drawable, uri, 0);
        }
        return null;
    }

    public void setMaxHeight(int i)
    {
        mMaxHeight = i;
    }

    public void setMaxWidth(int i)
    {
        mMaxWidth = i;
    }

    public void setScaleType(ScaleType scaletype)
    {
        if(scaletype == null)
            throw new NullPointerException();
        if(mScaleType != scaletype)
        {
            mScaleType = scaletype;
            boolean flag;
            if(mScaleType == ScaleType.CENTER)
                flag = true;
            else
                flag = false;
            setWillNotCacheDrawing(flag);
            requestLayout();
            invalidate();
        }
    }

    public void setSelected(boolean flag)
    {
        super.setSelected(flag);
        resizeFromDrawable();
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        if(mDrawable != null && sCompatDrawableVisibilityDispatch)
        {
            Drawable drawable = mDrawable;
            boolean flag;
            if(i == 0)
                flag = true;
            else
                flag = false;
            drawable.setVisible(flag, false);
        }
    }

    public final void setXfermode(Xfermode xfermode)
    {
        if(mXfermode != xfermode)
        {
            mXfermode = xfermode;
            mColorMod = true;
            applyColorMod();
            invalidate();
        }
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if(mDrawable != drawable)
            flag = super.verifyDrawable(drawable);
        else
            flag = true;
        return flag;
    }

    private static final String LOG_TAG = "ImageView";
    private static boolean sCompatAdjustViewBounds;
    private static boolean sCompatDone;
    private static boolean sCompatDrawableVisibilityDispatch;
    private static boolean sCompatUseCorrectStreamDensity;
    private static final android.graphics.Matrix.ScaleToFit sS2FArray[];
    private static final ScaleType sScaleTypeArray[];
    private boolean mAdjustViewBounds;
    private int mAlpha;
    private int mBaseline;
    private boolean mBaselineAlignBottom;
    private ColorFilter mColorFilter;
    private boolean mColorMod;
    private boolean mCropToPadding;
    private Matrix mDrawMatrix;
    private Drawable mDrawable;
    private int mDrawableHeight;
    private ColorStateList mDrawableTintList;
    private android.graphics.PorterDuff.Mode mDrawableTintMode;
    private int mDrawableWidth;
    private boolean mHasColorFilter;
    private boolean mHasDrawableTint;
    private boolean mHasDrawableTintMode;
    private boolean mHaveFrame;
    private int mLevel;
    private Matrix mMatrix;
    private int mMaxHeight;
    private int mMaxWidth;
    private boolean mMergeState;
    private BitmapDrawable mRecycleableBitmapDrawable;
    private int mResource;
    private ScaleType mScaleType;
    private int mState[];
    private final RectF mTempDst;
    private final RectF mTempSrc;
    private Uri mUri;
    private final int mViewAlphaScale;
    private Xfermode mXfermode;

    static 
    {
        sScaleTypeArray = (new ScaleType[] {
            ScaleType.MATRIX, ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER, ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE
        });
        sS2FArray = (new android.graphics.Matrix.ScaleToFit[] {
            android.graphics.Matrix.ScaleToFit.FILL, android.graphics.Matrix.ScaleToFit.START, android.graphics.Matrix.ScaleToFit.CENTER, android.graphics.Matrix.ScaleToFit.END
        });
    }
}
