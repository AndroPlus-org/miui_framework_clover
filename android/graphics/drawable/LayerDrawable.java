// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.*;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class LayerDrawable extends Drawable
    implements Drawable.Callback
{
    static class ChildDrawable
    {

        private void applyDensityScaling(int i, int j)
        {
            mInsetL = Drawable.scaleFromDensity(mInsetL, i, j, false);
            mInsetT = Drawable.scaleFromDensity(mInsetT, i, j, false);
            mInsetR = Drawable.scaleFromDensity(mInsetR, i, j, false);
            mInsetB = Drawable.scaleFromDensity(mInsetB, i, j, false);
            if(mInsetS != 0x80000000)
                mInsetS = Drawable.scaleFromDensity(mInsetS, i, j, false);
            if(mInsetE != 0x80000000)
                mInsetE = Drawable.scaleFromDensity(mInsetE, i, j, false);
            if(mWidth > 0)
                mWidth = Drawable.scaleFromDensity(mWidth, i, j, true);
            if(mHeight > 0)
                mHeight = Drawable.scaleFromDensity(mHeight, i, j, true);
        }

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
            {
                int j = mDensity;
                mDensity = i;
                applyDensityScaling(j, i);
            }
        }

        public int mDensity;
        public Drawable mDrawable;
        public int mGravity;
        public int mHeight;
        public int mId;
        public int mInsetB;
        public int mInsetE;
        public int mInsetL;
        public int mInsetR;
        public int mInsetS;
        public int mInsetT;
        public int mThemeAttrs[];
        public int mWidth;

        ChildDrawable(int i)
        {
            mDensity = 160;
            mInsetS = 0x80000000;
            mInsetE = 0x80000000;
            mWidth = -1;
            mHeight = -1;
            mGravity = 0;
            mId = -1;
            mDensity = i;
        }

        ChildDrawable(ChildDrawable childdrawable, LayerDrawable layerdrawable, Resources resources)
        {
            mDensity = 160;
            mInsetS = 0x80000000;
            mInsetE = 0x80000000;
            mWidth = -1;
            mHeight = -1;
            mGravity = 0;
            mId = -1;
            Drawable drawable = childdrawable.mDrawable;
            Object obj;
            if(drawable != null)
            {
                obj = drawable.getConstantState();
                if(obj == null)
                {
                    Drawable drawable1 = drawable;
                    obj = drawable1;
                    if(drawable.getCallback() != null)
                    {
                        Log.w("LayerDrawable", "Invalid drawable added to LayerDrawable! Drawable already belongs to another owner but does not expose a constant state.", new RuntimeException());
                        obj = drawable1;
                    }
                } else
                if(resources != null)
                    obj = ((Drawable.ConstantState) (obj)).newDrawable(resources);
                else
                    obj = ((Drawable.ConstantState) (obj)).newDrawable();
                ((Drawable) (obj)).setLayoutDirection(drawable.getLayoutDirection());
                ((Drawable) (obj)).setBounds(drawable.getBounds());
                ((Drawable) (obj)).setLevel(drawable.getLevel());
                ((Drawable) (obj)).setCallback(layerdrawable);
            } else
            {
                obj = null;
            }
            mDrawable = ((Drawable) (obj));
            mThemeAttrs = childdrawable.mThemeAttrs;
            mInsetL = childdrawable.mInsetL;
            mInsetT = childdrawable.mInsetT;
            mInsetR = childdrawable.mInsetR;
            mInsetB = childdrawable.mInsetB;
            mInsetS = childdrawable.mInsetS;
            mInsetE = childdrawable.mInsetE;
            mWidth = childdrawable.mWidth;
            mHeight = childdrawable.mHeight;
            mGravity = childdrawable.mGravity;
            mId = childdrawable.mId;
            mDensity = Drawable.resolveDensity(resources, childdrawable.mDensity);
            if(childdrawable.mDensity != mDensity)
                applyDensityScaling(childdrawable.mDensity, mDensity);
        }
    }

    static class LayerState extends Drawable.ConstantState
    {

        static boolean _2D_get0(LayerState layerstate)
        {
            return layerstate.mAutoMirrored;
        }

        static int _2D_get1(LayerState layerstate)
        {
            return layerstate.mPaddingMode;
        }

        static int[] _2D_get2(LayerState layerstate)
        {
            return layerstate.mThemeAttrs;
        }

        static boolean _2D_set0(LayerState layerstate, boolean flag)
        {
            layerstate.mAutoMirrored = flag;
            return flag;
        }

        static int _2D_set1(LayerState layerstate, int i)
        {
            layerstate.mPaddingMode = i;
            return i;
        }

        static int[] _2D_set2(LayerState layerstate, int ai[])
        {
            layerstate.mThemeAttrs = ai;
            return ai;
        }

        private void applyDensityScaling(int i, int j)
        {
            if(mPaddingLeft > 0)
                mPaddingLeft = Drawable.scaleFromDensity(mPaddingLeft, i, j, false);
            if(mPaddingTop > 0)
                mPaddingTop = Drawable.scaleFromDensity(mPaddingTop, i, j, false);
            if(mPaddingRight > 0)
                mPaddingRight = Drawable.scaleFromDensity(mPaddingRight, i, j, false);
            if(mPaddingBottom > 0)
                mPaddingBottom = Drawable.scaleFromDensity(mPaddingBottom, i, j, false);
            if(mPaddingStart > 0)
                mPaddingStart = Drawable.scaleFromDensity(mPaddingStart, i, j, false);
            if(mPaddingEnd > 0)
                mPaddingEnd = Drawable.scaleFromDensity(mPaddingEnd, i, j, false);
        }

        public boolean canApplyTheme()
        {
            if(mThemeAttrs != null || super.canApplyTheme())
                return true;
            ChildDrawable achilddrawable[] = mChildren;
            int i = mNumChildren;
            for(int j = 0; j < i; j++)
                if(achilddrawable[j].canApplyTheme())
                    return true;

            return false;
        }

        public final boolean canConstantState()
        {
            ChildDrawable achilddrawable[] = mChildren;
            int i = mNumChildren;
            for(int j = 0; j < i; j++)
            {
                Drawable drawable = achilddrawable[j].mDrawable;
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
            int i = mNumChildren;
            ChildDrawable achilddrawable[] = mChildren;
            byte byte0 = -1;
            int k = 0;
label0:
            do
            {
label1:
                {
                    int l = byte0;
                    if(k < i)
                    {
                        if(achilddrawable[k].mDrawable == null)
                            break label1;
                        l = k;
                    }
                    if(l >= 0)
                        k = achilddrawable[l].mDrawable.getOpacity();
                    else
                        k = -2;
                    for(l++; l < i;)
                    {
                        Drawable drawable = achilddrawable[l].mDrawable;
                        int j = k;
                        if(drawable != null)
                            j = Drawable.resolveOpacity(k, drawable.getOpacity());
                        l++;
                        k = j;
                    }

                    break label0;
                }
                k++;
            } while(true);
            mOpacity = k;
            mCheckedOpacity = true;
            return k;
        }

        public final boolean hasFocusStateSpecified()
        {
            int i = mNumChildren;
            ChildDrawable achilddrawable[] = mChildren;
            for(int j = 0; j < i; j++)
            {
                Drawable drawable = achilddrawable[j].mDrawable;
                if(drawable != null && drawable.hasFocusStateSpecified())
                    return true;
            }

            return false;
        }

        void invalidateCache()
        {
            mCheckedOpacity = false;
            mCheckedStateful = false;
        }

        public final boolean isStateful()
        {
            if(mCheckedStateful)
                return mIsStateful;
            int i = mNumChildren;
            ChildDrawable achilddrawable[] = mChildren;
            boolean flag = false;
            int j = 0;
            do
            {
label0:
                {
                    boolean flag1 = flag;
                    if(j < i)
                    {
                        Drawable drawable = achilddrawable[j].mDrawable;
                        if(drawable == null || !drawable.isStateful())
                            break label0;
                        flag1 = true;
                    }
                    mIsStateful = flag1;
                    mCheckedStateful = true;
                    return flag1;
                }
                j++;
            } while(true);
        }

        public Drawable newDrawable()
        {
            return new LayerDrawable(this, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new LayerDrawable(this, resources);
        }

        protected void onDensityChanged(int i, int j)
        {
            applyDensityScaling(i, j);
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

        private boolean mAutoMirrored;
        int mChangingConfigurations;
        private boolean mCheckedOpacity;
        private boolean mCheckedStateful;
        ChildDrawable mChildren[];
        int mChildrenChangingConfigurations;
        int mDensity;
        private boolean mIsStateful;
        int mNumChildren;
        private int mOpacity;
        int mOpacityOverride;
        int mPaddingBottom;
        int mPaddingEnd;
        int mPaddingLeft;
        private int mPaddingMode;
        int mPaddingRight;
        int mPaddingStart;
        int mPaddingTop;
        private int mThemeAttrs[];

        LayerState(LayerState layerstate, LayerDrawable layerdrawable, Resources resources)
        {
            mPaddingTop = -1;
            mPaddingBottom = -1;
            mPaddingLeft = -1;
            mPaddingRight = -1;
            mPaddingStart = -1;
            mPaddingEnd = -1;
            mOpacityOverride = 0;
            mAutoMirrored = false;
            mPaddingMode = 0;
            int i;
            if(layerstate != null)
                i = layerstate.mDensity;
            else
                i = 0;
            mDensity = Drawable.resolveDensity(resources, i);
            if(layerstate != null)
            {
                ChildDrawable achilddrawable[] = layerstate.mChildren;
                int j = layerstate.mNumChildren;
                mNumChildren = j;
                mChildren = new ChildDrawable[j];
                mChangingConfigurations = layerstate.mChangingConfigurations;
                mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations;
                for(i = 0; i < j; i++)
                {
                    ChildDrawable childdrawable = achilddrawable[i];
                    mChildren[i] = new ChildDrawable(childdrawable, layerdrawable, resources);
                }

                mCheckedOpacity = layerstate.mCheckedOpacity;
                mOpacity = layerstate.mOpacity;
                mCheckedStateful = layerstate.mCheckedStateful;
                mIsStateful = layerstate.mIsStateful;
                mAutoMirrored = layerstate.mAutoMirrored;
                mPaddingMode = layerstate.mPaddingMode;
                mThemeAttrs = layerstate.mThemeAttrs;
                mPaddingTop = layerstate.mPaddingTop;
                mPaddingBottom = layerstate.mPaddingBottom;
                mPaddingLeft = layerstate.mPaddingLeft;
                mPaddingRight = layerstate.mPaddingRight;
                mPaddingStart = layerstate.mPaddingStart;
                mPaddingEnd = layerstate.mPaddingEnd;
                mOpacityOverride = layerstate.mOpacityOverride;
                if(layerstate.mDensity != mDensity)
                    applyDensityScaling(layerstate.mDensity, mDensity);
            } else
            {
                mNumChildren = 0;
                mChildren = null;
            }
        }
    }


    LayerDrawable()
    {
        this((LayerState)null, ((Resources) (null)));
    }

    LayerDrawable(LayerState layerstate, Resources resources)
    {
        mTmpRect = new Rect();
        mTmpOutRect = new Rect();
        mTmpContainer = new Rect();
        mLayerState = createConstantState(layerstate, resources);
        if(mLayerState.mNumChildren > 0)
        {
            ensurePadding();
            refreshPadding();
        }
    }

    public LayerDrawable(Drawable adrawable[])
    {
        this(adrawable, ((LayerState) (null)));
    }

    LayerDrawable(Drawable adrawable[], LayerState layerstate)
    {
        this(layerstate, ((Resources) (null)));
        if(adrawable == null)
            throw new IllegalArgumentException("layers must be non-null");
        int i = adrawable.length;
        layerstate = new ChildDrawable[i];
        for(int j = 0; j < i; j++)
        {
            layerstate[j] = new ChildDrawable(mLayerState.mDensity);
            layerstate[j].mDrawable = adrawable[j];
            adrawable[j].setCallback(this);
            LayerState layerstate1 = mLayerState;
            layerstate1.mChildrenChangingConfigurations = layerstate1.mChildrenChangingConfigurations | adrawable[j].getChangingConfigurations();
        }

        mLayerState.mNumChildren = i;
        mLayerState.mChildren = layerstate;
        ensurePadding();
        refreshPadding();
    }

    private void computeNestedPadding(Rect rect)
    {
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            refreshChildPadding(j, achilddrawable[j]);
            rect.left = rect.left + mPaddingL[j];
            rect.top = rect.top + mPaddingT[j];
            rect.right = rect.right + mPaddingR[j];
            rect.bottom = rect.bottom + mPaddingB[j];
        }

    }

    private void computeStackedPadding(Rect rect)
    {
        rect.left = 0;
        rect.top = 0;
        rect.right = 0;
        rect.bottom = 0;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            refreshChildPadding(j, achilddrawable[j]);
            rect.left = Math.max(rect.left, mPaddingL[j]);
            rect.top = Math.max(rect.top, mPaddingT[j]);
            rect.right = Math.max(rect.right, mPaddingR[j]);
            rect.bottom = Math.max(rect.bottom, mPaddingB[j]);
        }

    }

    private ChildDrawable createLayer(Drawable drawable)
    {
        ChildDrawable childdrawable = new ChildDrawable(mLayerState.mDensity);
        childdrawable.mDrawable = drawable;
        return childdrawable;
    }

    private Drawable getFirstNonNullDrawable()
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                return drawable;
        }

        return null;
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
            int l = xmlpullparser.getDepth();
            if(l < i && j == 3)
                break;
            if(j == 2 && l <= i && !(xmlpullparser.getName().equals("item") ^ true))
            {
                ChildDrawable childdrawable = new ChildDrawable(layerstate.mDensity);
                TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.LayerDrawableItem);
                updateLayerFromTypedArray(childdrawable, typedarray);
                typedarray.recycle();
                if(childdrawable.mDrawable == null && (childdrawable.mThemeAttrs == null || childdrawable.mThemeAttrs[4] == 0))
                {
                    int k;
                    do
                        k = xmlpullparser.next();
                    while(k == 4);
                    if(k != 2)
                        throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": <item> tag requires a 'drawable' attribute or ").append("child tag defining a drawable").toString());
                    childdrawable.mDrawable = Drawable.createFromXmlInner(resources, xmlpullparser, attributeset, theme);
                    childdrawable.mDrawable.setCallback(this);
                    layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | childdrawable.mDrawable.getChangingConfigurations();
                }
                addLayer(childdrawable);
            }
        } while(true);
    }

    private boolean refreshChildPadding(int i, ChildDrawable childdrawable)
    {
        if(childdrawable.mDrawable != null)
        {
            Rect rect = mTmpRect;
            childdrawable.mDrawable.getPadding(rect);
            while(rect.left != mPaddingL[i] || rect.top != mPaddingT[i] || rect.right != mPaddingR[i] || rect.bottom != mPaddingB[i]) 
            {
                mPaddingL[i] = rect.left;
                mPaddingT[i] = rect.top;
                mPaddingR[i] = rect.right;
                mPaddingB[i] = rect.bottom;
                return true;
            }
        }
        return false;
    }

    private static int resolveGravity(int i, int j, int k, int l, int i1)
    {
        int j1 = i;
        if(!Gravity.isHorizontal(i))
            if(j < 0)
                j1 = i | 7;
            else
                j1 = i | 0x800003;
        i = j1;
        if(!Gravity.isVertical(j1))
            if(k < 0)
                i = j1 | 0x70;
            else
                i = j1 | 0x30;
        j1 = i;
        if(j < 0)
        {
            j1 = i;
            if(l < 0)
                j1 = i | 7;
        }
        i = j1;
        if(k < 0)
        {
            i = j1;
            if(i1 < 0)
                i = j1 | 0x70;
        }
        return i;
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

    private void setLayerInsetInternal(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        ChildDrawable childdrawable = mLayerState.mChildren[i];
        childdrawable.mInsetL = j;
        childdrawable.mInsetT = k;
        childdrawable.mInsetR = l;
        childdrawable.mInsetB = i1;
        childdrawable.mInsetS = j1;
        childdrawable.mInsetE = k1;
    }

    private void suspendChildInvalidation()
    {
        mSuspendChildInvalidation = true;
    }

    private void updateLayerBounds(Rect rect)
    {
        suspendChildInvalidation();
        updateLayerBoundsInternal(rect);
        resumeChildInvalidation();
        return;
        rect;
        resumeChildInvalidation();
        throw rect;
    }

    private void updateLayerBoundsInternal(Rect rect)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        Rect rect1 = mTmpOutRect;
        int i1 = getLayoutDirection();
        boolean flag;
        boolean flag1;
        ChildDrawable achilddrawable[];
        int j1;
        int k1;
        if(i1 == 1)
            flag = true;
        else
            flag = false;
        if(LayerState._2D_get1(mLayerState) == 0)
            flag1 = true;
        else
            flag1 = false;
        achilddrawable = mLayerState.mChildren;
        j1 = 0;
        k1 = mLayerState.mNumChildren;
        while(j1 < k1) 
        {
            ChildDrawable childdrawable = achilddrawable[j1];
            Drawable drawable = childdrawable.mDrawable;
            int l1;
            int i2;
            int j2;
            int k2;
            if(drawable == null)
            {
                l1 = j;
                i2 = k;
                j2 = i;
                k2 = l;
            } else
            {
                l1 = childdrawable.mInsetT;
                i2 = childdrawable.mInsetB;
                Rect rect2;
                int l2;
                if(flag)
                    j2 = childdrawable.mInsetE;
                else
                    j2 = childdrawable.mInsetS;
                if(flag)
                    k2 = childdrawable.mInsetS;
                else
                    k2 = childdrawable.mInsetE;
                if(j2 == 0x80000000)
                    j2 = childdrawable.mInsetL;
                if(k2 == 0x80000000)
                    k2 = childdrawable.mInsetR;
                rect2 = mTmpContainer;
                rect2.set(rect.left + j2 + i, rect.top + l1 + j, rect.right - k2 - k, rect.bottom - i2 - l);
                k2 = drawable.getIntrinsicWidth();
                i2 = drawable.getIntrinsicHeight();
                l1 = childdrawable.mWidth;
                j2 = childdrawable.mHeight;
                l2 = resolveGravity(childdrawable.mGravity, l1, j2, k2, i2);
                if(l1 >= 0)
                    k2 = l1;
                if(j2 < 0)
                    j2 = i2;
                Gravity.apply(l2, k2, j2, rect2, rect1, i1);
                drawable.setBounds(rect1);
                k2 = l;
                j2 = i;
                i2 = k;
                l1 = j;
                if(flag1)
                {
                    j2 = i + mPaddingL[j1];
                    i2 = k + mPaddingR[j1];
                    l1 = j + mPaddingT[j1];
                    k2 = l + mPaddingB[j1];
                }
            }
            j1++;
            l = k2;
            i = j2;
            k = i2;
            j = l1;
        }
    }

    private void updateLayerFromTypedArray(ChildDrawable childdrawable, TypedArray typedarray)
    {
        LayerState layerstate;
        int i;
        int j;
        layerstate = mLayerState;
        layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | typedarray.getChangingConfigurations();
        childdrawable.mThemeAttrs = typedarray.extractThemeAttrs();
        i = typedarray.getIndexCount();
        j = 0;
_L13:
        int k;
        if(j >= i)
            break MISSING_BLOCK_LABEL_288;
        k = typedarray.getIndex(j);
        k;
        JVM INSTR tableswitch 0 10: default 112
    //                   0 254
    //                   1 271
    //                   2 237
    //                   3 220
    //                   4 112
    //                   5 118
    //                   6 135
    //                   7 152
    //                   8 169
    //                   9 186
    //                   10 203;
           goto _L1 _L2 _L3 _L4 _L5 _L1 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
        break MISSING_BLOCK_LABEL_271;
_L1:
        break; /* Loop/switch isn't completed */
_L6:
        break; /* Loop/switch isn't completed */
_L14:
        j++;
        if(true) goto _L13; else goto _L12
_L12:
        childdrawable.mInsetL = typedarray.getDimensionPixelOffset(k, childdrawable.mInsetL);
          goto _L14
_L7:
        childdrawable.mInsetT = typedarray.getDimensionPixelOffset(k, childdrawable.mInsetT);
          goto _L14
_L8:
        childdrawable.mInsetR = typedarray.getDimensionPixelOffset(k, childdrawable.mInsetR);
          goto _L14
_L9:
        childdrawable.mInsetB = typedarray.getDimensionPixelOffset(k, childdrawable.mInsetB);
          goto _L14
_L10:
        childdrawable.mInsetS = typedarray.getDimensionPixelOffset(k, childdrawable.mInsetS);
          goto _L14
_L11:
        childdrawable.mInsetE = typedarray.getDimensionPixelOffset(k, childdrawable.mInsetE);
          goto _L14
_L5:
        childdrawable.mWidth = typedarray.getDimensionPixelSize(k, childdrawable.mWidth);
          goto _L14
_L4:
        childdrawable.mHeight = typedarray.getDimensionPixelSize(k, childdrawable.mHeight);
          goto _L14
_L2:
        childdrawable.mGravity = typedarray.getInteger(k, childdrawable.mGravity);
          goto _L14
        childdrawable.mId = typedarray.getResourceId(k, childdrawable.mId);
          goto _L14
        typedarray = typedarray.getDrawable(4);
        if(typedarray != null)
        {
            if(childdrawable.mDrawable != null)
                childdrawable.mDrawable.setCallback(null);
            childdrawable.mDrawable = typedarray;
            childdrawable.mDrawable.setCallback(this);
            layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | childdrawable.mDrawable.getChangingConfigurations();
        }
        return;
    }

    private void updateStateFromTypedArray(TypedArray typedarray)
    {
        LayerState layerstate;
        int i;
        int j;
        layerstate = mLayerState;
        layerstate.mChangingConfigurations = layerstate.mChangingConfigurations | typedarray.getChangingConfigurations();
        LayerState._2D_set2(layerstate, typedarray.extractThemeAttrs());
        i = typedarray.getIndexCount();
        j = 0;
_L12:
        int k;
        if(j >= i)
            break MISSING_BLOCK_LABEL_261;
        k = typedarray.getIndex(j);
        k;
        JVM INSTR tableswitch 0 8: default 100
    //                   0 157
    //                   1 123
    //                   2 174
    //                   3 140
    //                   4 106
    //                   5 191
    //                   6 208
    //                   7 225
    //                   8 243;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L10:
        break MISSING_BLOCK_LABEL_243;
_L1:
        break; /* Loop/switch isn't completed */
_L6:
        break; /* Loop/switch isn't completed */
_L13:
        j++;
        if(true) goto _L12; else goto _L11
_L11:
        layerstate.mOpacityOverride = typedarray.getInt(k, layerstate.mOpacityOverride);
          goto _L13
_L3:
        layerstate.mPaddingTop = typedarray.getDimensionPixelOffset(k, layerstate.mPaddingTop);
          goto _L13
_L5:
        layerstate.mPaddingBottom = typedarray.getDimensionPixelOffset(k, layerstate.mPaddingBottom);
          goto _L13
_L2:
        layerstate.mPaddingLeft = typedarray.getDimensionPixelOffset(k, layerstate.mPaddingLeft);
          goto _L13
_L4:
        layerstate.mPaddingRight = typedarray.getDimensionPixelOffset(k, layerstate.mPaddingRight);
          goto _L13
_L7:
        layerstate.mPaddingStart = typedarray.getDimensionPixelOffset(k, layerstate.mPaddingStart);
          goto _L13
_L8:
        layerstate.mPaddingEnd = typedarray.getDimensionPixelOffset(k, layerstate.mPaddingEnd);
          goto _L13
_L9:
        LayerState._2D_set0(layerstate, typedarray.getBoolean(k, LayerState._2D_get0(layerstate)));
          goto _L13
        LayerState._2D_set1(layerstate, typedarray.getInteger(k, LayerState._2D_get1(layerstate)));
          goto _L13
    }

    public int addLayer(Drawable drawable)
    {
        drawable = createLayer(drawable);
        int i = addLayer(((ChildDrawable) (drawable)));
        ensurePadding();
        refreshChildPadding(i, drawable);
        return i;
    }

    int addLayer(ChildDrawable childdrawable)
    {
        LayerState layerstate = mLayerState;
        int i;
        int j;
        if(layerstate.mChildren != null)
            i = layerstate.mChildren.length;
        else
            i = 0;
        j = layerstate.mNumChildren;
        if(j >= i)
        {
            ChildDrawable achilddrawable[] = new ChildDrawable[i + 10];
            if(j > 0)
                System.arraycopy(layerstate.mChildren, 0, achilddrawable, 0, j);
            layerstate.mChildren = achilddrawable;
        }
        layerstate.mChildren[j] = childdrawable;
        layerstate.mNumChildren = layerstate.mNumChildren + 1;
        layerstate.invalidateCache();
        return j;
    }

    ChildDrawable addLayer(Drawable drawable, int ai[], int i, int j, int k, int l, int i1)
    {
        ChildDrawable childdrawable = createLayer(drawable);
        childdrawable.mId = i;
        childdrawable.mThemeAttrs = ai;
        childdrawable.mDrawable.setAutoMirrored(isAutoMirrored());
        childdrawable.mInsetL = j;
        childdrawable.mInsetT = k;
        childdrawable.mInsetR = l;
        childdrawable.mInsetB = i1;
        addLayer(childdrawable);
        ai = mLayerState;
        ai.mChildrenChangingConfigurations = ((LayerState) (ai)).mChildrenChangingConfigurations | drawable.getChangingConfigurations();
        drawable.setCallback(this);
        return childdrawable;
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        LayerState layerstate = mLayerState;
        int i = Drawable.resolveDensity(theme.getResources(), 0);
        layerstate.setDensity(i);
        if(LayerState._2D_get2(layerstate) != null)
        {
            TypedArray typedarray = theme.resolveAttributes(LayerState._2D_get2(layerstate), com.android.internal.R.styleable.LayerDrawable);
            updateStateFromTypedArray(typedarray);
            typedarray.recycle();
        }
        ChildDrawable achilddrawable[] = layerstate.mChildren;
        int j = layerstate.mNumChildren;
        for(int k = 0; k < j; k++)
        {
            Object obj = achilddrawable[k];
            ((ChildDrawable) (obj)).setDensity(i);
            if(((ChildDrawable) (obj)).mThemeAttrs != null)
            {
                TypedArray typedarray1 = theme.resolveAttributes(((ChildDrawable) (obj)).mThemeAttrs, com.android.internal.R.styleable.LayerDrawableItem);
                updateLayerFromTypedArray(((ChildDrawable) (obj)), typedarray1);
                typedarray1.recycle();
            }
            obj = ((ChildDrawable) (obj)).mDrawable;
            if(obj != null && ((Drawable) (obj)).canApplyTheme())
            {
                ((Drawable) (obj)).applyTheme(theme);
                layerstate.mChildrenChangingConfigurations = layerstate.mChildrenChangingConfigurations | ((Drawable) (obj)).getChangingConfigurations();
            }
        }

    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(!mLayerState.canApplyTheme())
            flag = super.canApplyTheme();
        else
            flag = true;
        return flag;
    }

    public void clearMutated()
    {
        super.clearMutated();
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
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
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.draw(canvas);
        }

    }

    void ensurePadding()
    {
        int i = mLayerState.mNumChildren;
        if(mPaddingL != null && mPaddingL.length >= i)
        {
            return;
        } else
        {
            mPaddingL = new int[i];
            mPaddingT = new int[i];
            mPaddingR = new int[i];
            mPaddingB = new int[i];
            return;
        }
    }

    public Drawable findDrawableByLayerId(int i)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int j = mLayerState.mNumChildren - 1; j >= 0; j--)
            if(achilddrawable[j].mId == i)
                return achilddrawable[j].mDrawable;

        return null;
    }

    public int findIndexByLayerId(int i)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int j = mLayerState.mNumChildren;
        for(int k = 0; k < j; k++)
            if(achilddrawable[k].mId == i)
                return k;

        return -1;
    }

    public int getAlpha()
    {
        Drawable drawable = getFirstNonNullDrawable();
        if(drawable != null)
            return drawable.getAlpha();
        else
            return super.getAlpha();
    }

    public int getBottomPadding()
    {
        return mLayerState.mPaddingBottom;
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

    public Drawable getDrawable(int i)
    {
        if(i >= mLayerState.mNumChildren)
            throw new IndexOutOfBoundsException();
        else
            return mLayerState.mChildren[i].mDrawable;
    }

    public int getEndPadding()
    {
        return mLayerState.mPaddingEnd;
    }

    public void getHotspotBounds(Rect rect)
    {
        if(mHotspotBounds != null)
            rect.set(mHotspotBounds);
        else
            super.getHotspotBounds(rect);
    }

    public int getId(int i)
    {
        if(i >= mLayerState.mNumChildren)
            throw new IndexOutOfBoundsException();
        else
            return mLayerState.mChildren[i].mId;
    }

    public int getIntrinsicHeight()
    {
        int i = -1;
        int j = 0;
        int k = 0;
        boolean flag;
        ChildDrawable achilddrawable[];
        int l;
        int i1;
        if(LayerState._2D_get1(mLayerState) == 0)
            flag = true;
        else
            flag = false;
        achilddrawable = mLayerState.mChildren;
        l = mLayerState.mNumChildren;
        i1 = 0;
        while(i1 < l) 
        {
            ChildDrawable childdrawable = achilddrawable[i1];
            int j1;
            int k1;
            if(childdrawable.mDrawable == null)
            {
                j1 = j;
                k1 = k;
            } else
            {
                int l1;
                if(childdrawable.mHeight < 0)
                    l1 = childdrawable.mDrawable.getIntrinsicHeight();
                else
                    l1 = childdrawable.mHeight;
                if(l1 < 0)
                    k1 = -1;
                else
                    k1 = childdrawable.mInsetT + l1 + childdrawable.mInsetB + j + k;
                l1 = i;
                if(k1 > i)
                    l1 = k1;
                i = l1;
                k1 = k;
                j1 = j;
                if(flag)
                {
                    j1 = j + mPaddingT[i1];
                    k1 = k + mPaddingB[i1];
                    i = l1;
                }
            }
            i1++;
            k = k1;
            j = j1;
        }
        return i;
    }

    public int getIntrinsicWidth()
    {
        int i = -1;
        int j = 0;
        int k = 0;
        boolean flag;
        boolean flag1;
        ChildDrawable achilddrawable[];
        int l;
        int i1;
        if(LayerState._2D_get1(mLayerState) == 0)
            flag = true;
        else
            flag = false;
        if(getLayoutDirection() == 1)
            flag1 = true;
        else
            flag1 = false;
        achilddrawable = mLayerState.mChildren;
        l = mLayerState.mNumChildren;
        i1 = 0;
        while(i1 < l) 
        {
            ChildDrawable childdrawable = achilddrawable[i1];
            int j1;
            int k1;
            if(childdrawable.mDrawable == null)
            {
                j1 = i;
                k1 = k;
                i = j;
            } else
            {
                int l1;
                if(flag1)
                    j1 = childdrawable.mInsetE;
                else
                    j1 = childdrawable.mInsetS;
                if(flag1)
                    l1 = childdrawable.mInsetS;
                else
                    l1 = childdrawable.mInsetE;
                if(j1 == 0x80000000)
                    j1 = childdrawable.mInsetL;
                if(l1 == 0x80000000)
                    l1 = childdrawable.mInsetR;
                if(childdrawable.mWidth < 0)
                    k1 = childdrawable.mDrawable.getIntrinsicWidth();
                else
                    k1 = childdrawable.mWidth;
                if(k1 < 0)
                    j1 = -1;
                else
                    j1 = k1 + j1 + l1 + j + k;
                l1 = i;
                if(j1 > i)
                    l1 = j1;
                i = j;
                k1 = k;
                j1 = l1;
                if(flag)
                {
                    i = j + mPaddingL[i1];
                    k1 = k + mPaddingR[i1];
                    j1 = l1;
                }
            }
            i1++;
            j = i;
            k = k1;
            i = j1;
        }
        return i;
    }

    public int getLayerGravity(int i)
    {
        return mLayerState.mChildren[i].mGravity;
    }

    public int getLayerHeight(int i)
    {
        return mLayerState.mChildren[i].mHeight;
    }

    public int getLayerInsetBottom(int i)
    {
        return mLayerState.mChildren[i].mInsetB;
    }

    public int getLayerInsetEnd(int i)
    {
        return mLayerState.mChildren[i].mInsetE;
    }

    public int getLayerInsetLeft(int i)
    {
        return mLayerState.mChildren[i].mInsetL;
    }

    public int getLayerInsetRight(int i)
    {
        return mLayerState.mChildren[i].mInsetR;
    }

    public int getLayerInsetStart(int i)
    {
        return mLayerState.mChildren[i].mInsetS;
    }

    public int getLayerInsetTop(int i)
    {
        return mLayerState.mChildren[i].mInsetT;
    }

    public int getLayerWidth(int i)
    {
        return mLayerState.mChildren[i].mWidth;
    }

    public int getLeftPadding()
    {
        return mLayerState.mPaddingLeft;
    }

    public int getNumberOfLayers()
    {
        return mLayerState.mNumChildren;
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
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable == null)
                continue;
            drawable.getOutline(outline);
            if(!outline.isEmpty())
                return;
        }

    }

    public boolean getPadding(Rect rect)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        LayerState layerstate = mLayerState;
        int i;
        int j;
        int k;
        int l;
        if(LayerState._2D_get1(layerstate) == 0)
            computeNestedPadding(rect);
        else
            computeStackedPadding(rect);
        i = layerstate.mPaddingTop;
        j = layerstate.mPaddingBottom;
        if(getLayoutDirection() == 1)
            k = 1;
        else
            k = 0;
        if(k != 0)
            l = layerstate.mPaddingEnd;
        else
            l = layerstate.mPaddingStart;
        if(k != 0)
            k = layerstate.mPaddingStart;
        else
            k = layerstate.mPaddingEnd;
        if(l < 0)
            l = layerstate.mPaddingLeft;
        if(k < 0)
            k = layerstate.mPaddingRight;
        if(l >= 0)
            rect.left = l;
        if(i >= 0)
            rect.top = i;
        if(k >= 0)
            rect.right = k;
        if(j >= 0)
            rect.bottom = j;
        flag1 = flag;
        if(rect.left != 0) goto _L2; else goto _L1
