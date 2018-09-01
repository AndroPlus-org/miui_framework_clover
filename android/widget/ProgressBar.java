// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import android.view.View;
import android.view.ViewHierarchyEncoder;
import android.view.accessibility.*;
import android.view.animation.*;
import java.util.ArrayList;

public class ProgressBar extends View
{
    private class AccessibilityEventSender
        implements Runnable
    {

        public void run()
        {
            sendAccessibilityEvent(4);
        }

        final ProgressBar this$0;

        private AccessibilityEventSender()
        {
            this$0 = ProgressBar.this;
            super();
        }

        AccessibilityEventSender(AccessibilityEventSender accessibilityeventsender)
        {
            this();
        }
    }

    private static class ProgressTintInfo
    {

        boolean mHasIndeterminateTint;
        boolean mHasIndeterminateTintMode;
        boolean mHasProgressBackgroundTint;
        boolean mHasProgressBackgroundTintMode;
        boolean mHasProgressTint;
        boolean mHasProgressTintMode;
        boolean mHasSecondaryProgressTint;
        boolean mHasSecondaryProgressTintMode;
        ColorStateList mIndeterminateTintList;
        android.graphics.PorterDuff.Mode mIndeterminateTintMode;
        ColorStateList mProgressBackgroundTintList;
        android.graphics.PorterDuff.Mode mProgressBackgroundTintMode;
        ColorStateList mProgressTintList;
        android.graphics.PorterDuff.Mode mProgressTintMode;
        ColorStateList mSecondaryProgressTintList;
        android.graphics.PorterDuff.Mode mSecondaryProgressTintMode;

        private ProgressTintInfo()
        {
        }

        ProgressTintInfo(ProgressTintInfo progresstintinfo)
        {
            this();
        }
    }

    private static class RefreshData
    {

        public static RefreshData obtain(int i, int j, boolean flag, boolean flag1)
        {
            RefreshData refreshdata = (RefreshData)sPool.acquire();
            RefreshData refreshdata1 = refreshdata;
            if(refreshdata == null)
                refreshdata1 = new RefreshData();
            refreshdata1.id = i;
            refreshdata1.progress = j;
            refreshdata1.fromUser = flag;
            refreshdata1.animate = flag1;
            return refreshdata1;
        }

        public void recycle()
        {
            sPool.release(this);
        }

        private static final int POOL_MAX = 24;
        private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(24);
        public boolean animate;
        public boolean fromUser;
        public int id;
        public int progress;


        private RefreshData()
        {
        }
    }

    private class RefreshProgressRunnable
        implements Runnable
    {

        public void run()
        {
            ProgressBar progressbar = ProgressBar.this;
            progressbar;
            JVM INSTR monitorenter ;
            int i = ProgressBar._2D_get0(ProgressBar.this).size();
            int j = 0;
_L2:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            RefreshData refreshdata = (RefreshData)ProgressBar._2D_get0(ProgressBar.this).get(j);
            ProgressBar._2D_wrap0(ProgressBar.this, refreshdata.id, refreshdata.progress, refreshdata.fromUser, true, refreshdata.animate);
            refreshdata.recycle();
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            ProgressBar._2D_get0(ProgressBar.this).clear();
            ProgressBar._2D_set0(ProgressBar.this, false);
            progressbar;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final ProgressBar this$0;

        private RefreshProgressRunnable()
        {
            this$0 = ProgressBar.this;
            super();
        }

        RefreshProgressRunnable(RefreshProgressRunnable refreshprogressrunnable)
        {
            this();
        }
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(progress);
            parcel.writeInt(secondaryProgress);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int progress;
        int secondaryProgress;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            progress = parcel.readInt();
            secondaryProgress = parcel.readInt();
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    static ArrayList _2D_get0(ProgressBar progressbar)
    {
        return progressbar.mRefreshData;
    }

    static float _2D_get1(ProgressBar progressbar)
    {
        return progressbar.mVisualProgress;
    }

    static boolean _2D_set0(ProgressBar progressbar, boolean flag)
    {
        progressbar.mRefreshIsPosted = flag;
        return flag;
    }

    static float _2D_set1(ProgressBar progressbar, float f)
    {
        progressbar.mVisualProgress = f;
        return f;
    }

    static void _2D_wrap0(ProgressBar progressbar, int i, int j, boolean flag, boolean flag1, boolean flag2)
    {
        progressbar.doRefreshProgress(i, j, flag, flag1, flag2);
    }

    static void _2D_wrap1(ProgressBar progressbar, int i, float f)
    {
        progressbar.setVisualProgress(i, f);
    }

    public ProgressBar(Context context)
    {
        this(context, null);
    }

    public ProgressBar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010077);
    }

