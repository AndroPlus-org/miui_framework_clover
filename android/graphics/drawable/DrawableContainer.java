// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.*;
import android.os.SystemClock;
import android.util.SparseArray;

// Referenced classes of package android.graphics.drawable:
//            Drawable

public class DrawableContainer extends Drawable
    implements Drawable.Callback
{
    private static class BlockInvalidateCallback
        implements Drawable.Callback
    {

        public void invalidateDrawable(Drawable drawable)
        {
        }

        public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
        {
            if(mCallback != null)
                mCallback.scheduleDrawable(drawable, runnable, l);
        }

        public void unscheduleDrawable(Drawable drawable, Runnable runnable)
        {
            if(mCallback != null)
                mCallback.unscheduleDrawable(drawable, runnable);
        }

        public Drawable.Callback unwrap()
        {
            Drawable.Callback callback = mCallback;
            mCallback = null;
            return callback;
        }

        public BlockInvalidateCallback wrap(Drawable.Callback callback)
        {
            mCallback = callback;
            return this;
        }

        private Drawable.Callback mCallback;

        private BlockInvalidateCallback()
        {
        }

        BlockInvalidateCallback(BlockInvalidateCallback blockinvalidatecallback)
        {
            this();
        }
    }

    public static abstract class DrawableContainerState extends Drawable.ConstantState
    {

        static void _2D_wrap0(DrawableContainerState drawablecontainerstate)
        {
            drawablecontainerstate.mutate();
        }

        private void createAllFutures()
        {
            if(mDrawableFutures != null)
            {
                int i = mDrawableFutures.size();
                for(int j = 0; j < i; j++)
                {
                    int k = mDrawableFutures.keyAt(j);
                    Drawable.ConstantState constantstate = (Drawable.ConstantState)mDrawableFutures.valueAt(j);
                    mDrawables[k] = prepareDrawable(constantstate.newDrawable(mSourceRes));
                }

                mDrawableFutures = null;
            }
        }

        private void mutate()
        {
            int i = mNumChildren;
            Drawable adrawable[] = mDrawables;
            for(int j = 0; j < i; j++)
                if(adrawable[j] != null)
                    adrawable[j].mutate();

            mMutated = true;
        }

        private Drawable prepareDrawable(Drawable drawable)
        {
            drawable.setLayoutDirection(mLayoutDirection);
            drawable = drawable.mutate();
            drawable.setCallback(mOwner);
            return drawable;
        }

        public final int addChild(Drawable drawable)
        {
            int i = mNumChildren;
            if(i >= mDrawables.length)
                growArray(i, i + 10);
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(mOwner);
            mDrawables[i] = drawable;
            mNumChildren = mNumChildren + 1;
            mChildrenChangingConfigurations = mChildrenChangingConfigurations | drawable.getChangingConfigurations();
            invalidateCache();
            mConstantPadding = null;
            mCheckedPadding = false;
            mCheckedConstantSize = false;
            mCheckedConstantState = false;
            return i;
        }

        final void applyTheme(android.content.res.Resources.Theme theme)
        {
            if(theme != null)
            {
                createAllFutures();
                int i = mNumChildren;
                Drawable adrawable[] = mDrawables;
                for(int j = 0; j < i; j++)
                    if(adrawable[j] != null && adrawable[j].canApplyTheme())
                    {
                        adrawable[j].applyTheme(theme);
                        mChildrenChangingConfigurations = mChildrenChangingConfigurations | adrawable[j].getChangingConfigurations();
                    }

                updateDensity(theme.getResources());
            }
        }

        public boolean canApplyTheme()
        {
            int i = mNumChildren;
            Drawable adrawable[] = mDrawables;
            for(int j = 0; j < i; j++)
            {
                Object obj = adrawable[j];
                if(obj != null)
                {
                    if(((Drawable) (obj)).canApplyTheme())
                        return true;
                    continue;
                }
                obj = (Drawable.ConstantState)mDrawableFutures.get(j);
                if(obj != null && ((Drawable.ConstantState) (obj)).canApplyTheme())
                    return true;
            }

            return false;
        }

        public boolean canConstantState()
        {
            this;
            JVM INSTR monitorenter ;
            boolean flag;
            if(!mCheckedConstantState)
                break MISSING_BLOCK_LABEL_18;
            flag = mCanConstantState;
            this;
            JVM INSTR monitorexit ;
            return flag;
            int i;
            Drawable adrawable[];
            createAllFutures();
            mCheckedConstantState = true;
            i = mNumChildren;
            adrawable = mDrawables;
            int j = 0;
_L2:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            if(adrawable[j].getConstantState() != null)
                break MISSING_BLOCK_LABEL_65;
            mCanConstantState = false;
            this;
            JVM INSTR monitorexit ;
            return false;
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            mCanConstantState = true;
            this;
            JVM INSTR monitorexit ;
            return true;
            Exception exception;
            exception;
            throw exception;
        }

        final void clearMutated()
        {
            int i = mNumChildren;
            Drawable adrawable[] = mDrawables;
            for(int j = 0; j < i; j++)
                if(adrawable[j] != null)
                    adrawable[j].clearMutated();

            mMutated = false;
        }

        protected void computeConstantSize()
        {
            mCheckedConstantSize = true;
            createAllFutures();
            int i = mNumChildren;
            Drawable adrawable[] = mDrawables;
            mConstantHeight = -1;
            mConstantWidth = -1;
            mConstantMinimumHeight = 0;
            mConstantMinimumWidth = 0;
            for(int j = 0; j < i; j++)
            {
                Drawable drawable = adrawable[j];
                int k = drawable.getIntrinsicWidth();
                if(k > mConstantWidth)
                    mConstantWidth = k;
                k = drawable.getIntrinsicHeight();
                if(k > mConstantHeight)
                    mConstantHeight = k;
                k = drawable.getMinimumWidth();
                if(k > mConstantMinimumWidth)
                    mConstantMinimumWidth = k;
                k = drawable.getMinimumHeight();
                if(k > mConstantMinimumHeight)
                    mConstantMinimumHeight = k;
            }

        }

        final int getCapacity()
        {
            return mDrawables.length;
        }

        public int getChangingConfigurations()
        {
            return mChangingConfigurations | mChildrenChangingConfigurations;
        }

        public final Drawable getChild(int i)
        {
            Drawable drawable = mDrawables[i];
            if(drawable != null)
                return drawable;
            if(mDrawableFutures != null)
            {
                int j = mDrawableFutures.indexOfKey(i);
                if(j >= 0)
                {
                    Drawable drawable1 = prepareDrawable(((Drawable.ConstantState)mDrawableFutures.valueAt(j)).newDrawable(mSourceRes));
                    mDrawables[i] = drawable1;
                    mDrawableFutures.removeAt(j);
                    if(mDrawableFutures.size() == 0)
                        mDrawableFutures = null;
                    return drawable1;
                }
            }
            return null;
        }

        public final int getChildCount()
        {
            return mNumChildren;
        }

        public final Drawable[] getChildren()
        {
            createAllFutures();
            return mDrawables;
        }

        public final int getConstantHeight()
        {
            if(!mCheckedConstantSize)
                computeConstantSize();
            return mConstantHeight;
        }

        public final int getConstantMinimumHeight()
        {
            if(!mCheckedConstantSize)
                computeConstantSize();
            return mConstantMinimumHeight;
        }

        public final int getConstantMinimumWidth()
        {
            if(!mCheckedConstantSize)
                computeConstantSize();
            return mConstantMinimumWidth;
        }

        public final Rect getConstantPadding()
        {
            if(mVariablePadding)
                return null;
            if(mConstantPadding != null || mCheckedPadding)
                return mConstantPadding;
            createAllFutures();
            Rect rect = null;
            Rect rect1 = new Rect();
            int i = mNumChildren;
            Drawable adrawable[] = mDrawables;
            for(int j = 0; j < i;)
            {
                Rect rect2 = rect;
                if(adrawable[j].getPadding(rect1))
                {
                    Rect rect3 = rect;
                    if(rect == null)
                        rect3 = new Rect(0, 0, 0, 0);
                    if(rect1.left > rect3.left)
                        rect3.left = rect1.left;
                    if(rect1.top > rect3.top)
                        rect3.top = rect1.top;
                    if(rect1.right > rect3.right)
                        rect3.right = rect1.right;
                    rect2 = rect3;
                    if(rect1.bottom > rect3.bottom)
                    {
                        rect3.bottom = rect1.bottom;
                        rect2 = rect3;
                    }
                }
                j++;
                rect = rect2;
            }

            mCheckedPadding = true;
            mConstantPadding = rect;
            return rect;
        }

        public final int getConstantWidth()
        {
            if(!mCheckedConstantSize)
                computeConstantSize();
            return mConstantWidth;
        }

        public final int getEnterFadeDuration()
        {
            return mEnterFadeDuration;
        }

        public final int getExitFadeDuration()
        {
            return mExitFadeDuration;
        }

        public final int getOpacity()
        {
            if(mCheckedOpacity)
                return mOpacity;
            createAllFutures();
            int i = mNumChildren;
            Drawable adrawable[] = mDrawables;
            int j;
            boolean flag;
            int k;
            if(i > 0)
                j = adrawable[0].getOpacity();
            else
                j = -2;
            flag = true;
            k = j;
            for(j = ((flag) ? 1 : 0); j < i; j++)
                k = Drawable.resolveOpacity(k, adrawable[j].getOpacity());

            mOpacity = k;
            mCheckedOpacity = true;
            return k;
        }

        public void growArray(int i, int j)
        {
            Drawable adrawable[] = new Drawable[j];
            System.arraycopy(mDrawables, 0, adrawable, 0, i);
            mDrawables = adrawable;
        }

        void invalidateCache()
        {
            mCheckedOpacity = false;
            mCheckedStateful = false;
        }

        public final boolean isConstantSize()
        {
            return mConstantSize;
        }

        public final boolean isStateful()
        {
            if(mCheckedStateful)
                return mStateful;
            createAllFutures();
            int i = mNumChildren;
            Drawable adrawable[] = mDrawables;
            boolean flag = false;
            int j = 0;
            do
            {
label0:
                {
                    boolean flag1 = flag;
                    if(j < i)
                    {
                        if(!adrawable[j].isStateful())
                            break label0;
                        flag1 = true;
                    }
                    mStateful = flag1;
                    mCheckedStateful = true;
                    return flag1;
                }
                j++;
            } while(true);
        }

        public final void setConstantSize(boolean flag)
        {
            mConstantSize = flag;
        }

        public final void setEnterFadeDuration(int i)
        {
            mEnterFadeDuration = i;
        }

        public final void setExitFadeDuration(int i)
        {
            mExitFadeDuration = i;
        }

        final boolean setLayoutDirection(int i, int j)
        {
            boolean flag = false;
            int k = mNumChildren;
            Drawable adrawable[] = mDrawables;
            for(int l = 0; l < k;)
            {
                boolean flag1 = flag;
                if(adrawable[l] != null)
                {
                    boolean flag2 = adrawable[l].setLayoutDirection(i);
                    flag1 = flag;
                    if(l == j)
                        flag1 = flag2;
                }
                l++;
                flag = flag1;
            }

            mLayoutDirection = i;
            return flag;
        }

        public final void setVariablePadding(boolean flag)
        {
            mVariablePadding = flag;
        }

        final void updateDensity(Resources resources)
        {
            if(resources != null)
            {
                mSourceRes = resources;
                int i = Drawable.resolveDensity(resources, mDensity);
                int j = mDensity;
                mDensity = i;
                if(j != i)
                {
                    mCheckedConstantSize = false;
                    mCheckedPadding = false;
                }
            }
        }

        boolean mAutoMirrored;
        boolean mCanConstantState;
        int mChangingConfigurations;
        boolean mCheckedConstantSize;
        boolean mCheckedConstantState;
        boolean mCheckedOpacity;
        boolean mCheckedPadding;
        boolean mCheckedStateful;
        int mChildrenChangingConfigurations;
        ColorFilter mColorFilter;
        int mConstantHeight;
        int mConstantMinimumHeight;
        int mConstantMinimumWidth;
        Rect mConstantPadding;
        boolean mConstantSize;
        int mConstantWidth;
        int mDensity;
        boolean mDither;
        SparseArray mDrawableFutures;
        Drawable mDrawables[];
        int mEnterFadeDuration;
        int mExitFadeDuration;
        boolean mHasColorFilter;
        boolean mHasTintList;
        boolean mHasTintMode;
        int mLayoutDirection;
        boolean mMutated;
        int mNumChildren;
        int mOpacity;
        final DrawableContainer mOwner;
        Resources mSourceRes;
        boolean mStateful;
        ColorStateList mTintList;
        android.graphics.PorterDuff.Mode mTintMode;
        boolean mVariablePadding;

        protected DrawableContainerState(DrawableContainerState drawablecontainerstate, DrawableContainer drawablecontainer, Resources resources)
        {
            Object obj = null;
            super();
            mDensity = 160;
            mVariablePadding = false;
            mConstantSize = false;
            mDither = true;
            mEnterFadeDuration = 0;
            mExitFadeDuration = 0;
            mOwner = drawablecontainer;
            int i;
            if(resources != null)
            {
                drawablecontainer = resources;
            } else
            {
                drawablecontainer = obj;
                if(drawablecontainerstate != null)
                    drawablecontainer = drawablecontainerstate.mSourceRes;
            }
            mSourceRes = drawablecontainer;
            if(drawablecontainerstate != null)
                i = drawablecontainerstate.mDensity;
            else
                i = 0;
            mDensity = Drawable.resolveDensity(resources, i);
            if(drawablecontainerstate != null)
            {
                mChangingConfigurations = drawablecontainerstate.mChangingConfigurations;
                mChildrenChangingConfigurations = drawablecontainerstate.mChildrenChangingConfigurations;
                mCheckedConstantState = true;
                mCanConstantState = true;
                mVariablePadding = drawablecontainerstate.mVariablePadding;
                mConstantSize = drawablecontainerstate.mConstantSize;
                mDither = drawablecontainerstate.mDither;
                mMutated = drawablecontainerstate.mMutated;
                mLayoutDirection = drawablecontainerstate.mLayoutDirection;
                mEnterFadeDuration = drawablecontainerstate.mEnterFadeDuration;
                mExitFadeDuration = drawablecontainerstate.mExitFadeDuration;
                mAutoMirrored = drawablecontainerstate.mAutoMirrored;
                mColorFilter = drawablecontainerstate.mColorFilter;
                mHasColorFilter = drawablecontainerstate.mHasColorFilter;
                mTintList = drawablecontainerstate.mTintList;
                mTintMode = drawablecontainerstate.mTintMode;
                mHasTintList = drawablecontainerstate.mHasTintList;
                mHasTintMode = drawablecontainerstate.mHasTintMode;
                if(drawablecontainerstate.mDensity == mDensity)
                {
                    if(drawablecontainerstate.mCheckedPadding)
                    {
                        mConstantPadding = new Rect(drawablecontainerstate.mConstantPadding);
                        mCheckedPadding = true;
                    }
                    if(drawablecontainerstate.mCheckedConstantSize)
                    {
                        mConstantWidth = drawablecontainerstate.mConstantWidth;
                        mConstantHeight = drawablecontainerstate.mConstantHeight;
                        mConstantMinimumWidth = drawablecontainerstate.mConstantMinimumWidth;
                        mConstantMinimumHeight = drawablecontainerstate.mConstantMinimumHeight;
                        mCheckedConstantSize = true;
                    }
                }
                if(drawablecontainerstate.mCheckedOpacity)
                {
                    mOpacity = drawablecontainerstate.mOpacity;
                    mCheckedOpacity = true;
                }
                if(drawablecontainerstate.mCheckedStateful)
                {
                    mStateful = drawablecontainerstate.mStateful;
                    mCheckedStateful = true;
                }
                drawablecontainer = drawablecontainerstate.mDrawables;
                mDrawables = new Drawable[drawablecontainer.length];
                mNumChildren = drawablecontainerstate.mNumChildren;
                drawablecontainerstate = drawablecontainerstate.mDrawableFutures;
                int j;
                if(drawablecontainerstate != null)
                    mDrawableFutures = drawablecontainerstate.clone();
                else
                    mDrawableFutures = new SparseArray(mNumChildren);
                j = mNumChildren;
                i = 0;
                while(i < j) 
                {
                    if(drawablecontainer[i] != null)
                    {
                        drawablecontainerstate = drawablecontainer[i].getConstantState();
                        if(drawablecontainerstate != null)
                            mDrawableFutures.put(i, drawablecontainerstate);
                        else
                            mDrawables[i] = drawablecontainer[i];
                    }
                    i++;
                }
            } else
            {
                mDrawables = new Drawable[10];
                mNumChildren = 0;
            }
        }
    }


    public DrawableContainer()
    {
        mAlpha = 255;
        mCurIndex = -1;
        mLastIndex = -1;
    }

    private void initializeDrawableForDisplay(Drawable drawable)
    {
        if(mBlockInvalidateCallback == null)
            mBlockInvalidateCallback = new BlockInvalidateCallback(null);
        drawable.setCallback(mBlockInvalidateCallback.wrap(drawable.getCallback()));
        if(mDrawableContainerState.mEnterFadeDuration <= 0 && mHasAlpha)
            drawable.setAlpha(mAlpha);
        if(!mDrawableContainerState.mHasColorFilter)
            break MISSING_BLOCK_LABEL_188;
        drawable.setColorFilter(mDrawableContainerState.mColorFilter);
_L1:
        Rect rect;
        drawable.setVisible(isVisible(), true);
        drawable.setDither(mDrawableContainerState.mDither);
        drawable.setState(getState());
        drawable.setLevel(getLevel());
        drawable.setBounds(getBounds());
        drawable.setLayoutDirection(getLayoutDirection());
        drawable.setAutoMirrored(mDrawableContainerState.mAutoMirrored);
        rect = mHotspotBounds;
        if(rect == null)
            break MISSING_BLOCK_LABEL_176;
        drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
        drawable.setCallback(mBlockInvalidateCallback.unwrap());
        return;
        if(mDrawableContainerState.mHasTintList)
            drawable.setTintList(mDrawableContainerState.mTintList);
        if(mDrawableContainerState.mHasTintMode)
            drawable.setTintMode(mDrawableContainerState.mTintMode);
          goto _L1
        Exception exception;
        exception;
        drawable.setCallback(mBlockInvalidateCallback.unwrap());
        throw exception;
    }

    private boolean needsMirroring()
    {
        boolean flag = true;
        if(!isAutoMirrored() || getLayoutDirection() != 1)
            flag = false;
        return flag;
    }

    void animate(boolean flag)
    {
        mHasAlpha = true;
        long l = SystemClock.uptimeMillis();
        int i = 0;
        int j;
        if(mCurrDrawable != null)
        {
            j = i;
            if(mEnterAnimationEnd != 0L)
                if(mEnterAnimationEnd <= l)
                {
                    mCurrDrawable.setAlpha(mAlpha);
                    mEnterAnimationEnd = 0L;
                    j = i;
                } else
                {
                    j = (int)((mEnterAnimationEnd - l) * 255L) / mDrawableContainerState.mEnterFadeDuration;
                    mCurrDrawable.setAlpha(((255 - j) * mAlpha) / 255);
                    j = 1;
                }
        } else
        {
            mEnterAnimationEnd = 0L;
            j = i;
        }
        if(mLastDrawable != null)
        {
            i = j;
            if(mExitAnimationEnd != 0L)
                if(mExitAnimationEnd <= l)
                {
                    mLastDrawable.setVisible(false, false);
                    mLastDrawable = null;
                    mLastIndex = -1;
                    mExitAnimationEnd = 0L;
                    i = j;
                } else
                {
                    j = (int)((mExitAnimationEnd - l) * 255L) / mDrawableContainerState.mExitFadeDuration;
                    mLastDrawable.setAlpha((mAlpha * j) / 255);
                    i = 1;
                }
        } else
        {
            mExitAnimationEnd = 0L;
            i = j;
        }
        if(flag && i != 0)
            scheduleSelf(mAnimationRunnable, 16L + l);
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        mDrawableContainerState.applyTheme(theme);
    }

    public boolean canApplyTheme()
    {
        return mDrawableContainerState.canApplyTheme();
    }

    public void clearMutated()
    {
        super.clearMutated();
        mDrawableContainerState.clearMutated();
        mMutated = false;
    }

    DrawableContainerState cloneConstantState()
    {
        return mDrawableContainerState;
    }

    public void draw(Canvas canvas)
    {
        if(mCurrDrawable != null)
            mCurrDrawable.draw(canvas);
        if(mLastDrawable != null)
            mLastDrawable.draw(canvas);
    }

    public int getAlpha()
    {
        return mAlpha;
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mDrawableContainerState.getChangingConfigurations();
    }

    public Drawable.ConstantState getConstantState()
    {
        if(mDrawableContainerState.canConstantState())
        {
            mDrawableContainerState.mChangingConfigurations = getChangingConfigurations();
            return mDrawableContainerState;
        } else
        {
            return null;
        }
    }

    public Drawable getCurrent()
    {
        return mCurrDrawable;
    }

    public int getCurrentIndex()
    {
        return mCurIndex;
    }

    public void getHotspotBounds(Rect rect)
    {
        if(mHotspotBounds != null)
            rect.set(mHotspotBounds);
        else
            super.getHotspotBounds(rect);
    }

    public int getIntrinsicHeight()
    {
        if(mDrawableContainerState.isConstantSize())
            return mDrawableContainerState.getConstantHeight();
        int i;
        if(mCurrDrawable != null)
            i = mCurrDrawable.getIntrinsicHeight();
        else
            i = -1;
        return i;
    }

    public int getIntrinsicWidth()
    {
        if(mDrawableContainerState.isConstantSize())
            return mDrawableContainerState.getConstantWidth();
        int i;
        if(mCurrDrawable != null)
            i = mCurrDrawable.getIntrinsicWidth();
        else
            i = -1;
        return i;
    }

    public int getMinimumHeight()
    {
        if(mDrawableContainerState.isConstantSize())
            return mDrawableContainerState.getConstantMinimumHeight();
        int i;
        if(mCurrDrawable != null)
            i = mCurrDrawable.getMinimumHeight();
        else
            i = 0;
        return i;
    }

    public int getMinimumWidth()
    {
        if(mDrawableContainerState.isConstantSize())
            return mDrawableContainerState.getConstantMinimumWidth();
        int i;
        if(mCurrDrawable != null)
            i = mCurrDrawable.getMinimumWidth();
        else
            i = 0;
        return i;
    }

    public int getOpacity()
    {
        int i;
        if(mCurrDrawable == null || mCurrDrawable.isVisible() ^ true)
            i = -2;
        else
            i = mDrawableContainerState.getOpacity();
        return i;
    }

    public Insets getOpticalInsets()
    {
        if(mCurrDrawable != null)
            return mCurrDrawable.getOpticalInsets();
        else
            return Insets.NONE;
    }

    public void getOutline(Outline outline)
    {
        if(mCurrDrawable != null)
            mCurrDrawable.getOutline(outline);
    }

    public boolean getPadding(Rect rect)
    {
        Rect rect1 = mDrawableContainerState.getConstantPadding();
        boolean flag;
        if(rect1 != null)
        {
            rect.set(rect1);
            int i;
            if((rect1.left | rect1.top | rect1.bottom | rect1.right) != 0)
                flag = true;
            else
                flag = false;
        } else
        if(mCurrDrawable != null)
            flag = mCurrDrawable.getPadding(rect);
        else
            flag = super.getPadding(rect);
        if(needsMirroring())
        {
            i = rect.left;
            rect.left = rect.right;
            rect.right = i;
        }
        return flag;
    }

    public boolean hasFocusStateSpecified()
    {
        if(mCurrDrawable != null)
            return mCurrDrawable.hasFocusStateSpecified();
        if(mLastDrawable != null)
            return mLastDrawable.hasFocusStateSpecified();
        else
            return false;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if(mDrawableContainerState != null)
            mDrawableContainerState.invalidateCache();
        if(drawable == mCurrDrawable && getCallback() != null)
            getCallback().invalidateDrawable(this);
    }

    public boolean isAutoMirrored()
    {
        return mDrawableContainerState.mAutoMirrored;
    }

    public boolean isStateful()
    {
        return mDrawableContainerState.isStateful();
    }

    public void jumpToCurrentState()
    {
        boolean flag = false;
        if(mLastDrawable != null)
        {
            mLastDrawable.jumpToCurrentState();
            mLastDrawable = null;
            mLastIndex = -1;
            flag = true;
        }
        if(mCurrDrawable != null)
        {
            mCurrDrawable.jumpToCurrentState();
            if(mHasAlpha)
                mCurrDrawable.setAlpha(mAlpha);
        }
        if(mExitAnimationEnd != 0L)
        {
            mExitAnimationEnd = 0L;
            flag = true;
        }
        if(mEnterAnimationEnd != 0L)
        {
            mEnterAnimationEnd = 0L;
            flag = true;
        }
        if(flag)
            invalidateSelf();
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            DrawableContainerState drawablecontainerstate = cloneConstantState();
            DrawableContainerState._2D_wrap0(drawablecontainerstate);
            setConstantState(drawablecontainerstate);
            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        if(mLastDrawable != null)
            mLastDrawable.setBounds(rect);
        if(mCurrDrawable != null)
            mCurrDrawable.setBounds(rect);
    }

    public boolean onLayoutDirectionChanged(int i)
    {
        return mDrawableContainerState.setLayoutDirection(i, getCurrentIndex());
    }

    protected boolean onLevelChange(int i)
    {
        if(mLastDrawable != null)
            return mLastDrawable.setLevel(i);
        if(mCurrDrawable != null)
            return mCurrDrawable.setLevel(i);
        else
            return false;
    }

    protected boolean onStateChange(int ai[])
    {
        if(mLastDrawable != null)
            return mLastDrawable.setState(ai);
        if(mCurrDrawable != null)
            return mCurrDrawable.setState(ai);
        else
            return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        if(drawable == mCurrDrawable && getCallback() != null)
            getCallback().scheduleDrawable(this, runnable, l);
    }

    public boolean selectDrawable(int i)
    {
        if(i == mCurIndex)
            return false;
        long l = SystemClock.uptimeMillis();
        if(mDrawableContainerState.mExitFadeDuration > 0)
        {
            if(mLastDrawable != null)
                mLastDrawable.setVisible(false, false);
            Drawable drawable;
            if(mCurrDrawable != null)
            {
                mLastDrawable = mCurrDrawable;
                mLastIndex = mCurIndex;
                mExitAnimationEnd = (long)mDrawableContainerState.mExitFadeDuration + l;
            } else
            {
                mLastDrawable = null;
                mLastIndex = -1;
                mExitAnimationEnd = 0L;
            }
        } else
        if(mCurrDrawable != null)
            mCurrDrawable.setVisible(false, false);
        if(i >= 0 && i < mDrawableContainerState.mNumChildren)
        {
            drawable = mDrawableContainerState.getChild(i);
            mCurrDrawable = drawable;
            mCurIndex = i;
            if(drawable != null)
            {
                if(mDrawableContainerState.mEnterFadeDuration > 0)
                    mEnterAnimationEnd = (long)mDrawableContainerState.mEnterFadeDuration + l;
                initializeDrawableForDisplay(drawable);
            }
        } else
        {
            mCurrDrawable = null;
            mCurIndex = -1;
        }
        if(mEnterAnimationEnd != 0L || mExitAnimationEnd != 0L)
        {
            if(mAnimationRunnable == null)
                mAnimationRunnable = new Runnable() {

                    public void run()
                    {
                        animate(true);
                        invalidateSelf();
                    }

                    final DrawableContainer this$0;

            
            {
                this$0 = DrawableContainer.this;
                super();
            }
                }
;
            else
                unscheduleSelf(mAnimationRunnable);
            animate(true);
        }
        invalidateSelf();
        return true;
    }

    public void setAlpha(int i)
    {
        if(!mHasAlpha || mAlpha != i)
        {
            mHasAlpha = true;
            mAlpha = i;
            if(mCurrDrawable != null)
                if(mEnterAnimationEnd == 0L)
                    mCurrDrawable.setAlpha(i);
                else
                    animate(false);
        }
    }

    public void setAutoMirrored(boolean flag)
    {
        if(mDrawableContainerState.mAutoMirrored != flag)
        {
            mDrawableContainerState.mAutoMirrored = flag;
            if(mCurrDrawable != null)
                mCurrDrawable.setAutoMirrored(mDrawableContainerState.mAutoMirrored);
        }
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mDrawableContainerState.mHasColorFilter = true;
        if(mDrawableContainerState.mColorFilter != colorfilter)
        {
            mDrawableContainerState.mColorFilter = colorfilter;
            if(mCurrDrawable != null)
                mCurrDrawable.setColorFilter(colorfilter);
        }
    }

    protected void setConstantState(DrawableContainerState drawablecontainerstate)
    {
        mDrawableContainerState = drawablecontainerstate;
        if(mCurIndex >= 0)
        {
            mCurrDrawable = drawablecontainerstate.getChild(mCurIndex);
            if(mCurrDrawable != null)
                initializeDrawableForDisplay(mCurrDrawable);
        }
        mLastIndex = -1;
        mLastDrawable = null;
    }

    public void setCurrentIndex(int i)
    {
        selectDrawable(i);
    }

    public void setDither(boolean flag)
    {
        if(mDrawableContainerState.mDither != flag)
        {
            mDrawableContainerState.mDither = flag;
            if(mCurrDrawable != null)
                mCurrDrawable.setDither(mDrawableContainerState.mDither);
        }
    }

    public void setEnterFadeDuration(int i)
    {
        mDrawableContainerState.mEnterFadeDuration = i;
    }

    public void setExitFadeDuration(int i)
    {
        mDrawableContainerState.mExitFadeDuration = i;
    }

    public void setHotspot(float f, float f1)
    {
        if(mCurrDrawable != null)
            mCurrDrawable.setHotspot(f, f1);
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        if(mHotspotBounds == null)
            mHotspotBounds = new Rect(i, j, k, l);
        else
            mHotspotBounds.set(i, j, k, l);
        if(mCurrDrawable != null)
            mCurrDrawable.setHotspotBounds(i, j, k, l);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mDrawableContainerState.mHasTintList = true;
        if(mDrawableContainerState.mTintList != colorstatelist)
        {
            mDrawableContainerState.mTintList = colorstatelist;
            if(mCurrDrawable != null)
                mCurrDrawable.setTintList(colorstatelist);
        }
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mDrawableContainerState.mHasTintMode = true;
        if(mDrawableContainerState.mTintMode != mode)
        {
            mDrawableContainerState.mTintMode = mode;
            if(mCurrDrawable != null)
                mCurrDrawable.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        boolean flag2 = super.setVisible(flag, flag1);
        if(mLastDrawable != null)
            mLastDrawable.setVisible(flag, flag1);
        if(mCurrDrawable != null)
            mCurrDrawable.setVisible(flag, flag1);
        return flag2;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        if(drawable == mCurrDrawable && getCallback() != null)
            getCallback().unscheduleDrawable(this, runnable);
    }

    protected final void updateDensity(Resources resources)
    {
        mDrawableContainerState.updateDensity(resources);
    }

    private static final boolean DEBUG = false;
    private static final boolean DEFAULT_DITHER = true;
    private static final String TAG = "DrawableContainer";
    private int mAlpha;
    private Runnable mAnimationRunnable;
    private BlockInvalidateCallback mBlockInvalidateCallback;
    private int mCurIndex;
    private Drawable mCurrDrawable;
    private DrawableContainerState mDrawableContainerState;
    private long mEnterAnimationEnd;
    private long mExitAnimationEnd;
    private boolean mHasAlpha;
    private Rect mHotspotBounds;
    private Drawable mLastDrawable;
    private int mLastIndex;
    private boolean mMutated;
}
