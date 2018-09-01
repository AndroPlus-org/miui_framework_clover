// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.animation;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import miui.maml.CommandTriggers;
import miui.maml.animation.interpolater.InterpolatorHelper;
import miui.maml.data.*;
import miui.maml.elements.ScreenElement;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

public abstract class BaseAnimation
{
    public static class AnimationItem
    {

        private void load(Element element)
        {
            int i = 0;
            Variables variables = mAni.getVariables();
            mInterpolator = InterpolatorHelper.create(variables, element);
            String as[];
            try
            {
                mTime = Long.parseLong(element.getAttribute("time"));
            }
            catch(NumberFormatException numberformatexception) { }
            mDeltaTimeExp = Expression.build(variables, element.getAttribute("dtime"));
            as = mAni.getAttrs();
            if(as != null)
            {
                mAttrsValue = new double[as.length];
                mExps = new Expression[as.length];
                int j = as.length;
                for(int k = 0; i < j; k++)
                {
                    String s = as[i];
                    Expression expression = Expression.build(variables, element.getAttribute(s));
                    Expression expression1 = expression;
                    if(expression == null)
                    {
                        expression1 = expression;
                        if(k == 0)
                        {
                            expression1 = expression;
                            if("value".equals(s) ^ true)
                                expression1 = Expression.build(variables, element.getAttribute("value"));
                        }
                    }
                    mExps[k] = expression1;
                    i++;
                }

            }
            mInitTime = mTime;
        }

        private void reevaluate()
        {
            if(mExps == null)
                return;
            Expression aexpression[] = mExps;
            int i = 0;
            int j = aexpression.length;
            int k = 0;
            while(i < j) 
            {
                Expression expression = aexpression[i];
                double ad[] = mAttrsValue;
                double d;
                if(expression == null)
                    d = 0.0D;
                else
                    d = expression.evaluate();
                ad[k] = d;
                i++;
                k++;
            }
        }

        public boolean attrExists(int i)
        {
            boolean flag;
            for(flag = false; mExps == null || i < 0 || i >= mExps.length;)
                return false;

            if(mExps[i] != null)
                flag = true;
            return flag;
        }

        public double get(int i)
        {
            while(mAttrsValue == null || i < 0 || i >= mAttrsValue.length) 
            {
                Log.e("BaseAnimation", (new StringBuilder()).append("fail to get number in AnimationItem:").append(i).toString());
                return 0.0D;
            }
            if(mNeedEvaluate)
            {
                reevaluate();
                mNeedEvaluate = false;
            }
            return mAttrsValue[i];
        }

        public void reset()
        {
            mNeedEvaluate = true;
            mTime = mInitTime;
        }

        private BaseAnimation mAni;
        private double mAttrsValue[];
        public Expression mDeltaTimeExp;
        public Expression mExps[];
        public long mInitTime;
        public InterpolatorHelper mInterpolator;
        private boolean mNeedEvaluate;
        public long mTime;

        public AnimationItem(BaseAnimation baseanimation, Element element)
        {
            mNeedEvaluate = true;
            mAni = baseanimation;
            load(element);
        }
    }


    public BaseAnimation(Element element, String s, String s1, ScreenElement screenelement)
    {
        this(element, s, new String[] {
            s1
        }, screenelement);
    }

    public BaseAnimation(Element element, String s, ScreenElement screenelement)
    {
        this(element, s, "value", screenelement);
    }

    public BaseAnimation(Element element, String s, String as[], ScreenElement screenelement)
    {
        mItems = new ArrayList();
        mLoop = true;
        mScreenElement = screenelement;
        mAttrs = as;
        mCurValues = new double[mAttrs.length];
        load(element, s);
    }

    public BaseAnimation(Element element, ScreenElement screenelement)
    {
        this(element, null, "value", screenelement);
    }

    private float getRatio(AnimationItem animationitem, long l, long l1, long l2)
    {
        float f;
        float f1;
        if(l2 == 0L)
            f = 1.0F;
        else
            f = (float)(l - l1) / (float)l2;
        f1 = f;
        if(animationitem != null)
        {
            f1 = f;
            if(animationitem.mInterpolator != null)
                f1 = animationitem.mInterpolator.get(f);
        }
        return f1;
    }