    public ProgressBar(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ProgressBar(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mSampleWidth = 0;
        mMirrorForRtl = false;
        mRefreshData = new ArrayList();
        VISUAL_PROGRESS = new FloatProperty("visual_progress") {

            public Float get(ProgressBar progressbar)
            {
                return Float.valueOf(ProgressBar._2D_get1(progressbar));
            }

            public volatile Object get(Object obj)
            {
                return get((ProgressBar)obj);
            }

            public void setValue(ProgressBar progressbar, float f)
            {
                ProgressBar._2D_wrap1(progressbar, 0x102000d, f);
                ProgressBar._2D_set1(progressbar, f);
            }

            public volatile void setValue(Object obj, float f)
            {
                setValue((ProgressBar)obj, f);
            }

            final ProgressBar this$0;

            
            {
                this$0 = ProgressBar.this;
                super(s);
            }
        }
;
        mUiThreadId = Thread.currentThread().getId();
        initProgressBar();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ProgressBar, i, j);
        mNoInvalidate = true;
        Drawable drawable = attributeset.getDrawable(8);
        boolean flag;
        if(drawable != null)
            if(needsTileify(drawable))
                setProgressDrawableTiled(drawable);
            else
                setProgressDrawable(drawable);
        mDuration = attributeset.getInt(9, mDuration);
        mMinWidth = attributeset.getDimensionPixelSize(11, mMinWidth);
        mMaxWidth = attributeset.getDimensionPixelSize(0, mMaxWidth);
        mMinHeight = attributeset.getDimensionPixelSize(12, mMinHeight);
        mMaxHeight = attributeset.getDimensionPixelSize(1, mMaxHeight);
        mBehavior = attributeset.getInt(10, mBehavior);
        i = attributeset.getResourceId(13, 0x10a000b);
        if(i > 0)
            setInterpolator(context, i);
        setMin(attributeset.getInt(26, mMin));
        setMax(attributeset.getInt(2, mMax));
        setProgress(attributeset.getInt(3, mProgress));
        setSecondaryProgress(attributeset.getInt(4, mSecondaryProgress));
        context = attributeset.getDrawable(7);
        if(context != null)
            if(needsTileify(context))
                setIndeterminateDrawableTiled(context);
            else
                setIndeterminateDrawable(context);
        mOnlyIndeterminate = attributeset.getBoolean(6, mOnlyIndeterminate);
        mNoInvalidate = false;
        if(!mOnlyIndeterminate)
            flag = attributeset.getBoolean(5, mIndeterminate);
        else
            flag = true;
        setIndeterminate(flag);
        mMirrorForRtl = attributeset.getBoolean(15, mMirrorForRtl);
        if(attributeset.hasValue(17))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mProgressTintMode = Drawable.parseTintMode(attributeset.getInt(17, -1), null);
            mProgressTintInfo.mHasProgressTintMode = true;
        }
        if(attributeset.hasValue(16))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mProgressTintList = attributeset.getColorStateList(16);
            mProgressTintInfo.mHasProgressTint = true;
        }
        if(attributeset.hasValue(19))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mProgressBackgroundTintMode = Drawable.parseTintMode(attributeset.getInt(19, -1), null);
            mProgressTintInfo.mHasProgressBackgroundTintMode = true;
        }
        if(attributeset.hasValue(18))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mProgressBackgroundTintList = attributeset.getColorStateList(18);
            mProgressTintInfo.mHasProgressBackgroundTint = true;
        }
        if(attributeset.hasValue(21))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mSecondaryProgressTintMode = Drawable.parseTintMode(attributeset.getInt(21, -1), null);
            mProgressTintInfo.mHasSecondaryProgressTintMode = true;
        }
        if(attributeset.hasValue(20))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mSecondaryProgressTintList = attributeset.getColorStateList(20);
            mProgressTintInfo.mHasSecondaryProgressTint = true;
        }
        if(attributeset.hasValue(23))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mIndeterminateTintMode = Drawable.parseTintMode(attributeset.getInt(23, -1), null);
            mProgressTintInfo.mHasIndeterminateTintMode = true;
        }
        if(attributeset.hasValue(22))
        {
            if(mProgressTintInfo == null)
                mProgressTintInfo = new ProgressTintInfo(null);
            mProgressTintInfo.mIndeterminateTintList = attributeset.getColorStateList(22);
            mProgressTintInfo.mHasIndeterminateTint = true;
        }
        attributeset.recycle();
        applyProgressTints();
        applyIndeterminateTint();
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
    }

    private void applyIndeterminateTint()
    {
        if(mIndeterminateDrawable != null && mProgressTintInfo != null)
        {
            ProgressTintInfo progresstintinfo = mProgressTintInfo;
            if(progresstintinfo.mHasIndeterminateTint || progresstintinfo.mHasIndeterminateTintMode)
            {
                mIndeterminateDrawable = mIndeterminateDrawable.mutate();
                if(progresstintinfo.mHasIndeterminateTint)
                    mIndeterminateDrawable.setTintList(progresstintinfo.mIndeterminateTintList);
                if(progresstintinfo.mHasIndeterminateTintMode)
                    mIndeterminateDrawable.setTintMode(progresstintinfo.mIndeterminateTintMode);
                if(mIndeterminateDrawable.isStateful())
                    mIndeterminateDrawable.setState(getDrawableState());
            }
        }
    }

    private void applyPrimaryProgressTint()
    {
        if(mProgressTintInfo.mHasProgressTint || mProgressTintInfo.mHasProgressTintMode)
        {
            Drawable drawable = getTintTarget(0x102000d, true);
            if(drawable != null)
            {
                if(mProgressTintInfo.mHasProgressTint)
                    drawable.setTintList(mProgressTintInfo.mProgressTintList);
                if(mProgressTintInfo.mHasProgressTintMode)
                    drawable.setTintMode(mProgressTintInfo.mProgressTintMode);
                if(drawable.isStateful())
                    drawable.setState(getDrawableState());
            }
        }
    }

    private void applyProgressBackgroundTint()
    {
        if(mProgressTintInfo.mHasProgressBackgroundTint || mProgressTintInfo.mHasProgressBackgroundTintMode)
        {
            Drawable drawable = getTintTarget(0x1020000, false);
            if(drawable != null)
            {
                if(mProgressTintInfo.mHasProgressBackgroundTint)
                    drawable.setTintList(mProgressTintInfo.mProgressBackgroundTintList);
                if(mProgressTintInfo.mHasProgressBackgroundTintMode)
                    drawable.setTintMode(mProgressTintInfo.mProgressBackgroundTintMode);
                if(drawable.isStateful())
                    drawable.setState(getDrawableState());
            }
        }
    }

    private void applyProgressTints()
    {
        if(mProgressDrawable != null && mProgressTintInfo != null)
        {
            applyPrimaryProgressTint();
            applyProgressBackgroundTint();
            applySecondaryProgressTint();
        }
    }

    private void applySecondaryProgressTint()
    {
        if(mProgressTintInfo.mHasSecondaryProgressTint || mProgressTintInfo.mHasSecondaryProgressTintMode)
        {
            Drawable drawable = getTintTarget(0x102000f, false);
            if(drawable != null)
            {
                if(mProgressTintInfo.mHasSecondaryProgressTint)
                    drawable.setTintList(mProgressTintInfo.mSecondaryProgressTintList);
                if(mProgressTintInfo.mHasSecondaryProgressTintMode)
                    drawable.setTintMode(mProgressTintInfo.mSecondaryProgressTintMode);
                if(drawable.isStateful())
                    drawable.setState(getDrawableState());
            }
        }
    }

    private void doRefreshProgress(int i, int j, boolean flag, boolean flag1, boolean flag2)
    {
        this;
        JVM INSTR monitorenter ;
        int k = mMax - mMin;
        if(k <= 0) goto _L2; else goto _L1
_L1:
        float f = (float)(j - mMin) / (float)k;
_L3:
        boolean flag3;
        ObjectAnimator objectanimator;
        if(i == 0x102000d)
            flag3 = true;
        else
            flag3 = false;
        if(!flag3 || !flag2)
            break MISSING_BLOCK_LABEL_130;
        objectanimator = ObjectAnimator.ofFloat(this, VISUAL_PROGRESS, new float[] {
            f
        });
        objectanimator.setAutoCancel(true);
        objectanimator.setDuration(80L);
        objectanimator.setInterpolator(PROGRESS_ANIM_INTERPOLATOR);
        objectanimator.start();
_L4:
        if(!flag3 || !flag1)
            break MISSING_BLOCK_LABEL_115;
        onProgressRefresh(f, flag, j);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        f = 0.0F;
          goto _L3
        setVisualProgress(i, f);
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    private Drawable getTintTarget(int i, boolean flag)
    {
        Drawable drawable = null;
        Drawable drawable1 = null;
        Drawable drawable2 = mProgressDrawable;
        if(drawable2 != null)
        {
            mProgressDrawable = drawable2.mutate();
            if(drawable2 instanceof LayerDrawable)
                drawable1 = ((LayerDrawable)drawable2).findDrawableByLayerId(i);
            drawable = drawable1;
            if(flag)
            {
                drawable = drawable1;
                if(drawable1 == null)
                    drawable = drawable2;
            }
        }
        return drawable;
    }

    private void initProgressBar()
    {
        mMin = 0;
        mMax = 100;
        mProgress = 0;
        mSecondaryProgress = 0;
        mIndeterminate = false;
        mOnlyIndeterminate = false;
        mDuration = 4000;
        mBehavior = 1;
        mMinWidth = 24;
        mMaxWidth = 48;
        mMinHeight = 24;
        mMaxHeight = 48;
    }

    private static boolean needsTileify(Drawable drawable)
    {
        if(drawable instanceof LayerDrawable)
        {
            drawable = (LayerDrawable)drawable;
            int i = drawable.getNumberOfLayers();
            for(int k = 0; k < i; k++)
                if(needsTileify(drawable.getDrawable(k)))
                    return true;

            return false;
        }
        if(drawable instanceof StateListDrawable)
        {
            drawable = (StateListDrawable)drawable;
            int j = drawable.getStateCount();
            for(int l = 0; l < j; l++)
                if(needsTileify(drawable.getStateDrawable(l)))
                    return true;

            return false;
        }
        return drawable instanceof BitmapDrawable;
    }

    private void refreshProgress(int i, int j, boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        if(mUiThreadId != Thread.currentThread().getId())
            break MISSING_BLOCK_LABEL_29;
        doRefreshProgress(i, j, flag, true, flag1);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        if(mRefreshProgressRunnable == null)
        {
            RefreshProgressRunnable refreshprogressrunnable = JVM INSTR new #17  <Class ProgressBar$RefreshProgressRunnable>;
            refreshprogressrunnable.this. RefreshProgressRunnable(null);
            mRefreshProgressRunnable = refreshprogressrunnable;
        }
        RefreshData refreshdata = RefreshData.obtain(i, j, flag, flag1);
        mRefreshData.add(refreshdata);
        if(mAttached && mRefreshIsPosted ^ true)
        {
            post(mRefreshProgressRunnable);
            mRefreshIsPosted = true;
        }
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void scheduleAccessibilityEventSender()
    {
        if(mAccessibilityEventSender == null)
            mAccessibilityEventSender = new AccessibilityEventSender(null);
        else
            removeCallbacks(mAccessibilityEventSender);
        postDelayed(mAccessibilityEventSender, 200L);
    }

    private void setVisualProgress(int i, float f)
    {
        mVisualProgress = f;
        Drawable drawable = mCurrentDrawable;
        Drawable drawable1 = drawable;
        if(drawable instanceof LayerDrawable)
        {
            drawable = ((LayerDrawable)drawable).findDrawableByLayerId(i);
            drawable1 = drawable;
            if(drawable == null)
                drawable1 = mCurrentDrawable;
        }
        if(drawable1 != null)
            drawable1.setLevel((int)(10000F * f));
        else
            invalidate();
        onVisualProgressChanged(i, f);
    }

    private void swapCurrentDrawable(Drawable drawable)
    {
        Drawable drawable1 = mCurrentDrawable;
        mCurrentDrawable = drawable;
        if(drawable1 != mCurrentDrawable)
        {
            if(drawable1 != null)
                drawable1.setVisible(false, false);
            if(mCurrentDrawable != null)
            {
                drawable = mCurrentDrawable;
                boolean flag;
                if(getWindowVisibility() == 0)
                    flag = isShown();
                else
                    flag = false;
                drawable.setVisible(flag, false);
            }
        }
    }

    private Drawable tileify(Drawable drawable, boolean flag)
    {
        if(drawable instanceof LayerDrawable)
        {
            drawable = (LayerDrawable)drawable;
            int i = drawable.getNumberOfLayers();
            Drawable adrawable[] = new Drawable[i];
            int k = 0;
            while(k < i) 
            {
                int j1 = drawable.getId(k);
                Drawable drawable1 = drawable.getDrawable(k);
                if(j1 == 0x102000d || j1 == 0x102000f)
                    flag = true;
                else
                    flag = false;
                adrawable[k] = tileify(drawable1, flag);
                k++;
            }
            LayerDrawable layerdrawable = new LayerDrawable(adrawable);
            for(int l = 0; l < i; l++)
            {
                layerdrawable.setId(l, drawable.getId(l));
                layerdrawable.setLayerGravity(l, drawable.getLayerGravity(l));
                layerdrawable.setLayerWidth(l, drawable.getLayerWidth(l));
                layerdrawable.setLayerHeight(l, drawable.getLayerHeight(l));
                layerdrawable.setLayerInsetLeft(l, drawable.getLayerInsetLeft(l));
                layerdrawable.setLayerInsetRight(l, drawable.getLayerInsetRight(l));
                layerdrawable.setLayerInsetTop(l, drawable.getLayerInsetTop(l));
                layerdrawable.setLayerInsetBottom(l, drawable.getLayerInsetBottom(l));
                layerdrawable.setLayerInsetStart(l, drawable.getLayerInsetStart(l));
                layerdrawable.setLayerInsetEnd(l, drawable.getLayerInsetEnd(l));
            }

            return layerdrawable;
        }
        if(drawable instanceof StateListDrawable)
        {
            drawable = (StateListDrawable)drawable;
            StateListDrawable statelistdrawable = new StateListDrawable();
            int j = drawable.getStateCount();
            for(int i1 = 0; i1 < j; i1++)
                statelistdrawable.addState(drawable.getStateSet(i1), tileify(drawable.getStateDrawable(i1), flag));

            return statelistdrawable;
        }
        if(drawable instanceof BitmapDrawable)
        {
            drawable = (BitmapDrawable)drawable.getConstantState().newDrawable(getResources());
            drawable.setTileModeXY(android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.CLAMP);
            if(mSampleWidth <= 0)
                mSampleWidth = drawable.getIntrinsicWidth();
            if(flag)
                return new ClipDrawable(drawable, 3, 1);
            else
                return drawable;
        } else
        {
            return drawable;
        }
    }

    private Drawable tileifyIndeterminate(Drawable drawable)
    {
        Object obj = drawable;
        if(drawable instanceof AnimationDrawable)
        {
            AnimationDrawable animationdrawable = (AnimationDrawable)drawable;
            int i = animationdrawable.getNumberOfFrames();
            obj = new AnimationDrawable();
            ((AnimationDrawable) (obj)).setOneShot(animationdrawable.isOneShot());
            for(int j = 0; j < i; j++)
            {
                drawable = tileify(animationdrawable.getFrame(j), true);
                drawable.setLevel(10000);
                ((AnimationDrawable) (obj)).addFrame(drawable, animationdrawable.getDuration(j));
            }

            ((AnimationDrawable) (obj)).setLevel(10000);
        }
        return ((Drawable) (obj));
    }

    private void updateDrawableBounds(int i, int j)
    {
        int k = i - (mPaddingRight + mPaddingLeft);
        int l = j - (mPaddingTop + mPaddingBottom);
        i = k;
        j = l;
        boolean flag = false;
        boolean flag1 = false;
        int i1 = j;
        int j1 = i;
        if(mIndeterminateDrawable != null)
        {
            i1 = j;
            int k1 = ((flag1) ? 1 : 0);
            j1 = i;
            int l1 = ((flag) ? 1 : 0);
            if(mOnlyIndeterminate)
            {
                i1 = j;
                k1 = ((flag1) ? 1 : 0);
                j1 = i;
                l1 = ((flag) ? 1 : 0);
                if((mIndeterminateDrawable instanceof AnimationDrawable) ^ true)
                {
                    j1 = mIndeterminateDrawable.getIntrinsicWidth();
                    i1 = mIndeterminateDrawable.getIntrinsicHeight();
                    float f = (float)j1 / (float)i1;
                    float f1 = (float)k / (float)l;
                    i1 = j;
                    k1 = ((flag1) ? 1 : 0);
                    j1 = i;
                    l1 = ((flag) ? 1 : 0);
                    if(f != f1)
                        if(f1 > f)
                        {
                            i = (int)((float)l * f);
                            k1 = (k - i) / 2;
                            j1 = k1 + i;
                            l1 = ((flag) ? 1 : 0);
                            i1 = j;
                        } else
                        {
                            j = (int)((float)k * (1.0F / f));
                            l1 = (l - j) / 2;
                            i1 = l1 + j;
                            k1 = ((flag1) ? 1 : 0);
                            j1 = i;
                        }
                }
            }
            j = k1;
            i = j1;
            if(isLayoutRtl())
            {
                j = k1;
                i = j1;
                if(mMirrorForRtl)
                {
                    j = k - j1;
                    i = k - k1;
                }
            }
            mIndeterminateDrawable.setBounds(j, l1, i, i1);
            j1 = i;
        }
        if(mProgressDrawable != null)
            mProgressDrawable.setBounds(0, 0, j1, i1);
    }

    private void updateDrawableState()
    {
        int ai[] = getDrawableState();
        boolean flag = false;
        Drawable drawable = mProgressDrawable;
        boolean flag1 = flag;
        if(drawable != null)
        {
            flag1 = flag;
            if(drawable.isStateful())
                flag1 = drawable.setState(ai);
        }
        drawable = mIndeterminateDrawable;
        flag = flag1;
        if(drawable != null)
        {
            flag = flag1;
            if(drawable.isStateful())
                flag = flag1 | drawable.setState(ai);
        }
        if(flag)
            invalidate();
    }

    void drawTrack(Canvas canvas)
    {
        Drawable drawable = mCurrentDrawable;
        if(drawable == null)
            break MISSING_BLOCK_LABEL_155;
        int i = canvas.save();
        long l;
        float f;
        if(isLayoutRtl() && mMirrorForRtl)
        {
            canvas.translate(getWidth() - mPaddingRight, mPaddingTop);
            canvas.scale(-1F, 1.0F);
        } else
        {
            canvas.translate(mPaddingLeft, mPaddingTop);
        }
        l = getDrawingTime();
        if(!mHasAnimation)
            break MISSING_BLOCK_LABEL_117;
        mAnimation.getTransformation(l, mTransformation);
        f = mTransformation.getAlpha();
        mInDrawing = true;
        drawable.setLevel((int)(10000F * f));
        mInDrawing = false;
        postInvalidateOnAnimation();
        drawable.draw(canvas);
        canvas.restoreToCount(i);
        if(mShouldStartAnimationDrawable && (drawable instanceof Animatable))
        {
            ((Animatable)drawable).start();
            mShouldStartAnimationDrawable = false;
        }
        return;
        canvas;
        mInDrawing = false;
        throw canvas;
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if(mProgressDrawable != null)
            mProgressDrawable.setHotspot(f, f1);
        if(mIndeterminateDrawable != null)
            mIndeterminateDrawable.setHotspot(f, f1);
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        updateDrawableState();
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("progress:max", getMax());
        viewhierarchyencoder.addProperty("progress:progress", getProgress());
        viewhierarchyencoder.addProperty("progress:secondaryProgress", getSecondaryProgress());
        viewhierarchyencoder.addProperty("progress:indeterminate", isIndeterminate());
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ProgressBar.getName();
    }

    Drawable getCurrentDrawable()
    {
        return mCurrentDrawable;
    }

    Shape getDrawableShape()
    {
        return new RoundRectShape(new float[] {
            5F, 5F, 5F, 5F, 5F, 5F, 5F, 5F
        }, null, null);
    }

    public Drawable getIndeterminateDrawable()
    {
        return mIndeterminateDrawable;
    }

    public ColorStateList getIndeterminateTintList()
    {
        ColorStateList colorstatelist = null;
        if(mProgressTintInfo != null)
            colorstatelist = mProgressTintInfo.mIndeterminateTintList;
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getIndeterminateTintMode()
    {
        android.graphics.PorterDuff.Mode mode = null;
        if(mProgressTintInfo != null)
            mode = mProgressTintInfo.mIndeterminateTintMode;
        return mode;
    }

    public Interpolator getInterpolator()
    {
        return mInterpolator;
    }

    public int getMax()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mMax;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getMin()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mMin;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean getMirrorForRtl()
    {
        return mMirrorForRtl;
    }

    public int getProgress()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        if(!flag) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        i = mProgress;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public ColorStateList getProgressBackgroundTintList()
    {
        ColorStateList colorstatelist = null;
        if(mProgressTintInfo != null)
            colorstatelist = mProgressTintInfo.mProgressBackgroundTintList;
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getProgressBackgroundTintMode()
    {
        android.graphics.PorterDuff.Mode mode = null;
        if(mProgressTintInfo != null)
            mode = mProgressTintInfo.mProgressBackgroundTintMode;
        return mode;
    }

    public Drawable getProgressDrawable()
    {
        return mProgressDrawable;
    }

    public ColorStateList getProgressTintList()
    {
        ColorStateList colorstatelist = null;
        if(mProgressTintInfo != null)
            colorstatelist = mProgressTintInfo.mProgressTintList;
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getProgressTintMode()
    {
        android.graphics.PorterDuff.Mode mode = null;
        if(mProgressTintInfo != null)
            mode = mProgressTintInfo.mProgressTintMode;
        return mode;
    }

    public int getSecondaryProgress()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        if(!flag) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        this;
        JVM INSTR monitorexit ;
        return i;
_L2:
        i = mSecondaryProgress;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public ColorStateList getSecondaryProgressTintList()
    {
        ColorStateList colorstatelist = null;
        if(mProgressTintInfo != null)
            colorstatelist = mProgressTintInfo.mSecondaryProgressTintList;
        return colorstatelist;
    }

    public android.graphics.PorterDuff.Mode getSecondaryProgressTintMode()
    {
        android.graphics.PorterDuff.Mode mode = null;
        if(mProgressTintInfo != null)
            mode = mProgressTintInfo.mSecondaryProgressTintMode;
        return mode;
    }

    public final void incrementProgressBy(int i)
    {
        this;
        JVM INSTR monitorenter ;
        setProgress(mProgress + i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void incrementSecondaryProgressBy(int i)
    {
        this;
        JVM INSTR monitorenter ;
        setSecondaryProgress(mSecondaryProgress + i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void invalidateDrawable(Drawable drawable)
    {
        if(!mInDrawing)
            if(verifyDrawable(drawable))
            {
                drawable = drawable.getBounds();
                int i = mScrollX + mPaddingLeft;
                int j = mScrollY + mPaddingTop;
                invalidate(((Rect) (drawable)).left + i, ((Rect) (drawable)).top + j, ((Rect) (drawable)).right + i, ((Rect) (drawable)).bottom + j);
            } else
            {
                super.invalidateDrawable(drawable);
            }
    }

    public boolean isAnimating()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isIndeterminate())
        {
            flag1 = flag;
            if(getWindowVisibility() == 0)
                flag1 = isShown();
        }
        return flag1;
    }

    public boolean isIndeterminate()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if(mProgressDrawable != null)
            mProgressDrawable.jumpToCurrentState();
        if(mIndeterminateDrawable != null)
            mIndeterminateDrawable.jumpToCurrentState();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if(mIndeterminate)
            startAnimation();
        if(mRefreshData == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        int i = mRefreshData.size();
        int j = 0;
_L4:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        RefreshData refreshdata = (RefreshData)mRefreshData.get(j);
        doRefreshProgress(refreshdata.id, refreshdata.progress, refreshdata.fromUser, true, refreshdata.animate);
        refreshdata.recycle();
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        mRefreshData.clear();
        this;
        JVM INSTR monitorexit ;
_L2:
        mAttached = true;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected void onDetachedFromWindow()
    {
        if(mIndeterminate)
            stopAnimation();
        if(mRefreshProgressRunnable != null)
        {
            removeCallbacks(mRefreshProgressRunnable);
            mRefreshIsPosted = false;
        }
        if(mAccessibilityEventSender != null)
            removeCallbacks(mAccessibilityEventSender);
        super.onDetachedFromWindow();
        mAttached = false;
    }

    protected void onDraw(Canvas canvas)
    {
        this;
        JVM INSTR monitorenter ;
        super.onDraw(canvas);
        drawTrack(canvas);
        this;
        JVM INSTR monitorexit ;
        return;
        canvas;
        throw canvas;
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        accessibilityevent.setItemCount(mMax - mMin);
        accessibilityevent.setCurrentItemIndex(mProgress);
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(!isIndeterminate())
            accessibilitynodeinfo.setRangeInfo(android.view.accessibility.AccessibilityNodeInfo.RangeInfo.obtain(0, 0.0F, getMax(), getProgress()));
    }

    protected void onMeasure(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        int k;
        int l;
        k = 0;
        l = 0;
        Drawable drawable = mCurrentDrawable;
        if(drawable == null)
            break MISSING_BLOCK_LABEL_59;
        k = Math.max(mMinWidth, Math.min(mMaxWidth, drawable.getIntrinsicWidth()));
        l = Math.max(mMinHeight, Math.min(mMaxHeight, drawable.getIntrinsicHeight()));
        updateDrawableState();
        int i1 = mPaddingLeft;
        int j1 = mPaddingRight;
        int k1 = mPaddingTop;
        int l1 = mPaddingBottom;
        setMeasuredDimension(resolveSizeAndState(k + (i1 + j1), i, 0), resolveSizeAndState(l + (k1 + l1), j, 0));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void onProgressRefresh(float f, boolean flag, int i)
    {
        if(AccessibilityManager.getInstance(mContext).isEnabled())
            scheduleAccessibilityEventSender();
    }

    public void onResolveDrawables(int i)
    {
        Drawable drawable = mCurrentDrawable;
        if(drawable != null)
            drawable.setLayoutDirection(i);
        if(mIndeterminateDrawable != null)
            mIndeterminateDrawable.setLayoutDirection(i);
        if(mProgressDrawable != null)
            mProgressDrawable.setLayoutDirection(i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        setProgress(((SavedState) (parcelable)).progress);
        setSecondaryProgress(((SavedState) (parcelable)).secondaryProgress);
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.progress = mProgress;
        savedstate.secondaryProgress = mSecondaryProgress;
        return savedstate;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        updateDrawableBounds(i, j);
    }

    public void onVisibilityAggregated(boolean flag)
    {
        super.onVisibilityAggregated(flag);
        if(flag != mAggregatedIsVisible)
        {
            mAggregatedIsVisible = flag;
            if(mIndeterminate)
                if(flag)
                    startAnimation();
                else
                    stopAnimation();
            if(mCurrentDrawable != null)
                mCurrentDrawable.setVisible(flag, false);
        }
    }

    void onVisualProgressChanged(int i, float f)
    {
    }

    public void postInvalidate()
    {
        if(!mNoInvalidate)
            super.postInvalidate();
    }

    public void setIndeterminate(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(mOnlyIndeterminate && !(mIndeterminate ^ true) || flag == mIndeterminate)
            break MISSING_BLOCK_LABEL_47;
        mIndeterminate = flag;
        if(!flag)
            break MISSING_BLOCK_LABEL_50;
        swapCurrentDrawable(mIndeterminateDrawable);
        startAnimation();
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        swapCurrentDrawable(mProgressDrawable);
        stopAnimation();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void setIndeterminateDrawable(Drawable drawable)
    {
        if(mIndeterminateDrawable != drawable)
        {
            if(mIndeterminateDrawable != null)
            {
                mIndeterminateDrawable.setCallback(null);
                unscheduleDrawable(mIndeterminateDrawable);
            }
            mIndeterminateDrawable = drawable;
            if(drawable != null)
            {
                drawable.setCallback(this);
                drawable.setLayoutDirection(getLayoutDirection());
                if(drawable.isStateful())
                    drawable.setState(getDrawableState());
                applyIndeterminateTint();
            }
            if(mIndeterminate)
            {
                swapCurrentDrawable(drawable);
                postInvalidate();
            }
        }
    }

    public void setIndeterminateDrawableTiled(Drawable drawable)
    {
        Drawable drawable1 = drawable;
        if(drawable != null)
            drawable1 = tileifyIndeterminate(drawable);
        setIndeterminateDrawable(drawable1);
    }

    public void setIndeterminateTintList(ColorStateList colorstatelist)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mIndeterminateTintList = colorstatelist;
        mProgressTintInfo.mHasIndeterminateTint = true;
        applyIndeterminateTint();
    }

    public void setIndeterminateTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mIndeterminateTintMode = mode;
        mProgressTintInfo.mHasIndeterminateTintMode = true;
        applyIndeterminateTint();
    }

    public void setInterpolator(Context context, int i)
    {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mInterpolator = interpolator;
    }

    public void setMax(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = i;
        if(!mMinInitialized)
            break MISSING_BLOCK_LABEL_26;
        j = i;
        if(i < mMin)
            j = mMin;
        mMaxInitialized = true;
        if(!mMinInitialized || j == mMax)
            break MISSING_BLOCK_LABEL_84;
        mMax = j;
        postInvalidate();
        if(mProgress > j)
            mProgress = j;
        refreshProgress(0x102000d, mProgress, false, false);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        mMax = j;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void setMin(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = i;
        if(!mMaxInitialized)
            break MISSING_BLOCK_LABEL_26;
        j = i;
        if(i > mMax)
            j = mMax;
        mMinInitialized = true;
        if(!mMaxInitialized || j == mMin)
            break MISSING_BLOCK_LABEL_84;
        mMin = j;
        postInvalidate();
        if(mProgress < j)
            mProgress = j;
        refreshProgress(0x102000d, mProgress, false, false);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        mMin = j;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void setProgress(int i)
    {
        this;
        JVM INSTR monitorenter ;
        setProgressInternal(i, false, false);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setProgress(int i, boolean flag)
    {
        setProgressInternal(i, false, flag);
    }

    public void setProgressBackgroundTintList(ColorStateList colorstatelist)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mProgressBackgroundTintList = colorstatelist;
        mProgressTintInfo.mHasProgressBackgroundTint = true;
        if(mProgressDrawable != null)
            applyProgressBackgroundTint();
    }

    public void setProgressBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mProgressBackgroundTintMode = mode;
        mProgressTintInfo.mHasProgressBackgroundTintMode = true;
        if(mProgressDrawable != null)
            applyProgressBackgroundTint();
    }

    public void setProgressDrawable(Drawable drawable)
    {
        if(mProgressDrawable != drawable)
        {
            if(mProgressDrawable != null)
            {
                mProgressDrawable.setCallback(null);
                unscheduleDrawable(mProgressDrawable);
            }
            mProgressDrawable = drawable;
            if(drawable != null)
            {
                drawable.setCallback(this);
                drawable.setLayoutDirection(getLayoutDirection());
                if(drawable.isStateful())
                    drawable.setState(getDrawableState());
                int i = drawable.getMinimumHeight();
                if(mMaxHeight < i)
                {
                    mMaxHeight = i;
                    requestLayout();
                }
                applyProgressTints();
            }
            if(!mIndeterminate)
            {
                swapCurrentDrawable(drawable);
                postInvalidate();
            }
            updateDrawableBounds(getWidth(), getHeight());
            updateDrawableState();
            doRefreshProgress(0x102000d, mProgress, false, false, false);
            doRefreshProgress(0x102000f, mSecondaryProgress, false, false, false);
        }
    }

    public void setProgressDrawableTiled(Drawable drawable)
    {
        Drawable drawable1 = drawable;
        if(drawable != null)
            drawable1 = tileify(drawable, false);
        setProgressDrawable(drawable1);
    }

    boolean setProgressInternal(int i, boolean flag, boolean flag1)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag2 = mIndeterminate;
        if(!flag2)
            break MISSING_BLOCK_LABEL_17;
        this;
        JVM INSTR monitorexit ;
        return false;
        int j;
        j = MathUtils.constrain(i, mMin, mMax);
        i = mProgress;
        if(j != i)
            break MISSING_BLOCK_LABEL_46;
        this;
        JVM INSTR monitorexit ;
        return false;
        mProgress = j;
        refreshProgress(0x102000d, mProgress, flag, flag1);
        this;
        JVM INSTR monitorexit ;
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    public void setProgressTintList(ColorStateList colorstatelist)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mProgressTintList = colorstatelist;
        mProgressTintInfo.mHasProgressTint = true;
        if(mProgressDrawable != null)
            applyPrimaryProgressTint();
    }

    public void setProgressTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mProgressTintMode = mode;
        mProgressTintInfo.mHasProgressTintMode = true;
        if(mProgressDrawable != null)
            applyPrimaryProgressTint();
    }

    public void setSecondaryProgress(int i)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIndeterminate;
        if(!flag)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        int j = i;
        if(i < mMin)
            j = mMin;
        i = j;
        if(j > mMax)
            i = mMax;
        if(i != mSecondaryProgress)
        {
            mSecondaryProgress = i;
            refreshProgress(0x102000f, mSecondaryProgress, false, false);
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setSecondaryProgressTintList(ColorStateList colorstatelist)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mSecondaryProgressTintList = colorstatelist;
        mProgressTintInfo.mHasSecondaryProgressTint = true;
        if(mProgressDrawable != null)
            applySecondaryProgressTint();
    }

    public void setSecondaryProgressTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mProgressTintInfo == null)
            mProgressTintInfo = new ProgressTintInfo(null);
        mProgressTintInfo.mSecondaryProgressTintMode = mode;
        mProgressTintInfo.mHasSecondaryProgressTintMode = true;
        if(mProgressDrawable != null)
            applySecondaryProgressTint();
    }

    void startAnimation()
    {
        if(getVisibility() != 0 || getWindowVisibility() != 0)
            return;
        if(mIndeterminateDrawable instanceof Animatable)
        {
            mShouldStartAnimationDrawable = true;
            mHasAnimation = false;
        } else
        {
            mHasAnimation = true;
            if(mInterpolator == null)
                mInterpolator = new LinearInterpolator();
            if(mTransformation == null)
                mTransformation = new Transformation();
            else
                mTransformation.clear();
            if(mAnimation == null)
                mAnimation = new AlphaAnimation(0.0F, 1.0F);
            else
                mAnimation.reset();
            mAnimation.setRepeatMode(mBehavior);
            mAnimation.setRepeatCount(-1);
            mAnimation.setDuration(mDuration);
            mAnimation.setInterpolator(mInterpolator);
            mAnimation.setStartTime(-1L);
        }
        postInvalidate();
    }

    void stopAnimation()
    {
        mHasAnimation = false;
        if(mIndeterminateDrawable instanceof Animatable)
        {
            ((Animatable)mIndeterminateDrawable).stop();
            mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        boolean flag;
        if(drawable == mProgressDrawable || drawable == mIndeterminateDrawable)
            flag = true;
        else
            flag = super.verifyDrawable(drawable);
        return flag;
    }

    private static final int MAX_LEVEL = 10000;
    private static final int PROGRESS_ANIM_DURATION = 80;
    private static final DecelerateInterpolator PROGRESS_ANIM_INTERPOLATOR = new DecelerateInterpolator();
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    private final FloatProperty VISUAL_PROGRESS;
    private AccessibilityEventSender mAccessibilityEventSender;
    private boolean mAggregatedIsVisible;
    private AlphaAnimation mAnimation;
    private boolean mAttached;
    private int mBehavior;
    private Drawable mCurrentDrawable;
    private int mDuration;
    private boolean mHasAnimation;
    private boolean mInDrawing;
    private boolean mIndeterminate;
    private Drawable mIndeterminateDrawable;
    private Interpolator mInterpolator;
    private int mMax;
    int mMaxHeight;
    private boolean mMaxInitialized;
    int mMaxWidth;
    private int mMin;
    int mMinHeight;
    private boolean mMinInitialized;
    int mMinWidth;
    boolean mMirrorForRtl;
    private boolean mNoInvalidate;
    private boolean mOnlyIndeterminate;
    private int mProgress;
    private Drawable mProgressDrawable;
    private ProgressTintInfo mProgressTintInfo;
    private final ArrayList mRefreshData;
    private boolean mRefreshIsPosted;
    private RefreshProgressRunnable mRefreshProgressRunnable;
    int mSampleWidth;
    private int mSecondaryProgress;
    private boolean mShouldStartAnimationDrawable;
    private Transformation mTransformation;
    private long mUiThreadId;
    private float mVisualProgress;

}
