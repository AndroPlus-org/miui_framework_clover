// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Handler;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.TypedValue;
import dalvik.system.CloseGuard;

// Referenced classes of package android.view.animation:
//            Transformation, AccelerateDecelerateInterpolator, Interpolator, AnimationUtils

public abstract class Animation
    implements Cloneable
{
    public static interface AnimationListener
    {

        public abstract void onAnimationEnd(Animation animation);

        public abstract void onAnimationRepeat(Animation animation);

        public abstract void onAnimationStart(Animation animation);
    }

    protected static class Description
    {

        static Description parseValue(TypedValue typedvalue)
        {
            int i = 1;
            Description description = new Description();
            if(typedvalue == null)
            {
                description.type = 0;
                description.value = 0.0F;
            } else
            {
                if(typedvalue.type == 6)
                {
                    if((typedvalue.data & 0xf) == 1)
                        i = 2;
                    description.type = i;
                    description.value = TypedValue.complexToFloat(typedvalue.data);
                    return description;
                }
                if(typedvalue.type == 4)
                {
                    description.type = 0;
                    description.value = typedvalue.getFloat();
                    return description;
                }
                if(typedvalue.type >= 16 && typedvalue.type <= 31)
                {
                    description.type = 0;
                    description.value = typedvalue.data;
                    return description;
                }
            }
            description.type = 0;
            description.value = 0.0F;
            return description;
        }

        public int type;
        public float value;

        protected Description()
        {
        }
    }

    private static class NoImagePreloadHolder
    {

        public static final boolean USE_CLOSEGUARD = SystemProperties.getBoolean("log.closeguard.Animation", false);


        private NoImagePreloadHolder()
        {
        }
    }


    public Animation()
    {
        mEnded = false;
        mStarted = false;
        mCycleFlip = false;
        mInitialized = false;
        mFillBefore = true;
        mFillAfter = false;
        mFillEnabled = false;
        mStartTime = -1L;
        mRepeatCount = 0;
        mRepeated = 0;
        mRepeatMode = 1;
        mScaleFactor = 1.0F;
        mDetachWallpaper = false;
        mMore = true;
        mOneMoreTime = true;
        mPreviousRegion = new RectF();
        mRegion = new RectF();
        mTransformation = new Transformation();
        mPreviousTransformation = new Transformation();
        guard = CloseGuard.get();
        ensureInterpolator();
    }

    public Animation(Context context, AttributeSet attributeset)
    {
        mEnded = false;
        mStarted = false;
        mCycleFlip = false;
        mInitialized = false;
        mFillBefore = true;
        mFillAfter = false;
        mFillEnabled = false;
        mStartTime = -1L;
        mRepeatCount = 0;
        mRepeated = 0;
        mRepeatMode = 1;
        mScaleFactor = 1.0F;
        mDetachWallpaper = false;
        mMore = true;
        mOneMoreTime = true;
        mPreviousRegion = new RectF();
        mRegion = new RectF();
        mTransformation = new Transformation();
        mPreviousTransformation = new Transformation();
        guard = CloseGuard.get();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Animation);
        setDuration(attributeset.getInt(2, 0));
        setStartOffset(attributeset.getInt(5, 0));
        setFillEnabled(attributeset.getBoolean(9, mFillEnabled));
        setFillBefore(attributeset.getBoolean(3, mFillBefore));
        setFillAfter(attributeset.getBoolean(4, mFillAfter));
        setRepeatCount(attributeset.getInt(6, mRepeatCount));
        setRepeatMode(attributeset.getInt(7, 1));
        setZAdjustment(attributeset.getInt(8, 0));
        setBackgroundColor(attributeset.getInt(0, 0));
        setDetachWallpaper(attributeset.getBoolean(10, false));
        int i = attributeset.getResourceId(1, 0);
        attributeset.recycle();
        if(i > 0)
            setInterpolator(context, i);
        ensureInterpolator();
    }

    private void fireAnimationEnd()
    {
        if(mListener != null)
            if(mListenerHandler == null)
                mListener.onAnimationEnd(this);
            else
                mListenerHandler.postAtFrontOfQueue(mOnEnd);
    }

    private void fireAnimationRepeat()
    {
        if(mListener != null)
            if(mListenerHandler == null)
                mListener.onAnimationRepeat(this);
            else
                mListenerHandler.postAtFrontOfQueue(mOnRepeat);
    }

    private void fireAnimationStart()
    {
        if(mListener != null)
            if(mListenerHandler == null)
                mListener.onAnimationStart(this);
            else
                mListenerHandler.postAtFrontOfQueue(mOnStart);
    }

    private boolean isCanceled()
    {
        boolean flag;
        if(mStartTime == 0x8000000000000000L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    protected void applyTransformation(float f, Transformation transformation)
    {
    }

    public void cancel()
    {
        if(mStarted && mEnded ^ true)
        {
            fireAnimationEnd();
            mEnded = true;
            guard.close();
        }
        mStartTime = 0x8000000000000000L;
        mOneMoreTime = false;
        mMore = false;
    }

    protected Animation clone()
        throws CloneNotSupportedException
    {
        Animation animation = (Animation)super.clone();
        animation.mPreviousRegion = new RectF();
        animation.mRegion = new RectF();
        animation.mTransformation = new Transformation();
        animation.mPreviousTransformation = new Transformation();
        return animation;
    }

    protected volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public long computeDurationHint()
    {
        return (getStartOffset() + getDuration()) * (long)(getRepeatCount() + 1);
    }

    public void detach()
    {
        if(mStarted && mEnded ^ true)
        {
            mEnded = true;
            guard.close();
            fireAnimationEnd();
        }
    }

    protected void ensureInterpolator()
    {
        if(mInterpolator == null)
            mInterpolator = new AccelerateDecelerateInterpolator();
    }

    protected void finalize()
        throws Throwable
    {
        if(guard != null)
            guard.warnIfOpen();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getBackgroundColor()
    {
        return mBackgroundColor;
    }

    public boolean getDetachWallpaper()
    {
        return mDetachWallpaper;
    }

    public long getDuration()
    {
        return mDuration;
    }

    public boolean getFillAfter()
    {
        return mFillAfter;
    }

    public boolean getFillBefore()
    {
        return mFillBefore;
    }

    public Interpolator getInterpolator()
    {
        return mInterpolator;
    }

    public void getInvalidateRegion(int i, int j, int k, int l, RectF rectf, Transformation transformation)
    {
        RectF rectf1 = mRegion;
        Object obj = mPreviousRegion;
        rectf.set(i, j, k, l);
        transformation.getMatrix().mapRect(rectf);
        rectf.inset(-1F, -1F);
        rectf1.set(rectf);
        rectf.union(((RectF) (obj)));
        ((RectF) (obj)).set(rectf1);
        rectf = mTransformation;
        obj = mPreviousTransformation;
        rectf.set(transformation);
        transformation.set(((Transformation) (obj)));
        ((Transformation) (obj)).set(rectf);
    }

    public int getRepeatCount()
    {
        return mRepeatCount;
    }

    public int getRepeatMode()
    {
        return mRepeatMode;
    }

    protected float getScaleFactor()
    {
        return mScaleFactor;
    }

    public long getStartOffset()
    {
        return mStartOffset;
    }

    public long getStartTime()
    {
        return mStartTime;
    }

    public boolean getTransformation(long l, Transformation transformation)
    {
        if(mStartTime == -1L)
            mStartTime = l;
        long l1 = getStartOffset();
        long l2 = mDuration;
        float f;
        boolean flag;
        float f1;
        if(l2 != 0L)
            f = (float)(l - (mStartTime + l1)) / (float)l2;
        else
        if(l < mStartTime)
            f = 0.0F;
        else
            f = 1.0F;
        if(f < 1.0F)
            flag = isCanceled();
        else
            flag = true;
        mMore = flag ^ true;
        f1 = f;
        if(!mFillEnabled)
            f1 = Math.max(Math.min(f, 1.0F), 0.0F);
        if((f1 >= 0.0F || mFillBefore) && (f1 <= 1.0F || mFillAfter))
        {
            if(!mStarted)
            {
                fireAnimationStart();
                mStarted = true;
                if(NoImagePreloadHolder.USE_CLOSEGUARD)
                    guard.open("cancel or detach or getTransformation");
            }
            f = f1;
            if(mFillEnabled)
                f = Math.max(Math.min(f1, 1.0F), 0.0F);
            f1 = f;
            if(mCycleFlip)
                f1 = 1.0F - f;
            applyTransformation(mInterpolator.getInterpolation(f1), transformation);
        }
        if(flag)
            if(mRepeatCount == mRepeated || isCanceled())
            {
                if(!mEnded)
                {
                    mEnded = true;
                    guard.close();
                    fireAnimationEnd();
                }
            } else
            {
                if(mRepeatCount > 0)
                    mRepeated = mRepeated + 1;
                if(mRepeatMode == 2)
                    mCycleFlip = mCycleFlip ^ true;
                mStartTime = -1L;
                mMore = true;
                fireAnimationRepeat();
            }
        if(!mMore && mOneMoreTime)
        {
            mOneMoreTime = false;
            return true;
        } else
        {
            return mMore;
        }
    }

    public boolean getTransformation(long l, Transformation transformation, float f)
    {
        mScaleFactor = f;
        return getTransformation(l, transformation);
    }

    public int getZAdjustment()
    {
        return mZAdjustment;
    }

    public boolean hasAlpha()
    {
        return false;
    }

    public boolean hasEnded()
    {
        return mEnded;
    }

    public boolean hasStarted()
    {
        return mStarted;
    }

    public void initialize(int i, int j, int k, int l)
    {
        reset();
        mInitialized = true;
    }

    public void initializeInvalidateRegion(int i, int j, int k, int l)
    {
        RectF rectf = mPreviousRegion;
        rectf.set(i, j, k, l);
        rectf.inset(-1F, -1F);
        if(mFillBefore)
        {
            Transformation transformation = mPreviousTransformation;
            applyTransformation(mInterpolator.getInterpolation(0.0F), transformation);
        }
    }

    public boolean isFillEnabled()
    {
        return mFillEnabled;
    }

    public boolean isInitialized()
    {
        return mInitialized;
    }

    public void reset()
    {
        mPreviousRegion.setEmpty();
        mPreviousTransformation.clear();
        mInitialized = false;
        mCycleFlip = false;
        mRepeated = 0;
        mMore = true;
        mOneMoreTime = true;
        mListenerHandler = null;
    }

    protected float resolveSize(int i, float f, int j, int k)
    {
        switch(i)
        {
        default:
            return f;

        case 0: // '\0'
            return f;

        case 1: // '\001'
            return (float)j * f;

        case 2: // '\002'
            return (float)k * f;
        }
    }

    public void restrictDuration(long l)
    {
        long l2;
        if(mStartOffset > l)
        {
            mStartOffset = l;
            mDuration = 0L;
            mRepeatCount = 0;
            return;
        }
        long l1 = mDuration + mStartOffset;
        l2 = l1;
        if(l1 > l)
        {
            mDuration = l - mStartOffset;
            l2 = l;
        }
        if(mDuration <= 0L)
        {
            mDuration = 0L;
            mRepeatCount = 0;
            return;
        }
        break MISSING_BLOCK_LABEL_77;
        if(mRepeatCount < 0 || (long)mRepeatCount > l || (long)mRepeatCount * l2 > l)
        {
            mRepeatCount = (int)(l / l2) - 1;
            if(mRepeatCount < 0)
                mRepeatCount = 0;
        }
        return;
    }

    public void scaleCurrentDuration(float f)
    {
        mDuration = (long)((float)mDuration * f);
        mStartOffset = (long)((float)mStartOffset * f);
    }

    public void setAnimationListener(AnimationListener animationlistener)
    {
        mListener = animationlistener;
    }

    public void setBackgroundColor(int i)
    {
        mBackgroundColor = i;
    }

    public void setDetachWallpaper(boolean flag)
    {
        mDetachWallpaper = flag;
    }

    public void setDuration(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException("Animation duration cannot be negative");
        } else
        {
            mDuration = l;
            return;
        }
    }

    public void setFillAfter(boolean flag)
    {
        mFillAfter = flag;
    }

    public void setFillBefore(boolean flag)
    {
        mFillBefore = flag;
    }

    public void setFillEnabled(boolean flag)
    {
        mFillEnabled = flag;
    }

    public void setInterpolator(Context context, int i)
    {
        setInterpolator(AnimationUtils.loadInterpolator(context, i));
    }

    public void setInterpolator(Interpolator interpolator)
    {
        mInterpolator = interpolator;
    }

    public void setListenerHandler(Handler handler)
    {
        if(mListenerHandler == null)
        {
            mOnStart = new Runnable() {

                public void run()
                {
                    if(mListener != null)
                        mListener.onAnimationStart(Animation.this);
                }

                final Animation this$0;

            
            {
                this$0 = Animation.this;
                super();
            }
            }
;
            mOnRepeat = new Runnable() {

                public void run()
                {
                    if(mListener != null)
                        mListener.onAnimationRepeat(Animation.this);
                }

                final Animation this$0;

            
            {
                this$0 = Animation.this;
                super();
            }
            }
;
            mOnEnd = new Runnable() {

                public void run()
                {
                    if(mListener != null)
                        mListener.onAnimationEnd(Animation.this);
                }

                final Animation this$0;

            
            {
                this$0 = Animation.this;
                super();
            }
            }
;
        }
        mListenerHandler = handler;
    }

    public void setRepeatCount(int i)
    {
        int j = i;
        if(i < 0)
            j = -1;
        mRepeatCount = j;
    }

    public void setRepeatMode(int i)
    {
        mRepeatMode = i;
    }

    public void setStartOffset(long l)
    {
        mStartOffset = l;
    }

    public void setStartTime(long l)
    {
        mStartTime = l;
        mEnded = false;
        mStarted = false;
        mCycleFlip = false;
        mRepeated = 0;
        mMore = true;
    }

    public void setZAdjustment(int i)
    {
        mZAdjustment = i;
    }

    public void start()
    {
        setStartTime(-1L);
    }

    public void startNow()
    {
        setStartTime(AnimationUtils.currentAnimationTimeMillis());
    }

    public boolean willChangeBounds()
    {
        return true;
    }

    public boolean willChangeTransformationMatrix()
    {
        return true;
    }

    public static final int ABSOLUTE = 0;
    public static final int INFINITE = -1;
    public static final int RELATIVE_TO_PARENT = 2;
    public static final int RELATIVE_TO_SELF = 1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    public static final int START_ON_FIRST_FRAME = -1;
    public static final int ZORDER_BOTTOM = -1;
    public static final int ZORDER_NORMAL = 0;
    public static final int ZORDER_TOP = 1;
    private final CloseGuard guard;
    private int mBackgroundColor;
    boolean mCycleFlip;
    private boolean mDetachWallpaper;
    long mDuration;
    boolean mEnded;
    boolean mFillAfter;
    boolean mFillBefore;
    boolean mFillEnabled;
    boolean mInitialized;
    Interpolator mInterpolator;
    AnimationListener mListener;
    private Handler mListenerHandler;
    private boolean mMore;
    private Runnable mOnEnd;
    private Runnable mOnRepeat;
    private Runnable mOnStart;
    private boolean mOneMoreTime;
    RectF mPreviousRegion;
    Transformation mPreviousTransformation;
    RectF mRegion;
    int mRepeatCount;
    int mRepeatMode;
    int mRepeated;
    private float mScaleFactor;
    long mStartOffset;
    long mStartTime;
    boolean mStarted;
    Transformation mTransformation;
    private int mZAdjustment;
}
