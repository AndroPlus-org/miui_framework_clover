// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.*;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class GradientDrawable extends Drawable
{
    static final class GradientState extends Drawable.ConstantState
    {

        static void _2D_wrap0(GradientState gradientstate)
        {
            gradientstate.computeOpacity();
        }

        private void applyDensityScaling(int i, int j)
        {
            if(mInnerRadius > 0)
                mInnerRadius = Drawable.scaleFromDensity(mInnerRadius, i, j, true);
            if(mThickness > 0)
                mThickness = Drawable.scaleFromDensity(mThickness, i, j, true);
            if(mOpticalInsets != Insets.NONE)
                mOpticalInsets = Insets.of(Drawable.scaleFromDensity(mOpticalInsets.left, i, j, true), Drawable.scaleFromDensity(mOpticalInsets.top, i, j, true), Drawable.scaleFromDensity(mOpticalInsets.right, i, j, true), Drawable.scaleFromDensity(mOpticalInsets.bottom, i, j, true));
            if(mPadding != null)
            {
                mPadding.left = Drawable.scaleFromDensity(mPadding.left, i, j, false);
                mPadding.top = Drawable.scaleFromDensity(mPadding.top, i, j, false);
                mPadding.right = Drawable.scaleFromDensity(mPadding.right, i, j, false);
                mPadding.bottom = Drawable.scaleFromDensity(mPadding.bottom, i, j, false);
            }
            if(mRadius > 0.0F)
                mRadius = Drawable.scaleFromDensity(mRadius, i, j);
            if(mRadiusArray != null)
            {
                mRadiusArray[0] = Drawable.scaleFromDensity((int)mRadiusArray[0], i, j, true);
                mRadiusArray[1] = Drawable.scaleFromDensity((int)mRadiusArray[1], i, j, true);
                mRadiusArray[2] = Drawable.scaleFromDensity((int)mRadiusArray[2], i, j, true);
                mRadiusArray[3] = Drawable.scaleFromDensity((int)mRadiusArray[3], i, j, true);
            }
            if(mStrokeWidth > 0)
                mStrokeWidth = Drawable.scaleFromDensity(mStrokeWidth, i, j, true);
            if(mStrokeDashWidth > 0.0F)
                mStrokeDashWidth = Drawable.scaleFromDensity(mStrokeDashGap, i, j);
            if(mStrokeDashGap > 0.0F)
                mStrokeDashGap = Drawable.scaleFromDensity(mStrokeDashGap, i, j);
            if(mGradientRadiusType == 0)
                mGradientRadius = Drawable.scaleFromDensity(mGradientRadius, i, j);
            if(mWidth > 0)
                mWidth = Drawable.scaleFromDensity(mWidth, i, j, true);
            if(mHeight > 0)
                mHeight = Drawable.scaleFromDensity(mHeight, i, j, true);
        }

        private void computeOpacity()
        {
            boolean flag = true;
            mOpaqueOverBounds = false;
            mOpaqueOverShape = false;
            if(mGradientColors != null)
            {
                for(int i = 0; i < mGradientColors.length; i++)
                    if(!GradientDrawable.isOpaque(mGradientColors[i]))
                        return;

            }
            if(mGradientColors == null && mSolidColors == null)
                return;
            mOpaqueOverShape = true;
            if(mShape == 0 && mRadius <= 0.0F)
            {
                if(mRadiusArray != null)
                    flag = false;
            } else
            {
                flag = false;
            }
            mOpaqueOverBounds = flag;
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mThemeAttrs != null || mAttrSize != null || mAttrGradient != null || mAttrSolid != null || mAttrStroke != null || mAttrCorners != null || mAttrPadding != null || mTint != null && mTint.canApplyTheme() || mStrokeColors != null && mStrokeColors.canApplyTheme() || mSolidColors != null && mSolidColors.canApplyTheme())
                flag = true;
            else
                flag = super.canApplyTheme();
            return flag;
        }

        public int getChangingConfigurations()
        {
            int i = 0;
            int j = mChangingConfigurations;
            int k;
            int l;
            if(mStrokeColors != null)
                k = mStrokeColors.getChangingConfigurations();
            else
                k = 0;
            if(mSolidColors != null)
                l = mSolidColors.getChangingConfigurations();
            else
                l = 0;
            if(mTint != null)
                i = mTint.getChangingConfigurations();
            return l | (j | k) | i;
        }

        public Drawable newDrawable()
        {
            return new GradientDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            GradientState gradientstate;
            if(Drawable.resolveDensity(resources, mDensity) != mDensity)
                gradientstate = new GradientState(this, resources);
            else
                gradientstate = this;
            return new GradientDrawable(gradientstate, resources, null);
        }

        public void setCornerRadii(float af[])
        {
            mRadiusArray = af;
            if(af == null)
                mRadius = 0.0F;
        }

        public void setCornerRadius(float f)
        {
            float f1 = f;
            if(f < 0.0F)
                f1 = 0.0F;
            mRadius = f1;
            mRadiusArray = null;
        }

        public final void setDensity(int i)
        {
            if(mDensity != i)
            {
                int j = mDensity;
                mDensity = i;
                applyDensityScaling(j, i);
            }
        }

        public void setGradientCenter(float f, float f1)
        {
            mCenterX = f;
            mCenterY = f1;
        }

        public void setGradientColors(int ai[])
        {
            mGradientColors = ai;
            mSolidColors = null;
            computeOpacity();
        }

        public void setGradientRadius(float f, int i)
        {
            mGradientRadius = f;
            mGradientRadiusType = i;
        }

        public void setGradientType(int i)
        {
            mGradient = i;
        }

        public void setShape(int i)
        {
            mShape = i;
            computeOpacity();
        }

        public void setSize(int i, int j)
        {
            mWidth = i;
            mHeight = j;
        }

        public void setSolidColors(ColorStateList colorstatelist)
        {
            mGradientColors = null;
            mSolidColors = colorstatelist;
            computeOpacity();
        }

        public void setStroke(int i, ColorStateList colorstatelist, float f, float f1)
        {
            mStrokeWidth = i;
            mStrokeColors = colorstatelist;
            mStrokeDashWidth = f;
            mStrokeDashGap = f1;
            computeOpacity();
        }

        public int mAngle;
        int mAttrCorners[];
        int mAttrGradient[];
        int mAttrPadding[];
        int mAttrSize[];
        int mAttrSolid[];
        int mAttrStroke[];
        float mCenterX;
        float mCenterY;
        public int mChangingConfigurations;
        int mDensity;
        public boolean mDither;
        public int mGradient;
        public int mGradientColors[];
        float mGradientRadius;
        int mGradientRadiusType;
        public int mHeight;
        public int mInnerRadius;
        public float mInnerRadiusRatio;
        boolean mOpaqueOverBounds;
        boolean mOpaqueOverShape;
        public Insets mOpticalInsets;
        public Orientation mOrientation;
        public Rect mPadding;
        public float mPositions[];
        public float mRadius;
        public float mRadiusArray[];
        public int mShape;
        public ColorStateList mSolidColors;
        public ColorStateList mStrokeColors;
        public float mStrokeDashGap;
        public float mStrokeDashWidth;
        public int mStrokeWidth;
        public int mTempColors[];
        public float mTempPositions[];
        int mThemeAttrs[];
        public int mThickness;
        public float mThicknessRatio;
        ColorStateList mTint;
        android.graphics.PorterDuff.Mode mTintMode;
        boolean mUseLevel;
        boolean mUseLevelForShape;
        public int mWidth;

        public GradientState(GradientState gradientstate, Resources resources)
        {
            mShape = 0;
            mGradient = 0;
            mAngle = 0;
            mStrokeWidth = -1;
            mStrokeDashWidth = 0.0F;
            mStrokeDashGap = 0.0F;
            mRadius = 0.0F;
            mRadiusArray = null;
            mPadding = null;
            mWidth = -1;
            mHeight = -1;
            mInnerRadiusRatio = 3F;
            mThicknessRatio = 9F;
            mInnerRadius = -1;
            mThickness = -1;
            mDither = false;
            mOpticalInsets = Insets.NONE;
            mCenterX = 0.5F;
            mCenterY = 0.5F;
            mGradientRadius = 0.5F;
            mGradientRadiusType = 0;
            mUseLevel = false;
            mUseLevelForShape = true;
            mTint = null;
            mTintMode = GradientDrawable.DEFAULT_TINT_MODE;
            mDensity = 160;
            mChangingConfigurations = gradientstate.mChangingConfigurations;
            mShape = gradientstate.mShape;
            mGradient = gradientstate.mGradient;
            mAngle = gradientstate.mAngle;
            mOrientation = gradientstate.mOrientation;
            mSolidColors = gradientstate.mSolidColors;
            if(gradientstate.mGradientColors != null)
                mGradientColors = (int[])gradientstate.mGradientColors.clone();
            if(gradientstate.mPositions != null)
                mPositions = (float[])gradientstate.mPositions.clone();
            mStrokeColors = gradientstate.mStrokeColors;
            mStrokeWidth = gradientstate.mStrokeWidth;
            mStrokeDashWidth = gradientstate.mStrokeDashWidth;
            mStrokeDashGap = gradientstate.mStrokeDashGap;
            mRadius = gradientstate.mRadius;
            if(gradientstate.mRadiusArray != null)
                mRadiusArray = (float[])gradientstate.mRadiusArray.clone();
            if(gradientstate.mPadding != null)
                mPadding = new Rect(gradientstate.mPadding);
            mWidth = gradientstate.mWidth;
            mHeight = gradientstate.mHeight;
            mInnerRadiusRatio = gradientstate.mInnerRadiusRatio;
            mThicknessRatio = gradientstate.mThicknessRatio;
            mInnerRadius = gradientstate.mInnerRadius;
            mThickness = gradientstate.mThickness;
            mDither = gradientstate.mDither;
            mOpticalInsets = gradientstate.mOpticalInsets;
            mCenterX = gradientstate.mCenterX;
            mCenterY = gradientstate.mCenterY;
            mGradientRadius = gradientstate.mGradientRadius;
            mGradientRadiusType = gradientstate.mGradientRadiusType;
            mUseLevel = gradientstate.mUseLevel;
            mUseLevelForShape = gradientstate.mUseLevelForShape;
            mOpaqueOverBounds = gradientstate.mOpaqueOverBounds;
            mOpaqueOverShape = gradientstate.mOpaqueOverShape;
            mTint = gradientstate.mTint;
            mTintMode = gradientstate.mTintMode;
            mThemeAttrs = gradientstate.mThemeAttrs;
            mAttrSize = gradientstate.mAttrSize;
            mAttrGradient = gradientstate.mAttrGradient;
            mAttrSolid = gradientstate.mAttrSolid;
            mAttrStroke = gradientstate.mAttrStroke;
            mAttrCorners = gradientstate.mAttrCorners;
            mAttrPadding = gradientstate.mAttrPadding;
            mDensity = Drawable.resolveDensity(resources, gradientstate.mDensity);
            if(gradientstate.mDensity != mDensity)
                applyDensityScaling(gradientstate.mDensity, mDensity);
        }

        public GradientState(Orientation orientation, int ai[])
        {
            mShape = 0;
            mGradient = 0;
            mAngle = 0;
            mStrokeWidth = -1;
            mStrokeDashWidth = 0.0F;
            mStrokeDashGap = 0.0F;
            mRadius = 0.0F;
            mRadiusArray = null;
            mPadding = null;
            mWidth = -1;
            mHeight = -1;
            mInnerRadiusRatio = 3F;
            mThicknessRatio = 9F;
            mInnerRadius = -1;
            mThickness = -1;
            mDither = false;
            mOpticalInsets = Insets.NONE;
            mCenterX = 0.5F;
            mCenterY = 0.5F;
            mGradientRadius = 0.5F;
            mGradientRadiusType = 0;
            mUseLevel = false;
            mUseLevelForShape = true;
            mTint = null;
            mTintMode = GradientDrawable.DEFAULT_TINT_MODE;
            mDensity = 160;
            mOrientation = orientation;
            setGradientColors(ai);
        }
    }

    public static final class Orientation extends Enum
    {

        public static Orientation valueOf(String s)
        {
            return (Orientation)Enum.valueOf(android/graphics/drawable/GradientDrawable$Orientation, s);
        }

        public static Orientation[] values()
        {
            return $VALUES;
        }

        private static final Orientation $VALUES[];
        public static final Orientation BL_TR;
        public static final Orientation BOTTOM_TOP;
        public static final Orientation BR_TL;
        public static final Orientation LEFT_RIGHT;
        public static final Orientation RIGHT_LEFT;
        public static final Orientation TL_BR;
        public static final Orientation TOP_BOTTOM;
        public static final Orientation TR_BL;

        static 
        {
            TOP_BOTTOM = new Orientation("TOP_BOTTOM", 0);
            TR_BL = new Orientation("TR_BL", 1);
            RIGHT_LEFT = new Orientation("RIGHT_LEFT", 2);
            BR_TL = new Orientation("BR_TL", 3);
            BOTTOM_TOP = new Orientation("BOTTOM_TOP", 4);
            BL_TR = new Orientation("BL_TR", 5);
            LEFT_RIGHT = new Orientation("LEFT_RIGHT", 6);
            TL_BR = new Orientation("TL_BR", 7);
            $VALUES = (new Orientation[] {
                TOP_BOTTOM, TR_BL, RIGHT_LEFT, BR_TL, BOTTOM_TOP, BL_TR, LEFT_RIGHT, TL_BR
            });
        }

        private Orientation(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getandroid_2D_graphics_2D_drawable_2D_GradientDrawable$OrientationSwitchesValues()
    {
        if(_2D_android_2D_graphics_2D_drawable_2D_GradientDrawable$OrientationSwitchesValues != null)
            return _2D_android_2D_graphics_2D_drawable_2D_GradientDrawable$OrientationSwitchesValues;
        int ai[] = new int[Orientation.values().length];
        try
        {
            ai[Orientation.BL_TR.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[Orientation.BOTTOM_TOP.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[Orientation.BR_TL.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[Orientation.LEFT_RIGHT.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Orientation.RIGHT_LEFT.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Orientation.TL_BR.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Orientation.TOP_BOTTOM.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Orientation.TR_BL.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_graphics_2D_drawable_2D_GradientDrawable$OrientationSwitchesValues = ai;
        return ai;
    }

    public GradientDrawable()
    {
        this(new GradientState(Orientation.TOP_BOTTOM, null), ((Resources) (null)));
    }

    private GradientDrawable(GradientState gradientstate, Resources resources)
    {
        mFillPaint = new Paint(1);
        mAlpha = 255;
        mPath = new Path();
        mRect = new RectF();
        mPathIsDirty = true;
        mGradientState = gradientstate;
        updateLocalState(resources);
    }

    GradientDrawable(GradientState gradientstate, Resources resources, GradientDrawable gradientdrawable)
    {
        this(gradientstate, resources);
    }

    public GradientDrawable(Orientation orientation, int ai[])
    {
        this(new GradientState(orientation, ai), ((Resources) (null)));
    }

    private void applyThemeChildElements(android.content.res.Resources.Theme theme)
    {
        GradientState gradientstate;
        TypedArray typedarray1;
        gradientstate = mGradientState;
        if(gradientstate.mAttrSize != null)
        {
            TypedArray typedarray = theme.resolveAttributes(gradientstate.mAttrSize, com.android.internal.R.styleable.GradientDrawableSize);
            updateGradientDrawableSize(typedarray);
            typedarray.recycle();
        }
        if(gradientstate.mAttrGradient == null)
            break MISSING_BLOCK_LABEL_65;
        typedarray1 = theme.resolveAttributes(gradientstate.mAttrGradient, com.android.internal.R.styleable.GradientDrawableGradient);
        updateGradientDrawableGradient(theme.getResources(), typedarray1);
        typedarray1.recycle();
_L2:
        if(gradientstate.mAttrSolid != null)
        {
            typedarray1 = theme.resolveAttributes(gradientstate.mAttrSolid, com.android.internal.R.styleable.GradientDrawableSolid);
            updateGradientDrawableSolid(typedarray1);
            typedarray1.recycle();
        }
        if(gradientstate.mAttrStroke != null)
        {
            typedarray1 = theme.resolveAttributes(gradientstate.mAttrStroke, com.android.internal.R.styleable.GradientDrawableStroke);
            updateGradientDrawableStroke(typedarray1);
            typedarray1.recycle();
        }
        if(gradientstate.mAttrCorners != null)
        {
            typedarray1 = theme.resolveAttributes(gradientstate.mAttrCorners, com.android.internal.R.styleable.DrawableCorners);
            updateDrawableCorners(typedarray1);
            typedarray1.recycle();
        }
        if(gradientstate.mAttrPadding != null)
        {
            theme = theme.resolveAttributes(gradientstate.mAttrPadding, com.android.internal.R.styleable.GradientDrawablePadding);
            updateGradientDrawablePadding(theme);
            theme.recycle();
        }
        return;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        rethrowAsRuntimeException(xmlpullparserexception);
        typedarray1.recycle();
        if(true) goto _L2; else goto _L1
_L1:
        theme;
        typedarray1.recycle();
        throw theme;
    }

    private void buildPathIfDirty()
    {
        GradientState gradientstate = mGradientState;
        if(mPathIsDirty)
        {
            ensureValidRect();
            mPath.reset();
            mPath.addRoundRect(mRect, gradientstate.mRadiusArray, android.graphics.Path.Direction.CW);
            mPathIsDirty = false;
        }
    }

    private Path buildRing(GradientState gradientstate)
    {
        if(mRingPath != null && (!gradientstate.mUseLevelForShape || mPathIsDirty ^ true))
            return mRingPath;
        mPathIsDirty = false;
        float f;
        Object obj;
        float f1;
        float f2;
        float f3;
        float f4;
        RectF rectf;
        if(gradientstate.mUseLevelForShape)
            f = ((float)getLevel() * 360F) / 10000F;
        else
            f = 360F;
        obj = new RectF(mRect);
        f1 = ((RectF) (obj)).width() / 2.0F;
        f2 = ((RectF) (obj)).height() / 2.0F;
        if(gradientstate.mThickness != -1)
            f3 = gradientstate.mThickness;
        else
            f3 = ((RectF) (obj)).width() / gradientstate.mThicknessRatio;
        if(gradientstate.mInnerRadius != -1)
            f4 = gradientstate.mInnerRadius;
        else
            f4 = ((RectF) (obj)).width() / gradientstate.mInnerRadiusRatio;
        rectf = new RectF(((RectF) (obj)));
        rectf.inset(f1 - f4, f2 - f4);
        gradientstate = new RectF(rectf);
        gradientstate.inset(-f3, -f3);
        if(mRingPath == null)
            mRingPath = new Path();
        else
            mRingPath.reset();
        obj = mRingPath;
        if(f < 360F && f > -360F)
        {
            ((Path) (obj)).setFillType(android.graphics.Path.FillType.EVEN_ODD);
            ((Path) (obj)).moveTo(f1 + f4, f2);
            ((Path) (obj)).lineTo(f1 + f4 + f3, f2);
            ((Path) (obj)).arcTo(gradientstate, 0.0F, f, false);
            ((Path) (obj)).arcTo(rectf, f, -f, false);
            ((Path) (obj)).close();
        } else
        {
            ((Path) (obj)).addOval(gradientstate, android.graphics.Path.Direction.CW);
            ((Path) (obj)).addOval(rectf, android.graphics.Path.Direction.CCW);
        }
        return ((Path) (obj));
    }

    private boolean ensureValidRect()
    {
        if(!mGradientIsDirty) goto _L2; else goto _L1
_L1:
        GradientState gradientstate;
        float af[];
        mGradientIsDirty = false;
        Rect rect = getBounds();
        float f = 0.0F;
        if(mStrokePaint != null)
            f = mStrokePaint.getStrokeWidth() * 0.5F;
        gradientstate = mGradientState;
        mRect.set((float)rect.left + f, (float)rect.top + f, (float)rect.right - f, (float)rect.bottom - f);
        af = gradientstate.mGradientColors;
        if(af == null) goto _L2; else goto _L3
_L3:
        RectF rectf;
        float f1;
        float f14;
        float f17;
        float f20;
        rectf = mRect;
        if(gradientstate.mGradient != 0)
            break MISSING_BLOCK_LABEL_496;
        float f4;
        if(gradientstate.mUseLevel)
            f1 = (float)getLevel() / 10000F;
        else
            f1 = 1.0F;
        _2D_getandroid_2D_graphics_2D_drawable_2D_GradientDrawable$OrientationSwitchesValues()[gradientstate.mOrientation.ordinal()];
        JVM INSTR tableswitch 1 7: default 172
    //                   1 432
    //                   2 398
    //                   3 364
    //                   4 466
    //                   5 334
    //                   6 266
    //                   7 300;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L4:
        f4 = rectf.left;
        f14 = rectf.top;
        f17 = f1 * rectf.right;
        f20 = f1 * rectf.bottom;
        f1 = f4;
_L12:
        mFillPaint.setShader(new LinearGradient(f1, f14, f17, f20, af, gradientstate.mPositions, android.graphics.Shader.TileMode.CLAMP));
_L13:
        if(gradientstate.mSolidColors == null)
            mFillPaint.setColor(0xff000000);
_L2:
        return mRect.isEmpty() ^ true;
_L10:
        f14 = rectf.left;
        float f5 = rectf.top;
        f17 = f14;
        f20 = f1 * rectf.bottom;
        f1 = f14;
        f14 = f5;
          goto _L12
_L11:
        float f6 = rectf.right;
        f14 = rectf.top;
        f17 = f1 * rectf.left;
        f20 = f1 * rectf.bottom;
        f1 = f6;
          goto _L12
_L9:
        float f7 = rectf.right;
        f14 = rectf.top;
        f17 = f1 * rectf.left;
        f20 = f14;
        f1 = f7;
          goto _L12
_L7:
        float f8 = rectf.right;
        f14 = rectf.bottom;
        f17 = f1 * rectf.left;
        f20 = f1 * rectf.top;
        f1 = f8;
          goto _L12
_L6:
        f14 = rectf.left;
        float f9 = rectf.bottom;
        f17 = f14;
        f20 = f1 * rectf.top;
        f1 = f14;
        f14 = f9;
          goto _L12
_L5:
        float f10 = rectf.left;
        f14 = rectf.bottom;
        f17 = f1 * rectf.right;
        f20 = f1 * rectf.top;
        f1 = f10;
          goto _L12
_L8:
        float f11 = rectf.left;
        f14 = rectf.top;
        f17 = f1 * rectf.right;
        f20 = f14;
        f1 = f11;
          goto _L12
        if(gradientstate.mGradient == 1)
        {
            float f23 = rectf.left;
            float f25 = rectf.right;
            float f21 = rectf.left;
            float f27 = gradientstate.mCenterX;
            float f12 = rectf.top;
            float f29 = rectf.bottom;
            float f31 = rectf.top;
            float f33 = gradientstate.mCenterY;
            float f18 = gradientstate.mGradientRadius;
            float f2;
            if(gradientstate.mGradientRadiusType == 1)
            {
                float f15;
                if(gradientstate.mWidth >= 0)
                    f2 = gradientstate.mWidth;
                else
                    f2 = rectf.width();
                if(gradientstate.mHeight >= 0)
                    f15 = gradientstate.mHeight;
                else
                    f15 = rectf.height();
                f2 = f18 * Math.min(f2, f15);
            } else
            {
                f2 = f18;
                if(gradientstate.mGradientRadiusType == 2)
                    f2 = f18 * Math.min(rectf.width(), rectf.height());
            }
            f15 = f2;
            if(gradientstate.mUseLevel)
                f15 = f2 * ((float)getLevel() / 10000F);
            mGradientRadius = f15;
            f2 = f15;
            if(f15 <= 0.0F)
                f2 = 0.001F;
            mFillPaint.setShader(new RadialGradient(f23 + (f25 - f21) * f27, f12 + (f29 - f31) * f33, f2, af, null, android.graphics.Shader.TileMode.CLAMP));
        } else
        if(gradientstate.mGradient == 2)
        {
            int ai[];
            float f3;
            float f16;
            float f19;
            float f24;
            float f26;
            float f28;
            float f30;
            float f32;
            float af1[];
label0:
            {
                int i;
label1:
                {
                    f32 = rectf.left;
                    f19 = rectf.right;
                    f26 = rectf.left;
                    f30 = gradientstate.mCenterX;
                    f3 = rectf.top;
                    f28 = rectf.bottom;
                    f24 = rectf.top;
                    f16 = gradientstate.mCenterY;
                    ai = af;
                    af1 = null;
                    if(!gradientstate.mUseLevel)
                        break label0;
                    af1 = gradientstate.mTempColors;
                    i = af.length;
                    if(af1 != null)
                    {
                        ai = af1;
                        if(af1.length == i + 1)
                            break label1;
                    }
                    ai = new int[i + 1];
                    gradientstate.mTempColors = ai;
                }
                float f13;
label2:
                {
                    System.arraycopy(af, 0, ai, 0, i);
                    ai[i] = af[i - 1];
                    af = gradientstate.mTempPositions;
                    f13 = 1.0F / (float)(i - 1);
                    if(af != null)
                    {
                        af1 = af;
                        if(af.length == i + 1)
                            break label2;
                    }
                    af1 = new float[i + 1];
                    gradientstate.mTempPositions = af1;
                }
                float f22 = (float)getLevel() / 10000F;
                for(int j = 0; j < i; j++)
                    af1[j] = (float)j * f13 * f22;

                af1[i] = 1.0F;
            }
            mFillPaint.setShader(new SweepGradient(f32 + (f19 - f26) * f30, f3 + (f28 - f24) * f16, ai, af1));
        }
          goto _L13
    }

    private static float getFloatOrFraction(TypedArray typedarray, int i, float f)
    {
        typedarray = typedarray.peekValue(i);
        if(typedarray != null)
        {
            if(((TypedValue) (typedarray)).type == 6)
                i = 1;
            else
                i = 0;
            if(i != 0)
                f = typedarray.getFraction(1.0F, 1.0F);
            else
                f = typedarray.getFloat();
        }
        return f;
    }

    private void inflateChildElements(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth() + 1;
        do
        {
            int j = xmlpullparser.next();
            if(j == 1)
                break;
            int k = xmlpullparser.getDepth();
            if(k < i && j == 3)
                break;
            if(j == 2 && k <= i)
            {
                Object obj = xmlpullparser.getName();
                if(((String) (obj)).equals("size"))
                {
                    obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientDrawableSize);
                    updateGradientDrawableSize(((TypedArray) (obj)));
                    ((TypedArray) (obj)).recycle();
                } else
                if(((String) (obj)).equals("gradient"))
                {
                    obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientDrawableGradient);
                    updateGradientDrawableGradient(resources, ((TypedArray) (obj)));
                    ((TypedArray) (obj)).recycle();
                } else
                if(((String) (obj)).equals("solid"))
                {
                    obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientDrawableSolid);
                    updateGradientDrawableSolid(((TypedArray) (obj)));
                    ((TypedArray) (obj)).recycle();
                } else
                if(((String) (obj)).equals("stroke"))
                {
                    obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientDrawableStroke);
                    updateGradientDrawableStroke(((TypedArray) (obj)));
                    ((TypedArray) (obj)).recycle();
                } else
                if(((String) (obj)).equals("corners"))
                {
                    obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.DrawableCorners);
                    updateDrawableCorners(((TypedArray) (obj)));
                    ((TypedArray) (obj)).recycle();
                } else
                if(((String) (obj)).equals("padding"))
                {
                    obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientDrawablePadding);
                    updateGradientDrawablePadding(((TypedArray) (obj)));
                    ((TypedArray) (obj)).recycle();
                } else
                {
                    Log.w("drawable", (new StringBuilder()).append("Bad element under <shape>: ").append(((String) (obj))).toString());
                }
            }
        } while(true);
    }

    static boolean isOpaque(int i)
    {
        boolean flag;
        if((i >> 24 & 0xff) == 255)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isOpaqueForState()
    {
        if(mGradientState.mStrokeWidth >= 0 && mStrokePaint != null && isOpaque(mStrokePaint.getColor()) ^ true)
            return false;
        return mGradientState.mGradientColors != null || !(isOpaque(mFillPaint.getColor()) ^ true);
    }

    private int modulateAlpha(int i)
    {
        return i * (mAlpha + (mAlpha >> 7)) >> 8;
    }

    private void setStrokeInternal(int i, int j, float f, float f1)
    {
        if(mStrokePaint == null)
        {
            mStrokePaint = new Paint(1);
            mStrokePaint.setStyle(android.graphics.Paint.Style.STROKE);
        }
        mStrokePaint.setStrokeWidth(i);
        mStrokePaint.setColor(j);
        DashPathEffect dashpatheffect = null;
        if(f > 0.0F)
            dashpatheffect = new DashPathEffect(new float[] {
                f, f1
            }, 0.0F);
        mStrokePaint.setPathEffect(dashpatheffect);
        invalidateSelf();
    }

    private void updateDrawableCorners(TypedArray typedarray)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        GradientState gradientstate = mGradientState;
        gradientstate.mChangingConfigurations = gradientstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        gradientstate.mAttrCorners = typedarray.extractThemeAttrs();
        i = typedarray.getDimensionPixelSize(0, (int)gradientstate.mRadius);
        setCornerRadius(i);
        j = typedarray.getDimensionPixelSize(1, i);
        k = typedarray.getDimensionPixelSize(2, i);
        l = typedarray.getDimensionPixelSize(3, i);
        i1 = typedarray.getDimensionPixelSize(4, i);
        break MISSING_BLOCK_LABEL_75;
        if(j != i || k != i || l != i || i1 != i)
            setCornerRadii(new float[] {
                (float)j, (float)j, (float)k, (float)k, (float)i1, (float)i1, (float)l, (float)l
            });
        return;
    }

    private void updateGradientDrawableGradient(Resources resources, TypedArray typedarray)
        throws XmlPullParserException
    {
        GradientState gradientstate;
        int i;
        gradientstate = mGradientState;
        gradientstate.mChangingConfigurations = gradientstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        gradientstate.mAttrGradient = typedarray.extractThemeAttrs();
        gradientstate.mCenterX = getFloatOrFraction(typedarray, 5, gradientstate.mCenterX);
        gradientstate.mCenterY = getFloatOrFraction(typedarray, 6, gradientstate.mCenterY);
        gradientstate.mUseLevel = typedarray.getBoolean(2, gradientstate.mUseLevel);
        gradientstate.mGradient = typedarray.getInt(4, gradientstate.mGradient);
        i = typedarray.getColor(0, 0);
        boolean flag = typedarray.hasValue(8);
        int k = typedarray.getColor(8, 0);
        int l = typedarray.getColor(1, 0);
        if(flag)
        {
            gradientstate.mGradientColors = new int[3];
            gradientstate.mGradientColors[0] = i;
            gradientstate.mGradientColors[1] = k;
            gradientstate.mGradientColors[2] = l;
            gradientstate.mPositions = new float[3];
            gradientstate.mPositions[0] = 0.0F;
            float af[] = gradientstate.mPositions;
            float f;
            if(gradientstate.mCenterX != 0.5F)
                f = gradientstate.mCenterX;
            else
                f = gradientstate.mCenterY;
            af[1] = f;
            gradientstate.mPositions[2] = 1.0F;
        } else
        {
            gradientstate.mGradientColors = new int[2];
            gradientstate.mGradientColors[0] = i;
            gradientstate.mGradientColors[1] = l;
        }
        if(gradientstate.mGradient != 0) goto _L2; else goto _L1
_L1:
        i = (int)typedarray.getFloat(3, gradientstate.mAngle) % 360;
        if(i % 45 != 0)
            throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<gradient> tag requires 'angle' attribute to ").append("be a multiple of 45").toString());
        gradientstate.mAngle = i;
        i;
        JVM INSTR lookupswitch 8: default 384
    //                   0: 385
    //                   45: 395
    //                   90: 405
    //                   135: 415
    //                   180: 425
    //                   225: 435
    //                   270: 445
    //                   315: 455;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
        return;
_L4:
        gradientstate.mOrientation = Orientation.LEFT_RIGHT;
        continue; /* Loop/switch isn't completed */
_L5:
        gradientstate.mOrientation = Orientation.BL_TR;
        continue; /* Loop/switch isn't completed */
_L6:
        gradientstate.mOrientation = Orientation.BOTTOM_TOP;
        continue; /* Loop/switch isn't completed */
_L7:
        gradientstate.mOrientation = Orientation.BR_TL;
        continue; /* Loop/switch isn't completed */
_L8:
        gradientstate.mOrientation = Orientation.RIGHT_LEFT;
        continue; /* Loop/switch isn't completed */
_L9:
        gradientstate.mOrientation = Orientation.TR_BL;
        continue; /* Loop/switch isn't completed */
_L10:
        gradientstate.mOrientation = Orientation.TOP_BOTTOM;
        continue; /* Loop/switch isn't completed */
_L11:
        gradientstate.mOrientation = Orientation.TL_BR;
        continue; /* Loop/switch isn't completed */
_L2:
        TypedValue typedvalue = typedarray.peekValue(7);
        if(typedvalue == null)
            break; /* Loop/switch isn't completed */
        int j;
        float f1;
        if(typedvalue.type == 6)
        {
            f1 = typedvalue.getFraction(1.0F, 1.0F);
            if((typedvalue.data >> 0 & 0xf) == 1)
                j = 2;
            else
                j = 1;
        } else
        if(typedvalue.type == 5)
        {
            f1 = typedvalue.getDimension(resources.getDisplayMetrics());
            j = 0;
        } else
        {
            f1 = typedvalue.getFloat();
            j = 0;
        }
        gradientstate.mGradientRadius = f1;
        gradientstate.mGradientRadiusType = j;
        if(true) goto _L3; else goto _L12
_L12:
        if(gradientstate.mGradient != 1) goto _L3; else goto _L13
_L13:
        throw new XmlPullParserException((new StringBuilder()).append(typedarray.getPositionDescription()).append("<gradient> tag requires 'gradientRadius' ").append("attribute with radial type").toString());
    }

    private void updateGradientDrawablePadding(TypedArray typedarray)
    {
        Object obj = mGradientState;
        obj.mChangingConfigurations = ((GradientState) (obj)).mChangingConfigurations | typedarray.getChangingConfigurations();
        obj.mAttrPadding = typedarray.extractThemeAttrs();
        if(((GradientState) (obj)).mPadding == null)
            obj.mPadding = new Rect();
        obj = ((GradientState) (obj)).mPadding;
        ((Rect) (obj)).set(typedarray.getDimensionPixelOffset(0, ((Rect) (obj)).left), typedarray.getDimensionPixelOffset(1, ((Rect) (obj)).top), typedarray.getDimensionPixelOffset(2, ((Rect) (obj)).right), typedarray.getDimensionPixelOffset(3, ((Rect) (obj)).bottom));
        mPadding = ((Rect) (obj));
    }

    private void updateGradientDrawableSize(TypedArray typedarray)
    {
        GradientState gradientstate = mGradientState;
        gradientstate.mChangingConfigurations = gradientstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        gradientstate.mAttrSize = typedarray.extractThemeAttrs();
        gradientstate.mWidth = typedarray.getDimensionPixelSize(1, gradientstate.mWidth);
        gradientstate.mHeight = typedarray.getDimensionPixelSize(0, gradientstate.mHeight);
    }

    private void updateGradientDrawableSolid(TypedArray typedarray)
    {
        GradientState gradientstate = mGradientState;
        gradientstate.mChangingConfigurations = gradientstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        gradientstate.mAttrSolid = typedarray.extractThemeAttrs();
        typedarray = typedarray.getColorStateList(0);
        if(typedarray != null)
            setColor(typedarray);
    }

    private void updateGradientDrawableStroke(TypedArray typedarray)
    {
        GradientState gradientstate = mGradientState;
        gradientstate.mChangingConfigurations = gradientstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        gradientstate.mAttrStroke = typedarray.extractThemeAttrs();
        int i = typedarray.getDimensionPixelSize(0, Math.max(0, gradientstate.mStrokeWidth));
        float f = typedarray.getDimension(2, gradientstate.mStrokeDashWidth);
        ColorStateList colorstatelist = typedarray.getColorStateList(1);
        ColorStateList colorstatelist1 = colorstatelist;
        if(colorstatelist == null)
            colorstatelist1 = gradientstate.mStrokeColors;
        if(f != 0.0F)
            setStroke(i, colorstatelist1, f, typedarray.getDimension(3, gradientstate.mStrokeDashGap));
        else
            setStroke(i, colorstatelist1);
    }

    private void updateLocalState(Resources resources)
    {
        resources = mGradientState;
        if(((GradientState) (resources)).mSolidColors != null)
        {
            int ai[] = getState();
            int i = ((GradientState) (resources)).mSolidColors.getColorForState(ai, 0);
            mFillPaint.setColor(i);
        } else
        if(((GradientState) (resources)).mGradientColors == null)
            mFillPaint.setColor(0);
        else
            mFillPaint.setColor(0xff000000);
        mPadding = ((GradientState) (resources)).mPadding;
        if(((GradientState) (resources)).mStrokeWidth >= 0)
        {
            mStrokePaint = new Paint(1);
            mStrokePaint.setStyle(android.graphics.Paint.Style.STROKE);
            mStrokePaint.setStrokeWidth(((GradientState) (resources)).mStrokeWidth);
            if(((GradientState) (resources)).mStrokeColors != null)
            {
                int ai1[] = getState();
                int j = ((GradientState) (resources)).mStrokeColors.getColorForState(ai1, 0);
                mStrokePaint.setColor(j);
            }
            if(((GradientState) (resources)).mStrokeDashWidth != 0.0F)
            {
                DashPathEffect dashpatheffect = new DashPathEffect(new float[] {
                    ((GradientState) (resources)).mStrokeDashWidth, ((GradientState) (resources)).mStrokeDashGap
                }, 0.0F);
                mStrokePaint.setPathEffect(dashpatheffect);
            }
        }
        mTintFilter = updateTintFilter(mTintFilter, ((GradientState) (resources)).mTint, ((GradientState) (resources)).mTintMode);
        mGradientIsDirty = true;
        GradientState._2D_wrap0(resources);
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        GradientState gradientstate = mGradientState;
        gradientstate.mChangingConfigurations = gradientstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        gradientstate.mThemeAttrs = typedarray.extractThemeAttrs();
        gradientstate.mShape = typedarray.getInt(3, gradientstate.mShape);
        gradientstate.mDither = typedarray.getBoolean(0, gradientstate.mDither);
        if(gradientstate.mShape == 3)
        {
            gradientstate.mInnerRadius = typedarray.getDimensionPixelSize(7, gradientstate.mInnerRadius);
            if(gradientstate.mInnerRadius == -1)
                gradientstate.mInnerRadiusRatio = typedarray.getFloat(4, gradientstate.mInnerRadiusRatio);
            gradientstate.mThickness = typedarray.getDimensionPixelSize(8, gradientstate.mThickness);
            if(gradientstate.mThickness == -1)
                gradientstate.mThicknessRatio = typedarray.getFloat(5, gradientstate.mThicknessRatio);
            gradientstate.mUseLevelForShape = typedarray.getBoolean(6, gradientstate.mUseLevelForShape);
        }
        int i = typedarray.getInt(9, -1);
        if(i != -1)
            gradientstate.mTintMode = Drawable.parseTintMode(i, android.graphics.PorterDuff.Mode.SRC_IN);
        ColorStateList colorstatelist = typedarray.getColorStateList(1);
        if(colorstatelist != null)
            gradientstate.mTint = colorstatelist;
        gradientstate.mOpticalInsets = Insets.of(typedarray.getDimensionPixelSize(11, gradientstate.mOpticalInsets.left), typedarray.getDimensionPixelSize(13, gradientstate.mOpticalInsets.top), typedarray.getDimensionPixelSize(12, gradientstate.mOpticalInsets.right), typedarray.getDimensionPixelSize(10, gradientstate.mOpticalInsets.bottom));
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        GradientState gradientstate = mGradientState;
        if(gradientstate == null)
            return;
        gradientstate.setDensity(Drawable.resolveDensity(theme.getResources(), 0));
        if(gradientstate.mThemeAttrs != null)
        {
            TypedArray typedarray = theme.resolveAttributes(gradientstate.mThemeAttrs, com.android.internal.R.styleable.GradientDrawable);
            updateStateFromTypedArray(typedarray);
            typedarray.recycle();
        }
        if(gradientstate.mTint != null && gradientstate.mTint.canApplyTheme())
            gradientstate.mTint = gradientstate.mTint.obtainForTheme(theme);
        if(gradientstate.mSolidColors != null && gradientstate.mSolidColors.canApplyTheme())
            gradientstate.mSolidColors = gradientstate.mSolidColors.obtainForTheme(theme);
        if(gradientstate.mStrokeColors != null && gradientstate.mStrokeColors.canApplyTheme())
            gradientstate.mStrokeColors = gradientstate.mStrokeColors.obtainForTheme(theme);
        applyThemeChildElements(theme);
        updateLocalState(theme.getResources());
    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(mGradientState == null || !mGradientState.canApplyTheme())
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
        int i;
        int j;
        boolean flag;
        GradientState gradientstate;
        Object obj;
        if(!ensureValidRect())
            return;
        i = mFillPaint.getAlpha();
        int k;
        int l;
        boolean flag1;
        if(mStrokePaint != null)
            j = mStrokePaint.getAlpha();
        else
            j = 0;
        k = modulateAlpha(i);
        l = modulateAlpha(j);
        if(l > 0 && mStrokePaint != null)
        {
            if(mStrokePaint.getStrokeWidth() > 0.0F)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(k > 0)
            flag1 = true;
        else
            flag1 = false;
        gradientstate = mGradientState;
        if(mColorFilter != null)
            obj = mColorFilter;
        else
            obj = mTintFilter;
        if(flag && flag1 && gradientstate.mShape != 2 && l < 255)
        {
            if(mAlpha < 255 || obj != null)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = false;
        }
        if(flag1)
        {
            if(mLayerPaint == null)
                mLayerPaint = new Paint();
            mLayerPaint.setDither(gradientstate.mDither);
            mLayerPaint.setAlpha(mAlpha);
            mLayerPaint.setColorFilter(((ColorFilter) (obj)));
            float f = mStrokePaint.getStrokeWidth();
            canvas.saveLayer(mRect.left - f, mRect.top - f, mRect.right + f, mRect.bottom + f, mLayerPaint);
            mFillPaint.setColorFilter(null);
            mStrokePaint.setColorFilter(null);
        } else
        {
            mFillPaint.setAlpha(k);
            mFillPaint.setDither(gradientstate.mDither);
            mFillPaint.setColorFilter(((ColorFilter) (obj)));
            if(obj != null && gradientstate.mSolidColors == null)
                mFillPaint.setColor(mAlpha << 24);
            if(flag)
            {
                mStrokePaint.setAlpha(l);
                mStrokePaint.setDither(gradientstate.mDither);
                mStrokePaint.setColorFilter(((ColorFilter) (obj)));
            }
        }
        gradientstate.mShape;
        JVM INSTR tableswitch 0 3: default 312
    //                   0 463
    //                   1 648
    //                   2 680
    //                   3 723;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        break; /* Loop/switch isn't completed */
_L5:
        break MISSING_BLOCK_LABEL_723;
_L6:
        float f1;
        if(flag1)
        {
            canvas.restore();
        } else
        {
            mFillPaint.setAlpha(i);
            if(flag)
                mStrokePaint.setAlpha(j);
        }
        return;
_L2:
label0:
        {
            if(gradientstate.mRadiusArray == null)
                break label0;
            buildPathIfDirty();
            canvas.drawPath(mPath, mFillPaint);
            if(flag)
                canvas.drawPath(mPath, mStrokePaint);
        }
          goto _L6
        if(gradientstate.mRadius > 0.0F)
        {
            f1 = Math.min(gradientstate.mRadius, Math.min(mRect.width(), mRect.height()) * 0.5F);
            canvas.drawRoundRect(mRect, f1, f1, mFillPaint);
            if(flag)
                canvas.drawRoundRect(mRect, f1, f1, mStrokePaint);
        } else
        {
            if(mFillPaint.getColor() != 0 || obj != null || mFillPaint.getShader() != null)
                canvas.drawRect(mRect, mFillPaint);
            if(flag)
                canvas.drawRect(mRect, mStrokePaint);
        }
          goto _L6
_L3:
        canvas.drawOval(mRect, mFillPaint);
        if(flag)
            canvas.drawOval(mRect, mStrokePaint);
          goto _L6
_L4:
        obj = mRect;
        f1 = ((RectF) (obj)).centerY();
        if(flag)
            canvas.drawLine(((RectF) (obj)).left, f1, ((RectF) (obj)).right, f1, mStrokePaint);
          goto _L6
        obj = buildRing(gradientstate);
        canvas.drawPath(((Path) (obj)), mFillPaint);
        if(flag)
            canvas.drawPath(((Path) (obj)), mStrokePaint);
          goto _L6
    }

    public int getAlpha()
    {
        return mAlpha;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mGradientState.getChangingConfigurations();
    }

    public ColorStateList getColor()
    {
        return mGradientState.mSolidColors;
    }

    public ColorFilter getColorFilter()
    {
        return mColorFilter;
    }

    public int[] getColors()
    {
        int ai[] = null;
        if(mGradientState.mGradientColors != null)
            ai = (int[])mGradientState.mGradientColors.clone();
        return ai;
    }

    public Drawable.ConstantState getConstantState()
    {
        mGradientState.mChangingConfigurations = getChangingConfigurations();
        return mGradientState;
    }

    public float[] getCornerRadii()
    {
        return (float[])mGradientState.mRadiusArray.clone();
    }

    public float getCornerRadius()
    {
        return mGradientState.mRadius;
    }

    public float getGradientCenterX()
    {
        return mGradientState.mCenterX;
    }

    public float getGradientCenterY()
    {
        return mGradientState.mCenterY;
    }

    public float getGradientRadius()
    {
        if(mGradientState.mGradient != 1)
        {
            return 0.0F;
        } else
        {
            ensureValidRect();
            return mGradientRadius;
        }
    }

    public int getGradientType()
    {
        return mGradientState.mGradient;
    }

    public int getIntrinsicHeight()
    {
        return mGradientState.mHeight;
    }

    public int getIntrinsicWidth()
    {
        return mGradientState.mWidth;
    }

    public int getOpacity()
    {
        byte byte0;
        if(mAlpha == 255 && mGradientState.mOpaqueOverBounds && isOpaqueForState())
            byte0 = -1;
        else
            byte0 = -3;
        return byte0;
    }

    public Insets getOpticalInsets()
    {
        return mGradientState.mOpticalInsets;
    }

    public Orientation getOrientation()
    {
        return mGradientState.mOrientation;
    }

    public void getOutline(Outline outline)
    {
        GradientState gradientstate = mGradientState;
        Rect rect = getBounds();
        boolean flag;
        float f;
        if(gradientstate.mOpaqueOverShape)
        {
            if(mGradientState.mStrokeWidth <= 0 || mStrokePaint == null)
                flag = true;
            else
            if(mStrokePaint.getAlpha() == mFillPaint.getAlpha())
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(flag)
            f = (float)modulateAlpha(mFillPaint.getAlpha()) / 255F;
        else
            f = 0.0F;
        outline.setAlpha(f);
        switch(gradientstate.mShape)
        {
        default:
            return;

        case 0: // '\0'
            if(gradientstate.mRadiusArray != null)
            {
                buildPathIfDirty();
                outline.setConvexPath(mPath);
                return;
            }
            f = 0.0F;
            if(gradientstate.mRadius > 0.0F)
                f = Math.min(gradientstate.mRadius, (float)Math.min(rect.width(), rect.height()) * 0.5F);
            outline.setRoundRect(rect, f);
            return;

        case 1: // '\001'
            outline.setOval(rect);
            return;

        case 2: // '\002'
            break;
        }
        int i;
        float f1;
        int j;
        if(mStrokePaint == null)
            f = 0.0001F;
        else
            f = mStrokePaint.getStrokeWidth() * 0.5F;
        f1 = rect.centerY();
        j = (int)Math.floor(f1 - f);
        i = (int)Math.ceil(f1 + f);
        outline.setRect(rect.left, j, rect.right, i);
    }

    public boolean getPadding(Rect rect)
    {
        if(mPadding != null)
        {
            rect.set(mPadding);
            return true;
        } else
        {
            return super.getPadding(rect);
        }
    }

    public int getShape()
    {
        return mGradientState.mShape;
    }

    public boolean getUseLevel()
    {
        return mGradientState.mUseLevel;
    }

    public boolean hasFocusStateSpecified()
    {
        GradientState gradientstate = mGradientState;
        boolean flag;
        if((gradientstate.mSolidColors == null || !gradientstate.mSolidColors.hasFocusStateSpecified()) && (gradientstate.mStrokeColors == null || !gradientstate.mStrokeColors.hasFocusStateSpecified()))
        {
            if(gradientstate.mTint != null)
                flag = gradientstate.mTint.hasFocusStateSpecified();
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        super.inflate(resources, xmlpullparser, attributeset, theme);
        mGradientState.setDensity(Drawable.resolveDensity(resources, 0));
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.GradientDrawable);
        updateStateFromTypedArray(typedarray);
        typedarray.recycle();
        inflateChildElements(resources, xmlpullparser, attributeset, theme);
        updateLocalState(resources);
    }

    public boolean isStateful()
    {
        GradientState gradientstate = mGradientState;
        boolean flag;
        if(!super.isStateful() && (gradientstate.mSolidColors == null || !gradientstate.mSolidColors.isStateful()) && (gradientstate.mStrokeColors == null || !gradientstate.mStrokeColors.isStateful()))
        {
            if(gradientstate.mTint != null)
                flag = gradientstate.mTint.isStateful();
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
            mGradientState = new GradientState(mGradientState, null);
            updateLocalState(null);
            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        super.onBoundsChange(rect);
        mRingPath = null;
        mPathIsDirty = true;
        mGradientIsDirty = true;
    }

    protected boolean onLevelChange(int i)
    {
        super.onLevelChange(i);
        mGradientIsDirty = true;
        mPathIsDirty = true;
        invalidateSelf();
        return true;
    }

    protected boolean onStateChange(int ai[])
    {
        boolean flag = false;
        GradientState gradientstate = mGradientState;
        ColorStateList colorstatelist = gradientstate.mSolidColors;
        boolean flag1 = flag;
        if(colorstatelist != null)
        {
            int i = colorstatelist.getColorForState(ai, 0);
            flag1 = flag;
            if(mFillPaint.getColor() != i)
            {
                mFillPaint.setColor(i);
                flag1 = true;
            }
        }
        Paint paint = mStrokePaint;
        flag = flag1;
        if(paint != null)
        {
            ColorStateList colorstatelist1 = gradientstate.mStrokeColors;
            flag = flag1;
            if(colorstatelist1 != null)
            {
                int j = colorstatelist1.getColorForState(ai, 0);
                flag = flag1;
                if(paint.getColor() != j)
                {
                    paint.setColor(j);
                    flag = true;
                }
            }
        }
        flag1 = flag;
        if(gradientstate.mTint != null)
        {
            flag1 = flag;
            if(gradientstate.mTintMode != null)
            {
                mTintFilter = updateTintFilter(mTintFilter, gradientstate.mTint, gradientstate.mTintMode);
                flag1 = true;
            }
        }
        if(flag1)
        {
            invalidateSelf();
            return true;
        } else
        {
            return false;
        }
    }

    public void setAlpha(int i)
    {
        if(i != mAlpha)
        {
            mAlpha = i;
            invalidateSelf();
        }
    }

    public void setColor(int i)
    {
        mGradientState.setSolidColors(ColorStateList.valueOf(i));
        mFillPaint.setColor(i);
        invalidateSelf();
    }

    public void setColor(ColorStateList colorstatelist)
    {
        mGradientState.setSolidColors(colorstatelist);
        int i;
        if(colorstatelist == null)
            i = 0;
        else
            i = colorstatelist.getColorForState(getState(), 0);
        mFillPaint.setColor(i);
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(colorfilter != mColorFilter)
        {
            mColorFilter = colorfilter;
            invalidateSelf();
        }
    }

    public void setColors(int ai[])
    {
        mGradientState.setGradientColors(ai);
        mGradientIsDirty = true;
        invalidateSelf();
    }

    public void setCornerRadii(float af[])
    {
        mGradientState.setCornerRadii(af);
        mPathIsDirty = true;
        invalidateSelf();
    }

    public void setCornerRadius(float f)
    {
        mGradientState.setCornerRadius(f);
        mPathIsDirty = true;
        invalidateSelf();
    }

    public void setDither(boolean flag)
    {
        if(flag != mGradientState.mDither)
        {
            mGradientState.mDither = flag;
            invalidateSelf();
        }
    }

    public void setGradientCenter(float f, float f1)
    {
        mGradientState.setGradientCenter(f, f1);
        mGradientIsDirty = true;
        invalidateSelf();
    }

    public void setGradientRadius(float f)
    {
        mGradientState.setGradientRadius(f, 0);
        mGradientIsDirty = true;
        invalidateSelf();
    }

    public void setGradientType(int i)
    {
        mGradientState.setGradientType(i);
        mGradientIsDirty = true;
        invalidateSelf();
    }

    public void setOrientation(Orientation orientation)
    {
        mGradientState.mOrientation = orientation;
        mGradientIsDirty = true;
        invalidateSelf();
    }

    public void setShape(int i)
    {
        mRingPath = null;
        mPathIsDirty = true;
        mGradientState.setShape(i);
        invalidateSelf();
    }

    public void setSize(int i, int j)
    {
        mGradientState.setSize(i, j);
        mPathIsDirty = true;
        invalidateSelf();
    }

    public void setStroke(int i, int j)
    {
        setStroke(i, j, 0.0F, 0.0F);
    }

    public void setStroke(int i, int j, float f, float f1)
    {
        mGradientState.setStroke(i, ColorStateList.valueOf(j), f, f1);
        setStrokeInternal(i, j, f, f1);
    }

    public void setStroke(int i, ColorStateList colorstatelist)
    {
        setStroke(i, colorstatelist, 0.0F, 0.0F);
    }

    public void setStroke(int i, ColorStateList colorstatelist, float f, float f1)
    {
        mGradientState.setStroke(i, colorstatelist, f, f1);
        int j;
        if(colorstatelist == null)
            j = 0;
        else
            j = colorstatelist.getColorForState(getState(), 0);
        setStrokeInternal(i, j, f, f1);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mGradientState.mTint = colorstatelist;
        mTintFilter = updateTintFilter(mTintFilter, colorstatelist, mGradientState.mTintMode);
        invalidateSelf();
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mGradientState.mTintMode = mode;
        mTintFilter = updateTintFilter(mTintFilter, mGradientState.mTint, mode);
        invalidateSelf();
    }

    public void setUseLevel(boolean flag)
    {
        mGradientState.mUseLevel = flag;
        mGradientIsDirty = true;
        invalidateSelf();
    }

    private static final int _2D_android_2D_graphics_2D_drawable_2D_GradientDrawable$OrientationSwitchesValues[];
    private static final float DEFAULT_INNER_RADIUS_RATIO = 3F;
    private static final float DEFAULT_THICKNESS_RATIO = 9F;
    public static final int LINE = 2;
    public static final int LINEAR_GRADIENT = 0;
    public static final int OVAL = 1;
    public static final int RADIAL_GRADIENT = 1;
    private static final int RADIUS_TYPE_FRACTION = 1;
    private static final int RADIUS_TYPE_FRACTION_PARENT = 2;
    private static final int RADIUS_TYPE_PIXELS = 0;
    public static final int RECTANGLE = 0;
    public static final int RING = 3;
    public static final int SWEEP_GRADIENT = 2;
    private int mAlpha;
    private ColorFilter mColorFilter;
    private final Paint mFillPaint;
    private boolean mGradientIsDirty;
    private float mGradientRadius;
    private GradientState mGradientState;
    private Paint mLayerPaint;
    private boolean mMutated;
    private Rect mPadding;
    private final Path mPath;
    private boolean mPathIsDirty;
    private final RectF mRect;
    private Path mRingPath;
    private Paint mStrokePaint;
    private PorterDuffColorFilter mTintFilter;
}
