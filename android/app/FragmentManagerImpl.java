// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.animation.*;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.os.*;
import android.util.*;
import android.view.*;
import com.android.internal.util.FastPrintWriter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package android.app:
//            FragmentManager, Fragment, BackStackRecord, FragmentTransition, 
//            FragmentHostCallback, FragmentManagerNonConfig, LoaderManagerImpl, FragmentContainer, 
//            FragmentManagerState, FragmentState, BackStackState, FragmentTransaction

final class FragmentManagerImpl extends FragmentManager
    implements android.view.LayoutInflater.Factory2
{
    static class AnimateOnHWLayerIfNeededListener
        implements android.animation.Animator.AnimatorListener
    {

        public void onAnimationCancel(Animator animator)
        {
        }

        public void onAnimationEnd(Animator animator)
        {
            if(mShouldRunOnHWLayer)
                mView.setLayerType(0, null);
            mView = null;
            animator.removeListener(this);
        }

        public void onAnimationRepeat(Animator animator)
        {
        }

        public void onAnimationStart(Animator animator)
        {
            mShouldRunOnHWLayer = FragmentManagerImpl.shouldRunOnHWLayer(mView, animator);
            if(mShouldRunOnHWLayer)
                mView.setLayerType(2, null);
        }

        private boolean mShouldRunOnHWLayer;
        private View mView;

        public AnimateOnHWLayerIfNeededListener(View view)
        {
            mShouldRunOnHWLayer = false;
            if(view == null)
            {
                return;
            } else
            {
                mView = view;
                return;
            }
        }
    }

    static interface OpGenerator
    {

        public abstract boolean generateOps(ArrayList arraylist, ArrayList arraylist1);
    }

    private class PopBackStackState
        implements OpGenerator
    {

        public boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
        {
            if(mPrimaryNav != null && mId < 0 && mName == null)
            {
                FragmentManagerImpl fragmentmanagerimpl = mPrimaryNav.mChildFragmentManager;
                if(fragmentmanagerimpl != null && fragmentmanagerimpl.popBackStackImmediate())
                    return false;
            }
            return popBackStackState(arraylist, arraylist1, mName, mId, mFlags);
        }

        final int mFlags;
        final int mId;
        final String mName;
        final FragmentManagerImpl this$0;

        public PopBackStackState(String s, int i, int j)
        {
            this$0 = FragmentManagerImpl.this;
            super();
            mName = s;
            mId = i;
            mFlags = j;
        }
    }

    static class StartEnterTransitionListener
        implements Fragment.OnStartEnterTransitionListener
    {

        static boolean _2D_get0(StartEnterTransitionListener startentertransitionlistener)
        {
            return startentertransitionlistener.mIsBack;
        }

        static BackStackRecord _2D_get1(StartEnterTransitionListener startentertransitionlistener)
        {
            return startentertransitionlistener.mRecord;
        }

        public void cancelTransaction()
        {
            FragmentManagerImpl._2D_wrap0(mRecord.mManager, mRecord, mIsBack, false, false);
        }

        public void completeTransaction()
        {
            boolean flag;
            FragmentManagerImpl fragmentmanagerimpl;
            int i;
            if(mNumPostponed > 0)
                flag = true;
            else
                flag = false;
            fragmentmanagerimpl = mRecord.mManager;
            i = fragmentmanagerimpl.mAdded.size();
            for(int j = 0; j < i; j++)
            {
                Fragment fragment = (Fragment)fragmentmanagerimpl.mAdded.get(j);
                fragment.setOnStartEnterTransitionListener(null);
                if(flag && fragment.isPostponed())
                    fragment.startPostponedEnterTransition();
            }

            FragmentManagerImpl._2D_wrap0(mRecord.mManager, mRecord, mIsBack, flag ^ true, true);
        }

        public boolean isReady()
        {
            boolean flag = false;
            if(mNumPostponed == 0)
                flag = true;
            return flag;
        }

        public void onStartEnterTransition()
        {
            mNumPostponed = mNumPostponed - 1;
            if(mNumPostponed != 0)
            {
                return;
            } else
            {
                FragmentManagerImpl._2D_wrap1(mRecord.mManager);
                return;
            }
        }

        public void startListening()
        {
            mNumPostponed = mNumPostponed + 1;
        }

        private final boolean mIsBack;
        private int mNumPostponed;
        private final BackStackRecord mRecord;

        public StartEnterTransitionListener(BackStackRecord backstackrecord, boolean flag)
        {
            mIsBack = flag;
            mRecord = backstackrecord;
        }
    }


    static void _2D_wrap0(FragmentManagerImpl fragmentmanagerimpl, BackStackRecord backstackrecord, boolean flag, boolean flag1, boolean flag2)
    {
        fragmentmanagerimpl.completeExecute(backstackrecord, flag, flag1, flag2);
    }

    static void _2D_wrap1(FragmentManagerImpl fragmentmanagerimpl)
    {
        fragmentmanagerimpl.scheduleCommit();
    }

    FragmentManagerImpl()
    {
        mNextFragmentIndex = 0;
        mCurState = 0;
        mStateBundle = null;
        mStateArray = null;
        mExecCommit = new Runnable() {

            public void run()
            {
                execPendingActions();
            }

            final FragmentManagerImpl this$0;

            
            {
                this$0 = FragmentManagerImpl.this;
                super();
            }
        }
;
    }

    private void addAddedFragments(ArraySet arrayset)
    {
        if(mCurState < 1)
            return;
        int i = Math.min(mCurState, 4);
        int j = mAdded.size();
        for(int k = 0; k < j; k++)
        {
            Fragment fragment = (Fragment)mAdded.get(k);
            if(fragment.mState >= i)
                continue;
            moveToState(fragment, i, fragment.getNextAnim(), fragment.getNextTransition(), false);
            if(fragment.mView != null && fragment.mHidden ^ true && fragment.mIsNewlyAdded)
                arrayset.add(fragment);
        }

    }

    private void burpActive()
    {
        if(mActive != null)
        {
            for(int i = mActive.size() - 1; i >= 0; i--)
                if(mActive.valueAt(i) == null)
                    mActive.delete(mActive.keyAt(i));

        }
    }

    private void checkStateLoss()
    {
        if(mStateSaved)
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        if(mNoTransactionsBecause != null)
            throw new IllegalStateException((new StringBuilder()).append("Can not perform this action inside of ").append(mNoTransactionsBecause).toString());
        else
            return;
    }

    private void cleanupExec()
    {
        mExecutingActions = false;
        mTmpIsPop.clear();
        mTmpRecords.clear();
    }

    private void completeExecute(BackStackRecord backstackrecord, boolean flag, boolean flag1, boolean flag2)
    {
        ArrayList arraylist;
        ArrayList arraylist1;
        if(flag)
            backstackrecord.executePopOps(flag2);
        else
            backstackrecord.executeOps();
        arraylist = new ArrayList(1);
        arraylist1 = new ArrayList(1);
        arraylist.add(backstackrecord);
        arraylist1.add(Boolean.valueOf(flag));
        if(flag1)
            FragmentTransition.startTransitions(this, arraylist, arraylist1, 0, 1, true);
        if(flag2)
            moveToState(mCurState, true);
        if(mActive != null)
        {
            int i = mActive.size();
            for(int j = 0; j < i; j++)
            {
                Fragment fragment = (Fragment)mActive.valueAt(j);
                if(fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && backstackrecord.interactsWith(fragment.mContainerId))
                    fragment.mIsNewlyAdded = false;
            }

        }
    }

    private void dispatchMoveToState(int i)
    {
        if(!mAllowOldReentrantBehavior) goto _L2; else goto _L1
_L1:
        moveToState(i, false);
_L4:
        execPendingActions();
        return;
_L2:
        mExecutingActions = true;
        moveToState(i, false);
        mExecutingActions = false;
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        mExecutingActions = false;
        throw exception;
    }

    private void endAnimatingAwayFragments()
    {
        int i;
        int j;
        if(mActive == null)
            i = 0;
        else
            i = mActive.size();
        for(j = 0; j < i; j++)
        {
            Fragment fragment = (Fragment)mActive.valueAt(j);
            if(fragment != null && fragment.getAnimatingAway() != null)
                fragment.getAnimatingAway().end();
        }

    }

    private void ensureExecReady(boolean flag)
    {
        if(mExecutingActions)
            throw new IllegalStateException("FragmentManager is already executing transactions");
        if(Looper.myLooper() != mHost.getHandler().getLooper())
            throw new IllegalStateException("Must be called from main thread of fragment host");
        if(!flag)
            checkStateLoss();
        if(mTmpRecords == null)
        {
            mTmpRecords = new ArrayList();
            mTmpIsPop = new ArrayList();
        }
        mExecutingActions = true;
        executePostponedTransaction(null, null);
        mExecutingActions = false;
        return;
        Exception exception;
        exception;
        mExecutingActions = false;
        throw exception;
    }

    private static void executeOps(ArrayList arraylist, ArrayList arraylist1, int i, int j)
    {
        while(i < j) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(i);
            if(((Boolean)arraylist1.get(i)).booleanValue())
            {
                backstackrecord.bumpBackStackNesting(-1);
                boolean flag;
                if(i == j - 1)
                    flag = true;
                else
                    flag = false;
                backstackrecord.executePopOps(flag);
            } else
            {
                backstackrecord.bumpBackStackNesting(1);
                backstackrecord.executeOps();
            }
            i++;
        }
    }

    private void executeOpsTogether(ArrayList arraylist, ArrayList arraylist1, int i, int j)
    {
        boolean flag = ((BackStackRecord)arraylist.get(i)).mReorderingAllowed;
        boolean flag1 = false;
        Fragment fragment;
        int k;
        if(mTmpAddedFragments == null)
            mTmpAddedFragments = new ArrayList();
        else
            mTmpAddedFragments.clear();
        mTmpAddedFragments.addAll(mAdded);
        fragment = getPrimaryNavigationFragment();
        k = i;
        while(k < j) 
        {
            BackStackRecord backstackrecord1 = (BackStackRecord)arraylist.get(k);
            if(!((Boolean)arraylist1.get(k)).booleanValue())
                fragment = backstackrecord1.expandOps(mTmpAddedFragments, fragment);
            else
                backstackrecord1.trackAddedFragmentsInPop(mTmpAddedFragments);
            if(!flag1)
                flag1 = backstackrecord1.mAddToBackStack;
            else
                flag1 = true;
            k++;
        }
        mTmpAddedFragments.clear();
        if(!flag)
            FragmentTransition.startTransitions(this, arraylist, arraylist1, i, j, false);
        executeOps(arraylist, arraylist1, i, j);
        k = j;
        if(flag)
        {
            ArraySet arrayset = new ArraySet();
            addAddedFragments(arrayset);
            k = postponePostponableTransactions(arraylist, arraylist1, i, j, arrayset);
            makeRemovedFragmentsInvisible(arrayset);
        }
        if(k != i && flag)
        {
            FragmentTransition.startTransitions(this, arraylist, arraylist1, i, k, true);
            moveToState(mCurState, true);
        }
        for(; i < j; i++)
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(i);
            if(((Boolean)arraylist1.get(i)).booleanValue() && backstackrecord.mIndex >= 0)
            {
                freeBackStackIndex(backstackrecord.mIndex);
                backstackrecord.mIndex = -1;
            }
            backstackrecord.runOnCommitRunnables();
        }

        if(flag1)
            reportBackStackChanged();
    }

    private void executePostponedTransaction(ArrayList arraylist, ArrayList arraylist1)
    {
        int i;
        int j;
        int k;
        StartEnterTransitionListener startentertransitionlistener;
        int l;
        if(mPostponedTransactions == null)
            i = 0;
        else
            i = mPostponedTransactions.size();
        j = 0;
        k = i;
        i = j;
        if(i >= k)
            break MISSING_BLOCK_LABEL_240;
        startentertransitionlistener = (StartEnterTransitionListener)mPostponedTransactions.get(i);
        if(arraylist == null || !(StartEnterTransitionListener._2D_get0(startentertransitionlistener) ^ true))
            break; /* Loop/switch isn't completed */
        j = arraylist.indexOf(StartEnterTransitionListener._2D_get1(startentertransitionlistener));
        if(j == -1 || !((Boolean)arraylist1.get(j)).booleanValue())
            break; /* Loop/switch isn't completed */
        startentertransitionlistener.cancelTransaction();
        j = k;
        l = i;
_L4:
        i = l + 1;
        k = j;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_18;
_L1:
        if(!startentertransitionlistener.isReady())
        {
            l = i;
            j = k;
            if(arraylist == null)
                continue; /* Loop/switch isn't completed */
            l = i;
            j = k;
            if(!StartEnterTransitionListener._2D_get1(startentertransitionlistener).interactsWith(arraylist, 0, arraylist.size()))
                continue; /* Loop/switch isn't completed */
        }
label0:
        {
            mPostponedTransactions.remove(i);
            l = i - 1;
            j = k - 1;
            if(arraylist == null || !(StartEnterTransitionListener._2D_get0(startentertransitionlistener) ^ true))
                break label0;
            i = arraylist.indexOf(StartEnterTransitionListener._2D_get1(startentertransitionlistener));
            if(i == -1 || !((Boolean)arraylist1.get(i)).booleanValue())
                break label0;
            startentertransitionlistener.cancelTransaction();
        }
        continue; /* Loop/switch isn't completed */
        startentertransitionlistener.completeTransaction();
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Fragment findFragmentUnder(Fragment fragment)
    {
        ViewGroup viewgroup = fragment.mContainer;
        View view = fragment.mView;
        if(viewgroup == null || view == null)
            return null;
        for(int i = mAdded.indexOf(fragment) - 1; i >= 0; i--)
        {
            fragment = (Fragment)mAdded.get(i);
            if(fragment.mContainer == viewgroup && fragment.mView != null)
                return fragment;
        }

        return null;
    }

    private void forcePostponedTransactions()
    {
        if(mPostponedTransactions != null)
            for(; !mPostponedTransactions.isEmpty(); ((StartEnterTransitionListener)mPostponedTransactions.remove(0)).completeTransaction());
    }

    private boolean generateOpsForPendingActions(ArrayList arraylist, ArrayList arraylist1)
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        int i;
        if(mPendingActions == null)
            break MISSING_BLOCK_LABEL_25;
        i = mPendingActions.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_29;
        this;
        JVM INSTR monitorexit ;
        return false;
        int j = mPendingActions.size();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        flag |= ((OpGenerator)mPendingActions.get(i)).generateOps(arraylist, arraylist1);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        mPendingActions.clear();
        mHost.getHandler().removeCallbacks(mExecCommit);
        this;
        JVM INSTR monitorexit ;
        return flag;
        arraylist;
        throw arraylist;
    }

    private void makeRemovedFragmentsInvisible(ArraySet arrayset)
    {
        int i = arrayset.size();
        for(int j = 0; j < i; j++)
        {
            Fragment fragment = (Fragment)arrayset.valueAt(j);
            if(!fragment.mAdded)
                fragment.getView().setTransitionAlpha(0.0F);
        }

    }

    static boolean modifiesAlpha(Animator animator)
    {
        if(animator == null)
            return false;
        if(animator instanceof ValueAnimator)
        {
            animator = ((ValueAnimator)animator).getValues();
            for(int i = 0; i < animator.length; i++)
                if("alpha".equals(animator[i].getPropertyName()))
                    return true;

        } else
        if(animator instanceof AnimatorSet)
        {
            animator = ((AnimatorSet)animator).getChildAnimations();
            for(int j = 0; j < animator.size(); j++)
                if(modifiesAlpha((Animator)animator.get(j)))
                    return true;

        }
        return false;
    }

    private boolean popBackStackImmediate(String s, int i, int j)
    {
        boolean flag;
        execPendingActions();
        ensureExecReady(true);
        if(mPrimaryNav != null && i < 0 && s == null)
        {
            FragmentManagerImpl fragmentmanagerimpl = mPrimaryNav.mChildFragmentManager;
            if(fragmentmanagerimpl != null && fragmentmanagerimpl.popBackStackImmediate())
                return true;
        }
        flag = popBackStackState(mTmpRecords, mTmpIsPop, s, i, j);
        if(!flag)
            break MISSING_BLOCK_LABEL_92;
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        burpActive();
        return flag;
        s;
        cleanupExec();
        throw s;
    }

    private int postponePostponableTransactions(ArrayList arraylist, ArrayList arraylist1, int i, int j, ArraySet arrayset)
    {
        int k = j;
        int l = j - 1;
        while(l >= i) 
        {
            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(l);
            boolean flag = ((Boolean)arraylist1.get(l)).booleanValue();
            boolean flag1;
            int i1;
            if(backstackrecord.isPostponed())
                flag1 = backstackrecord.interactsWith(arraylist, l + 1, j) ^ true;
            else
                flag1 = false;
            i1 = k;
            if(flag1)
            {
                if(mPostponedTransactions == null)
                    mPostponedTransactions = new ArrayList();
                StartEnterTransitionListener startentertransitionlistener = new StartEnterTransitionListener(backstackrecord, flag);
                mPostponedTransactions.add(startentertransitionlistener);
                backstackrecord.setOnStartPostponedListener(startentertransitionlistener);
                if(flag)
                    backstackrecord.executeOps();
                else
                    backstackrecord.executePopOps(false);
                i1 = k - 1;
                if(l != i1)
                {
                    arraylist.remove(l);
                    arraylist.add(i1, backstackrecord);
                }
                addAddedFragments(arrayset);
            }
            l--;
            k = i1;
        }
        return k;
    }

    private void removeRedundantOperationsAndExecute(ArrayList arraylist, ArrayList arraylist1)
    {
        if(arraylist == null || arraylist.isEmpty())
            return;
        if(arraylist1 == null || arraylist.size() != arraylist1.size())
            throw new IllegalStateException("Internal error with the back stack records");
        executePostponedTransaction(arraylist, arraylist1);
        int i = arraylist.size();
        int j = 0;
        for(int k = 0; k < i;)
        {
            int l = k;
            int i1 = j;
            if(!((BackStackRecord)arraylist.get(k)).mReorderingAllowed)
            {
                if(j != k)
                    executeOpsTogether(arraylist, arraylist1, j, k);
                j = k + 1;
                i1 = j;
                if(((Boolean)arraylist1.get(k)).booleanValue())
                    do
                    {
                        i1 = j;
                        if(j >= i)
                            break;
                        i1 = j;
                        if(!((Boolean)arraylist1.get(j)).booleanValue())
                            break;
                        i1 = j;
                        if(!(((BackStackRecord)arraylist.get(j)).mReorderingAllowed ^ true))
                            break;
                        j++;
                    } while(true);
                executeOpsTogether(arraylist, arraylist1, k, i1);
                k = i1;
                l = i1 - 1;
                i1 = k;
            }
            k = l + 1;
            j = i1;
        }

        if(j != i)
            executeOpsTogether(arraylist, arraylist1, j, i);
    }

    public static int reverseTransit(int i)
    {
        boolean flag = false;
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   4097: 40
    //                   4099: 54
    //                   8194: 47;
           goto _L1 _L2 _L3 _L4
_L1:
        i = ((flag) ? 1 : 0);
_L6:
        return i;
_L2:
        i = 8194;
        continue; /* Loop/switch isn't completed */
_L4:
        i = 4097;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 4099;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void scheduleCommit()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mPostponedTransactions == null)
            break MISSING_BLOCK_LABEL_79;
        flag = mPostponedTransactions.isEmpty() ^ true;