_L1:
        if(rect.top == 0) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(rect.right == 0)
        {
            flag1 = flag;
            if(rect.bottom == 0)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public int getPaddingMode()
    {
        return LayerState._2D_get1(mLayerState);
    }

    public int getRightPadding()
    {
        return mLayerState.mPaddingRight;
    }

    public int getStartPadding()
    {
        return mLayerState.mPaddingStart;
    }

    public int getTopPadding()
    {
        return mLayerState.mPaddingTop;
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
        int i = Drawable.resolveDensity(resources, 0);
        layerstate.setDensity(i);
        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.LayerDrawable);
        updateStateFromTypedArray(typedarray);
        typedarray.recycle();
        ChildDrawable achilddrawable[] = layerstate.mChildren;
        int j = layerstate.mNumChildren;
        for(int k = 0; k < j; k++)
            achilddrawable[k].setDensity(i);

        inflateLayers(resources, xmlpullparser, attributeset, theme);
        ensurePadding();
        refreshPadding();
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if(mSuspendChildInvalidation)
        {
            mChildRequestedInvalidation = true;
        } else
        {
            mLayerState.invalidateCache();
            invalidateSelf();
        }
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
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
            if(achilddrawable[j].mDrawable.isProjected())
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
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.jumpToCurrentState();
        }

    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mLayerState = createConstantState(mLayerState, null);
            ChildDrawable achilddrawable[] = mLayerState.mChildren;
            int i = mLayerState.mNumChildren;
            for(int j = 0; j < i; j++)
            {
                Drawable drawable = achilddrawable[j].mDrawable;
                if(drawable != null)
                    drawable.mutate();
            }

            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        updateLayerBounds(rect);
    }

    public boolean onLayoutDirectionChanged(int i)
    {
        boolean flag = false;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int j = mLayerState.mNumChildren;
        for(int k = 0; k < j;)
        {
            Drawable drawable = achilddrawable[k].mDrawable;
            boolean flag1 = flag;
            if(drawable != null)
                flag1 = flag | drawable.setLayoutDirection(i);
            k++;
            flag = flag1;
        }

        updateLayerBounds(getBounds());
        return flag;
    }

    protected boolean onLevelChange(int i)
    {
        boolean flag = false;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int j = mLayerState.mNumChildren;
        for(int k = 0; k < j;)
        {
            Drawable drawable = achilddrawable[k].mDrawable;
            boolean flag1 = flag;
            if(drawable != null)
            {
                flag1 = flag;
                if(drawable.setLevel(i))
                {
                    refreshChildPadding(k, achilddrawable[k]);
                    flag1 = true;
                }
            }
            k++;
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
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i;)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            boolean flag1 = flag;
            if(drawable != null)
            {
                flag1 = flag;
                if(drawable.isStateful())
                {
                    flag1 = flag;
                    if(drawable.setState(ai))
                    {
                        refreshChildPadding(j, achilddrawable[j]);
                        flag1 = true;
                    }
                }
            }
            j++;
            flag = flag1;
        }

        if(flag)
            updateLayerBounds(getBounds());
        return flag;
    }

    void refreshPadding()
    {
        int i = mLayerState.mNumChildren;
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        for(int j = 0; j < i; j++)
            refreshChildPadding(j, achilddrawable[j]);

    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public void setAlpha(int i)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int j = mLayerState.mNumChildren;
        for(int k = 0; k < j; k++)
        {
            Drawable drawable = achilddrawable[k].mDrawable;
            if(drawable != null)
                drawable.setAlpha(i);
        }

    }

    public void setAutoMirrored(boolean flag)
    {
        LayerState._2D_set0(mLayerState, flag);
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setAutoMirrored(flag);
        }

    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setColorFilter(colorfilter);
        }

    }

    public void setDither(boolean flag)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setDither(flag);
        }

    }

    public void setDrawable(int i, Drawable drawable)
    {
        if(i >= mLayerState.mNumChildren)
            throw new IndexOutOfBoundsException();
        ChildDrawable childdrawable = mLayerState.mChildren[i];
        if(childdrawable.mDrawable != null)
        {
            if(drawable != null)
                drawable.setBounds(childdrawable.mDrawable.getBounds());
            childdrawable.mDrawable.setCallback(null);
        }
        if(drawable != null)
            drawable.setCallback(this);
        childdrawable.mDrawable = drawable;
        mLayerState.invalidateCache();
        refreshChildPadding(i, childdrawable);
    }

    public boolean setDrawableByLayerId(int i, Drawable drawable)
    {
        i = findIndexByLayerId(i);
        if(i < 0)
        {
            return false;
        } else
        {
            setDrawable(i, drawable);
            return true;
        }
    }

    public void setHotspot(float f, float f1)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setHotspot(f, f1);
        }

    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i1 = mLayerState.mNumChildren;
        for(int j1 = 0; j1 < i1; j1++)
        {
            Drawable drawable = achilddrawable[j1].mDrawable;
            if(drawable != null)
                drawable.setHotspotBounds(i, j, k, l);
        }

        if(mHotspotBounds == null)
            mHotspotBounds = new Rect(i, j, k, l);
        else
            mHotspotBounds.set(i, j, k, l);
    }

    public void setId(int i, int j)
    {
        mLayerState.mChildren[i].mId = j;
    }

    public void setLayerGravity(int i, int j)
    {
        mLayerState.mChildren[i].mGravity = j;
    }

    public void setLayerHeight(int i, int j)
    {
        mLayerState.mChildren[i].mHeight = j;
    }

    public void setLayerInset(int i, int j, int k, int l, int i1)
    {
        setLayerInsetInternal(i, j, k, l, i1, 0x80000000, 0x80000000);
    }

    public void setLayerInsetBottom(int i, int j)
    {
        mLayerState.mChildren[i].mInsetB = j;
    }

    public void setLayerInsetEnd(int i, int j)
    {
        mLayerState.mChildren[i].mInsetE = j;
    }

    public void setLayerInsetLeft(int i, int j)
    {
        mLayerState.mChildren[i].mInsetL = j;
    }

    public void setLayerInsetRelative(int i, int j, int k, int l, int i1)
    {
        setLayerInsetInternal(i, 0, k, 0, i1, j, l);
    }

    public void setLayerInsetRight(int i, int j)
    {
        mLayerState.mChildren[i].mInsetR = j;
    }

    public void setLayerInsetStart(int i, int j)
    {
        mLayerState.mChildren[i].mInsetS = j;
    }

    public void setLayerInsetTop(int i, int j)
    {
        mLayerState.mChildren[i].mInsetT = j;
    }

    public void setLayerSize(int i, int j, int k)
    {
        ChildDrawable childdrawable = mLayerState.mChildren[i];
        childdrawable.mWidth = j;
        childdrawable.mHeight = k;
    }

    public void setLayerWidth(int i, int j)
    {
        mLayerState.mChildren[i].mWidth = j;
    }

    public void setOpacity(int i)
    {
        mLayerState.mOpacityOverride = i;
    }

    public void setPadding(int i, int j, int k, int l)
    {
        LayerState layerstate = mLayerState;
        layerstate.mPaddingLeft = i;
        layerstate.mPaddingTop = j;
        layerstate.mPaddingRight = k;
        layerstate.mPaddingBottom = l;
        layerstate.mPaddingStart = -1;
        layerstate.mPaddingEnd = -1;
    }

    public void setPaddingMode(int i)
    {
        if(LayerState._2D_get1(mLayerState) != i)
            LayerState._2D_set1(mLayerState, i);
    }

    public void setPaddingRelative(int i, int j, int k, int l)
    {
        LayerState layerstate = mLayerState;
        layerstate.mPaddingStart = i;
        layerstate.mPaddingTop = j;
        layerstate.mPaddingEnd = k;
        layerstate.mPaddingBottom = l;
        layerstate.mPaddingLeft = -1;
        layerstate.mPaddingRight = -1;
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setTintList(colorstatelist);
        }

    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setTintMode(mode);
        }

    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag2 = super.setVisible(flag, flag1);
        ChildDrawable achilddrawable[] = mLayerState.mChildren;
        int i = mLayerState.mNumChildren;
        for(int j = 0; j < i; j++)
        {
            Drawable drawable = achilddrawable[j].mDrawable;
            if(drawable != null)
                drawable.setVisible(flag, flag1);
        }

        return flag2;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    public static final int INSET_UNDEFINED = 0x80000000;
    private static final String LOG_TAG = "LayerDrawable";
    public static final int PADDING_MODE_NEST = 0;
    public static final int PADDING_MODE_STACK = 1;
    private boolean mChildRequestedInvalidation;
    private Rect mHotspotBounds;
    LayerState mLayerState;
    private boolean mMutated;
    private int mPaddingB[];
    private int mPaddingL[];
    private int mPaddingR[];
    private int mPaddingT[];
    private boolean mSuspendChildInvalidation;
    private final Rect mTmpContainer;
    private final Rect mTmpOutRect;
    private final Rect mTmpRect;
}
