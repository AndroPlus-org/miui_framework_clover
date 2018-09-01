// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.drawable;

import android.animation.*;
import android.app.ActivityThread;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import com.android.internal.util.VirtualRefBasePtr;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.graphics.drawable:
//            Drawable, Animatable2, VectorDrawable

public class AnimatedVectorDrawable extends Drawable
    implements Animatable2
{
    private static class AnimatedVectorDrawableState extends Drawable.ConstantState
    {

        static boolean _2D_get0(AnimatedVectorDrawableState animatedvectordrawablestate)
        {
            return animatedvectordrawablestate.mShouldIgnoreInvalidAnim;
        }

        private Animator prepareLocalAnimator(int i)
        {
            Object obj = (Animator)mAnimators.get(i);
            Animator animator = ((Animator) (obj)).clone();
            String s = (String)mTargetNameMap.get(obj);
            obj = mVectorDrawable.getTargetByName(s);
            if(!mShouldIgnoreInvalidAnim)
            {
                if(obj == null)
                    throw new IllegalStateException((new StringBuilder()).append("Target with the name \"").append(s).append("\" cannot be found in the VectorDrawable to be animated.").toString());
                if(!(obj instanceof VectorDrawable.VectorDrawableState) && (obj instanceof VectorDrawable.VObject) ^ true)
                    throw new UnsupportedOperationException((new StringBuilder()).append("Target should be either VGroup, VPath, or ConstantState, ").append(obj.getClass()).append(" is not supported").toString());
            }
            animator.setTarget(obj);
            return animator;
        }

        public void addPendingAnimator(int i, float f, String s)
        {
            if(mPendingAnims == null)
                mPendingAnims = new ArrayList(1);
            mPendingAnims.add(new PendingAnimator(i, f, s));
        }

        public void addTargetAnimator(String s, Animator animator)
        {
            if(mAnimators == null)
            {
                mAnimators = new ArrayList(1);
                mTargetNameMap = new ArrayMap(1);
            }
            mAnimators.add(animator);
            mTargetNameMap.put(animator, s);
        }

        public boolean canApplyTheme()
        {
            boolean flag;
            if(mVectorDrawable != null && mVectorDrawable.canApplyTheme() || mPendingAnims != null)
                flag = true;
            else
                flag = super.canApplyTheme();
            return flag;
        }

        public int getChangingConfigurations()
        {
            return mChangingConfigurations;
        }

        public void inflatePendingAnimators(Resources resources, android.content.res.Resources.Theme theme)
        {
            ArrayList arraylist = mPendingAnims;
            if(arraylist != null)
            {
                mPendingAnims = null;
                int i = 0;
                for(int j = arraylist.size(); i < j; i++)
                {
                    PendingAnimator pendinganimator = (PendingAnimator)arraylist.get(i);
                    Animator animator = pendinganimator.newInstance(resources, theme);
                    AnimatedVectorDrawable._2D_wrap15(animator, pendinganimator.target, mVectorDrawable, mShouldIgnoreInvalidAnim);
                    addTargetAnimator(pendinganimator.target, animator);
                }

            }
        }

        public Drawable newDrawable()
        {
            return new AnimatedVectorDrawable(this, null, null);
        }

        public Drawable newDrawable(Resources resources)
        {
            return new AnimatedVectorDrawable(this, resources, null);
        }

        public void prepareLocalAnimators(AnimatorSet animatorset, Resources resources)
        {
            int i;
            if(mPendingAnims != null)
            {
                int j;
                if(resources != null)
                    inflatePendingAnimators(resources, null);
                else
                    Log.e("AnimatedVectorDrawable", "Failed to load animators. Either the AnimatedVectorDrawable must be created using a Resources object or applyTheme() must be called with a non-null Theme object.");
                mPendingAnims = null;
            }
            if(mAnimators == null)
                i = 0;
            else
                i = mAnimators.size();
            if(i > 0)
            {
                animatorset = animatorset.play(prepareLocalAnimator(0));
                for(j = 1; j < i; j++)
                    animatorset.with(prepareLocalAnimator(j));

            }
        }

        ArrayList mAnimators;
        int mChangingConfigurations;
        ArrayList mPendingAnims;
        private final boolean mShouldIgnoreInvalidAnim = AnimatedVectorDrawable._2D_wrap0();
        ArrayMap mTargetNameMap;
        VectorDrawable mVectorDrawable;

        public AnimatedVectorDrawableState(AnimatedVectorDrawableState animatedvectordrawablestate, Drawable.Callback callback, Resources resources)
        {
            if(animatedvectordrawablestate != null)
            {
                mChangingConfigurations = animatedvectordrawablestate.mChangingConfigurations;
                if(animatedvectordrawablestate.mVectorDrawable != null)
                {
                    Drawable.ConstantState constantstate = animatedvectordrawablestate.mVectorDrawable.getConstantState();
                    if(resources != null)
                        mVectorDrawable = (VectorDrawable)constantstate.newDrawable(resources);
                    else
                        mVectorDrawable = (VectorDrawable)constantstate.newDrawable();
                    mVectorDrawable = (VectorDrawable)mVectorDrawable.mutate();
                    mVectorDrawable.setCallback(callback);
                    mVectorDrawable.setLayoutDirection(animatedvectordrawablestate.mVectorDrawable.getLayoutDirection());
                    mVectorDrawable.setBounds(animatedvectordrawablestate.mVectorDrawable.getBounds());
                    mVectorDrawable.setAllowCaching(false);
                }
                if(animatedvectordrawablestate.mAnimators != null)
                    mAnimators = new ArrayList(animatedvectordrawablestate.mAnimators);
                if(animatedvectordrawablestate.mTargetNameMap != null)
                    mTargetNameMap = new ArrayMap(animatedvectordrawablestate.mTargetNameMap);
                if(animatedvectordrawablestate.mPendingAnims != null)
                    mPendingAnims = new ArrayList(animatedvectordrawablestate.mPendingAnims);
            } else
            {
                mVectorDrawable = new VectorDrawable();
            }
        }
    }

    private static class AnimatedVectorDrawableState.PendingAnimator
    {

        public Animator newInstance(Resources resources, android.content.res.Resources.Theme theme)
        {
            return AnimatorInflater.loadAnimator(resources, theme, animResId, pathErrorScale);
        }

        public final int animResId;
        public final float pathErrorScale;
        public final String target;

        public AnimatedVectorDrawableState.PendingAnimator(int i, float f, String s)
        {
            animResId = i;
            pathErrorScale = f;
            target = s;
        }
    }

    private static interface VectorDrawableAnimator
    {

        public abstract boolean canReverse();

        public abstract void end();

        public abstract void init(AnimatorSet animatorset);

        public abstract boolean isInfinite();

        public abstract boolean isRunning();

        public abstract boolean isStarted();

        public abstract void onDraw(Canvas canvas);

        public abstract void pause();

        public abstract void removeListener(android.animation.Animator.AnimatorListener animatorlistener);

        public abstract void reset();

        public abstract void resume();

        public abstract void reverse();

        public abstract void setListener(android.animation.Animator.AnimatorListener animatorlistener);

        public abstract void start();
    }

    public static class VectorDrawableAnimatorRT
        implements VectorDrawableAnimator
    {

        static android.animation.Animator.AnimatorListener _2D_get0(VectorDrawableAnimatorRT vectordrawableanimatorrt)
        {
            return vectordrawableanimatorrt.mListener;
        }

        static IntArray _2D_get1(VectorDrawableAnimatorRT vectordrawableanimatorrt)
        {
            return vectordrawableanimatorrt.mPendingAnimationActions;
        }

        static void _2D_wrap0(VectorDrawableAnimatorRT vectordrawableanimatorrt, VectorDrawableAnimator vectordrawableanimator)
        {
            vectordrawableanimatorrt.transferPendingActions(vectordrawableanimator);
        }

        private void addPendingAction(int i)
        {
            invalidateOwningView();
            mPendingAnimationActions.add(i);
        }

        private static void callOnFinished(VectorDrawableAnimatorRT vectordrawableanimatorrt, int i)
        {
            vectordrawableanimatorrt.onAnimationEnd(i);
        }

        private static float[] createFloatDataPoints(android.animation.PropertyValuesHolder.PropertyValues.DataSource datasource, long l)
        {
            int i = getFrameCount(l);
            float af[] = new float[i];
            float f = i - 1;
            for(int j = 0; j < i; j++)
                af[j] = ((Float)datasource.getValueAtFraction((float)j / f)).floatValue();

            return af;
        }

        private static int[] createIntDataPoints(android.animation.PropertyValuesHolder.PropertyValues.DataSource datasource, long l)
        {
            int i = getFrameCount(l);
            int ai[] = new int[i];
            float f = i - 1;
            for(int j = 0; j < i; j++)
                ai[j] = ((Integer)datasource.getValueAtFraction((float)j / f)).intValue();

            return ai;
        }

        private void createNativeChildAnimator(long l, long l1, ObjectAnimator objectanimator)
        {
            long l2 = objectanimator.getDuration();
            int i = objectanimator.getRepeatCount();
            long l3 = objectanimator.getStartDelay();
            long l4 = RenderNodeAnimatorSetHelper.createNativeInterpolator(objectanimator.getInterpolator(), l2);
            l1 = (long)((float)(l1 + l3) * ValueAnimator.getDurationScale());
            l2 = (long)((float)l2 * ValueAnimator.getDurationScale());
            mStartDelays.add(l1);
            AnimatedVectorDrawable._2D_wrap7(mSetPtr, l, l4, l1, l2, i, objectanimator.getRepeatMode());
        }

        private void createRTAnimator(ObjectAnimator objectanimator, long l)
        {
            PropertyValuesHolder apropertyvaluesholder[];
            Object obj;
            apropertyvaluesholder = objectanimator.getValues();
            obj = objectanimator.getTarget();
            if(!(obj instanceof VectorDrawable.VGroup)) goto _L2; else goto _L1
_L1:
            createRTAnimatorForGroup(apropertyvaluesholder, objectanimator, (VectorDrawable.VGroup)obj, l);
_L7:
            return;
_L2:
            int i;
            if(!(obj instanceof VectorDrawable.VPath))
                break MISSING_BLOCK_LABEL_155;
            i = 0;
_L3:
            if(i >= apropertyvaluesholder.length)
                continue; /* Loop/switch isn't completed */
            apropertyvaluesholder[i].getPropertyValues(mTmpValues);
            if((mTmpValues.endValue instanceof android.util.PathParser.PathData) && mTmpValues.propertyName.equals("pathData"))
            {
                createRTAnimatorForPath(objectanimator, (VectorDrawable.VPath)obj, l);
            } else
            {
                if(!(obj instanceof VectorDrawable.VFullPath))
                    continue; /* Loop/switch isn't completed */
                createRTAnimatorForFullPath(objectanimator, (VectorDrawable.VFullPath)obj, l);
            }
_L5:
            i++;
              goto _L3
            if(AnimatedVectorDrawableState._2D_get0(AnimatedVectorDrawable._2D_get0(mDrawable))) goto _L5; else goto _L4
_L4:
            throw new IllegalArgumentException("ClipPath only supports PathData property");
            if(obj instanceof VectorDrawable.VectorDrawableState)
                createRTAnimatorForRootGroup(apropertyvaluesholder, objectanimator, (VectorDrawable.VectorDrawableState)obj, l);
            if(true) goto _L7; else goto _L6
_L6:
        }

        private void createRTAnimatorForFullPath(ObjectAnimator objectanimator, VectorDrawable.VFullPath vfullpath, long l)
        {
            int i;
            long l1;
            i = vfullpath.getPropertyIndex(mTmpValues.propertyName);
            l1 = vfullpath.getNativePtr();
            if(mTmpValues.type != java/lang/Float && mTmpValues.type != Float.TYPE) goto _L2; else goto _L1
_L1:
            if(i < 0)
                if(AnimatedVectorDrawableState._2D_get0(AnimatedVectorDrawable._2D_get0(mDrawable)))
                    return;
                else
                    throw new IllegalArgumentException((new StringBuilder()).append("Property: ").append(mTmpValues.propertyName).append(" is not supported for FullPath").toString());
            long l2 = AnimatedVectorDrawable._2D_wrap5(l1, i, ((Float)mTmpValues.startValue).floatValue(), ((Float)mTmpValues.endValue).floatValue());
            l1 = l2;
            if(mTmpValues.dataSource != null)
            {
                vfullpath = createFloatDataPoints(mTmpValues.dataSource, objectanimator.getDuration());
                AnimatedVectorDrawable._2D_wrap11(l2, vfullpath, vfullpath.length);
                l1 = l2;
            }
_L4:
            createNativeChildAnimator(l1, l, objectanimator);
            return;
_L2:
            if(mTmpValues.type != java/lang/Integer && mTmpValues.type != Integer.TYPE)
                break; /* Loop/switch isn't completed */
            long l3 = AnimatedVectorDrawable._2D_wrap3(l1, i, ((Integer)mTmpValues.startValue).intValue(), ((Integer)mTmpValues.endValue).intValue());
            l1 = l3;
            if(mTmpValues.dataSource != null)
            {
                vfullpath = createIntDataPoints(mTmpValues.dataSource, objectanimator.getDuration());
                AnimatedVectorDrawable._2D_wrap12(l3, vfullpath, vfullpath.length);
                l1 = l3;
            }
            if(true) goto _L4; else goto _L3
_L3:
            if(AnimatedVectorDrawableState._2D_get0(AnimatedVectorDrawable._2D_get0(mDrawable)))
                return;
            else
                throw new UnsupportedOperationException((new StringBuilder()).append("Unsupported type: ").append(mTmpValues.type).append(". Only float, int or PathData value is ").append("supported for Paths.").toString());
        }

        private void createRTAnimatorForGroup(PropertyValuesHolder apropertyvaluesholder[], ObjectAnimator objectanimator, VectorDrawable.VGroup vgroup, long l)
        {
            long l1 = vgroup.getNativePtr();
            int i = 0;
            do
            {
                if(i >= apropertyvaluesholder.length)
                    break;
                apropertyvaluesholder[i].getPropertyValues(mTmpValues);
                int j = VectorDrawable.VGroup.getPropertyIndex(mTmpValues.propertyName);
                if((mTmpValues.type == java/lang/Float || mTmpValues.type == Float.TYPE) && j >= 0)
                {
                    long l2 = AnimatedVectorDrawable._2D_wrap2(l1, j, ((Float)mTmpValues.startValue).floatValue(), ((Float)mTmpValues.endValue).floatValue());
                    if(mTmpValues.dataSource != null)
                    {
                        vgroup = createFloatDataPoints(mTmpValues.dataSource, objectanimator.getDuration());
                        AnimatedVectorDrawable._2D_wrap11(l2, vgroup, vgroup.length);
                    }
                    createNativeChildAnimator(l2, l, objectanimator);
                }
                i++;
            } while(true);
        }

        private void createRTAnimatorForPath(ObjectAnimator objectanimator, VectorDrawable.VPath vpath, long l)
        {
            createNativeChildAnimator(AnimatedVectorDrawable._2D_wrap4(vpath.getNativePtr(), ((android.util.PathParser.PathData)mTmpValues.startValue).getNativePtr(), ((android.util.PathParser.PathData)mTmpValues.endValue).getNativePtr()), l, objectanimator);
        }

        private void createRTAnimatorForRootGroup(PropertyValuesHolder apropertyvaluesholder[], ObjectAnimator objectanimator, VectorDrawable.VectorDrawableState vectordrawablestate, long l)
        {
            long l1 = vectordrawablestate.getNativeRenderer();
            if(!objectanimator.getPropertyName().equals("alpha"))
                if(AnimatedVectorDrawableState._2D_get0(AnimatedVectorDrawable._2D_get0(mDrawable)))
                    return;
                else
                    throw new UnsupportedOperationException("Only alpha is supported for root group");
            Object obj = null;
            Object obj1 = null;
            int i = 0;
            Float float1;
label0:
            do
            {
label1:
                {
                    float1 = obj1;
                    vectordrawablestate = obj;
                    if(i < apropertyvaluesholder.length)
                    {
                        apropertyvaluesholder[i].getPropertyValues(mTmpValues);
                        if(!mTmpValues.propertyName.equals("alpha"))
                            break label1;
                        vectordrawablestate = (Float)mTmpValues.startValue;
                        float1 = (Float)mTmpValues.endValue;
                    }
                    if(vectordrawablestate == null && float1 == null)
                        if(AnimatedVectorDrawableState._2D_get0(AnimatedVectorDrawable._2D_get0(mDrawable)))
                            return;
                        else
                            throw new UnsupportedOperationException("No alpha values are specified");
                    break label0;
                }
                i++;
            } while(true);
            l1 = AnimatedVectorDrawable._2D_wrap6(l1, vectordrawablestate.floatValue(), float1.floatValue());
            if(mTmpValues.dataSource != null)
            {
                apropertyvaluesholder = createFloatDataPoints(mTmpValues.dataSource, objectanimator.getDuration());
                AnimatedVectorDrawable._2D_wrap11(l1, apropertyvaluesholder, apropertyvaluesholder.length);
            }
            createNativeChildAnimator(l1, l, objectanimator);
        }

        private void endAnimation()
        {
            AnimatedVectorDrawable._2D_wrap8(mSetPtr);
            invalidateOwningView();
        }

        private static int getFrameCount(long l)
        {
            int i = (int)(Choreographer.getInstance().getFrameIntervalNanos() / 0xf4240L);
            int j = Math.max(2, (int)Math.ceil((double)l / (double)i));
            i = j;
            if(j > 300)
            {
                Log.w("AnimatedVectorDrawable", (new StringBuilder()).append("Duration for the animation is too long :").append(l).append(", the animation will subsample the keyframe or path data.").toString());
                i = 300;
            }
            return i;
        }

        private void handlePendingAction(int i)
        {
            if(i == 1)
                startAnimation();
            else
            if(i == 2)
                reverseAnimation();
            else
            if(i == 3)
                resetAnimation();
            else
            if(i == 4)
                endAnimation();
            else
                throw new UnsupportedOperationException((new StringBuilder()).append("Animation action ").append(i).append("is not supported").toString());
        }

        private void invalidateOwningView()
        {
            mDrawable.invalidateSelf();
        }

        private void onAnimationEnd(int i)
        {
            if(i != mLastListenerId)
                return;
            mStarted = false;
            invalidateOwningView();
            if(mListener != null)
                mListener.onAnimationEnd(null);
        }

        private void parseAnimatorSet(AnimatorSet animatorset, long l)
        {
            ArrayList arraylist = animatorset.getChildAnimations();
            boolean flag = animatorset.shouldPlayTogether();
            int i = 0;
            long l1 = l;
            while(i < arraylist.size()) 
            {
                animatorset = (Animator)arraylist.get(i);
                if(animatorset instanceof AnimatorSet)
                    parseAnimatorSet((AnimatorSet)animatorset, l1);
                else
                if(animatorset instanceof ObjectAnimator)
                    createRTAnimator((ObjectAnimator)animatorset, l1);
                l = l1;
                if(!flag)
                {
                    l = l1 + animatorset.getTotalDuration();
                    mContainsSequentialAnimators = true;
                }
                i++;
                l1 = l;
            }
        }

        private void resetAnimation()
        {
            AnimatedVectorDrawable._2D_wrap9(mSetPtr);
            invalidateOwningView();
        }

        private void reverseAnimation()
        {
            mStarted = true;
            long l = mSetPtr;
            int i = mLastListenerId + 1;
            mLastListenerId = i;
            AnimatedVectorDrawable._2D_wrap10(l, this, i);
            invalidateOwningView();
            if(mListener != null)
                mListener.onAnimationStart(null);
        }

        private void startAnimation()
        {
            mStarted = true;
            long l = mSetPtr;
            int i = mLastListenerId + 1;
            mLastListenerId = i;
            AnimatedVectorDrawable._2D_wrap14(l, this, i);
            invalidateOwningView();
            if(mListener != null)
                mListener.onAnimationStart(null);
        }

        private void transferPendingActions(VectorDrawableAnimator vectordrawableanimator)
        {
            int i = 0;
            while(i < mPendingAnimationActions.size()) 
            {
                int j = mPendingAnimationActions.get(i);
                if(j == 1)
                    vectordrawableanimator.start();
                else
                if(j == 4)
                    vectordrawableanimator.end();
                else
                if(j == 2)
                    vectordrawableanimator.reverse();
                else
                if(j == 3)
                    vectordrawableanimator.reset();
                else
                    throw new UnsupportedOperationException((new StringBuilder()).append("Animation action ").append(j).append("is not supported").toString());
                i++;
            }
            mPendingAnimationActions.clear();
        }

        private boolean useLastSeenTarget()
        {
            if(mLastSeenTarget != null)
                return useTarget((RenderNode)mLastSeenTarget.get());
            else
                return false;
        }

        private boolean useTarget(RenderNode rendernode)
        {
            if(rendernode != null && rendernode.isAttached())
            {
                rendernode.registerVectorDrawableAnimator(this);
                return true;
            } else
            {
                return false;
            }
        }

        public boolean canReverse()
        {
            return mIsReversible;
        }

        public void end()
        {
            if(!mInitialized)
                return;
            if(useLastSeenTarget())
                endAnimation();
            else
                addPendingAction(4);
        }

        public long getAnimatorNativePtr()
        {
            return mSetPtr;
        }

        public void init(AnimatorSet animatorset)
        {
            if(mInitialized)
                throw new UnsupportedOperationException("VectorDrawableAnimator cannot be re-initialized");
            parseAnimatorSet(animatorset, 0L);
            long l = AnimatedVectorDrawable._2D_get0(mDrawable).mVectorDrawable.getNativeTree();
            AnimatedVectorDrawable._2D_wrap13(mSetPtr, l);
            mInitialized = true;
            boolean flag;
            if(animatorset.getTotalDuration() == -1L)
                flag = true;
            else
                flag = false;
            mIsInfinite = flag;
            mIsReversible = true;
            if(mContainsSequentialAnimators)
            {
                mIsReversible = false;
            } else
            {
                int i = 0;
                while(i < mStartDelays.size()) 
                {
                    if(mStartDelays.get(i) > 0L)
                    {
                        mIsReversible = false;
                        return;
                    }
                    i++;
                }
            }
        }

        public boolean isInfinite()
        {
            return mIsInfinite;
        }

        public boolean isRunning()
        {
            if(!mInitialized)
                return false;
            else
                return mStarted;
        }

        public boolean isStarted()
        {
            return mStarted;
        }

        public void onDraw(Canvas canvas)
        {
            if(canvas.isHardwareAccelerated())
                recordLastSeenTarget((DisplayListCanvas)canvas);
        }

        public void pause()
        {
        }

        protected void recordLastSeenTarget(DisplayListCanvas displaylistcanvas)
        {
            displaylistcanvas = RenderNodeAnimatorSetHelper.getTarget(displaylistcanvas);
            mLastSeenTarget = new WeakReference(displaylistcanvas);
            if((mInitialized || mPendingAnimationActions.size() > 0) && useTarget(displaylistcanvas))
            {
                for(int i = 0; i < mPendingAnimationActions.size(); i++)
                    handlePendingAction(mPendingAnimationActions.get(i));

                mPendingAnimationActions.clear();
            }
        }

        public void removeListener(android.animation.Animator.AnimatorListener animatorlistener)
        {
            mListener = null;
        }

        public void reset()
        {
            if(!mInitialized)
                return;
            if(useLastSeenTarget())
                resetAnimation();
            else
                addPendingAction(3);
        }

        public void resume()
        {
        }

        public void reverse()
        {
            if(!mIsReversible || mInitialized ^ true)
                return;
            if(useLastSeenTarget())
                reverseAnimation();
            else
                addPendingAction(2);
        }

        public void setListener(android.animation.Animator.AnimatorListener animatorlistener)
        {
            mListener = animatorlistener;
        }

        public void start()
        {
            if(!mInitialized)
                return;
            if(useLastSeenTarget())
                startAnimation();
            else
                addPendingAction(1);
        }

        private static final int END_ANIMATION = 4;
        private static final int MAX_SAMPLE_POINTS = 300;
        private static final int RESET_ANIMATION = 3;
        private static final int REVERSE_ANIMATION = 2;
        private static final int START_ANIMATION = 1;
        private boolean mContainsSequentialAnimators;
        private final AnimatedVectorDrawable mDrawable;
        private boolean mInitialized;
        private boolean mIsInfinite;
        private boolean mIsReversible;
        private int mLastListenerId;
        private WeakReference mLastSeenTarget;
        private android.animation.Animator.AnimatorListener mListener;
        private final IntArray mPendingAnimationActions = new IntArray();
        private long mSetPtr;
        private final VirtualRefBasePtr mSetRefBasePtr;
        private final LongArray mStartDelays = new LongArray();
        private boolean mStarted;
        private android.animation.PropertyValuesHolder.PropertyValues mTmpValues;

        VectorDrawableAnimatorRT(AnimatedVectorDrawable animatedvectordrawable)
        {
            mListener = null;
            mTmpValues = new android.animation.PropertyValuesHolder.PropertyValues();
            mSetPtr = 0L;
            mContainsSequentialAnimators = false;
            mStarted = false;
            mInitialized = false;
            mIsReversible = false;
            mIsInfinite = false;
            mLastSeenTarget = null;
            mLastListenerId = 0;
            mDrawable = animatedvectordrawable;
            mSetPtr = AnimatedVectorDrawable._2D_wrap1();
            mSetRefBasePtr = new VirtualRefBasePtr(mSetPtr);
        }
    }

    private static class VectorDrawableAnimatorUI
        implements VectorDrawableAnimator
    {

        private void invalidateOwningView()
        {
            mDrawable.invalidateSelf();
        }

        public boolean canReverse()
        {
            boolean flag;
            if(mSet != null)
                flag = mSet.canReverse();
            else
                flag = false;
            return flag;
        }

        public void end()
        {
            if(mSet == null)
            {
                return;
            } else
            {
                mSet.end();
                return;
            }
        }

        public void init(AnimatorSet animatorset)
        {
            if(mSet != null)
                throw new UnsupportedOperationException("VectorDrawableAnimator cannot be re-initialized");
            mSet = animatorset.clone();
            boolean flag;
            if(mSet.getTotalDuration() == -1L)
                flag = true;
            else
                flag = false;
            mIsInfinite = flag;
            if(mListenerArray != null && mListenerArray.isEmpty() ^ true)
            {
                for(int i = 0; i < mListenerArray.size(); i++)
                    mSet.addListener((android.animation.Animator.AnimatorListener)mListenerArray.get(i));

                mListenerArray.clear();
                mListenerArray = null;
            }
        }

        public boolean isInfinite()
        {
            return mIsInfinite;
        }

        public boolean isRunning()
        {
            boolean flag;
            if(mSet != null)
                flag = mSet.isRunning();
            else
                flag = false;
            return flag;
        }

        public boolean isStarted()
        {
            boolean flag;
            if(mSet != null)
                flag = mSet.isStarted();
            else
                flag = false;
            return flag;
        }

        public void onDraw(Canvas canvas)
        {
            if(mSet != null && mSet.isStarted())
                invalidateOwningView();
        }

        public void pause()
        {
            if(mSet == null)
            {
                return;
            } else
            {
                mSet.pause();
                return;
            }
        }

        public void removeListener(android.animation.Animator.AnimatorListener animatorlistener)
        {
            if(mSet == null)
            {
                if(mListenerArray == null)
                    return;
                mListenerArray.remove(animatorlistener);
            } else
            {
                mSet.removeListener(animatorlistener);
            }
        }

        public void reset()
        {
            if(mSet == null)
            {
                return;
            } else
            {
                start();
                mSet.cancel();
                return;
            }
        }

        public void resume()
        {
            if(mSet == null)
            {
                return;
            } else
            {
                mSet.resume();
                return;
            }
        }

        public void reverse()
        {
            if(mSet == null)
            {
                return;
            } else
            {
                mSet.reverse();
                invalidateOwningView();
                return;
            }
        }

        public void setListener(android.animation.Animator.AnimatorListener animatorlistener)
        {
            if(mSet == null)
            {
                if(mListenerArray == null)
                    mListenerArray = new ArrayList();
                mListenerArray.add(animatorlistener);
            } else
            {
                mSet.addListener(animatorlistener);
            }
        }

        public void start()
        {
            if(mSet == null || mSet.isStarted())
            {
                return;
            } else
            {
                mSet.start();
                invalidateOwningView();
                return;
            }
        }

        private final Drawable mDrawable;
        private boolean mIsInfinite;
        private ArrayList mListenerArray;
        private AnimatorSet mSet;

        VectorDrawableAnimatorUI(AnimatedVectorDrawable animatedvectordrawable)
        {
            mSet = null;
            mListenerArray = null;
            mIsInfinite = false;
            mDrawable = animatedvectordrawable;
        }
    }


    static AnimatedVectorDrawableState _2D_get0(AnimatedVectorDrawable animatedvectordrawable)
    {
        return animatedvectordrawable.mAnimatedVectorState;
    }

    static ArrayList _2D_get1(AnimatedVectorDrawable animatedvectordrawable)
    {
        return animatedvectordrawable.mAnimationCallbacks;
    }

    static boolean _2D_wrap0()
    {
        return shouldIgnoreInvalidAnimation();
    }

    static long _2D_wrap1()
    {
        return nCreateAnimatorSet();
    }

    static void _2D_wrap10(long l, VectorDrawableAnimatorRT vectordrawableanimatorrt, int i)
    {
        nReverse(l, vectordrawableanimatorrt, i);
    }

    static void _2D_wrap11(long l, float af[], int i)
    {
        nSetPropertyHolderData(l, af, i);
    }

    static void _2D_wrap12(long l, int ai[], int i)
    {
        nSetPropertyHolderData(l, ai, i);
    }

    static void _2D_wrap13(long l, long l1)
    {
        nSetVectorDrawableTarget(l, l1);
    }

    static void _2D_wrap14(long l, VectorDrawableAnimatorRT vectordrawableanimatorrt, int i)
    {
        nStart(l, vectordrawableanimatorrt, i);
    }

    static void _2D_wrap15(Animator animator, String s, VectorDrawable vectordrawable, boolean flag)
    {
        updateAnimatorProperty(animator, s, vectordrawable, flag);
    }

    static long _2D_wrap2(long l, int i, float f, float f1)
    {
        return nCreateGroupPropertyHolder(l, i, f, f1);
    }

    static long _2D_wrap3(long l, int i, int j, int k)
    {
        return nCreatePathColorPropertyHolder(l, i, j, k);
    }

    static long _2D_wrap4(long l, long l1, long l2)
    {
        return nCreatePathDataPropertyHolder(l, l1, l2);
    }

    static long _2D_wrap5(long l, int i, float f, float f1)
    {
        return nCreatePathPropertyHolder(l, i, f, f1);
    }

    static long _2D_wrap6(long l, float f, float f1)
    {
        return nCreateRootAlphaPropertyHolder(l, f, f1);
    }

    static void _2D_wrap7(long l, long l1, long l2, long l3, 
            long l4, int i, int j)
    {
        nAddAnimator(l, l1, l2, l3, l4, i, j);
    }

    static void _2D_wrap8(long l)
    {
        nEnd(l);
    }

    static void _2D_wrap9(long l)
    {
        nReset(l);
    }

    public AnimatedVectorDrawable()
    {
        this(null, null);
    }

    private AnimatedVectorDrawable(AnimatedVectorDrawableState animatedvectordrawablestate, Resources resources)
    {
        mAnimatorSetFromXml = null;
        mAnimationCallbacks = null;
        mAnimatorListener = null;
        mCallback = new Drawable.Callback() {

            public void invalidateDrawable(Drawable drawable)
            {
                invalidateSelf();
            }

            public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
            {
                scheduleSelf(runnable, l);
            }

            public void unscheduleDrawable(Drawable drawable, Runnable runnable)
            {
                unscheduleSelf(runnable);
            }

            final AnimatedVectorDrawable this$0;

            
            {
                this$0 = AnimatedVectorDrawable.this;
                super();
            }
        }
;
        mAnimatedVectorState = new AnimatedVectorDrawableState(animatedvectordrawablestate, mCallback, resources);
        mAnimatorSet = new VectorDrawableAnimatorRT(this);
        mRes = resources;
    }

    AnimatedVectorDrawable(AnimatedVectorDrawableState animatedvectordrawablestate, Resources resources, AnimatedVectorDrawable animatedvectordrawable)
    {
        this(animatedvectordrawablestate, resources);
    }

    private static boolean containsSameValueType(PropertyValuesHolder propertyvaluesholder, Property property)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        propertyvaluesholder = propertyvaluesholder.getValueType();
        property = property.getType();
        if(propertyvaluesholder == Float.TYPE || propertyvaluesholder == java/lang/Float)
        {
            flag1 = flag2;
            if(property != Float.TYPE)
                if(property == java/lang/Float)
                    flag1 = flag2;
                else
                    flag1 = false;
            return flag1;
        }
        if(propertyvaluesholder == Integer.TYPE || propertyvaluesholder == java/lang/Integer)
        {
            flag1 = flag;
            if(property != Integer.TYPE)
                if(property == java/lang/Integer)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }
        if(propertyvaluesholder != property)
            flag1 = false;
        return flag1;
    }

    private void ensureAnimatorSet()
    {
        if(mAnimatorSetFromXml == null)
        {
            mAnimatorSetFromXml = new AnimatorSet();
            mAnimatedVectorState.prepareLocalAnimators(mAnimatorSetFromXml, mRes);
            mAnimatorSet.init(mAnimatorSetFromXml);
            mRes = null;
        }
    }

    private void fallbackOntoUI()
    {
        if(mAnimatorSet instanceof VectorDrawableAnimatorRT)
        {
            VectorDrawableAnimatorRT vectordrawableanimatorrt = (VectorDrawableAnimatorRT)mAnimatorSet;
            mAnimatorSet = new VectorDrawableAnimatorUI(this);
            if(mAnimatorSetFromXml != null)
                mAnimatorSet.init(mAnimatorSetFromXml);
            if(VectorDrawableAnimatorRT._2D_get0(vectordrawableanimatorrt) != null)
                mAnimatorSet.setListener(VectorDrawableAnimatorRT._2D_get0(vectordrawableanimatorrt));
            VectorDrawableAnimatorRT._2D_wrap0(vectordrawableanimatorrt, mAnimatorSet);
        }
    }

    private static native void nAddAnimator(long l, long l1, long l2, long l3, 
            long l4, int i, int j);

    private static native long nCreateAnimatorSet();

    private static native long nCreateGroupPropertyHolder(long l, int i, float f, float f1);

    private static native long nCreatePathColorPropertyHolder(long l, int i, int j, int k);

    private static native long nCreatePathDataPropertyHolder(long l, long l1, long l2);

    private static native long nCreatePathPropertyHolder(long l, int i, float f, float f1);

    private static native long nCreateRootAlphaPropertyHolder(long l, float f, float f1);

    private static native void nEnd(long l);

    private static native void nReset(long l);

    private static native void nReverse(long l, VectorDrawableAnimatorRT vectordrawableanimatorrt, int i);

    private static native void nSetPropertyHolderData(long l, float af[], int i);

    private static native void nSetPropertyHolderData(long l, int ai[], int i);

    private static native void nSetVectorDrawableTarget(long l, long l1);

    private static native void nStart(long l, VectorDrawableAnimatorRT vectordrawableanimatorrt, int i);

    private void removeAnimatorSetListener()
    {
        if(mAnimatorListener != null)
        {
            mAnimatorSet.removeListener(mAnimatorListener);
            mAnimatorListener = null;
        }
    }

    private static boolean shouldIgnoreInvalidAnimation()
    {
        Application application = ActivityThread.currentApplication();
        if(application == null || application.getApplicationInfo() == null)
            return true;
        return application.getApplicationInfo().targetSdkVersion < 24;
    }

    private static void updateAnimatorProperty(Animator animator, String s, VectorDrawable vectordrawable, boolean flag)
    {
        if(!(animator instanceof ObjectAnimator)) goto _L2; else goto _L1
_L1:
        PropertyValuesHolder apropertyvaluesholder[];
        int i;
        apropertyvaluesholder = ((ObjectAnimator)animator).getValues();
        i = 0;
_L11:
        if(i >= apropertyvaluesholder.length) goto _L4; else goto _L3
_L3:
        PropertyValuesHolder propertyvaluesholder;
        String s1;
        propertyvaluesholder = apropertyvaluesholder[i];
        s1 = propertyvaluesholder.getPropertyName();
        Object obj = vectordrawable.getTargetByName(s);
        animator = null;
        if(obj instanceof VectorDrawable.VObject)
            animator = ((VectorDrawable.VObject)obj).getProperty(s1);
        else
        if(obj instanceof VectorDrawable.VectorDrawableState)
            animator = ((VectorDrawable.VectorDrawableState)obj).getProperty(s1);
        if(animator == null) goto _L6; else goto _L5
_L5:
        if(!containsSameValueType(propertyvaluesholder, animator)) goto _L8; else goto _L7
_L7:
        propertyvaluesholder.setProperty(animator);
_L6:
        i++;
        continue; /* Loop/switch isn't completed */
_L8:
        if(flag) goto _L6; else goto _L9
_L9:
        throw new RuntimeException((new StringBuilder()).append("Wrong valueType for Property: ").append(s1).append(".  Expected type: ").append(animator.getType().toString()).append(". Actual ").append("type defined in resources: ").append(propertyvaluesholder.getValueType().toString()).toString());
_L2:
        if(animator instanceof AnimatorSet)
            for(animator = ((AnimatorSet)animator).getChildAnimations().iterator(); animator.hasNext(); updateAnimatorProperty((Animator)animator.next(), s, vectordrawable, flag));
_L4:
        return;
        if(true) goto _L11; else goto _L10
_L10:
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        super.applyTheme(theme);
        VectorDrawable vectordrawable = mAnimatedVectorState.mVectorDrawable;
        if(vectordrawable != null && vectordrawable.canApplyTheme())
            vectordrawable.applyTheme(theme);
        if(theme != null)
            mAnimatedVectorState.inflatePendingAnimators(theme.getResources(), theme);
        if(mAnimatedVectorState.mPendingAnims == null)
            mRes = null;
    }

    public boolean canApplyTheme()
    {
        boolean flag;
        if(mAnimatedVectorState == null || !mAnimatedVectorState.canApplyTheme())
            flag = super.canApplyTheme();
        else
            flag = true;
        return flag;
    }

    public boolean canReverse()
    {
        return mAnimatorSet.canReverse();
    }

    public void clearAnimationCallbacks()
    {
        removeAnimatorSetListener();
        if(mAnimationCallbacks == null)
        {
            return;
        } else
        {
            mAnimationCallbacks.clear();
            return;
        }
    }

    public void clearMutated()
    {
        super.clearMutated();
        if(mAnimatedVectorState.mVectorDrawable != null)
            mAnimatedVectorState.mVectorDrawable.clearMutated();
        mMutated = false;
    }

    public void draw(Canvas canvas)
    {
        if(!canvas.isHardwareAccelerated() && (mAnimatorSet instanceof VectorDrawableAnimatorRT) && !mAnimatorSet.isRunning() && VectorDrawableAnimatorRT._2D_get1((VectorDrawableAnimatorRT)mAnimatorSet).size() > 0)
            fallbackOntoUI();
        mAnimatorSet.onDraw(canvas);
        mAnimatedVectorState.mVectorDrawable.draw(canvas);
    }

    public void forceAnimationOnUI()
    {
        if(mAnimatorSet instanceof VectorDrawableAnimatorRT)
        {
            if(((VectorDrawableAnimatorRT)mAnimatorSet).isRunning())
                throw new UnsupportedOperationException("Cannot force Animated Vector Drawable to run on UI thread when the animation has started on RenderThread.");
            fallbackOntoUI();
        }
    }

    public int getAlpha()
    {
        return mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    public int getChangingConfigurations()
    {
        return super.getChangingConfigurations() | mAnimatedVectorState.getChangingConfigurations();
    }

    public ColorFilter getColorFilter()
    {
        return mAnimatedVectorState.mVectorDrawable.getColorFilter();
    }

    public Drawable.ConstantState getConstantState()
    {
        mAnimatedVectorState.mChangingConfigurations = getChangingConfigurations();
        return mAnimatedVectorState;
    }

    public int getIntrinsicHeight()
    {
        return mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth()
    {
        return mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    public int getOpacity()
    {
        return -3;
    }

    public Insets getOpticalInsets()
    {
        return mAnimatedVectorState.mVectorDrawable.getOpticalInsets();
    }

    public void getOutline(Outline outline)
    {
        mAnimatedVectorState.mVectorDrawable.getOutline(outline);
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        AnimatedVectorDrawableState animatedvectordrawablestate = mAnimatedVectorState;
        int i = xmlpullparser.getEventType();
        float f = 1.0F;
        int k = xmlpullparser.getDepth();
        while(i != 1 && (xmlpullparser.getDepth() >= k + 1 || i != 3)) 
        {
            float f1 = f;
            if(i == 2)
            {
                Object obj = xmlpullparser.getName();
                if("animated-vector".equals(obj))
                {
                    obj = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimatedVectorDrawable);
                    i = ((TypedArray) (obj)).getResourceId(0, 0);
                    if(i != 0)
                    {
                        VectorDrawable vectordrawable = (VectorDrawable)resources.getDrawable(i, theme).mutate();
                        vectordrawable.setAllowCaching(false);
                        vectordrawable.setCallback(mCallback);
                        f = vectordrawable.getPixelSize();
                        if(animatedvectordrawablestate.mVectorDrawable != null)
                            animatedvectordrawablestate.mVectorDrawable.setCallback(null);
                        animatedvectordrawablestate.mVectorDrawable = vectordrawable;
                    }
                    ((TypedArray) (obj)).recycle();
                    f1 = f;
                } else
                {
                    f1 = f;
                    if("target".equals(obj))
                    {
                        TypedArray typedarray = obtainAttributes(resources, theme, attributeset, com.android.internal.R.styleable.AnimatedVectorDrawableTarget);
                        String s = typedarray.getString(0);
                        int j = typedarray.getResourceId(1, 0);
                        if(j != 0)
                            if(theme != null)
                            {
                                Animator animator = AnimatorInflater.loadAnimator(resources, theme, j, f);
                                updateAnimatorProperty(animator, s, animatedvectordrawablestate.mVectorDrawable, AnimatedVectorDrawableState._2D_get0(animatedvectordrawablestate));
                                animatedvectordrawablestate.addTargetAnimator(s, animator);
                            } else
                            {
                                animatedvectordrawablestate.addPendingAnimator(j, f, s);
                            }
                        typedarray.recycle();
                        f1 = f;
                    }
                }
            }
            i = xmlpullparser.next();
            f = f1;
        }
        if(animatedvectordrawablestate.mPendingAnims == null)
            resources = null;
        mRes = resources;
    }

    public boolean isRunning()
    {
        return mAnimatorSet.isRunning();
    }

    public boolean isStateful()
    {
        return mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    public Drawable mutate()
    {
        if(!mMutated && super.mutate() == this)
        {
            mAnimatedVectorState = new AnimatedVectorDrawableState(mAnimatedVectorState, mCallback, mRes);
            mMutated = true;
        }
        return this;
    }

    protected void onBoundsChange(Rect rect)
    {
        mAnimatedVectorState.mVectorDrawable.setBounds(rect);
    }

    public boolean onLayoutDirectionChanged(int i)
    {
        return mAnimatedVectorState.mVectorDrawable.setLayoutDirection(i);
    }

    protected boolean onLevelChange(int i)
    {
        return mAnimatedVectorState.mVectorDrawable.setLevel(i);
    }

    protected boolean onStateChange(int ai[])
    {
        return mAnimatedVectorState.mVectorDrawable.setState(ai);
    }

    public void registerAnimationCallback(Animatable2.AnimationCallback animationcallback)
    {
        if(animationcallback == null)
            return;
        if(mAnimationCallbacks == null)
            mAnimationCallbacks = new ArrayList();
        mAnimationCallbacks.add(animationcallback);
        if(mAnimatorListener == null)
            mAnimatorListener = new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator)
                {
                    animator = new ArrayList(AnimatedVectorDrawable._2D_get1(AnimatedVectorDrawable.this));
                    int i = animator.size();
                    for(int j = 0; j < i; j++)
                        ((Animatable2.AnimationCallback)animator.get(j)).onAnimationEnd(AnimatedVectorDrawable.this);

                }

                public void onAnimationStart(Animator animator)
                {
                    animator = new ArrayList(AnimatedVectorDrawable._2D_get1(AnimatedVectorDrawable.this));
                    int i = animator.size();
                    for(int j = 0; j < i; j++)
                        ((Animatable2.AnimationCallback)animator.get(j)).onAnimationStart(AnimatedVectorDrawable.this);

                }

                final AnimatedVectorDrawable this$0;

            
            {
                this$0 = AnimatedVectorDrawable.this;
                super();
            }
            }
;
        mAnimatorSet.setListener(mAnimatorListener);
    }

    public void reset()
    {
        ensureAnimatorSet();
        mAnimatorSet.reset();
    }

    public void reverse()
    {
        ensureAnimatorSet();
        if(!canReverse())
        {
            Log.w("AnimatedVectorDrawable", "AnimatedVectorDrawable can't reverse()");
            return;
        } else
        {
            mAnimatorSet.reverse();
            return;
        }
    }

    public void setAlpha(int i)
    {
        mAnimatedVectorState.mVectorDrawable.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        mAnimatedVectorState.mVectorDrawable.setColorFilter(colorfilter);
    }

    public void setHotspot(float f, float f1)
    {
        mAnimatedVectorState.mVectorDrawable.setHotspot(f, f1);
    }

    public void setHotspotBounds(int i, int j, int k, int l)
    {
        mAnimatedVectorState.mVectorDrawable.setHotspotBounds(i, j, k, l);
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        mAnimatedVectorState.mVectorDrawable.setTintList(colorstatelist);
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mAnimatedVectorState.mVectorDrawable.setTintMode(mode);
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        if(mAnimatorSet.isInfinite() && mAnimatorSet.isStarted())
            if(flag)
                mAnimatorSet.resume();
            else
                mAnimatorSet.pause();
        mAnimatedVectorState.mVectorDrawable.setVisible(flag, flag1);
        return super.setVisible(flag, flag1);
    }

    public void start()
    {
        ensureAnimatorSet();
        mAnimatorSet.start();
    }

    public void stop()
    {
        mAnimatorSet.end();
    }

    public boolean unregisterAnimationCallback(Animatable2.AnimationCallback animationcallback)
    {
        if(mAnimationCallbacks == null || animationcallback == null)
            return false;
        boolean flag = mAnimationCallbacks.remove(animationcallback);
        if(mAnimationCallbacks.size() == 0)
            removeAnimatorSetListener();
        return flag;
    }

    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVectorDrawable";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableState mAnimatedVectorState;
    private ArrayList mAnimationCallbacks;
    private android.animation.Animator.AnimatorListener mAnimatorListener;
    private VectorDrawableAnimator mAnimatorSet;
    private AnimatorSet mAnimatorSetFromXml;
    private final Drawable.Callback mCallback;
    private boolean mMutated;
    private Resources mRes;
}