_L1:
        boolean flag1;
        if(mPendingActions != null && mPendingActions.size() == 1)
            flag1 = true;
        else
            flag1 = false;
        if(!flag && !flag1)
            break MISSING_BLOCK_LABEL_76;
        mHost.getHandler().removeCallbacks(mExecCommit);
        mHost.getHandler().post(mExecCommit);
        this;
        JVM INSTR monitorexit ;
        return;
        flag = false;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void setHWLayerAnimListenerIfAlpha(View view, Animator animator)
    {
        if(view == null || animator == null)
            return;
        if(shouldRunOnHWLayer(view, animator))
            animator.addListener(new AnimateOnHWLayerIfNeededListener(view));
    }

    private static void setRetaining(FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        if(fragmentmanagernonconfig == null)
            return;
        Object obj = fragmentmanagernonconfig.getFragments();
        if(obj != null)
            for(obj = ((Iterable) (obj)).iterator(); ((Iterator) (obj)).hasNext();)
                ((Fragment)((Iterator) (obj)).next()).mRetaining = true;

        fragmentmanagernonconfig = fragmentmanagernonconfig.getChildNonConfigs();
        if(fragmentmanagernonconfig != null)
            for(fragmentmanagernonconfig = fragmentmanagernonconfig.iterator(); fragmentmanagernonconfig.hasNext(); setRetaining((FragmentManagerNonConfig)fragmentmanagernonconfig.next()));
    }

    static boolean shouldRunOnHWLayer(View view, Animator animator)
    {
        boolean flag = false;
        if(view == null || animator == null)
            return false;
        boolean flag1 = flag;
        if(view.getLayerType() == 0)
        {
            flag1 = flag;
            if(view.hasOverlappingRendering())
                flag1 = modifiesAlpha(animator);
        }
        return flag1;
    }

    private void throwException(RuntimeException runtimeexception)
    {
        Log.e("FragmentManager", runtimeexception.getMessage());
        FastPrintWriter fastprintwriter = new FastPrintWriter(new LogWriter(6, "FragmentManager"), false, 1024);
        if(mHost != null)
        {
            Log.e("FragmentManager", "Activity state:");
            try
            {
                mHost.onDump("  ", null, fastprintwriter, new String[0]);
            }
            catch(Exception exception)
            {
                fastprintwriter.flush();
                Log.e("FragmentManager", "Failed dumping state", exception);
            }
        } else
        {
            Log.e("FragmentManager", "Fragment manager state:");
            try
            {
                dump("  ", null, fastprintwriter, new String[0]);
            }
            catch(Exception exception1)
            {
                fastprintwriter.flush();
                Log.e("FragmentManager", "Failed dumping state", exception1);
            }
        }
        fastprintwriter.flush();
        throw runtimeexception;
    }

    public static int transitToStyleIndex(int i, boolean flag)
    {
        byte byte0 = -1;
        i;
        JVM INSTR lookupswitch 3: default 36
    //                   4097: 40
    //                   4099: 68
    //                   8194: 54;
           goto _L1 _L2 _L3 _L4
_L1:
        i = byte0;
_L6:
        return i;
_L2:
        if(flag)
            i = 0;
        else
            i = 1;
        continue; /* Loop/switch isn't completed */
_L4:
        if(flag)
            i = 2;
        else
            i = 3;
        continue; /* Loop/switch isn't completed */
_L3:
        if(flag)
            i = 4;
        else
            i = 5;
        if(true) goto _L6; else goto _L5
_L5:
    }

    void addBackStackState(BackStackRecord backstackrecord)
    {
        if(mBackStack == null)
            mBackStack = new ArrayList();
        mBackStack.add(backstackrecord);
    }

    public void addFragment(Fragment fragment, boolean flag)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("add: ").append(fragment).toString());
        makeActive(fragment);
        if(fragment.mDetached)
            break MISSING_BLOCK_LABEL_151;
        if(mAdded.contains(fragment))
            throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
        ArrayList arraylist = mAdded;
        arraylist;
        JVM INSTR monitorenter ;
        mAdded.add(fragment);
        arraylist;
        JVM INSTR monitorexit ;
        fragment.mAdded = true;
        fragment.mRemoving = false;
        if(fragment.mView == null)
            fragment.mHiddenChanged = false;
        if(fragment.mHasMenu && fragment.mMenuVisible)
            mNeedMenuInvalidate = true;
        if(flag)
            moveToState(fragment);
        return;
        fragment;
        throw fragment;
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if(mBackStackChangeListeners == null)
            mBackStackChangeListeners = new ArrayList();
        mBackStackChangeListeners.add(onbackstackchangedlistener);
    }

    public int allocBackStackIndex(BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        if(mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
            break MISSING_BLOCK_LABEL_104;
        if(mBackStackIndices == null)
        {
            ArrayList arraylist = JVM INSTR new #117 <Class ArrayList>;
            arraylist.ArrayList();
            mBackStackIndices = arraylist;
        }
        i = mBackStackIndices.size();
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #215 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("FragmentManager", stringbuilder.append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
        }
        mBackStackIndices.add(backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        i = ((Integer)mAvailBackStackIndices.remove(mAvailBackStackIndices.size() - 1)).intValue();
        if(DEBUG)
        {
            StringBuilder stringbuilder1 = JVM INSTR new #215 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.v("FragmentManager", stringbuilder1.append("Adding back stack index ").append(i).append(" with ").append(backstackrecord).toString());
        }
        mBackStackIndices.set(i, backstackrecord);
        this;
        JVM INSTR monitorexit ;
        return i;
        backstackrecord;
        throw backstackrecord;
    }

    public void attachController(FragmentHostCallback fragmenthostcallback, FragmentContainer fragmentcontainer, Fragment fragment)
    {
        if(mHost != null)
            throw new IllegalStateException("Already attached");
        mHost = fragmenthostcallback;
        mContainer = fragmentcontainer;
        mParent = fragment;
        boolean flag;
        if(getTargetSdk() <= 25)
            flag = true;
        else
            flag = false;
        mAllowOldReentrantBehavior = flag;
    }

    public void attachFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("attach: ").append(fragment).toString());
        if(!fragment.mDetached)
            break MISSING_BLOCK_LABEL_164;
        fragment.mDetached = false;
        if(fragment.mAdded)
            break MISSING_BLOCK_LABEL_164;
        if(mAdded.contains(fragment))
            throw new IllegalStateException((new StringBuilder()).append("Fragment already added: ").append(fragment).toString());
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("add from attach: ").append(fragment).toString());
        ArrayList arraylist = mAdded;
        arraylist;
        JVM INSTR monitorenter ;
        mAdded.add(fragment);
        arraylist;
        JVM INSTR monitorexit ;
        fragment.mAdded = true;
        if(fragment.mHasMenu && fragment.mMenuVisible)
            mNeedMenuInvalidate = true;
        return;
        fragment;
        throw fragment;
    }

    public FragmentTransaction beginTransaction()
    {
        return new BackStackRecord(this);
    }

    void completeShowHideFragment(final Fragment fragment)
    {
        if(fragment.mView == null) goto _L2; else goto _L1
_L1:
        Animator animator = loadAnimator(fragment, fragment.getNextTransition(), fragment.mHidden ^ true, fragment.getNextTransitionStyle());
        if(animator == null) goto _L4; else goto _L3
_L3:
        animator.setTarget(fragment.mView);
        if(fragment.mHidden)
        {
            if(fragment.isHideReplaced())
            {
                fragment.setHideReplaced(false);
            } else
            {
                final ViewGroup container = fragment.mContainer;
                final View animatingView = fragment.mView;
                if(container != null)
                    container.startViewTransition(animatingView);
                animator.addListener(new AnimatorListenerAdapter() {

                    public void onAnimationEnd(Animator animator1)
                    {
                        if(container != null)
                            container.endViewTransition(animatingView);
                        animator1.removeListener(this);
                        if(fragment.isHidden())
                            animatingView.setVisibility(8);
                    }

                    final FragmentManagerImpl this$0;
                    final View val$animatingView;
                    final ViewGroup val$container;
                    final Fragment val$fragment;

            
            {
                this$0 = FragmentManagerImpl.this;
                container = viewgroup;
                animatingView = view;
                fragment = fragment1;
                super();
            }
                }
);
            }
        } else
        {
            fragment.mView.setVisibility(0);
        }
        setHWLayerAnimListenerIfAlpha(fragment.mView, animator);
        animator.start();
_L2:
        if(fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible)
            mNeedMenuInvalidate = true;
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
        return;
_L4:
        byte byte0;
        if(fragment.mHidden && fragment.isHideReplaced() ^ true)
            byte0 = 8;
        else
            byte0 = 0;
        fragment.mView.setVisibility(byte0);
        if(fragment.isHideReplaced())
            fragment.setHideReplaced(false);
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void detachFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("detach: ").append(fragment).toString());
        if(fragment.mDetached)
            break MISSING_BLOCK_LABEL_125;
        fragment.mDetached = true;
        if(!fragment.mAdded)
            break MISSING_BLOCK_LABEL_125;
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("remove from detach: ").append(fragment).toString());
        ArrayList arraylist = mAdded;
        arraylist;
        JVM INSTR monitorenter ;
        mAdded.remove(fragment);
        arraylist;
        JVM INSTR monitorexit ;
        if(fragment.mHasMenu && fragment.mMenuVisible)
            mNeedMenuInvalidate = true;
        fragment.mAdded = false;
        return;
        fragment;
        throw fragment;
    }

    public void dispatchActivityCreated()
    {
        mStateSaved = false;
        dispatchMoveToState(2);
    }

    public void dispatchConfigurationChanged(Configuration configuration)
    {
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performConfigurationChanged(configuration);
        }

    }

    public boolean dispatchContextItemSelected(MenuItem menuitem)
    {
        if(mCurState < 1)
            return false;
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null && fragment.performContextItemSelected(menuitem))
                return true;
        }

        return false;
    }

    public void dispatchCreate()
    {
        mStateSaved = false;
        dispatchMoveToState(1);
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        if(mCurState < 1)
            return false;
        boolean flag = false;
        ArrayList arraylist = null;
        for(int i = 0; i < mAdded.size();)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            ArrayList arraylist1 = arraylist;
            boolean flag1 = flag;
            if(fragment != null)
            {
                arraylist1 = arraylist;
                flag1 = flag;
                if(fragment.performCreateOptionsMenu(menu, menuinflater))
                {
                    flag1 = true;
                    arraylist1 = arraylist;
                    if(arraylist == null)
                        arraylist1 = new ArrayList();
                    arraylist1.add(fragment);
                }
            }
            i++;
            arraylist = arraylist1;
            flag = flag1;
        }

        if(mCreatedMenus != null)
        {
            for(int j = 0; j < mCreatedMenus.size(); j++)
            {
                menu = (Fragment)mCreatedMenus.get(j);
                if(arraylist == null || arraylist.contains(menu) ^ true)
                    menu.onDestroyOptionsMenu();
            }

        }
        mCreatedMenus = arraylist;
        return flag;
    }

    public void dispatchDestroy()
    {
        mDestroyed = true;
        execPendingActions();
        dispatchMoveToState(0);
        mHost = null;
        mContainer = null;
        mParent = null;
    }

    public void dispatchDestroyView()
    {
        dispatchMoveToState(1);
    }

    public void dispatchLowMemory()
    {
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performLowMemory();
        }

    }

    public void dispatchMultiWindowModeChanged(boolean flag)
    {
        for(int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performMultiWindowModeChanged(flag);
        }

    }

    public void dispatchMultiWindowModeChanged(boolean flag, Configuration configuration)
    {
        for(int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performMultiWindowModeChanged(flag, configuration);
        }

    }

    void dispatchOnFragmentActivityCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentActivityCreated(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentActivityCreated(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentAttached(Fragment fragment, Context context, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentAttached(fragment, context, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentAttached(this, fragment, context);
        } while(true);
    }

    void dispatchOnFragmentCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentCreated(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentCreated(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentDestroyed(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentDestroyed(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDestroyed(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentDetached(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentDetached(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentDetached(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentPaused(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPaused(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPaused(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentPreAttached(Fragment fragment, Context context, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPreAttached(fragment, context, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreAttached(this, fragment, context);
        } while(true);
    }

    void dispatchOnFragmentPreCreated(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentPreCreated(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentPreCreated(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentResumed(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentResumed(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentResumed(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentSaveInstanceState(Fragment fragment, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentSaveInstanceState(fragment, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentSaveInstanceState(this, fragment, bundle);
        } while(true);
    }

    void dispatchOnFragmentStarted(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentStarted(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStarted(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentStopped(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentStopped(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentStopped(this, fragment);
        } while(true);
    }

    void dispatchOnFragmentViewCreated(Fragment fragment, View view, Bundle bundle, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentViewCreated(fragment, view, bundle, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewCreated(this, fragment, view, bundle);
        } while(true);
    }

    void dispatchOnFragmentViewDestroyed(Fragment fragment, boolean flag)
    {
        if(mParent != null)
        {
            FragmentManager fragmentmanager = mParent.getFragmentManager();
            if(fragmentmanager instanceof FragmentManagerImpl)
                ((FragmentManagerImpl)fragmentmanager).dispatchOnFragmentViewDestroyed(fragment, true);
        }
        Iterator iterator = mLifecycleCallbacks.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(!flag || ((Boolean)pair.second).booleanValue())
                ((FragmentManager.FragmentLifecycleCallbacks)pair.first).onFragmentViewDestroyed(this, fragment);
        } while(true);
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuitem)
    {
        if(mCurState < 1)
            return false;
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null && fragment.performOptionsItemSelected(menuitem))
                return true;
        }

        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu)
    {
        if(mCurState < 1)
            return;
        for(int i = 0; i < mAdded.size(); i++)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performOptionsMenuClosed(menu);
        }

    }

    public void dispatchPause()
    {
        dispatchMoveToState(4);
    }

    public void dispatchPictureInPictureModeChanged(boolean flag)
    {
        for(int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performPictureInPictureModeChanged(flag);
        }

    }

    public void dispatchPictureInPictureModeChanged(boolean flag, Configuration configuration)
    {
        for(int i = mAdded.size() - 1; i >= 0; i--)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            if(fragment != null)
                fragment.performPictureInPictureModeChanged(flag, configuration);
        }

    }

    public boolean dispatchPrepareOptionsMenu(Menu menu)
    {
        if(mCurState < 1)
            return false;
        boolean flag = false;
        for(int i = 0; i < mAdded.size();)
        {
            Fragment fragment = (Fragment)mAdded.get(i);
            boolean flag1 = flag;
            if(fragment != null)
            {
                flag1 = flag;
                if(fragment.performPrepareOptionsMenu(menu))
                    flag1 = true;
            }
            i++;
            flag = flag1;
        }

        return flag;
    }

    public void dispatchResume()
    {
        mStateSaved = false;
        dispatchMoveToState(5);
    }

    public void dispatchStart()
    {
        mStateSaved = false;
        dispatchMoveToState(4);
    }

    public void dispatchStop()
    {
        dispatchMoveToState(3);
    }

    public void dispatchTrimMemory(int i)
    {
        for(int j = 0; j < mAdded.size(); j++)
        {
            Fragment fragment = (Fragment)mAdded.get(j);
            if(fragment != null)
                fragment.performTrimMemory(i);
        }

    }

    void doPendingDeferredStart()
    {
        if(mHavePendingDeferredStart)
        {
            boolean flag = false;
            for(int i = 0; i < mActive.size();)
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                boolean flag1 = flag;
                if(fragment != null)
                {
                    flag1 = flag;
                    if(fragment.mLoaderManager != null)
                        flag1 = flag | fragment.mLoaderManager.hasRunningLoaders();
                }
                i++;
                flag = flag1;
            }

            if(!flag)
            {
                mHavePendingDeferredStart = false;
                startPendingDeferredFragments();
            }
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        String s1 = (new StringBuilder()).append(s).append("    ").toString();
        if(mActive != null)
        {
            int i = mActive.size();
            if(i > 0)
            {
                printwriter.print(s);
                printwriter.print("Active Fragments in ");
                printwriter.print(Integer.toHexString(System.identityHashCode(this)));
                printwriter.println(":");
                for(int k1 = 0; k1 < i; k1++)
                {
                    Fragment fragment = (Fragment)mActive.valueAt(k1);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(k1);
                    printwriter.print(": ");
                    printwriter.println(fragment);
                    if(fragment != null)
                        fragment.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        int j = mAdded.size();
        if(j > 0)
        {
            printwriter.print(s);
            printwriter.println("Added Fragments:");
            for(int l1 = 0; l1 < j; l1++)
            {
                Fragment fragment1 = (Fragment)mAdded.get(l1);
                printwriter.print(s);
                printwriter.print("  #");
                printwriter.print(l1);
                printwriter.print(": ");
                printwriter.println(fragment1.toString());
            }

        }
        if(mCreatedMenus != null)
        {
            int k = mCreatedMenus.size();
            if(k > 0)
            {
                printwriter.print(s);
                printwriter.println("Fragments Created Menus:");
                for(int i2 = 0; i2 < k; i2++)
                {
                    Fragment fragment2 = (Fragment)mCreatedMenus.get(i2);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(i2);
                    printwriter.print(": ");
                    printwriter.println(fragment2.toString());
                }

            }
        }
        if(mBackStack != null)
        {
            int l = mBackStack.size();
            if(l > 0)
            {
                printwriter.print(s);
                printwriter.println("Back Stack:");
                for(int j2 = 0; j2 < l; j2++)
                {
                    BackStackRecord backstackrecord = (BackStackRecord)mBackStack.get(j2);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(j2);
                    printwriter.print(": ");
                    printwriter.println(backstackrecord.toString());
                    backstackrecord.dump(s1, filedescriptor, printwriter, as);
                }

            }
        }
        this;
        JVM INSTR monitorenter ;
        if(mBackStackIndices == null) goto _L2; else goto _L1
_L1:
        int i1 = mBackStackIndices.size();
        if(i1 <= 0) goto _L2; else goto _L3
_L3:
        printwriter.print(s);
        printwriter.println("Back Stack Indices:");
        int k2 = 0;
_L4:
        if(k2 >= i1)
            break; /* Loop/switch isn't completed */
        filedescriptor = (BackStackRecord)mBackStackIndices.get(k2);
        printwriter.print(s);
        printwriter.print("  #");
        printwriter.print(k2);
        printwriter.print(": ");
        printwriter.println(filedescriptor);
        k2++;
        if(true) goto _L4; else goto _L2
_L2:
        if(mAvailBackStackIndices != null && mAvailBackStackIndices.size() > 0)
        {
            printwriter.print(s);
            printwriter.print("mAvailBackStackIndices: ");
            printwriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
        }
        this;
        JVM INSTR monitorexit ;
        if(mPendingActions != null)
        {
            int j1 = mPendingActions.size();
            if(j1 > 0)
            {
                printwriter.print(s);
                printwriter.println("Pending Actions:");
                for(int l2 = 0; l2 < j1; l2++)
                {
                    filedescriptor = (OpGenerator)mPendingActions.get(l2);
                    printwriter.print(s);
                    printwriter.print("  #");
                    printwriter.print(l2);
                    printwriter.print(": ");
                    printwriter.println(filedescriptor);
                }

            }
        }
        break MISSING_BLOCK_LABEL_681;
        s;
        throw s;
        printwriter.print(s);
        printwriter.println("FragmentManager misc state:");
        printwriter.print(s);
        printwriter.print("  mHost=");
        printwriter.println(mHost);
        printwriter.print(s);
        printwriter.print("  mContainer=");
        printwriter.println(mContainer);
        if(mParent != null)
        {
            printwriter.print(s);
            printwriter.print("  mParent=");
            printwriter.println(mParent);
        }
        printwriter.print(s);
        printwriter.print("  mCurState=");
        printwriter.print(mCurState);
        printwriter.print(" mStateSaved=");
        printwriter.print(mStateSaved);
        printwriter.print(" mDestroyed=");
        printwriter.println(mDestroyed);
        if(mNeedMenuInvalidate)
        {
            printwriter.print(s);
            printwriter.print("  mNeedMenuInvalidate=");
            printwriter.println(mNeedMenuInvalidate);
        }
        if(mNoTransactionsBecause != null)
        {
            printwriter.print(s);
            printwriter.print("  mNoTransactionsBecause=");
            printwriter.println(mNoTransactionsBecause);
        }
        return;
    }

    public void enqueueAction(OpGenerator opgenerator, boolean flag)
    {
        if(!flag)
            checkStateLoss();
        this;
        JVM INSTR monitorenter ;
        FragmentHostCallback fragmenthostcallback;
        if(mDestroyed)
            break MISSING_BLOCK_LABEL_26;
        fragmenthostcallback = mHost;
        if(fragmenthostcallback != null)
            break MISSING_BLOCK_LABEL_51;
        if(flag)
            return;
        opgenerator = JVM INSTR new #206 <Class IllegalStateException>;
        opgenerator.IllegalStateException("Activity has been destroyed");
        throw opgenerator;
        opgenerator;
        this;
        JVM INSTR monitorexit ;
        throw opgenerator;
        if(mPendingActions == null)
        {
            ArrayList arraylist = JVM INSTR new #117 <Class ArrayList>;
            arraylist.ArrayList();
            mPendingActions = arraylist;
        }
        mPendingActions.add(opgenerator);
        scheduleCommit();
        this;
        JVM INSTR monitorexit ;
    }

    void ensureInflatedFragmentView(Fragment fragment)
    {
        if(fragment.mFromLayout && fragment.mPerformedCreateView ^ true)
        {
            fragment.mView = fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
            if(fragment.mView != null)
            {
                fragment.mView.setSaveFromParentEnabled(false);
                if(fragment.mHidden)
                    fragment.mView.setVisibility(8);
                fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                dispatchOnFragmentViewCreated(fragment, fragment.mView, fragment.mSavedFragmentState, false);
            }
        }
    }

    public boolean execPendingActions()
    {
        boolean flag;
        ensureExecReady(true);
        flag = false;
_L2:
        if(!generateOpsForPendingActions(mTmpRecords, mTmpIsPop))
            break; /* Loop/switch isn't completed */
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        cleanupExec();
        flag = true;
        if(true) goto _L2; else goto _L1
        Exception exception;
        exception;
        cleanupExec();
        throw exception;
_L1:
        doPendingDeferredStart();
        burpActive();
        return flag;
    }

    public void execSingleAction(OpGenerator opgenerator, boolean flag)
    {
        if(flag && (mHost == null || mDestroyed))
            return;
        ensureExecReady(flag);
        if(!opgenerator.generateOps(mTmpRecords, mTmpIsPop))
            break MISSING_BLOCK_LABEL_62;
        mExecutingActions = true;
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        cleanupExec();
        doPendingDeferredStart();
        burpActive();
        return;
        opgenerator;
        cleanupExec();
        throw opgenerator;
    }

    public boolean executePendingTransactions()
    {
        boolean flag = execPendingActions();
        forcePostponedTransactions();
        return flag;
    }

    public Fragment findFragmentById(int i)
    {
        for(int j = mAdded.size() - 1; j >= 0; j--)
        {
            Fragment fragment = (Fragment)mAdded.get(j);
            if(fragment != null && fragment.mFragmentId == i)
                return fragment;
        }

        if(mActive != null)
        {
            for(int k = mActive.size() - 1; k >= 0; k--)
            {
                Fragment fragment1 = (Fragment)mActive.valueAt(k);
                if(fragment1 != null && fragment1.mFragmentId == i)
                    return fragment1;
            }

        }
        return null;
    }

    public Fragment findFragmentByTag(String s)
    {
        if(s != null)
        {
            for(int i = mAdded.size() - 1; i >= 0; i--)
            {
                Fragment fragment = (Fragment)mAdded.get(i);
                if(fragment != null && s.equals(fragment.mTag))
                    return fragment;
            }

        }
        if(mActive != null && s != null)
        {
            for(int j = mActive.size() - 1; j >= 0; j--)
            {
                Fragment fragment1 = (Fragment)mActive.valueAt(j);
                if(fragment1 != null && s.equals(fragment1.mTag))
                    return fragment1;
            }

        }
        return null;
    }

    public Fragment findFragmentByWho(String s)
    {
        if(mActive != null && s != null)
        {
            for(int i = mActive.size() - 1; i >= 0; i--)
            {
                Fragment fragment = (Fragment)mActive.valueAt(i);
                if(fragment == null)
                    continue;
                fragment = fragment.findFragmentByWho(s);
                if(fragment != null)
                    return fragment;
            }

        }
        return null;
    }

    public void freeBackStackIndex(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mBackStackIndices.set(i, null);
        if(mAvailBackStackIndices == null)
        {
            ArrayList arraylist = JVM INSTR new #117 <Class ArrayList>;
            arraylist.ArrayList();
            mAvailBackStackIndices = arraylist;
        }
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #215 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("FragmentManager", stringbuilder.append("Freeing back stack index ").append(i).toString());
        }
        mAvailBackStackIndices.add(Integer.valueOf(i));
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public FragmentManager.BackStackEntry getBackStackEntryAt(int i)
    {
        return (FragmentManager.BackStackEntry)mBackStack.get(i);
    }

    public int getBackStackEntryCount()
    {
        int i;
        if(mBackStack != null)
            i = mBackStack.size();
        else
            i = 0;
        return i;
    }

    public Fragment getFragment(Bundle bundle, String s)
    {
        int i = bundle.getInt(s, -1);
        if(i == -1)
            return null;
        bundle = (Fragment)mActive.get(i);
        if(bundle == null)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment no longer exists for key ").append(s).append(": index ").append(i).toString()));
        return bundle;
    }

    public List getFragments()
    {
        if(mAdded.isEmpty())
            return Collections.EMPTY_LIST;
        ArrayList arraylist = mAdded;
        arraylist;
        JVM INSTR monitorenter ;
        List list = (List)mAdded.clone();
        arraylist;
        JVM INSTR monitorexit ;
        return list;
        Exception exception;
        exception;
        throw exception;
    }

    android.view.LayoutInflater.Factory2 getLayoutInflaterFactory()
    {
        return this;
    }

    public Fragment getPrimaryNavigationFragment()
    {
        return mPrimaryNav;
    }

    int getTargetSdk()
    {
        if(mHost != null)
        {
            Object obj = mHost.getContext();
            if(obj != null)
            {
                obj = ((Context) (obj)).getApplicationInfo();
                if(obj != null)
                    return ((ApplicationInfo) (obj)).targetSdkVersion;
            }
        }
        return 0;
    }

    public void hideFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("hide: ").append(fragment).toString());
        if(!fragment.mHidden)
        {
            fragment.mHidden = true;
            fragment.mHiddenChanged = fragment.mHiddenChanged ^ true;
        }
    }

    public void invalidateOptionsMenu()
    {
        if(mHost != null && mCurState == 5)
            mHost.onInvalidateOptionsMenu();
        else
            mNeedMenuInvalidate = true;
    }

    public boolean isDestroyed()
    {
        return mDestroyed;
    }

    boolean isStateAtLeast(int i)
    {
        boolean flag;
        if(mCurState >= i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isStateSaved()
    {
        return mStateSaved;
    }

    Animator loadAnimator(Fragment fragment, int i, boolean flag, int j)
    {
        Animator animator = fragment.onCreateAnimator(i, flag, fragment.getNextAnim());
        if(animator != null)
            return animator;
        if(fragment.getNextAnim() != 0)
        {
            fragment = AnimatorInflater.loadAnimator(mHost.getContext(), fragment.getNextAnim());
            if(fragment != null)
                return fragment;
        }
        if(i == 0)
            return null;
        int k = transitToStyleIndex(i, flag);
        if(k < 0)
            return null;
        i = j;
        if(j == 0)
        {
            i = j;
            if(mHost.onHasWindowAnimations())
                i = mHost.onGetWindowAnimations();
        }
        if(i == 0)
            return null;
        fragment = mHost.getContext().obtainStyledAttributes(i, com.android.internal.R.styleable.FragmentAnimation);
        i = fragment.getResourceId(k, 0);
        fragment.recycle();
        if(i == 0)
            return null;
        else
            return AnimatorInflater.loadAnimator(mHost.getContext(), i);
    }

    void makeActive(Fragment fragment)
    {
        if(fragment.mIndex >= 0)
            return;
        int i = mNextFragmentIndex;
        mNextFragmentIndex = i + 1;
        fragment.setIndex(i, mParent);
        if(mActive == null)
            mActive = new SparseArray();
        mActive.put(fragment.mIndex, fragment);
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Allocated fragment index ").append(fragment).toString());
    }

    void makeInactive(Fragment fragment)
    {
        if(fragment.mIndex < 0)
            return;
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Freeing fragment index ").append(fragment).toString());
        mActive.put(fragment.mIndex, null);
        mHost.inactivateFragment(fragment.mWho);
        fragment.initState();
    }

    void moveFragmentToExpectedState(Fragment fragment)
    {
        if(fragment == null)
            return;
        int i = mCurState;
        int j = i;
        if(fragment.mRemoving)
            if(fragment.isInBackStack())
                j = Math.min(i, 1);
            else
                j = Math.min(i, 0);
        moveToState(fragment, j, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if(fragment.mView != null)
        {
            Object obj = findFragmentUnder(fragment);
            if(obj != null)
            {
                View view = ((Fragment) (obj)).mView;
                obj = fragment.mContainer;
                j = ((ViewGroup) (obj)).indexOfChild(view);
                i = ((ViewGroup) (obj)).indexOfChild(fragment.mView);
                if(i < j)
                {
                    ((ViewGroup) (obj)).removeViewAt(i);
                    ((ViewGroup) (obj)).addView(fragment.mView, j);
                }
            }
            if(fragment.mIsNewlyAdded && fragment.mContainer != null)
            {
                fragment.mView.setTransitionAlpha(1.0F);
                fragment.mIsNewlyAdded = false;
                Animator animator = loadAnimator(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if(animator != null)
                {
                    animator.setTarget(fragment.mView);
                    setHWLayerAnimListenerIfAlpha(fragment.mView, animator);
                    animator.start();
                }
            }
        }
        if(fragment.mHiddenChanged)
            completeShowHideFragment(fragment);
    }

    void moveToState(int i, boolean flag)
    {
label0:
        {
            if(mHost == null && i != 0)
                throw new IllegalStateException("No activity");
            if(!flag && mCurState == i)
                return;
            mCurState = i;
            if(mActive == null)
                break label0;
            i = 0;
            int j = mAdded.size();
            for(int k = 0; k < j;)
            {
                Fragment fragment = (Fragment)mAdded.get(k);
                moveFragmentToExpectedState(fragment);
                int i1 = i;
                if(fragment.mLoaderManager != null)
                    i1 = i | fragment.mLoaderManager.hasRunningLoaders();
                k++;
                i = i1;
            }

            j = mActive.size();
            for(int l = 0; l < j;)
            {
                int j1;
label1:
                {
                    Fragment fragment1 = (Fragment)mActive.valueAt(l);
                    j1 = i;
                    if(fragment1 == null)
                        break label1;
                    if(!fragment1.mRemoving)
                    {
                        j1 = i;
                        if(!fragment1.mDetached)
                            break label1;
                    }
                    j1 = i;
                    if(fragment1.mIsNewlyAdded ^ true)
                    {
                        moveFragmentToExpectedState(fragment1);
                        j1 = i;
                        if(fragment1.mLoaderManager != null)
                            j1 = i | fragment1.mLoaderManager.hasRunningLoaders();
                    }
                }
                l++;
                i = j1;
            }

            if(i == 0)
                startPendingDeferredFragments();
            if(mNeedMenuInvalidate && mHost != null && mCurState == 5)
            {
                mHost.onInvalidateOptionsMenu();
                mNeedMenuInvalidate = false;
            }
        }
    }

    void moveToState(Fragment fragment)
    {
        moveToState(fragment, mCurState, 0, 0, false);
    }

    void moveToState(final Fragment f, int i, int j, int k, boolean flag)
    {
        int l;
label0:
        {
            boolean flag1 = DEBUG;
            if(f.mAdded)
            {
                l = i;
                if(!f.mDetached)
                    break label0;
            }
            l = i;
            if(i > 1)
                l = 1;
        }
        int j1 = l;
        if(f.mRemoving)
        {
            j1 = l;
            if(l > f.mState)
                if(f.mState == 0 && f.isInBackStack())
                    j1 = 1;
                else
                    j1 = f.mState;
        }
        i = j1;
        if(f.mDeferStart)
        {
            i = j1;
            if(f.mState < 4)
            {
                i = j1;
                if(j1 > 3)
                    i = 3;
            }
        }
        if(f.mState > i) goto _L2; else goto _L1
_L1:
        int i1;
        int k1;
        if(f.mFromLayout && f.mInLayout ^ true)
            return;
        if(f.getAnimatingAway() != null)
        {
            f.setAnimatingAway(null);
            moveToState(f, f.getStateAfterAnimating(), 0, 0, true);
        }
        k1 = i;
        k = i;
        i1 = i;
        j = i;
        f.mState;
        JVM INSTR tableswitch 0 4: default 212
    //                   0 287
    //                   1 710
    //                   2 1099
    //                   3 1118
    //                   4 1172;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        k1 = i;
_L16:
        if(f.mState != k1)
        {
            Log.w("FragmentManager", (new StringBuilder()).append("moveToState: Fragment state for ").append(f).append(" not updated inline; ").append("expected state ").append(k1).append(" found ").append(f.mState).toString());
            f.mState = k1;
        }
        return;
_L4:
        k1 = i;
        if(i > 0)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto CREATED: ").append(f).toString());
            k1 = i;
            if(f.mSavedFragmentState != null)
            {
                f.mSavedViewState = f.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                f.mTarget = getFragment(f.mSavedFragmentState, "android:target_state");
                if(f.mTarget != null)
                    f.mTargetRequestCode = f.mSavedFragmentState.getInt("android:target_req_state", 0);
                f.mUserVisibleHint = f.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                k1 = i;
                if(!f.mUserVisibleHint)
                {
                    f.mDeferStart = true;
                    k1 = i;
                    if(i > 3)
                        k1 = 3;
                }
            }
            f.mHost = mHost;
            f.mParentFragment = mParent;
            FragmentManagerImpl fragmentmanagerimpl;
            if(mParent != null)
                fragmentmanagerimpl = mParent.mChildFragmentManager;
            else
                fragmentmanagerimpl = mHost.getFragmentManagerImpl();
            f.mFragmentManager = fragmentmanagerimpl;
            if(f.mTarget != null)
            {
                if(mActive.get(f.mTarget.mIndex) != f.mTarget)
                    throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(f).append(" declared target fragment ").append(f.mTarget).append(" that does not belong to this FragmentManager!").toString());
                if(f.mTarget.mState < 1)
                    moveToState(f.mTarget, 1, 0, 0, true);
            }
            dispatchOnFragmentPreAttached(f, mHost.getContext(), false);
            f.mCalled = false;
            f.onAttach(mHost.getContext());
            if(!f.mCalled)
                throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(f).append(" did not call through to super.onAttach()").toString());
            ViewGroup viewgroup;
            if(f.mParentFragment == null)
                mHost.onAttachFragment(f);
            else
                f.mParentFragment.onAttachFragment(f);
            dispatchOnFragmentAttached(f, mHost.getContext(), false);
            if(!f.mIsCreated)
            {
                dispatchOnFragmentPreCreated(f, f.mSavedFragmentState, false);
                f.performCreate(f.mSavedFragmentState);
                dispatchOnFragmentCreated(f, f.mSavedFragmentState, false);
            } else
            {
                f.restoreChildFragmentState(f.mSavedFragmentState, true);
                f.mState = 1;
            }
            f.mRetaining = false;
        }
_L5:
        ensureInflatedFragmentView(f);
        k = k1;
        if(k1 > 1)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto ACTIVITY_CREATED: ").append(f).toString());
            if(!f.mFromLayout)
            {
                Object obj = null;
                if(f.mContainerId != 0)
                {
                    if(f.mContainerId == -1)
                        throwException(new IllegalArgumentException((new StringBuilder()).append("Cannot create fragment ").append(f).append(" for a container view with no id").toString()));
                    viewgroup = (ViewGroup)mContainer.onFindViewById(f.mContainerId);
                    obj = viewgroup;
                    if(viewgroup == null)
                    {
                        obj = viewgroup;
                        if(f.mRestored ^ true)
                        {
                            try
                            {
                                obj = f.getResources().getResourceName(f.mContainerId);
                            }
                            catch(android.content.res.Resources.NotFoundException notfoundexception)
                            {
                                notfoundexception = "unknown";
                            }
                            throwException(new IllegalArgumentException((new StringBuilder()).append("No view found for id 0x").append(Integer.toHexString(f.mContainerId)).append(" (").append(((String) (obj))).append(") for fragment ").append(f).toString()));
                            obj = viewgroup;
                        }
                    }
                }
                f.mContainer = ((ViewGroup) (obj));
                f.mView = f.performCreateView(f.performGetLayoutInflater(f.mSavedFragmentState), ((ViewGroup) (obj)), f.mSavedFragmentState);
                if(f.mView != null)
                {
                    f.mView.setSaveFromParentEnabled(false);
                    if(obj != null)
                        ((ViewGroup) (obj)).addView(f.mView);
                    if(f.mHidden)
                        f.mView.setVisibility(8);
                    f.onViewCreated(f.mView, f.mSavedFragmentState);
                    dispatchOnFragmentViewCreated(f, f.mView, f.mSavedFragmentState, false);
                    if(f.mView.getVisibility() == 0)
                    {
                        if(f.mContainer != null)
                            flag = true;
                        else
                            flag = false;
                    } else
                    {
                        flag = false;
                    }
                    f.mIsNewlyAdded = flag;
                }
            }
            f.performActivityCreated(f.mSavedFragmentState);
            dispatchOnFragmentActivityCreated(f, f.mSavedFragmentState, false);
            if(f.mView != null)
                f.restoreViewState(f.mSavedFragmentState);
            f.mSavedFragmentState = null;
            k = k1;
        }
_L6:
        i1 = k;
        if(k > 2)
        {
            f.mState = 3;
            i1 = k;
        }
_L7:
        j = i1;
        if(i1 > 3)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto STARTED: ").append(f).toString());
            f.performStart();
            dispatchOnFragmentStarted(f, false);
            j = i1;
        }
_L8:
        k1 = j;
        if(j > 4)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("moveto RESUMED: ").append(f).toString());
            f.performResume();
            dispatchOnFragmentResumed(f, false);
            f.mSavedFragmentState = null;
            f.mSavedViewState = null;
            k1 = j;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        k1 = i;
        if(f.mState <= i)
            continue; /* Loop/switch isn't completed */
        f.mState;
        JVM INSTR tableswitch 1 5: default 1336
    //                   1 1342
    //                   2 1492
    //                   3 1492
    //                   4 1445
    //                   5 1398;
           goto _L9 _L10 _L11 _L11 _L12 _L13
_L9:
        k1 = i;
        break; /* Loop/switch isn't completed */
_L10:
        k1 = i;
        if(i >= 1)
            break; /* Loop/switch isn't completed */
        if(mDestroyed && f.getAnimatingAway() != null)
        {
            Animator animator = f.getAnimatingAway();
            f.setAnimatingAway(null);
            animator.cancel();
        }
        if(f.getAnimatingAway() != null)
        {
            f.setStateAfterAnimating(i);
            k1 = 1;
            break; /* Loop/switch isn't completed */
        }
        break; /* Loop/switch isn't completed */
_L13:
        if(i < 5)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom RESUMED: ").append(f).toString());
            f.performPause();
            dispatchOnFragmentPaused(f, false);
        }
_L12:
        if(i < 4)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom STARTED: ").append(f).toString());
            f.performStop();
            dispatchOnFragmentStopped(f, false);
        }
_L11:
        if(i < 2)
        {
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("movefrom ACTIVITY_CREATED: ").append(f).toString());
            if(f.mView != null && mHost.onShouldSaveFragmentState(f) && f.mSavedViewState == null)
                saveFragmentViewState(f);
            f.performDestroyView();
            dispatchOnFragmentViewDestroyed(f, false);
            if(f.mView != null && f.mContainer != null)
            {
                if(getTargetSdk() >= 26)
                {
                    f.mView.clearAnimation();
                    f.mContainer.endViewTransition(f.mView);
                }
                Object obj1 = null;
                Animator animator1 = obj1;
                if(mCurState > 0)
                {
                    animator1 = obj1;
                    if(mDestroyed ^ true)
                    {
                        animator1 = obj1;
                        if(f.mView.getVisibility() == 0)
                        {
                            animator1 = obj1;
                            if(f.mView.getTransitionAlpha() > 0.0F)
                                animator1 = loadAnimator(f, j, false, k);
                        }
                    }
                }
                f.mView.setTransitionAlpha(1.0F);
                if(animator1 != null)
                {
                    final ViewGroup container = f.mContainer;
                    final View view = f.mView;
                    container.startViewTransition(view);
                    f.setAnimatingAway(animator1);
                    f.setStateAfterAnimating(i);
                    animator1.addListener(new AnimatorListenerAdapter() {

                        public void onAnimationEnd(Animator animator2)
                        {
                            container.endViewTransition(view);
                            animator2 = f.getAnimatingAway();
                            f.setAnimatingAway(null);
                            if(container.indexOfChild(view) == -1 && animator2 != null)
                                moveToState(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                        }

                        final FragmentManagerImpl this$0;
                        final ViewGroup val$container;
                        final Fragment val$f;
                        final Fragment val$fragment;
                        final View val$view;

            
            {
                this$0 = FragmentManagerImpl.this;
                container = viewgroup;
                view = view1;
                f = fragment1;
                fragment = fragment2;
                super();
            }
                    }
);
                    animator1.setTarget(f.mView);
                    setHWLayerAnimListenerIfAlpha(f.mView, animator1);
                    animator1.start();
                }
                f.mContainer.removeView(f.mView);
            }
            f.mContainer = null;
            f.mView = null;
            f.mInLayout = false;
        }
        if(true) goto _L10; else goto _L14
_L14:
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("movefrom CREATED: ").append(f).toString());
        if(!f.mRetaining)
        {
            f.performDestroy();
            dispatchOnFragmentDestroyed(f, false);
        } else
        {
            f.mState = 0;
        }
        f.performDetach();
        dispatchOnFragmentDetached(f, false);
        k1 = i;
        if(!flag)
            if(!f.mRetaining)
            {
                makeInactive(f);
                k1 = i;
            } else
            {
                f.mHost = null;
                f.mParentFragment = null;
                f.mFragmentManager = null;
                k1 = i;
            }
        if(true) goto _L16; else goto _L15
_L15:
    }

    public void noteStateNotSaved()
    {
        mSavedNonConfig = null;
        mStateSaved = false;
        int i = mAdded.size();
        for(int j = 0; j < i; j++)
        {
            Fragment fragment = (Fragment)mAdded.get(j);
            if(fragment != null)
                fragment.noteStateNotSaved();
        }

    }

    public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        if(!"fragment".equals(s))
            return null;
        s = attributeset.getAttributeValue(null, "class");
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Fragment);
        String s1 = s;
        if(s == null)
            s1 = typedarray.getString(0);
        int i = typedarray.getResourceId(1, -1);
        String s2 = typedarray.getString(2);
        typedarray.recycle();
        int j;
        if(view != null)
            j = view.getId();
        else
            j = 0;
        if(j == -1 && i == -1 && s2 == null)
            throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Must specify unique android:id, android:tag, or have a parent with").append(" an id for ").append(s1).toString());
        if(i != -1)
            view = findFragmentById(i);
        else
            view = null;
        s = view;
        if(view == null)
        {
            s = view;
            if(s2 != null)
                s = findFragmentByTag(s2);
        }
        view = s;
        if(s == null)
        {
            view = s;
            if(j != -1)
                view = findFragmentById(j);
        }
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("onCreateView: id=0x").append(Integer.toHexString(i)).append(" fname=").append(s1).append(" existing=").append(view).toString());
        if(view == null)
        {
            s = mContainer.instantiate(context, s1, null);
            s.mFromLayout = true;
            int k;
            if(i != 0)
                k = i;
            else
                k = j;
            s.mFragmentId = k;
            s.mContainerId = j;
            s.mTag = s2;
            s.mInLayout = true;
            s.mFragmentManager = this;
            s.mHost = mHost;
            s.onInflate(mHost.getContext(), attributeset, ((Fragment) (s)).mSavedFragmentState);
            addFragment(s, true);
        } else
        {
            if(((Fragment) (view)).mInLayout)
                throw new IllegalArgumentException((new StringBuilder()).append(attributeset.getPositionDescription()).append(": Duplicate id 0x").append(Integer.toHexString(i)).append(", tag ").append(s2).append(", or parent id 0x").append(Integer.toHexString(j)).append(" with another fragment for ").append(s1).toString());
            view.mInLayout = true;
            view.mHost = mHost;
            s = view;
            if(!((Fragment) (view)).mRetaining)
            {
                view.onInflate(mHost.getContext(), attributeset, ((Fragment) (view)).mSavedFragmentState);
                s = view;
            }
        }
        if(mCurState < 1 && ((Fragment) (s)).mFromLayout)
            moveToState(s, 1, 0, 0, false);
        else
            moveToState(s);
        if(((Fragment) (s)).mView == null)
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(s1).append(" did not create a view.").toString());
        if(i != 0)
            ((Fragment) (s)).mView.setId(i);
        if(((Fragment) (s)).mView.getTag() == null)
            ((Fragment) (s)).mView.setTag(s2);
        return ((Fragment) (s)).mView;
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        return null;
    }

    public void performPendingDeferredStart(Fragment fragment)
    {
        if(fragment.mDeferStart)
        {
            if(mExecutingActions)
            {
                mHavePendingDeferredStart = true;
                return;
            }
            fragment.mDeferStart = false;
            moveToState(fragment, mCurState, 0, 0, false);
        }
    }

    public void popBackStack()
    {
        enqueueAction(new PopBackStackState(null, -1, 0), false);
    }

    public void popBackStack(int i, int j)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(i).toString());
        } else
        {
            enqueueAction(new PopBackStackState(null, i, j), false);
            return;
        }
    }

    public void popBackStack(String s, int i)
    {
        enqueueAction(new PopBackStackState(s, -1, i), false);
    }

    public boolean popBackStackImmediate()
    {
        checkStateLoss();
        return popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackImmediate(int i, int j)
    {
        checkStateLoss();
        if(i < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad id: ").append(i).toString());
        else
            return popBackStackImmediate(null, i, j);
    }

    public boolean popBackStackImmediate(String s, int i)
    {
        checkStateLoss();
        return popBackStackImmediate(s, -1, i);
    }

    boolean popBackStackState(ArrayList arraylist, ArrayList arraylist1, String s, int i, int j)
    {
        if(mBackStack == null)
            return false;
        if(s != null || i >= 0 || (j & 1) != 0) goto _L2; else goto _L1
_L1:
        i = mBackStack.size() - 1;
        if(i < 0)
            return false;
        arraylist.add((BackStackRecord)mBackStack.remove(i));
        arraylist1.add(Boolean.valueOf(true));
_L4:
        return true;
_L2:
        int k;
label0:
        {
            k = -1;
            if(s == null && i < 0)
                break label0;
            int l = mBackStack.size() - 1;
label1:
            do
            {
                BackStackRecord backstackrecord;
                if(l >= 0)
                {
                    backstackrecord = (BackStackRecord)mBackStack.get(l);
                    break MISSING_BLOCK_LABEL_113;
                }
                do
                {
                    if(l < 0)
                        return false;
                    break label1;
                } while(s != null && s.equals(backstackrecord.getName()) || i >= 0 && i == backstackrecord.mIndex);
                l--;
            } while(true);
            k = l;
            if((j & 1) != 0)
            {
                j = l - 1;
                do
                {
                    k = j;
                    if(j < 0)
                        break;
                    BackStackRecord backstackrecord1 = (BackStackRecord)mBackStack.get(j);
                    if(s == null || !s.equals(backstackrecord1.getName()))
                    {
                        k = j;
                        if(i < 0)
                            break;
                        k = j;
                        if(i != backstackrecord1.mIndex)
                            break;
                    }
                    j--;
                } while(true);
            }
        }
        if(k == mBackStack.size() - 1)
            return false;
        i = mBackStack.size() - 1;
        while(i > k) 
        {
            arraylist.add((BackStackRecord)mBackStack.remove(i));
            arraylist1.add(Boolean.valueOf(true));
            i--;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void putFragment(Bundle bundle, String s, Fragment fragment)
    {
        if(fragment.mIndex < 0)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        bundle.putInt(s, fragment.mIndex);
    }

    public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentlifecyclecallbacks, boolean flag)
    {
        mLifecycleCallbacks.add(new Pair(fragmentlifecyclecallbacks, Boolean.valueOf(flag)));
    }

    public void removeFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("remove: ").append(fragment).append(" nesting=").append(fragment.mBackStackNesting).toString());
        boolean flag = fragment.isInBackStack();
        if(fragment.mDetached && !(flag ^ true))
            break MISSING_BLOCK_LABEL_110;
        ArrayList arraylist = mAdded;
        arraylist;
        JVM INSTR monitorenter ;
        mAdded.remove(fragment);
        arraylist;
        JVM INSTR monitorexit ;
        if(fragment.mHasMenu && fragment.mMenuVisible)
            mNeedMenuInvalidate = true;
        fragment.mAdded = false;
        fragment.mRemoving = true;
        return;
        fragment;
        throw fragment;
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener onbackstackchangedlistener)
    {
        if(mBackStackChangeListeners != null)
            mBackStackChangeListeners.remove(onbackstackchangedlistener);
    }

    void reportBackStackChanged()
    {
        if(mBackStackChangeListeners != null)
        {
            for(int i = 0; i < mBackStackChangeListeners.size(); i++)
                ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();

        }
    }

    void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        FragmentManagerState fragmentmanagerstate;
        if(parcelable == null)
            return;
        fragmentmanagerstate = (FragmentManagerState)parcelable;
        if(fragmentmanagerstate.mActive == null)
            return;
        parcelable = null;
        if(fragmentmanagernonconfig != null)
        {
            List list = fragmentmanagernonconfig.getFragments();
            List list1 = fragmentmanagernonconfig.getChildNonConfigs();
            int i;
            int j1;
            if(list != null)
                i = list.size();
            else
                i = 0;
            j1 = 0;
            do
            {
                parcelable = list1;
                if(j1 >= i)
                    break;
                Fragment fragment = (Fragment)list.get(j1);
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: re-attaching retained ").append(fragment).toString());
                int l1;
                for(l1 = 0; l1 < fragmentmanagerstate.mActive.length && fragmentmanagerstate.mActive[l1].mIndex != fragment.mIndex; l1++);
                if(l1 == fragmentmanagerstate.mActive.length)
                    throwException(new IllegalStateException((new StringBuilder()).append("Could not find active fragment with index ").append(fragment.mIndex).toString()));
                parcelable = fragmentmanagerstate.mActive[l1];
                parcelable.mInstance = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                fragment.mTarget = null;
                if(((FragmentState) (parcelable)).mSavedFragmentState != null)
                {
                    ((FragmentState) (parcelable)).mSavedFragmentState.setClassLoader(mHost.getContext().getClassLoader());
                    fragment.mSavedViewState = ((FragmentState) (parcelable)).mSavedFragmentState.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = ((FragmentState) (parcelable)).mSavedFragmentState;
                }
                j1++;
            } while(true);
        }
        mActive = new SparseArray(fragmentmanagerstate.mActive.length);
        for(int j = 0; j < fragmentmanagerstate.mActive.length; j++)
        {
            FragmentState fragmentstate = fragmentmanagerstate.mActive[j];
            if(fragmentstate == null)
                continue;
            Object obj = null;
            Object obj1 = obj;
            if(parcelable != null)
            {
                obj1 = obj;
                if(j < parcelable.size())
                    obj1 = (FragmentManagerNonConfig)parcelable.get(j);
            }
            obj1 = fragmentstate.instantiate(mHost, mContainer, mParent, ((FragmentManagerNonConfig) (obj1)));
            if(DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: active #").append(j).append(": ").append(obj1).toString());
            mActive.put(((Fragment) (obj1)).mIndex, obj1);
            fragmentstate.mInstance = null;
        }

        if(fragmentmanagernonconfig != null)
        {
            fragmentmanagernonconfig = fragmentmanagernonconfig.getFragments();
            int k;
            int k1;
            if(fragmentmanagernonconfig != null)
                k = fragmentmanagernonconfig.size();
            else
                k = 0;
            for(k1 = 0; k1 < k; k1++)
            {
                parcelable = (Fragment)fragmentmanagernonconfig.get(k1);
                if(((Fragment) (parcelable)).mTargetIndex < 0)
                    continue;
                parcelable.mTarget = (Fragment)mActive.get(((Fragment) (parcelable)).mTargetIndex);
                if(((Fragment) (parcelable)).mTarget == null)
                {
                    Log.w("FragmentManager", (new StringBuilder()).append("Re-attaching retained fragment ").append(parcelable).append(" target no longer exists: ").append(((Fragment) (parcelable)).mTargetIndex).toString());
                    parcelable.mTarget = null;
                }
            }

        }
        mAdded.clear();
        if(fragmentmanagerstate.mAdded == null) goto _L2; else goto _L1
_L1:
        int l = 0;
_L3:
        if(l >= fragmentmanagerstate.mAdded.length)
            break; /* Loop/switch isn't completed */
        fragmentmanagernonconfig = (Fragment)mActive.get(fragmentmanagerstate.mAdded[l]);
        if(fragmentmanagernonconfig == null)
            throwException(new IllegalStateException((new StringBuilder()).append("No instantiated fragment for index #").append(fragmentmanagerstate.mAdded[l]).toString()));
        fragmentmanagernonconfig.mAdded = true;
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: added #").append(l).append(": ").append(fragmentmanagernonconfig).toString());
        if(mAdded.contains(fragmentmanagernonconfig))
            throw new IllegalStateException("Already added!");
        parcelable = mAdded;
        parcelable;
        JVM INSTR monitorenter ;
        mAdded.add(fragmentmanagernonconfig);
        parcelable;
        JVM INSTR monitorexit ;
        l++;
        if(true) goto _L3; else goto _L2
        fragmentmanagernonconfig;
        throw fragmentmanagernonconfig;
_L2:
        if(fragmentmanagerstate.mBackStack != null)
        {
            mBackStack = new ArrayList(fragmentmanagerstate.mBackStack.length);
            for(int i1 = 0; i1 < fragmentmanagerstate.mBackStack.length; i1++)
            {
                parcelable = fragmentmanagerstate.mBackStack[i1].instantiate(this);
                if(DEBUG)
                {
                    Log.v("FragmentManager", (new StringBuilder()).append("restoreAllState: back stack #").append(i1).append(" (index ").append(((BackStackRecord) (parcelable)).mIndex).append("): ").append(parcelable).toString());
                    fragmentmanagernonconfig = new FastPrintWriter(new LogWriter(2, "FragmentManager"), false, 1024);
                    parcelable.dump("  ", fragmentmanagernonconfig, false);
                    fragmentmanagernonconfig.flush();
                }
                mBackStack.add(parcelable);
                if(((BackStackRecord) (parcelable)).mIndex >= 0)
                    setBackStackIndex(((BackStackRecord) (parcelable)).mIndex, parcelable);
            }

        } else
        {
            mBackStack = null;
        }
        if(fragmentmanagerstate.mPrimaryNavActiveIndex >= 0)
            mPrimaryNav = (Fragment)mActive.get(fragmentmanagerstate.mPrimaryNavActiveIndex);
        mNextFragmentIndex = fragmentmanagerstate.mNextFragmentIndex;
        return;
    }

    FragmentManagerNonConfig retainNonConfig()
    {
        setRetaining(mSavedNonConfig);
        return mSavedNonConfig;
    }

    Parcelable saveAllState()
    {
        forcePostponedTransactions();
        endAnimatingAwayFragments();
        execPendingActions();
        mStateSaved = true;
        mSavedNonConfig = null;
        if(mActive == null || mActive.size() <= 0)
            return null;
        int i = mActive.size();
        FragmentState afragmentstate[] = new FragmentState[i];
        boolean flag = false;
        int l = 0;
        while(l < i) 
        {
            Fragment fragment = (Fragment)mActive.valueAt(l);
            if(fragment == null)
                continue;
            if(fragment.mIndex < 0)
                throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(fragment).append(" has cleared index: ").append(fragment.mIndex).toString()));
            boolean flag1 = true;
            FragmentState fragmentstate = new FragmentState(fragment);
            afragmentstate[l] = fragmentstate;
            if(fragment.mState > 0 && fragmentstate.mSavedFragmentState == null)
            {
                fragmentstate.mSavedFragmentState = saveFragmentBasicState(fragment);
                if(fragment.mTarget != null)
                {
                    if(fragment.mTarget.mIndex < 0)
                        throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: ").append(fragment).append(" has target not in fragment manager: ").append(fragment.mTarget).toString()));
                    if(fragmentstate.mSavedFragmentState == null)
                        fragmentstate.mSavedFragmentState = new Bundle();
                    putFragment(fragmentstate.mSavedFragmentState, "android:target_state", fragment.mTarget);
                    if(fragment.mTargetRequestCode != 0)
                        fragmentstate.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode);
                }
            } else
            {
                fragmentstate.mSavedFragmentState = fragment.mSavedFragmentState;
            }
            flag = flag1;
            if(DEBUG)
            {
                Log.v("FragmentManager", (new StringBuilder()).append("Saved state of ").append(fragment).append(": ").append(fragmentstate.mSavedFragmentState).toString());
                flag = flag1;
            }
            l++;
        }
        if(!flag)
        {
            if(DEBUG)
                Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
        int ai1[] = null;
        FragmentManagerState fragmentmanagerstate = null;
        int j = mAdded.size();
        if(j > 0)
        {
            int ai[] = new int[j];
            int i1 = 0;
            do
            {
                ai1 = ai;
                if(i1 >= j)
                    break;
                ai[i1] = ((Fragment)mAdded.get(i1)).mIndex;
                if(ai[i1] < 0)
                    throwException(new IllegalStateException((new StringBuilder()).append("Failure saving state: active ").append(mAdded.get(i1)).append(" has cleared index: ").append(ai[i1]).toString()));
                if(DEBUG)
                    Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding fragment #").append(i1).append(": ").append(mAdded.get(i1)).toString());
                i1++;
            } while(true);
        }
        BackStackState abackstackstate[] = fragmentmanagerstate;
        if(mBackStack != null)
        {
            int k = mBackStack.size();
            abackstackstate = fragmentmanagerstate;
            if(k > 0)
            {
                BackStackState abackstackstate1[] = new BackStackState[k];
                int j1 = 0;
                do
                {
                    abackstackstate = abackstackstate1;
                    if(j1 >= k)
                        break;
                    abackstackstate1[j1] = new BackStackState(this, (BackStackRecord)mBackStack.get(j1));
                    if(DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("saveAllState: adding back stack #").append(j1).append(": ").append(mBackStack.get(j1)).toString());
                    j1++;
                } while(true);
            }
        }
        abackstackstate1 = new FragmentManagerState();
        abackstackstate1.mActive = afragmentstate;
        abackstackstate1.mAdded = ai1;
        abackstackstate1.mBackStack = abackstackstate;
        abackstackstate1.mNextFragmentIndex = mNextFragmentIndex;
        if(mPrimaryNav != null)
            abackstackstate1.mPrimaryNavActiveIndex = mPrimaryNav.mIndex;
        saveNonConfig();
        return abackstackstate1;
    }

    Bundle saveFragmentBasicState(Fragment fragment)
    {
        Bundle bundle = null;
        if(mStateBundle == null)
            mStateBundle = new Bundle();
        fragment.performSaveInstanceState(mStateBundle);
        dispatchOnFragmentSaveInstanceState(fragment, mStateBundle, false);
        if(!mStateBundle.isEmpty())
        {
            bundle = mStateBundle;
            mStateBundle = null;
        }
        if(fragment.mView != null)
            saveFragmentViewState(fragment);
        Bundle bundle1 = bundle;
        if(fragment.mSavedViewState != null)
        {
            bundle1 = bundle;
            if(bundle == null)
                bundle1 = new Bundle();
            bundle1.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        bundle = bundle1;
        if(!fragment.mUserVisibleHint)
        {
            bundle = bundle1;
            if(bundle1 == null)
                bundle = new Bundle();
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    public Fragment.SavedState saveFragmentInstanceState(Fragment fragment)
    {
        Object obj = null;
        if(fragment.mIndex < 0)
            throwException(new IllegalStateException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not currently in the FragmentManager").toString()));
        if(fragment.mState > 0)
        {
            Bundle bundle = saveFragmentBasicState(fragment);
            fragment = obj;
            if(bundle != null)
                fragment = new Fragment.SavedState(bundle);
            return fragment;
        } else
        {
            return null;
        }
    }

    void saveFragmentViewState(Fragment fragment)
    {
        if(fragment.mView == null)
            return;
        if(mStateArray == null)
            mStateArray = new SparseArray();
        else
            mStateArray.clear();
        fragment.mView.saveHierarchyState(mStateArray);
        if(mStateArray.size() > 0)
        {
            fragment.mSavedViewState = mStateArray;
            mStateArray = null;
        }
    }

    void saveNonConfig()
    {
        ArrayList arraylist = null;
        ArrayList arraylist1 = null;
        ArrayList arraylist2 = null;
        ArrayList arraylist3 = null;
        if(mActive != null)
        {
            int i = 0;
            do
            {
                arraylist2 = arraylist3;
                arraylist = arraylist1;
                if(i >= mActive.size())
                    break;
                Object obj = (Fragment)mActive.valueAt(i);
                ArrayList arraylist4 = arraylist3;
                arraylist = arraylist1;
                if(obj != null)
                {
                    arraylist2 = arraylist1;
                    if(((Fragment) (obj)).mRetainInstance)
                    {
                        arraylist = arraylist1;
                        if(arraylist1 == null)
                            arraylist = new ArrayList();
                        arraylist.add(obj);
                        int j;
                        if(((Fragment) (obj)).mTarget != null)
                            j = ((Fragment) (obj)).mTarget.mIndex;
                        else
                            j = -1;
                        obj.mTargetIndex = j;
                        arraylist2 = arraylist;
                        if(DEBUG)
                        {
                            Log.v("FragmentManager", (new StringBuilder()).append("retainNonConfig: keeping retained ").append(obj).toString());
                            arraylist2 = arraylist;
                        }
                    }
                    if(((Fragment) (obj)).mChildFragmentManager != null)
                    {
                        ((Fragment) (obj)).mChildFragmentManager.saveNonConfig();
                        obj = ((Fragment) (obj)).mChildFragmentManager.mSavedNonConfig;
                    } else
                    {
                        obj = ((Fragment) (obj)).mChildNonConfig;
                    }
                    arraylist1 = arraylist3;
                    if(arraylist3 == null)
                    {
                        arraylist1 = arraylist3;
                        if(obj != null)
                        {
                            arraylist3 = new ArrayList(mActive.size());
                            j = 0;
                            do
                            {
                                arraylist1 = arraylist3;
                                if(j >= i)
                                    break;
                                arraylist3.add(null);
                                j++;
                            } while(true);
                        }
                    }
                    arraylist4 = arraylist1;
                    arraylist = arraylist2;
                    if(arraylist1 != null)
                    {
                        arraylist1.add(obj);
                        arraylist = arraylist2;
                        arraylist4 = arraylist1;
                    }
                }
                i++;
                arraylist3 = arraylist4;
                arraylist1 = arraylist;
            } while(true);
        }
        if(arraylist == null && arraylist2 == null)
            mSavedNonConfig = null;
        else
            mSavedNonConfig = new FragmentManagerNonConfig(arraylist, arraylist2);
    }

    public void setBackStackIndex(int i, BackStackRecord backstackrecord)
    {
        this;
        JVM INSTR monitorenter ;
        int j;
        if(mBackStackIndices == null)
        {
            ArrayList arraylist = JVM INSTR new #117 <Class ArrayList>;
            arraylist.ArrayList();
            mBackStackIndices = arraylist;
        }
        j = mBackStackIndices.size();
        int k = j;
        if(i >= j) goto _L2; else goto _L1
_L1:
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #215 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("FragmentManager", stringbuilder.append("Setting back stack index ").append(i).append(" to ").append(backstackrecord).toString());
        }
        mBackStackIndices.set(i, backstackrecord);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(k >= i)
            break; /* Loop/switch isn't completed */
        mBackStackIndices.add(null);
        if(mAvailBackStackIndices == null)
        {
            ArrayList arraylist1 = JVM INSTR new #117 <Class ArrayList>;
            arraylist1.ArrayList();
            mAvailBackStackIndices = arraylist1;
        }
        if(DEBUG)
        {
            StringBuilder stringbuilder1 = JVM INSTR new #215 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.v("FragmentManager", stringbuilder1.append("Adding available back stack index ").append(k).toString());
        }
        mAvailBackStackIndices.add(Integer.valueOf(k));
        k++;
        if(true) goto _L2; else goto _L3
_L3:
        if(DEBUG)
        {
            StringBuilder stringbuilder2 = JVM INSTR new #215 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            Log.v("FragmentManager", stringbuilder2.append("Adding back stack index ").append(i).append(" with ").append(backstackrecord).toString());
        }
        mBackStackIndices.add(backstackrecord);
          goto _L4
        backstackrecord;
        throw backstackrecord;
    }

    public void setPrimaryNavigationFragment(Fragment fragment)
    {
        if(fragment != null && (mActive.get(fragment.mIndex) != fragment || fragment.mHost != null && fragment.getFragmentManager() != this))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Fragment ").append(fragment).append(" is not an active fragment of FragmentManager ").append(this).toString());
        } else
        {
            mPrimaryNav = fragment;
            return;
        }
    }

    public void showFragment(Fragment fragment)
    {
        if(DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("show: ").append(fragment).toString());
        if(fragment.mHidden)
        {
            fragment.mHidden = false;
            fragment.mHiddenChanged = fragment.mHiddenChanged ^ true;
        }
    }

    void startPendingDeferredFragments()
    {
        if(mActive == null)
            return;
        for(int i = 0; i < mActive.size(); i++)
        {
            Fragment fragment = (Fragment)mActive.valueAt(i);
            if(fragment != null)
                performPendingDeferredStart(fragment);
        }

    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("FragmentManager{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(" in ");
        if(mParent != null)
            DebugUtils.buildShortClassTag(mParent, stringbuilder);
        else
            DebugUtils.buildShortClassTag(mHost, stringbuilder);
        stringbuilder.append("}}");
        return stringbuilder.toString();
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks fragmentlifecyclecallbacks)
    {
        CopyOnWriteArrayList copyonwritearraylist = mLifecycleCallbacks;
        copyonwritearraylist;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = mLifecycleCallbacks.size();
_L2:
        if(i >= j)
            break MISSING_BLOCK_LABEL_51;
        if(((Pair)mLifecycleCallbacks.get(i)).first != fragmentlifecyclecallbacks)
            break MISSING_BLOCK_LABEL_54;
        mLifecycleCallbacks.remove(i);
        copyonwritearraylist;
        JVM INSTR monitorexit ;
        return;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        fragmentlifecyclecallbacks;
        throw fragmentlifecyclecallbacks;
    }

    static boolean DEBUG = false;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    SparseArray mActive;
    final ArrayList mAdded = new ArrayList();
    boolean mAllowOldReentrantBehavior;
    ArrayList mAvailBackStackIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    FragmentContainer mContainer;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    final CopyOnWriteArrayList mLifecycleCallbacks = new CopyOnWriteArrayList();
    boolean mNeedMenuInvalidate;
    int mNextFragmentIndex;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList mPendingActions;
    ArrayList mPostponedTransactions;
    Fragment mPrimaryNav;
    FragmentManagerNonConfig mSavedNonConfig;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    ArrayList mTmpAddedFragments;
    ArrayList mTmpIsPop;
    ArrayList mTmpRecords;

    static 
    {
        DEBUG = false;
    }
}