    private void load(Element element, String s)
    {
        mName = element.getAttribute("name");
        mHasName = TextUtils.isEmpty(mName) ^ true;
        Object obj = getVariables();
        if(mHasName)
            mCurrentFrame = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("current_frame").toString(), ((Variables) (obj)), true);
        mDelay = Expression.build(((Variables) (obj)), element.getAttribute("delay"));
        mInitPaused = Boolean.parseBoolean(element.getAttribute("initPause"));
        mLoop = "false".equalsIgnoreCase(element.getAttribute("loop")) ^ true;
        mTag = element.getAttribute("tag");
        obj = new miui.maml.util.Utils.XmlTraverseListener() {

            public void onChild(Element element1)
            {
                mItems.add(onCreateItem(BaseAnimation.this, element1));
            }

            final BaseAnimation this$0;

            
            {
                this$0 = BaseAnimation.this;
                super();
            }
        }
;
        Utils.traverseXmlElementChildrenTags(element, new String[] {
            s, "Item"
        }, ((miui.maml.util.Utils.XmlTraverseListener) (obj)));
        if(mItems.size() <= 0)
        {
            Log.e("BaseAnimation", "empty items");
            return;
        }
        boolean flag;
        if(((AnimationItem)mItems.get(mItems.size() - 1)).mTime >= 0xe8d4a51000L)
            flag = true;
        else
            flag = false;
        mIsTimeInfinite = flag;
        if(mItems.size() > 1 && mIsTimeInfinite)
            mRealTimeRange = ((AnimationItem)mItems.get(mItems.size() - 2)).mTime;
        else
            mRealTimeRange = ((AnimationItem)mItems.get(mItems.size() - 1)).mTime;
        element = Utils.getChild(element, "Triggers");
        if(element != null)
            mTriggers = new CommandTriggers(element, mScreenElement);
    }

    private void reevaluate()
    {
        long l = 0L;
        int i = mItems.size();
        int j = 0;
        while(j < i) 
        {
            AnimationItem animationitem = (AnimationItem)mItems.get(j);
            long l2;
            if(animationitem.mDeltaTimeExp != null)
            {
                long l1 = (long)animationitem.mDeltaTimeExp.evaluate();
                l2 = l1;
                if(l1 < 0L)
                    l2 = 0L;
                l2 = l + l2;
                animationitem.mTime = l2;
            } else
            {
                l2 = l;
                if(animationitem.mTime >= l)
                    l2 = animationitem.mTime;
            }
            j++;
            l = l2;
        }
        boolean flag;
        if(l >= 0xe8d4a51000L)
            flag = true;
        else
            flag = false;
        mIsTimeInfinite = flag;
        if(i > 1 && mIsTimeInfinite)
            mRealTimeRange = ((AnimationItem)mItems.get(i - 2)).mTime;
        else
            mRealTimeRange = l;
    }

    private void resetTime()
    {
        if(mIsFirstReset)
            mIsFirstReset = false;
        mIsLastFrame = false;
        int i = mItems.size();
        for(int j = 0; j < i; j++)
            ((AnimationItem)mItems.get(j)).reset();

        reevaluate();
        mAnimStartTime = transToAnimTime(mStartTime);
        mAnimEndTime = transToAnimTime(mEndTime);
        mPlayTimeRange = Math.abs(mAnimEndTime - mAnimStartTime);
    }

    private long transToAnimTime(long l)
    {
        if(l == -1L || l > mRealTimeRange)
            return mRealTimeRange;
        else
            return l;
    }

    public void finish()
    {
        if(mTriggers != null)
            mTriggers.finish();
        int i = mItems.size();
        for(int j = 0; j < i; j++)
            ((AnimationItem)mItems.get(j)).reset();

        i = mCurValues.length;
        for(int k = 0; k < i; k++)
            mCurValues[k] = 0.0D;

    }

    public String[] getAttrs()
    {
        return mAttrs;
    }

    public double getCurValue(int i)
    {
        return mCurValues[i];
    }

    protected double getDefaultValue()
    {
        return 0.0D;
    }

    protected double getDelayValue(int i)
    {
        AnimationItem animationitem = getItem(0);
        double d;
        if(animationitem != null)
            d = animationitem.get(i);
        else
            d = 0.0D;
        return d;
    }

    protected AnimationItem getItem(int i)
    {
        if(i < 0 || i >= mItems.size())
            return null;
        else
            return (AnimationItem)mItems.get(i);
    }

    public String getTag()
    {
        return mTag;
    }

    protected final Variables getVariables()
    {
        return mScreenElement.getVariables();
    }

    public void init()
    {
        if(mTriggers != null)
            mTriggers.init();
    }

    public void onAction(String s)
    {
        if(mTriggers != null)
            mTriggers.onAction(s);
    }

    protected AnimationItem onCreateItem(BaseAnimation baseanimation, Element element)
    {
        return new AnimationItem(baseanimation, element);
    }

    protected void onTick(AnimationItem animationitem, AnimationItem animationitem1, float f)
    {
        if(animationitem == null && animationitem1 == null)
            return;
        double d = getDefaultValue();
        int i = mAttrs.length;
        int j = 0;
        while(j < i) 
        {
            double d1;
            if(animationitem == null)
                d1 = d;
            else
                d1 = animationitem.get(j);
            mCurValues[j] = (animationitem1.get(j) - d1) * (double)f + d1;
            j++;
        }
    }

    public void pause()
    {
        if(mTriggers != null)
            mTriggers.pause();
    }

    public void pauseAnim(long l)
    {
        if(mDisable)
            return;
        if(!mIsPaused)
        {
            mIsPaused = true;
            mPauseTime = l;
        }
    }

    public void playAnim(long l, long l1, long l2, boolean flag, 
            boolean flag1)
    {
        if(mDisable)
            return;
        mResetTime = l;
        l = l1;
        if(l1 < 0L)
            if(l1 == -1L)
                l = l1;
            else
                l = 0L;
        mStartTime = l;
        mAnimStartTime = l;
        l = l2;
        if(l2 < 0L)
            if(l2 == -1L)
                l = l2;
            else
                l = 0L;
        mEndTime = l;
        mAnimEndTime = l;
        mIsLoop = flag;
        mIsDelay = flag1;
        if(mStartTime == -1L || mStartTime >= mEndTime && mEndTime >= 0L)
            flag = true;
        else
            flag = false;
        mIsReverse = flag;
        if(mStartTime == mEndTime)
            mIsLoop = false;
        if(mIsDelay && mDelay != null)
            mResetTime = (long)((double)mResetTime + mDelay.evaluate());
        mIsFirstFrame = true;
        mIsLastFrame = false;
        mIsPaused = false;
        mIsFirstReset = true;
        mPlayTimeRange = 0L;
    }

    public void reset(long l)
    {
        if(mDisable)
            return;
        int i = mAttrs.length;
        for(int j = 0; j < i; j++)
            mCurValues[j] = getDelayValue(j);

        if(mInitPaused)
            playAnim(l, 0L, 0L, false, false);
        else
            playAnim(l, 0L, -1L, true, true);
        if(mHasName)
            mCurrentFrame.set(0.0D);
        onAction("init");
    }

    public void resume()
    {
        if(mTriggers != null)
            mTriggers.resume();
    }

    public void resumeAnim(long l)
    {
        if(mDisable)
            return;
        if(mIsPaused)
        {
            mIsPaused = false;
            mResetTime = mResetTime + (l - mPauseTime);
        }
    }

    public void setCurValue(int i, double d)
    {
        mCurValues[i] = d;
    }

    public void setDisable(boolean flag)
    {
        mDisable = flag;
    }

    public final void tick(long l)
    {
        long l3;
label0:
        {
label1:
            {
                if(mIsPaused || mDisable)
                    return;
                long l1 = l - mResetTime;
                l3 = l1;
                if(l1 < 0L)
                {
                    if(!mIsFirstFrame)
                        break label1;
                    mIsFirstFrame = false;
                    l3 = 0L;
                }
                if(mIsFirstReset || mIsLastFrame && mIsTimeInfinite ^ true && mLoop && mIsLoop)
                    resetTime();
                if((mIsTimeInfinite || mLoop ^ true || mIsLoop ^ true) && mIsLastFrame)
                {
                    mIsPaused = true;
                    mPauseTime = mResetTime + mPlayTimeRange;
                    if(mHasName)
                        mCurrentFrame.set(mEndTime);
                    onAction("end");
                    return;
                }
                break label0;
            }
            onTick(null, null, 0.0F);
            return;
        }
        long l2 = l3;
        if(l3 >= mPlayTimeRange)
        {
            mResetTime = l - l3 % (mPlayTimeRange + 1L);
            l2 = mPlayTimeRange;
            mIsLastFrame = true;
        }
        Object obj;
        AnimationItem animationitem;
        int i;
        if(mIsReverse)
            l = mAnimStartTime - l2;
        else
            l = l2 + mAnimStartTime;
        l2 = l % (mRealTimeRange + 1L);
        obj = null;
        animationitem = null;
        i = mItems.size();
        for(int j = 0; j < i;)
        {
            AnimationItem animationitem1 = (AnimationItem)mItems.get(j);
            if(l2 < animationitem1.mTime)
            {
                long l4 = 0L;
                if(j == 0)
                {
                    l = animationitem1.mTime;
                    animationitem = obj;
                } else
                {
                    animationitem = (AnimationItem)mItems.get(j - 1);
                    l = animationitem1.mTime - animationitem.mTime;
                    l4 = animationitem.mTime;
                }
                onTick(animationitem, animationitem1, getRatio(animationitem, l2, l4, l));
                return;
            }
            j++;
            animationitem = animationitem1;
        }

        onTick(null, animationitem, 1.0F);
    }

    private static final long INFINITE_TIME = 0xe8d4a51000L;
    private static final String LOG_TAG = "BaseAnimation";
    public static final int PLAY_TO_END = -1;
    private static final String VAR_CURRENT_FRAME = "current_frame";
    private long mAnimEndTime;
    private long mAnimStartTime;
    protected String mAttrs[];
    private double mCurValues[];
    private IndexedVariable mCurrentFrame;
    private Expression mDelay;
    private boolean mDisable;
    private long mEndTime;
    private boolean mHasName;
    private boolean mInitPaused;
    private boolean mIsDelay;
    private boolean mIsFirstFrame;
    private boolean mIsFirstReset;
    private boolean mIsLastFrame;
    private boolean mIsLoop;
    private boolean mIsPaused;
    private boolean mIsReverse;
    private boolean mIsTimeInfinite;
    protected ArrayList mItems;
    private boolean mLoop;
    private String mName;
    private long mPauseTime;
    private long mPlayTimeRange;
    private long mRealTimeRange;
    private long mResetTime;
    protected ScreenElement mScreenElement;
    private long mStartTime;
    private String mTag;
    private CommandTriggers mTriggers;
}
