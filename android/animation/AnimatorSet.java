// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;

import android.app.ActivityThread;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Looper;
import android.util.*;
import java.util.*;

// Referenced classes of package android.animation:
//            Animator, ValueAnimator, AnimationHandler, ObjectAnimator, 
//            AnimatorListenerAdapter, TimeInterpolator

public final class AnimatorSet extends Animator
    implements AnimationHandler.AnimationFrameCallback
{
    private static class AnimationEvent
    {

        long getTime()
        {
            long l = -1L;
            if(mEvent == 0)
                return mNode.mStartTime;
            if(mEvent == 1)
            {
                if(mNode.mStartTime != -1L)
                    l = mNode.mStartTime + mNode.mAnimation.getStartDelay();
                return l;
            } else
            {
                return mNode.mEndTime;
            }
        }

        public String toString()
        {
            String s;
            if(mEvent == 0)
                s = "start";
            else
            if(mEvent == 1)
                s = "delay ended";
            else
                s = "end";
            return (new StringBuilder()).append(s).append(" ").append(mNode.mAnimation.toString()).toString();
        }

        static final int ANIMATION_DELAY_ENDED = 1;
        static final int ANIMATION_END = 2;
        static final int ANIMATION_START = 0;
        final int mEvent;
        final Node mNode;

        AnimationEvent(Node node, int i)
        {
            mNode = node;
            mEvent = i;
        }
    }

    public class Builder
    {

        public Builder after(long l)
        {
            ValueAnimator valueanimator = ValueAnimator.ofFloat(new float[] {
                0.0F, 1.0F
            });
            valueanimator.setDuration(l);
            after(((Animator) (valueanimator)));
            return this;
        }

        public Builder after(Animator animator)
        {
            animator = AnimatorSet._2D_wrap0(AnimatorSet.this, animator);
            mCurrentNode.addParent(animator);
            return this;
        }

        public Builder before(Animator animator)
        {
            animator = AnimatorSet._2D_wrap0(AnimatorSet.this, animator);
            mCurrentNode.addChild(animator);
            return this;
        }

        public Builder with(Animator animator)
        {
            animator = AnimatorSet._2D_wrap0(AnimatorSet.this, animator);
            mCurrentNode.addSibling(animator);
            return this;
        }

        private Node mCurrentNode;
        final AnimatorSet this$0;

        Builder(Animator animator)
        {
            this$0 = AnimatorSet.this;
            super();
            AnimatorSet._2D_set0(AnimatorSet.this, true);
            mCurrentNode = AnimatorSet._2D_wrap0(AnimatorSet.this, animator);
        }
    }

    private static class Node
        implements Cloneable
    {

        void addChild(Node node)
        {
            if(mChildNodes == null)
                mChildNodes = new ArrayList();
            if(!mChildNodes.contains(node))
            {
                mChildNodes.add(node);
                node.addParent(this);
            }
        }

        public void addParent(Node node)
        {
            if(mParents == null)
                mParents = new ArrayList();
            if(!mParents.contains(node))
            {
                mParents.add(node);
                node.addChild(this);
            }
        }

        public void addParents(ArrayList arraylist)
        {
            if(arraylist == null)
                return;
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                addParent((Node)arraylist.get(j));

        }

        public void addSibling(Node node)
        {
            if(mSiblings == null)
                mSiblings = new ArrayList();
            if(!mSiblings.contains(node))
            {
                mSiblings.add(node);
                node.addSibling(this);
            }
        }

        public Node clone()
        {
            Node node;
            try
            {
                node = (Node)super.clone();
                node.mAnimation = mAnimation.clone();
                if(mChildNodes != null)
                {
                    ArrayList arraylist = JVM INSTR new #51  <Class ArrayList>;
                    arraylist.ArrayList(mChildNodes);
                    node.mChildNodes = arraylist;
                }
                if(mSiblings != null)
                {
                    ArrayList arraylist1 = JVM INSTR new #51  <Class ArrayList>;
                    arraylist1.ArrayList(mSiblings);
                    node.mSiblings = arraylist1;
                }
                if(mParents != null)
                {
                    ArrayList arraylist2 = JVM INSTR new #51  <Class ArrayList>;
                    arraylist2.ArrayList(mParents);
                    node.mParents = arraylist2;
                }
                node.mEnded = false;
            }
            catch(CloneNotSupportedException clonenotsupportedexception)
            {
                throw new AssertionError();
            }
            return node;
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        Animator mAnimation;
        ArrayList mChildNodes;
        long mEndTime;
        boolean mEnded;
        Node mLatestParent;
        ArrayList mParents;
        boolean mParentsAdded;
        ArrayList mSiblings;
        long mStartTime;
        long mTotalDuration;

        public Node(Animator animator)
        {
            mChildNodes = null;
            mEnded = false;
            mLatestParent = null;
            mParentsAdded = false;
            mStartTime = 0L;
            mEndTime = 0L;
            mTotalDuration = 0L;
            mAnimation = animator;
        }
    }

    private class SeekState
    {

        long getPlayTime()
        {
            return mPlayTime;
        }

        long getPlayTimeNormalized()
        {
            if(AnimatorSet._2D_get1(AnimatorSet.this))
                return getTotalDuration() - AnimatorSet._2D_get2(AnimatorSet.this) - mPlayTime;
            else
                return mPlayTime;
        }

        boolean isActive()
        {
            boolean flag;
            if(mPlayTime != -1L)
                flag = true;
            else
                flag = false;
            return flag;
        }

        void reset()
        {
            mPlayTime = -1L;
            mSeekingInReverse = false;
        }

        void setPlayTime(long l, boolean flag)
        {
            if(getTotalDuration() != -1L)
                mPlayTime = Math.min(l, getTotalDuration() - AnimatorSet._2D_get2(AnimatorSet.this));
            mPlayTime = Math.max(0L, mPlayTime);
            mSeekingInReverse = flag;
        }

        void updateSeekDirection(boolean flag)
        {
            if(flag && getTotalDuration() == -1L)
                throw new UnsupportedOperationException("Error: Cannot reverse infinite animator set");
            if(mPlayTime >= 0L && flag != mSeekingInReverse)
            {
                mPlayTime = getTotalDuration() - AnimatorSet._2D_get2(AnimatorSet.this) - mPlayTime;
                mSeekingInReverse = flag;
            }
        }

        private long mPlayTime;
        private boolean mSeekingInReverse;
        final AnimatorSet this$0;

        private SeekState()
        {
            this$0 = AnimatorSet.this;
            super();
            mPlayTime = -1L;
            mSeekingInReverse = false;
        }

        SeekState(SeekState seekstate)
        {
            this();
        }
    }


    static ArrayMap _2D_get0(AnimatorSet animatorset)
    {
        return animatorset.mNodeMap;
    }

    static boolean _2D_get1(AnimatorSet animatorset)
    {
        return animatorset.mReversing;
    }

    static long _2D_get2(AnimatorSet animatorset)
    {
        return animatorset.mStartDelay;
    }

    static boolean _2D_set0(AnimatorSet animatorset, boolean flag)
    {
        animatorset.mDependencyDirty = flag;
        return flag;
    }

    static Node _2D_wrap0(AnimatorSet animatorset, Animator animator)
    {
        return animatorset.getNodeForAnimation(animator);
    }

    public AnimatorSet()
    {
        mPlayingSet = new ArrayList();
        mNodeMap = new ArrayMap();
        mEvents = new ArrayList();
        mNodes = new ArrayList();
        mDependencyDirty = false;
        mStarted = false;
        mStartDelay = 0L;
        mDelayAnim = ValueAnimator.ofFloat(new float[] {
            0.0F, 1.0F
        }).setDuration(0L);
        mRootNode = new Node(mDelayAnim);
        mDuration = -1L;
        mInterpolator = null;
        mTotalDuration = 0L;
        mLastFrameTime = -1L;
        mFirstFrame = -1L;
        mLastEventId = -1;
        mReversing = false;
        mSelfPulse = true;
        mSeekState = new SeekState(null);
        mChildrenInitialized = false;
        mPauseTime = -1L;
        mDummyListener = new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                if(AnimatorSet._2D_get0(AnimatorSet.this).get(animator) == null)
                {
                    throw new AndroidRuntimeException("Error: animation ended is not in the node map");
                } else
                {
                    ((Node)AnimatorSet._2D_get0(AnimatorSet.this).get(animator)).mEnded = true;
                    return;
                }
            }

            final AnimatorSet this$0;

            
            {
                this$0 = AnimatorSet.this;
                super();
            }
        }
;
        mNodeMap.put(mDelayAnim, mRootNode);
        mNodes.add(mRootNode);
        Application application = ActivityThread.currentApplication();
        boolean flag;
        if(application == null || application.getApplicationInfo() == null)
        {
            mShouldIgnoreEndWithoutStart = true;
            flag = true;
        } else
        {
            if(application.getApplicationInfo().targetSdkVersion < 24)
                mShouldIgnoreEndWithoutStart = true;
            else
                mShouldIgnoreEndWithoutStart = false;
            if(application.getApplicationInfo().targetSdkVersion < 26)
                flag = true;
            else
                flag = false;
        }
        mShouldResetValuesAtStart = flag ^ true;
        mEndCanBeCalled = flag ^ true;
    }

    private void addAnimationCallback(long l)
    {
        if(!mSelfPulse)
        {
            return;
        } else
        {
            AnimationHandler.getInstance().addAnimationFrameCallback(this, l);
            return;
        }
    }

    private void addDummyListener()
    {
        for(int i = 1; i < mNodes.size(); i++)
            ((Node)mNodes.get(i)).mAnimation.addListener(mDummyListener);

    }

    private void createDependencyGraph()
    {
        if(!mDependencyDirty)
        {
            boolean flag = false;
            int j = 0;
label0:
            do
            {
label1:
                {
                    boolean flag1 = flag;
                    if(j < mNodes.size())
                    {
                        Animator animator = ((Node)mNodes.get(j)).mAnimation;
                        if(((Node)mNodes.get(j)).mTotalDuration == animator.getTotalDuration())
                            break label1;
                        flag1 = true;
                    }
                    if(!flag1)
                        return;
                    break label0;
                }
                j++;
            } while(true);
        }
        mDependencyDirty = false;
        int i = mNodes.size();
        for(int i1 = 0; i1 < i; i1++)
            ((Node)mNodes.get(i1)).mParentsAdded = false;

        int j1 = 0;
        while(j1 < i) 
        {
            Node node2 = (Node)mNodes.get(j1);
            if(!node2.mParentsAdded)
            {
                node2.mParentsAdded = true;
                if(node2.mSiblings != null)
                {
                    findSiblings(node2, node2.mSiblings);
                    node2.mSiblings.remove(node2);
                    int l1 = node2.mSiblings.size();
                    for(int k = 0; k < l1; k++)
                        node2.addParents(((Node)node2.mSiblings.get(k)).mParents);

                    int l = 0;
                    while(l < l1) 
                    {
                        Node node = (Node)node2.mSiblings.get(l);
                        node.addParents(node2.mParents);
                        node.mParentsAdded = true;
                        l++;
                    }
                }
            }
            j1++;
        }
        for(int k1 = 0; k1 < i; k1++)
        {
            Node node1 = (Node)mNodes.get(k1);
            if(node1 != mRootNode && node1.mParents == null)
                node1.addParent(mRootNode);
        }

        ArrayList arraylist = new ArrayList(mNodes.size());
        mRootNode.mStartTime = 0L;
        mRootNode.mEndTime = mDelayAnim.getDuration();
        updatePlayTime(mRootNode, arraylist);
        sortAnimationEvents();
        mTotalDuration = ((AnimationEvent)mEvents.get(mEvents.size() - 1)).getTime();
    }

    private void endAnimation()
    {
        mStarted = false;
        mLastFrameTime = -1L;
        mFirstFrame = -1L;
        mLastEventId = -1;
        mPaused = false;
        mPauseTime = -1L;
        mSeekState.reset();
        mPlayingSet.clear();
        removeAnimationCallback();
        if(mListeners != null)
        {
            ArrayList arraylist = (ArrayList)mListeners.clone();
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((Animator.AnimatorListener)arraylist.get(j)).onAnimationEnd(this, mReversing);

        }
        removeDummyListener();
        mSelfPulse = true;
        mReversing = false;
    }

    private int findLatestEventIdForTime(long l)
    {
        int i = mEvents.size();
        int j = mLastEventId;
        int j1;
        if(mReversing)
        {
            long l1 = getTotalDuration();
            int k;
            if(mLastEventId == -1)
                k = i;
            else
                k = mLastEventId;
            mLastEventId = k;
            k = mLastEventId - 1;
            do
            {
                j1 = j;
                if(k < 0)
                    break;
                if(((AnimationEvent)mEvents.get(k)).getTime() >= l1 - l)
                    j = k;
                k--;
            } while(true);
        } else
        {
            int i1 = mLastEventId + 1;
            do
            {
                j1 = j;
                if(i1 >= i)
                    break;
                AnimationEvent animationevent = (AnimationEvent)mEvents.get(i1);
                j1 = j;
                if(animationevent.getTime() != -1L)
                {
                    j1 = j;
                    if(animationevent.getTime() <= l)
                        j1 = i1;
                }
                i1++;
                j = j1;
            } while(true);
        }
        return j1;
    }

    private void findSiblings(Node node, ArrayList arraylist)
    {
        if(!arraylist.contains(node))
        {
            arraylist.add(node);
            if(node.mSiblings == null)
                return;
            for(int i = 0; i < node.mSiblings.size(); i++)
                findSiblings((Node)node.mSiblings.get(i), arraylist);

        }
    }

    private void forceToEnd()
    {
        if(mEndCanBeCalled)
        {
            end();
            return;
        }
        if(mReversing)
        {
            handleAnimationEvents(mLastEventId, 0, getTotalDuration());
        } else
        {
            long l = getTotalDuration();
            long l1 = l;
            if(l == -1L)
                l1 = 0x7fffffffL;
            handleAnimationEvents(mLastEventId, mEvents.size() - 1, l1);
        }
        mPlayingSet.clear();
        endAnimation();
    }

    private Node getNodeForAnimation(Animator animator)
    {
        Node node = (Node)mNodeMap.get(animator);
        Node node1 = node;
        if(node == null)
        {
            node1 = new Node(animator);
            mNodeMap.put(animator, node1);
            mNodes.add(node1);
        }
        return node1;
    }

    private long getPlayTimeForNode(long l, Node node)
    {
        return getPlayTimeForNode(l, node, mReversing);
    }

    private long getPlayTimeForNode(long l, Node node, boolean flag)
    {
        if(flag)
        {
            long l1 = getTotalDuration();
            return node.mEndTime - (l1 - l);
        } else
        {
            return l - node.mStartTime;
        }
    }

    private void handleAnimationEvents(int i, int j, long l)
    {
        if(mReversing)
        {
            int k = i;
            if(i == -1)
                k = mEvents.size();
            i = k - 1;
            while(i >= j) 
            {
                AnimationEvent animationevent = (AnimationEvent)mEvents.get(i);
                Node node = animationevent.mNode;
                if(animationevent.mEvent == 2)
                {
                    if(node.mAnimation.isStarted())
                        node.mAnimation.cancel();
                    node.mEnded = false;
                    mPlayingSet.add(animationevent.mNode);
                    node.mAnimation.startWithoutPulsing(true);
                    pulseFrame(node, 0L);
                } else
                if(animationevent.mEvent == 1 && node.mEnded ^ true)
                    pulseFrame(node, getPlayTimeForNode(l, node));
                i--;
            }
        } else
        {
            i++;
            while(i <= j) 
            {
                AnimationEvent animationevent1 = (AnimationEvent)mEvents.get(i);
                Node node1 = animationevent1.mNode;
                if(animationevent1.mEvent == 0)
                {
                    mPlayingSet.add(animationevent1.mNode);
                    if(node1.mAnimation.isStarted())
                        node1.mAnimation.cancel();
                    node1.mEnded = false;
                    node1.mAnimation.startWithoutPulsing(false);
                    pulseFrame(node1, 0L);
                } else
                if(animationevent1.mEvent == 2 && node1.mEnded ^ true)
                    pulseFrame(node1, getPlayTimeForNode(l, node1));
                i++;
            }
        }
    }

    private void initAnimation()
    {
        if(mInterpolator != null)
        {
            for(int i = 0; i < mNodes.size(); i++)
                ((Node)mNodes.get(i)).mAnimation.setInterpolator(mInterpolator);

        }
        updateAnimatorsDuration();
        createDependencyGraph();
    }

    private void initChildren()
    {
        if(!isInitialized())
        {
            mChildrenInitialized = true;
            skipToEndValue(false);
        }
    }

    private static boolean isEmptySet(AnimatorSet animatorset)
    {
        if(animatorset.getStartDelay() > 0L)
            return false;
        for(int i = 0; i < animatorset.getChildAnimations().size(); i++)
        {
            Animator animator = (Animator)animatorset.getChildAnimations().get(i);
            if(!(animator instanceof AnimatorSet))
                return false;
            if(!isEmptySet((AnimatorSet)animator))
                return false;
        }

        return true;
    }

    private void printChildCount()
    {
        ArrayList arraylist = new ArrayList(mNodes.size());
        arraylist.add(mRootNode);
        Log.d("AnimatorSet", "Current tree: ");
        StringBuilder stringbuilder;
        for(int i = 0; i < arraylist.size(); Log.d("AnimatorSet", stringbuilder.toString()))
        {
            int j = arraylist.size();
            stringbuilder = new StringBuilder();
            for(; i < j; i++)
            {
                Node node = (Node)arraylist.get(i);
                int k = 0;
                int l = 0;
                if(node.mChildNodes != null)
                {
                    int i1 = 0;
                    do
                    {
                        k = l;
                        if(i1 >= node.mChildNodes.size())
                            break;
                        Node node1 = (Node)node.mChildNodes.get(i1);
                        k = l;
                        if(node1.mLatestParent == node)
                        {
                            k = l + 1;
                            arraylist.add(node1);
                        }
                        i1++;
                        l = k;
                    } while(true);
                }
                stringbuilder.append(" ");
                stringbuilder.append(k);
            }

        }

    }

    private void pulseFrame(Node node, long l)
    {
        if(!node.mEnded)
        {
            float f = ValueAnimator.getDurationScale();
            float f1 = f;
            if(f == 0.0F)
                f1 = 1.0F;
            node.mEnded = node.mAnimation.pulseAnimationFrame((long)((float)l * f1));
        }
    }

    private void removeAnimationCallback()
    {
        if(!mSelfPulse)
        {
            return;
        } else
        {
            AnimationHandler.getInstance().removeCallback(this);
            return;
        }
    }

    private void removeDummyListener()
    {
        for(int i = 1; i < mNodes.size(); i++)
            ((Node)mNodes.get(i)).mAnimation.removeListener(mDummyListener);

    }

    private void skipToStartValue(boolean flag)
    {
        skipToEndValue(flag ^ true);
    }

    private void sortAnimationEvents()
    {
        int j;
        int k;
        mEvents.clear();
        for(int i = 1; i < mNodes.size(); i++)
        {
            Node node = (Node)mNodes.get(i);
            mEvents.add(new AnimationEvent(node, 0));
            mEvents.add(new AnimationEvent(node, 1));
            mEvents.add(new AnimationEvent(node, 2));
        }

        mEvents.sort(new Comparator() {

            public int compare(AnimationEvent animationevent3, AnimationEvent animationevent4)
            {
                long l2 = animationevent3.getTime();
                long l3 = animationevent4.getTime();
                if(l2 == l3)
                    if(animationevent4.mEvent + animationevent3.mEvent == 1)
                        return animationevent3.mEvent - animationevent4.mEvent;
                    else
                        return animationevent4.mEvent - animationevent3.mEvent;
                if(l3 == -1L)
                    return -1;
                if(l2 == -1L)
                    return 1;
                else
                    return (int)(l2 - l3);
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((AnimationEvent)obj, (AnimationEvent)obj1);
            }

            final AnimatorSet this$0;

            
            {
                this$0 = AnimatorSet.this;
                super();
            }
        }
);
        k = mEvents.size();
        j = 0;
_L6:
        AnimationEvent animationevent;
        boolean flag;
        int l;
        int i1;
        int j1;
        if(j >= k)
            break; /* Loop/switch isn't completed */
        animationevent = (AnimationEvent)mEvents.get(j);
        if(animationevent.mEvent != 2)
            break MISSING_BLOCK_LABEL_455;
        if(animationevent.mNode.mStartTime == animationevent.mNode.mEndTime)
            flag = true;
        else
        if(animationevent.mNode.mEndTime == animationevent.mNode.mStartTime + animationevent.mNode.mAnimation.getStartDelay())
        {
            flag = false;
        } else
        {
            j++;
            continue; /* Loop/switch isn't completed */
        }
        l = k;
        i1 = k;
        j1 = j + 1;
_L2:
        int l1;
        int i2;
        if(j1 >= k || l < k && i1 < k)
        {
            if(flag && l == mEvents.size())
                throw new UnsupportedOperationException("Something went wrong, no start isfound after stop for an animation that has the same start and endtime.");
            break; /* Loop/switch isn't completed */
        }
        l1 = i1;
        i2 = l;
        if(((AnimationEvent)mEvents.get(j1)).mNode == animationevent.mNode)
        {
            if(((AnimationEvent)mEvents.get(j1)).mEvent != 0)
                break; /* Loop/switch isn't completed */
            i2 = j1;
            l1 = i1;
        }
_L3:
        j1++;
        i1 = l1;
        l = i2;
        if(true) goto _L2; else goto _L1
_L1:
        l1 = i1;
        i2 = l;
        if(((AnimationEvent)mEvents.get(j1)).mEvent == 1)
        {
            l1 = j1;
            i2 = l;
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
        if(i1 == mEvents.size())
            throw new UnsupportedOperationException("Something went wrong, no startdelay end is found after stop for an animation");
        int k1 = j;
        if(flag)
        {
            AnimationEvent animationevent1 = (AnimationEvent)mEvents.remove(l);
            mEvents.add(j, animationevent1);
            k1 = j + 1;
        }
        AnimationEvent animationevent2 = (AnimationEvent)mEvents.remove(i1);
        mEvents.add(k1, animationevent2);
        j = k1 + 2;
        continue; /* Loop/switch isn't completed */
        j++;
        if(true) goto _L6; else goto _L5
_L5:
        if(!mEvents.isEmpty() && ((AnimationEvent)mEvents.get(0)).mEvent != 0)
            throw new UnsupportedOperationException("Sorting went bad, the start event should always be at index 0");
        mEvents.add(0, new AnimationEvent(mRootNode, 0));
        mEvents.add(1, new AnimationEvent(mRootNode, 1));
        mEvents.add(2, new AnimationEvent(mRootNode, 2));
        if(((AnimationEvent)mEvents.get(mEvents.size() - 1)).mEvent == 0 || ((AnimationEvent)mEvents.get(mEvents.size() - 1)).mEvent == 1)
            throw new UnsupportedOperationException("Something went wrong, the last event is not an end event");
        else
            return;
    }

    private void start(boolean flag, boolean flag1)
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        mStarted = true;
        mSelfPulse = flag1;
        mPaused = false;
        mPauseTime = -1L;
        int i = mNodes.size();
        for(int k = 0; k < i; k++)
        {
            Node node = (Node)mNodes.get(k);
            node.mEnded = false;
            node.mAnimation.setAllowRunningAsynchronously(false);
        }

        initAnimation();
        if(flag && canReverse() ^ true)
            throw new UnsupportedOperationException("Cannot reverse infinite AnimatorSet");
        mReversing = flag;
        flag1 = isEmptySet(this);
        if(!flag1)
            startAnimation();
        if(mListeners != null)
        {
            ArrayList arraylist = (ArrayList)mListeners.clone();
            int j = arraylist.size();
            for(int l = 0; l < j; l++)
                ((Animator.AnimatorListener)arraylist.get(l)).onAnimationStart(this, flag);

        }
        if(flag1)
            end();
    }

    private void startAnimation()
    {
        addDummyListener();
        addAnimationCallback(0L);
        if(mSeekState.getPlayTimeNormalized() == 0L && mReversing)
            mSeekState.reset();
        int j;
        if(mShouldResetValuesAtStart)
            if(isInitialized())
                skipToEndValue(mReversing ^ true);
            else
            if(mReversing)
            {
                initChildren();
                skipToEndValue(mReversing ^ true);
            } else
            {
                int k = mEvents.size() - 1;
                while(k >= 0) 
                {
                    if(((AnimationEvent)mEvents.get(k)).mEvent == 1)
                    {
                        Animator animator = ((AnimationEvent)mEvents.get(k)).mNode.mAnimation;
                        if(animator.isInitialized())
                            animator.skipToEndValue(true);
                    }
                    k--;
                }
            }
        if(mReversing || mStartDelay == 0L || mSeekState.isActive())
            break MISSING_BLOCK_LABEL_75;
_L2:
        return;
        long l;
        int i;
        if(mSeekState.isActive())
        {
            mSeekState.updateSeekDirection(mReversing);
            l = mSeekState.getPlayTime();
        } else
        {
            l = 0L;
        }
        i = findLatestEventIdForTime(l);
        handleAnimationEvents(-1, i, l);
        for(j = mPlayingSet.size() - 1; j >= 0; j--)
            if(((Node)mPlayingSet.get(j)).mEnded)
                mPlayingSet.remove(j);

        mLastEventId = i;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private void updateAnimatorsDuration()
    {
        if(mDuration >= 0L)
        {
            int i = mNodes.size();
            for(int j = 0; j < i; j++)
                ((Node)mNodes.get(j)).mAnimation.setDuration(mDuration);

        }
        mDelayAnim.setDuration(mStartDelay);
    }

    private void updatePlayTime(Node node, ArrayList arraylist)
    {
        if(node.mChildNodes == null)
        {
            if(node == mRootNode)
            {
                for(int i = 0; i < mNodes.size(); i++)
                {
                    node = (Node)mNodes.get(i);
                    if(node != mRootNode)
                    {
                        node.mStartTime = -1L;
                        node.mEndTime = -1L;
                    }
                }

            }
            return;
        }
        arraylist.add(node);
        int k = node.mChildNodes.size();
        int j = 0;
        while(j < k) 
        {
            Node node1 = (Node)node.mChildNodes.get(j);
            node1.mTotalDuration = node1.mAnimation.getTotalDuration();
            int l = arraylist.indexOf(node1);
            if(l >= 0)
            {
                for(; l < arraylist.size(); l++)
                {
                    ((Node)arraylist.get(l)).mLatestParent = null;
                    ((Node)arraylist.get(l)).mStartTime = -1L;
                    ((Node)arraylist.get(l)).mEndTime = -1L;
                }

                node1.mStartTime = -1L;
                node1.mEndTime = -1L;
                node1.mLatestParent = null;
                Log.w("AnimatorSet", (new StringBuilder()).append("Cycle found in AnimatorSet: ").append(this).toString());
            } else
            {
                if(node1.mStartTime != -1L)
                    if(node.mEndTime == -1L)
                    {
                        node1.mLatestParent = node;
                        node1.mStartTime = -1L;
                        node1.mEndTime = -1L;
                    } else
                    {
                        if(node.mEndTime >= node1.mStartTime)
                        {
                            node1.mLatestParent = node;
                            node1.mStartTime = node.mEndTime;
                        }
                        long l1;
                        if(node1.mTotalDuration == -1L)
                            l1 = -1L;
                        else
                            l1 = node1.mStartTime + node1.mTotalDuration;
                        node1.mEndTime = l1;
                    }
                updatePlayTime(node1, arraylist);
            }
            j++;
        }
        arraylist.remove(node);
    }

    void animateBasedOnPlayTime(long l, long l1, boolean flag)
    {
        if(l < 0L || l1 < 0L)
            throw new UnsupportedOperationException("Error: Play time should never be negative.");
        long l2 = l;
        long l3 = l1;
        boolean flag1 = flag;
        if(flag)
        {
            if(getTotalDuration() == -1L)
                throw new UnsupportedOperationException("Cannot reverse AnimatorSet with infinite duration");
            l3 = getTotalDuration() - mStartDelay;
            l2 = l3 - Math.min(l, l3);
            l3 -= l1;
            flag1 = false;
        }
        skipToStartValue(false);
        ArrayList arraylist = new ArrayList();
        int i = 0;
label0:
        do
        {
            Object obj;
label1:
            {
                if(i < mEvents.size())
                {
                    obj = (AnimationEvent)mEvents.get(i);
                    if(((AnimationEvent) (obj)).getTime() <= l2 && ((AnimationEvent) (obj)).getTime() != -1L)
                        break label1;
                }
                for(i = 0; i < arraylist.size(); i++)
                {
                    obj = (Node)arraylist.get(i);
                    l1 = getPlayTimeForNode(l2, ((Node) (obj)), flag1);
                    l = l1;
                    if(!flag1)
                        l = l1 - ((Node) (obj)).mAnimation.getStartDelay();
                    ((Node) (obj)).mAnimation.animateBasedOnPlayTime(l, l3, flag1);
                }

                break label0;
            }
            if(((AnimationEvent) (obj)).mEvent == 1 && (((AnimationEvent) (obj)).mNode.mEndTime == -1L || ((AnimationEvent) (obj)).mNode.mEndTime > l2))
                arraylist.add(((AnimationEvent) (obj)).mNode);
            if(((AnimationEvent) (obj)).mEvent == 2)
                ((AnimationEvent) (obj)).mNode.mAnimation.skipToEndValue(false);
            i++;
        } while(true);
    }

    public boolean canReverse()
    {
        boolean flag;
        if(getTotalDuration() != -1L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void cancel()
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        if(isStarted())
        {
            if(mListeners != null)
            {
                ArrayList arraylist = (ArrayList)mListeners.clone();
                int i = arraylist.size();
                for(int k = 0; k < i; k++)
                    ((Animator.AnimatorListener)arraylist.get(k)).onAnimationCancel(this);

            }
            ArrayList arraylist1 = new ArrayList(mPlayingSet);
            int j = arraylist1.size();
            for(int l = 0; l < j; l++)
                ((Node)arraylist1.get(l)).mAnimation.cancel();

            mPlayingSet.clear();
            endAnimation();
        }
    }

    public volatile Animator clone()
    {
        return clone();
    }

    public AnimatorSet clone()
    {
        final AnimatorSet anim = (AnimatorSet)super.clone();
        int i = mNodes.size();
        anim.mStarted = false;
        anim.mLastFrameTime = -1L;
        anim.mFirstFrame = -1L;
        anim.mLastEventId = -1;
        anim.mPaused = false;
        anim.mPauseTime = -1L;
        anim.mSeekState = new SeekState(null);
        anim.mSelfPulse = true;
        anim.mPlayingSet = new ArrayList();
        anim.mNodeMap = new ArrayMap();
        anim.mNodes = new ArrayList(i);
        anim.mEvents = new ArrayList();
        anim.mDummyListener = new AnimatorListenerAdapter() {

            public void onAnimationEnd(Animator animator)
            {
                if(AnimatorSet._2D_get0(anim).get(animator) == null)
                {
                    throw new AndroidRuntimeException("Error: animation ended is not in the node map");
                } else
                {
                    ((Node)AnimatorSet._2D_get0(anim).get(animator)).mEnded = true;
                    return;
                }
            }

            final AnimatorSet this$0;
            final AnimatorSet val$anim;

            
            {
                this$0 = AnimatorSet.this;
                anim = animatorset1;
                super();
            }
        }
;
        anim.mReversing = false;
        anim.mDependencyDirty = true;
        HashMap hashmap = new HashMap(i);
        for(int j = 0; j < i; j++)
        {
            Node node = (Node)mNodes.get(j);
            Node node2 = node.clone();
            node2.mAnimation.removeListener(mDummyListener);
            hashmap.put(node, node2);
            anim.mNodes.add(node2);
            anim.mNodeMap.put(node2.mAnimation, node2);
        }

        anim.mRootNode = (Node)hashmap.get(mRootNode);
        anim.mDelayAnim = (ValueAnimator)anim.mRootNode.mAnimation;
        Node node4;
        int l;
        for(int k = 0; k < i; k++)
        {
            node4 = (Node)mNodes.get(k);
            Node node3 = (Node)hashmap.get(node4);
            Node node1;
            int i1;
            if(node4.mLatestParent == null)
                node1 = null;
            else
                node1 = (Node)hashmap.get(node4.mLatestParent);
            node3.mLatestParent = node1;
            if(node4.mChildNodes == null)
                l = 0;
            else
                l = node4.mChildNodes.size();
            for(i1 = 0; i1 < l; i1++)
                node3.mChildNodes.set(i1, (Node)hashmap.get(node4.mChildNodes.get(i1)));

            if(node4.mSiblings == null)
                l = 0;
            else
                l = node4.mSiblings.size();
            for(i1 = 0; i1 < l; i1++)
                node3.mSiblings.set(i1, (Node)hashmap.get(node4.mSiblings.get(i1)));

            if(node4.mParents == null)
                l = 0;
            else
                l = node4.mParents.size();
            for(i1 = 0; i1 < l; i1++)
                node3.mParents.set(i1, (Node)hashmap.get(node4.mParents.get(i1)));

        }

        return anim;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public void commitAnimationFrame(long l)
    {
    }

    public boolean doAnimationFrame(long l)
    {
        float f = ValueAnimator.getDurationScale();
        if(f == 0.0F)
        {
            forceToEnd();
            return true;
        }
        if(mFirstFrame < 0L)
            mFirstFrame = l;
        if(mPaused)
        {
            mPauseTime = l;
            removeAnimationCallback();
            return false;
        }
        if(mPauseTime > 0L)
        {
            mFirstFrame = mFirstFrame + (l - mPauseTime);
            mPauseTime = -1L;
        }
        if(mSeekState.isActive())
        {
            mSeekState.updateSeekDirection(mReversing);
            if(mReversing)
                mFirstFrame = (long)((float)l - (float)mSeekState.getPlayTime() * f);
            else
                mFirstFrame = (long)((float)l - (float)(mSeekState.getPlayTime() + mStartDelay) * f);
            mSeekState.reset();
        }
        if(!mReversing && (float)l < (float)mFirstFrame + (float)mStartDelay * f)
            return false;
        long l1 = (long)((float)(l - mFirstFrame) / f);
        mLastFrameTime = l;
        int i = findLatestEventIdForTime(l1);
        handleAnimationEvents(mLastEventId, i, l1);
        mLastEventId = i;
        for(int j = 0; j < mPlayingSet.size(); j++)
        {
            Node node = (Node)mPlayingSet.get(j);
            if(!node.mEnded)
                pulseFrame(node, getPlayTimeForNode(l1, node));
        }

        for(int k = mPlayingSet.size() - 1; k >= 0; k--)
            if(((Node)mPlayingSet.get(k)).mEnded)
                mPlayingSet.remove(k);

        boolean flag1 = false;
        boolean flag;
        if(mReversing)
        {
            if(mPlayingSet.size() == 1 && mPlayingSet.get(0) == mRootNode)
            {
                flag = true;
            } else
            {
                flag = flag1;
                if(mPlayingSet.isEmpty())
                {
                    flag = flag1;
                    if(mLastEventId < 3)
                        flag = true;
                }
            }
        } else
        if(mPlayingSet.isEmpty() && mLastEventId == mEvents.size() - 1)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            endAnimation();
            return true;
        } else
        {
            return false;
        }
    }

    public void end()
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        if(mShouldIgnoreEndWithoutStart && isStarted() ^ true)
            return;
        if(isStarted())
        {
            if(mReversing)
            {
                int i;
                if(mLastEventId == -1)
                    i = mEvents.size();
                else
                    i = mLastEventId;
                mLastEventId = i;
                do
                {
                    if(mLastEventId <= 0)
                        break;
                    mLastEventId = mLastEventId - 1;
                    AnimationEvent animationevent = (AnimationEvent)mEvents.get(mLastEventId);
                    Animator animator = animationevent.mNode.mAnimation;
                    if(!((Node)mNodeMap.get(animator)).mEnded)
                        if(animationevent.mEvent == 2)
                            animator.reverse();
                        else
                        if(animationevent.mEvent == 1 && animator.isStarted())
                            animator.end();
                } while(true);
            } else
            {
                do
                {
                    if(mLastEventId >= mEvents.size() - 1)
                        break;
                    mLastEventId = mLastEventId + 1;
                    AnimationEvent animationevent1 = (AnimationEvent)mEvents.get(mLastEventId);
                    Animator animator1 = animationevent1.mNode.mAnimation;
                    if(!((Node)mNodeMap.get(animator1)).mEnded)
                        if(animationevent1.mEvent == 0)
                            animator1.start();
                        else
                        if(animationevent1.mEvent == 2 && animator1.isStarted())
                            animator1.end();
                } while(true);
            }
            mPlayingSet.clear();
        }
        endAnimation();
    }

    public int getChangingConfigurations()
    {
        int i = super.getChangingConfigurations();
        int j = mNodes.size();
        for(int k = 0; k < j; k++)
            i |= ((Node)mNodes.get(k)).mAnimation.getChangingConfigurations();

        return i;
    }

    public ArrayList getChildAnimations()
    {
        ArrayList arraylist = new ArrayList();
        int i = mNodes.size();
        for(int j = 0; j < i; j++)
        {
            Node node = (Node)mNodes.get(j);
            if(node != mRootNode)
                arraylist.add(node.mAnimation);
        }

        return arraylist;
    }

    public long getCurrentPlayTime()
    {
        if(mSeekState.isActive())
            return mSeekState.getPlayTime();
        if(mLastFrameTime == -1L)
            return 0L;
        float f = ValueAnimator.getDurationScale();
        float f1 = f;
        if(f == 0.0F)
            f1 = 1.0F;
        if(mReversing)
            return (long)((float)(mLastFrameTime - mFirstFrame) / f1);
        else
            return (long)((float)(mLastFrameTime - mFirstFrame - mStartDelay) / f1);
    }

    public long getDuration()
    {
        return mDuration;
    }

    public TimeInterpolator getInterpolator()
    {
        return mInterpolator;
    }

    public long getStartDelay()
    {
        return mStartDelay;
    }

    public long getTotalDuration()
    {
        updateAnimatorsDuration();
        createDependencyGraph();
        return mTotalDuration;
    }

    boolean isInitialized()
    {
        if(mChildrenInitialized)
            return true;
        boolean flag = true;
        int i = 0;
        do
        {
label0:
            {
                boolean flag1 = flag;
                if(i < mNodes.size())
                {
                    if(((Node)mNodes.get(i)).mAnimation.isInitialized())
                        break label0;
                    flag1 = false;
                }
                mChildrenInitialized = flag1;
                return mChildrenInitialized;
            }
            i++;
        } while(true);
    }

    public boolean isRunning()
    {
        if(mStartDelay == 0L)
            return mStarted;
        boolean flag;
        if(mLastFrameTime > 0L)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isStarted()
    {
        return mStarted;
    }

    public void pause()
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        boolean flag = mPaused;
        super.pause();
        if(!flag && mPaused)
            mPauseTime = -1L;
    }

    public Builder play(Animator animator)
    {
        if(animator != null)
            return new Builder(animator);
        else
            return null;
    }

    public void playSequentially(List list)
    {
        if(list != null && list.size() > 0)
            if(list.size() == 1)
            {
                play((Animator)list.get(0));
            } else
            {
                int i = 0;
                while(i < list.size() - 1) 
                {
                    play((Animator)list.get(i)).before((Animator)list.get(i + 1));
                    i++;
                }
            }
    }

    public transient void playSequentially(Animator aanimator[])
    {
        if(aanimator != null)
            if(aanimator.length == 1)
            {
                play(aanimator[0]);
            } else
            {
                int i = 0;
                while(i < aanimator.length - 1) 
                {
                    play(aanimator[i]).before(aanimator[i + 1]);
                    i++;
                }
            }
    }

    public void playTogether(Collection collection)
    {
        if(collection != null && collection.size() > 0)
        {
            Object obj = null;
            Iterator iterator = collection.iterator();
            collection = obj;
            while(iterator.hasNext()) 
            {
                Animator animator = (Animator)iterator.next();
                if(collection == null)
                    collection = play(animator);
                else
                    collection.with(animator);
            }
        }
    }

    public transient void playTogether(Animator aanimator[])
    {
        if(aanimator != null)
        {
            Builder builder = play(aanimator[0]);
            for(int i = 1; i < aanimator.length; i++)
                builder.with(aanimator[i]);

        }
    }

    boolean pulseAnimationFrame(long l)
    {
        return doAnimationFrame(l);
    }

    public void resume()
    {
        if(Looper.myLooper() == null)
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        boolean flag = mPaused;
        super.resume();
        if(flag && mPaused ^ true && mPauseTime >= 0L)
            addAnimationCallback(0L);
    }

    public void reverse()
    {
        start(true, true);
    }

    public void setCurrentPlayTime(long l)
    {
        if(mReversing && getTotalDuration() == -1L)
            throw new UnsupportedOperationException("Error: Cannot seek in reverse in an infinite AnimatorSet");
        while(getTotalDuration() != -1L && l > getTotalDuration() - mStartDelay || l < 0L) 
            throw new UnsupportedOperationException("Error: Play time should always be in between0 and duration.");
        initAnimation();
        if(!isStarted())
        {
            if(mReversing)
                throw new UnsupportedOperationException("Error: Something went wrong. mReversing should not be set when AnimatorSet is not started.");
            if(!mSeekState.isActive())
            {
                findLatestEventIdForTime(0L);
                initChildren();
                skipToStartValue(mReversing);
                mSeekState.setPlayTime(0L, mReversing);
            }
            animateBasedOnPlayTime(l, 0L, mReversing);
            mSeekState.setPlayTime(l, mReversing);
        } else
        {
            mSeekState.setPlayTime(l, mReversing);
        }
    }

    public volatile Animator setDuration(long l)
    {
        return setDuration(l);
    }

    public AnimatorSet setDuration(long l)
    {
        if(l < 0L)
        {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        } else
        {
            mDependencyDirty = true;
            mDuration = l;
            return this;
        }
    }

    public void setInterpolator(TimeInterpolator timeinterpolator)
    {
        mInterpolator = timeinterpolator;
    }

    public void setStartDelay(long l)
    {
        long l1 = l;
        if(l < 0L)
        {
            Log.w("AnimatorSet", "Start delay should always be non-negative");
            l1 = 0L;
        }
        long l2 = l1 - mStartDelay;
        if(l2 == 0L)
            return;
        mStartDelay = l1;
        if(!mDependencyDirty)
        {
            int i = mNodes.size();
            int j = 0;
            while(j < i) 
            {
                Node node = (Node)mNodes.get(j);
                if(node == mRootNode)
                {
                    node.mEndTime = mStartDelay;
                } else
                {
                    if(node.mStartTime == -1L)
                        l = -1L;
                    else
                        l = node.mStartTime + l2;
                    node.mStartTime = l;
                    if(node.mEndTime == -1L)
                        l = -1L;
                    else
                        l = node.mEndTime + l2;
                    node.mEndTime = l;
                }
                j++;
            }
            if(mTotalDuration != -1L)
                mTotalDuration = mTotalDuration + l2;
        }
    }

    public void setTarget(Object obj)
    {
        int i = mNodes.size();
        int j = 0;
        while(j < i) 
        {
            Animator animator = ((Node)mNodes.get(j)).mAnimation;
            if(animator instanceof AnimatorSet)
                ((AnimatorSet)animator).setTarget(obj);
            else
            if(animator instanceof ObjectAnimator)
                ((ObjectAnimator)animator).setTarget(obj);
            j++;
        }
    }

    public void setupEndValues()
    {
        int i = mNodes.size();
        for(int j = 0; j < i; j++)
        {
            Node node = (Node)mNodes.get(j);
            if(node != mRootNode)
                node.mAnimation.setupEndValues();
        }

    }

    public void setupStartValues()
    {
        int i = mNodes.size();
        for(int j = 0; j < i; j++)
        {
            Node node = (Node)mNodes.get(j);
            if(node != mRootNode)
                node.mAnimation.setupStartValues();
        }

    }

    public boolean shouldPlayTogether()
    {
        boolean flag = true;
        updateAnimatorsDuration();
        createDependencyGraph();
        boolean flag1 = flag;
        if(mRootNode.mChildNodes != null)
            if(mRootNode.mChildNodes.size() == mNodes.size() - 1)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    void skipToEndValue(boolean flag)
    {
        if(!isInitialized())
            throw new UnsupportedOperationException("Children must be initialized.");
        initAnimation();
        if(flag)
        {
            for(int i = mEvents.size() - 1; i >= 0; i--)
                if(((AnimationEvent)mEvents.get(i)).mEvent == 1)
                    ((AnimationEvent)mEvents.get(i)).mNode.mAnimation.skipToEndValue(true);

        } else
        {
            for(int j = 0; j < mEvents.size(); j++)
                if(((AnimationEvent)mEvents.get(j)).mEvent == 2)
                    ((AnimationEvent)mEvents.get(j)).mNode.mAnimation.skipToEndValue(false);

        }
    }

    public void start()
    {
        start(false, true);
    }

    void startWithoutPulsing(boolean flag)
    {
        start(flag, false);
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("AnimatorSet@").append(Integer.toHexString(hashCode())).append("{").toString();
        int i = mNodes.size();
        for(int j = 0; j < i; j++)
        {
            Node node = (Node)mNodes.get(j);
            s = (new StringBuilder()).append(s).append("\n    ").append(node.mAnimation.toString()).toString();
        }

        return (new StringBuilder()).append(s).append("\n}").toString();
    }

    private static final String TAG = "AnimatorSet";
    private boolean mChildrenInitialized;
    private ValueAnimator mDelayAnim;
    private boolean mDependencyDirty;
    private AnimatorListenerAdapter mDummyListener;
    private long mDuration;
    private final boolean mEndCanBeCalled;
    private ArrayList mEvents;
    private long mFirstFrame;
    private TimeInterpolator mInterpolator;
    private int mLastEventId;
    private long mLastFrameTime;
    private ArrayMap mNodeMap;
    private ArrayList mNodes;
    private long mPauseTime;
    private ArrayList mPlayingSet;
    private boolean mReversing;
    private Node mRootNode;
    private SeekState mSeekState;
    private boolean mSelfPulse;
    private final boolean mShouldIgnoreEndWithoutStart;
    private final boolean mShouldResetValuesAtStart;
    private long mStartDelay;
    private boolean mStarted;
    private long mTotalDuration;
}
