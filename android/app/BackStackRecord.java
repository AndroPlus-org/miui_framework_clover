// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.util.Log;
import android.util.LogWriter;
import android.view.View;
import com.android.internal.util.FastPrintWriter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            FragmentTransaction, FragmentManagerImpl, Fragment, FragmentHostCallback

final class BackStackRecord extends FragmentTransaction
    implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator
{
    static final class Op
    {

        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        int popEnterAnim;
        int popExitAnim;

        Op()
        {
        }

        Op(int i, Fragment fragment1)
        {
            cmd = i;
            fragment = fragment1;
        }
    }


    public BackStackRecord(FragmentManagerImpl fragmentmanagerimpl)
    {
        boolean flag = true;
        super();
        mOps = new ArrayList();
        mAllowAddToBackStack = true;
        mIndex = -1;
        mManager = fragmentmanagerimpl;
        if(mManager.getTargetSdk() <= 25)
            flag = false;
        mReorderingAllowed = flag;
    }

    private void doAddOp(int i, Fragment fragment, String s, int j)
    {
        if(mManager.getTargetSdk() > 25)
        {
            Class class1 = fragment.getClass();
            int k = class1.getModifiers();
            if(class1.isAnonymousClass() || Modifier.isPublic(k) ^ true || class1.isMemberClass() && Modifier.isStatic(k) ^ true)
                throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(class1.getCanonicalName()).append(" must be a public static class to be  properly recreated from").append(" instance state.").toString());
        }
        fragment.mFragmentManager = mManager;
        if(s != null)
        {
            if(fragment.mTag != null && s.equals(fragment.mTag) ^ true)
                throw new IllegalStateException((new StringBuilder()).append("Can't change tag of fragment ").append(fragment).append(": was ").append(fragment.mTag).append(" now ").append(s).toString());
            fragment.mTag = s;
        }
        if(i != 0)
        {
            if(i == -1)
                throw new IllegalArgumentException((new StringBuilder()).append("Can't add fragment ").append(fragment).append(" with tag ").append(s).append(" to container view with no id").toString());
            if(fragment.mFragmentId != 0 && fragment.mFragmentId != i)
                throw new IllegalStateException((new StringBuilder()).append("Can't change container ID of fragment ").append(fragment).append(": was ").append(fragment.mFragmentId).append(" now ").append(i).toString());
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        addOp(new Op(j, fragment));
    }

    private static boolean isFragmentPostponed(Op op)
    {
        op = op.fragment;
        boolean flag;
        if(op != null && ((Fragment) (op)).mAdded && ((Fragment) (op)).mView != null && ((Fragment) (op)).mDetached ^ true && ((Fragment) (op)).mHidden ^ true)
            flag = op.isPostponed();
        else
            flag = false;
        return flag;
    }

    public FragmentTransaction add(int i, Fragment fragment)
    {
        doAddOp(i, fragment, null, 1);
        return this;
    }

    public FragmentTransaction add(int i, Fragment fragment, String s)
    {
        doAddOp(i, fragment, s, 1);
        return this;
    }

    public FragmentTransaction add(Fragment fragment, String s)
    {
        doAddOp(0, fragment, s, 1);
        return this;
    }

    void addOp(Op op)
    {
        mOps.add(op);
        op.enterAnim = mEnterAnim;
        op.exitAnim = mExitAnim;
        op.popEnterAnim = mPopEnterAnim;
        op.popExitAnim = mPopExitAnim;
    }

    public FragmentTransaction addSharedElement(View view, String s)
    {
        view = view.getTransitionName();
        if(view == null)
            throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
        if(mSharedElementSourceNames == null)
        {
            mSharedElementSourceNames = new ArrayList();
            mSharedElementTargetNames = new ArrayList();
        } else
        {
            if(mSharedElementTargetNames.contains(s))
                throw new IllegalArgumentException((new StringBuilder()).append("A shared element with the target name '").append(s).append("' has already been added to the transaction.").toString());
            if(mSharedElementSourceNames.contains(view))
                throw new IllegalArgumentException((new StringBuilder()).append("A shared element with the source name '").append(view).append(" has already been added to the transaction.").toString());
        }
        mSharedElementSourceNames.add(view);
        mSharedElementTargetNames.add(s);
        return this;
    }

    public FragmentTransaction addToBackStack(String s)
    {
        if(!mAllowAddToBackStack)
        {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        } else
        {
            mAddToBackStack = true;
            mName = s;
            return this;
        }
    }

    public FragmentTransaction attach(Fragment fragment)
    {
        addOp(new Op(7, fragment));
        return this;
    }

    void bumpBackStackNesting(int i)
    {
        if(!mAddToBackStack)
            return;
        if(FragmentManagerImpl.DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting in ").append(this).append(" by ").append(i).toString());
        int j = mOps.size();
        for(int k = 0; k < j; k++)
        {
            Op op = (Op)mOps.get(k);
            if(op.fragment == null)
                continue;
            Fragment fragment = op.fragment;
            fragment.mBackStackNesting = fragment.mBackStackNesting + i;
            if(FragmentManagerImpl.DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(op.fragment).append(" to ").append(op.fragment.mBackStackNesting).toString());
        }

    }

    public int commit()
    {
        return commitInternal(false);
    }

    public int commitAllowingStateLoss()
    {
        return commitInternal(true);
    }

    int commitInternal(boolean flag)
    {
        if(mCommitted)
            throw new IllegalStateException("commit already called");
        if(FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Commit: ").append(this).toString());
            FastPrintWriter fastprintwriter = new FastPrintWriter(new LogWriter(2, "FragmentManager"), false, 1024);
            dump("  ", null, fastprintwriter, null);
            fastprintwriter.flush();
        }
        mCommitted = true;
        if(mAddToBackStack)
            mIndex = mManager.allocBackStackIndex(this);
        else
            mIndex = -1;
        mManager.enqueueAction(this, flag);
        return mIndex;
    }

    public void commitNow()
    {
        disallowAddToBackStack();
        mManager.execSingleAction(this, false);
    }

    public void commitNowAllowingStateLoss()
    {
        disallowAddToBackStack();
        mManager.execSingleAction(this, true);
    }

    public FragmentTransaction detach(Fragment fragment)
    {
        addOp(new Op(6, fragment));
        return this;
    }

    public FragmentTransaction disallowAddToBackStack()
    {
        if(mAddToBackStack)
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else
        {
            mAllowAddToBackStack = false;
            return this;
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dump(s, printwriter, true);
    }

    void dump(String s, PrintWriter printwriter, boolean flag)
    {
        String s1;
        int i;
        int j;
        if(flag)
        {
            printwriter.print(s);
            printwriter.print("mName=");
            printwriter.print(mName);
            printwriter.print(" mIndex=");
            printwriter.print(mIndex);
            printwriter.print(" mCommitted=");
            printwriter.println(mCommitted);
            if(mTransition != 0)
            {
                printwriter.print(s);
                printwriter.print("mTransition=#");
                printwriter.print(Integer.toHexString(mTransition));
                printwriter.print(" mTransitionStyle=#");
                printwriter.println(Integer.toHexString(mTransitionStyle));
            }
            if(mEnterAnim != 0 || mExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mEnterAnim=#");
                printwriter.print(Integer.toHexString(mEnterAnim));
                printwriter.print(" mExitAnim=#");
                printwriter.println(Integer.toHexString(mExitAnim));
            }
            if(mPopEnterAnim != 0 || mPopExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mPopEnterAnim=#");
                printwriter.print(Integer.toHexString(mPopEnterAnim));
                printwriter.print(" mPopExitAnim=#");
                printwriter.println(Integer.toHexString(mPopExitAnim));
            }
            if(mBreadCrumbTitleRes != 0 || mBreadCrumbTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbTitleRes));
                printwriter.print(" mBreadCrumbTitleText=");
                printwriter.println(mBreadCrumbTitleText);
            }
            if(mBreadCrumbShortTitleRes != 0 || mBreadCrumbShortTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbShortTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbShortTitleRes));
                printwriter.print(" mBreadCrumbShortTitleText=");
                printwriter.println(mBreadCrumbShortTitleText);
            }
        }
        if(mOps.isEmpty())
            break MISSING_BLOCK_LABEL_735;
        printwriter.print(s);
        printwriter.println("Operations:");
        s1 = (new StringBuilder()).append(s).append("    ").toString();
        i = mOps.size();
        j = 0;
_L13:
        Op op;
        if(j >= i)
            break MISSING_BLOCK_LABEL_735;
        op = (Op)mOps.get(j);
        op.cmd;
        JVM INSTR tableswitch 0 9: default 452
    //                   0 655
    //                   1 663
    //                   2 671
    //                   3 679
    //                   4 687
    //                   5 695
    //                   6 703
    //                   7 711
    //                   8 719
    //                   9 727;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L11:
        break MISSING_BLOCK_LABEL_727;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        String s2 = (new StringBuilder()).append("cmd=").append(op.cmd).toString();
_L14:
        printwriter.print(s);
        printwriter.print("  Op #");
        printwriter.print(j);
        printwriter.print(": ");
        printwriter.print(s2);
        printwriter.print(" ");
        printwriter.println(op.fragment);
        if(flag)
        {
            if(op.enterAnim != 0 || op.exitAnim != 0)
            {
                printwriter.print(s1);
                printwriter.print("enterAnim=#");
                printwriter.print(Integer.toHexString(op.enterAnim));
                printwriter.print(" exitAnim=#");
                printwriter.println(Integer.toHexString(op.exitAnim));
            }
            if(op.popEnterAnim != 0 || op.popExitAnim != 0)
            {
                printwriter.print(s1);
                printwriter.print("popEnterAnim=#");
                printwriter.print(Integer.toHexString(op.popEnterAnim));
                printwriter.print(" popExitAnim=#");
                printwriter.println(Integer.toHexString(op.popExitAnim));
            }
        }
        j++;
        if(true) goto _L13; else goto _L12
_L12:
        s2 = "NULL";
          goto _L14
_L3:
        s2 = "ADD";
          goto _L14
_L4:
        s2 = "REPLACE";
          goto _L14
_L5:
        s2 = "REMOVE";
          goto _L14
_L6:
        s2 = "HIDE";
          goto _L14
_L7:
        s2 = "SHOW";
          goto _L14
_L8:
        s2 = "DETACH";
          goto _L14
_L9:
        s2 = "ATTACH";
          goto _L14
_L10:
        s2 = "SET_PRIMARY_NAV";
          goto _L14
        s2 = "UNSET_PRIMARY_NAV";
          goto _L14
    }

    void executeOps()
    {
        int i;
        int j;
        i = mOps.size();
        j = 0;
_L11:
        Op op;
        Fragment fragment;
        if(j >= i)
            break MISSING_BLOCK_LABEL_317;
        op = (Op)mOps.get(j);
        fragment = op.fragment;
        if(fragment != null)
            fragment.setNextTransition(mTransition, mTransitionStyle);
        op.cmd;
        JVM INSTR tableswitch 1 9: default 104
    //                   1 135
    //                   2 104
    //                   3 189
    //                   4 210
    //                   5 231
    //                   6 252
    //                   7 273
    //                   8 294
    //                   9 306;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_306;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        fragment.setNextAnim(op.enterAnim);
        mManager.addFragment(fragment, false);
_L12:
        if(!mReorderingAllowed && op.cmd != 1 && fragment != null)
            mManager.moveFragmentToExpectedState(fragment);
        j++;
        if(true) goto _L11; else goto _L10
_L10:
        fragment.setNextAnim(op.exitAnim);
        mManager.removeFragment(fragment);
          goto _L12
_L4:
        fragment.setNextAnim(op.exitAnim);
        mManager.hideFragment(fragment);
          goto _L12
_L5:
        fragment.setNextAnim(op.enterAnim);
        mManager.showFragment(fragment);
          goto _L12
_L6:
        fragment.setNextAnim(op.exitAnim);
        mManager.detachFragment(fragment);
          goto _L12
_L7:
        fragment.setNextAnim(op.enterAnim);
        mManager.attachFragment(fragment);
          goto _L12
_L8:
        mManager.setPrimaryNavigationFragment(fragment);
          goto _L12
        mManager.setPrimaryNavigationFragment(null);
          goto _L12
        if(!mReorderingAllowed)
            mManager.moveToState(mManager.mCurState, true);
        return;
    }

    void executePopOps(boolean flag)
    {
        int i = mOps.size() - 1;
_L11:
        Op op;
        Fragment fragment;
        if(i < 0)
            break MISSING_BLOCK_LABEL_321;
        op = (Op)mOps.get(i);
        fragment = op.fragment;
        if(fragment != null)
            fragment.setNextTransition(FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
        op.cmd;
        JVM INSTR tableswitch 1 9: default 108
    //                   1 139
    //                   2 108
    //                   3 192
    //                   4 214
    //                   5 235
    //                   6 256
    //                   7 277
    //                   8 298
    //                   9 309;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_309;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        fragment.setNextAnim(op.popExitAnim);
        mManager.removeFragment(fragment);
_L12:
        if(!mReorderingAllowed && op.cmd != 3 && fragment != null)
            mManager.moveFragmentToExpectedState(fragment);
        i--;
        if(true) goto _L11; else goto _L10
_L10:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.addFragment(fragment, false);
          goto _L12
_L4:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.showFragment(fragment);
          goto _L12
_L5:
        fragment.setNextAnim(op.popExitAnim);
        mManager.hideFragment(fragment);
          goto _L12
_L6:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.attachFragment(fragment);
          goto _L12
_L7:
        fragment.setNextAnim(op.popExitAnim);
        mManager.detachFragment(fragment);
          goto _L12
_L8:
        mManager.setPrimaryNavigationFragment(null);
          goto _L12
        mManager.setPrimaryNavigationFragment(fragment);
          goto _L12
        if(!mReorderingAllowed && flag)
            mManager.moveToState(mManager.mCurState, true);
        return;
    }

    Fragment expandOps(ArrayList arraylist, Fragment fragment)
    {
        int i;
        Fragment fragment1;
        i = 0;
        fragment1 = fragment;
_L8:
        Op op;
        int j;
        if(i >= mOps.size())
            break MISSING_BLOCK_LABEL_471;
        op = (Op)mOps.get(i);
        j = i;
        fragment = fragment1;
        op.cmd;
        JVM INSTR tableswitch 1 8: default 88
    //                   1 105
    //                   2 182
    //                   3 124
    //                   4 94
    //                   5 94
    //                   6 124
    //                   7 105
    //                   8 438;
           goto _L1 _L2 _L3 _L4 _L5 _L5 _L4 _L2 _L6
_L6:
        break MISSING_BLOCK_LABEL_438;
_L2:
        break; /* Loop/switch isn't completed */
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        fragment = fragment1;
        j = i;
_L9:
        i = j + 1;
        fragment1 = fragment;
        if(true) goto _L8; else goto _L7
_L7:
        arraylist.add(op.fragment);
        j = i;
        fragment = fragment1;
          goto _L9
_L4:
        arraylist.remove(op.fragment);
        j = i;
        fragment = fragment1;
        if(op.fragment == fragment1)
        {
            mOps.add(i, new Op(9, op.fragment));
            j = i + 1;
            fragment = null;
        }
          goto _L9
_L3:
        Fragment fragment2 = op.fragment;
        int k = fragment2.mContainerId;
        boolean flag = false;
        int l = arraylist.size() - 1;
        fragment = fragment1;
        j = i;
        i = l;
        while(i >= 0) 
        {
            Fragment fragment3 = (Fragment)arraylist.get(i);
            boolean flag1 = flag;
            int i1 = j;
            fragment1 = fragment;
            if(fragment3.mContainerId == k)
                if(fragment3 == fragment2)
                {
                    flag1 = true;
                    fragment1 = fragment;
                    i1 = j;
                } else
                {
                    i1 = j;
                    fragment1 = fragment;
                    if(fragment3 == fragment)
                    {
                        mOps.add(j, new Op(9, fragment3));
                        i1 = j + 1;
                        fragment1 = null;
                    }
                    fragment = new Op(3, fragment3);
                    fragment.enterAnim = op.enterAnim;
                    fragment.popEnterAnim = op.popEnterAnim;
                    fragment.exitAnim = op.exitAnim;
                    fragment.popExitAnim = op.popExitAnim;
                    mOps.add(i1, fragment);
                    arraylist.remove(fragment3);
                    i1++;
                    flag1 = flag;
                }
            i--;
            flag = flag1;
            j = i1;
            fragment = fragment1;
        }
        if(flag)
        {
            mOps.remove(j);
            j--;
        } else
        {
            op.cmd = 1;
            arraylist.add(fragment2);
        }
          goto _L9
        mOps.add(i, new Op(9, fragment1));
        j = i + 1;
        fragment = op.fragment;
          goto _L9
        return fragment1;
    }

    public boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
    {
        if(FragmentManagerImpl.DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Run: ").append(this).toString());
        arraylist.add(this);
        arraylist1.add(Boolean.valueOf(false));
        if(mAddToBackStack)
            mManager.addBackStackState(this);
        return true;
    }

    public CharSequence getBreadCrumbShortTitle()
    {
        if(mBreadCrumbShortTitleRes != 0 && mManager.mHost != null)
            return mManager.mHost.getContext().getText(mBreadCrumbShortTitleRes);
        else
            return mBreadCrumbShortTitleText;
    }

    public int getBreadCrumbShortTitleRes()
    {
        return mBreadCrumbShortTitleRes;
    }

    public CharSequence getBreadCrumbTitle()
    {
        if(mBreadCrumbTitleRes != 0 && mManager.mHost != null)
            return mManager.mHost.getContext().getText(mBreadCrumbTitleRes);
        else
            return mBreadCrumbTitleText;
    }

    public int getBreadCrumbTitleRes()
    {
        return mBreadCrumbTitleRes;
    }

    public int getId()
    {
        return mIndex;
    }

    public String getName()
    {
        return mName;
    }

    public int getTransition()
    {
        return mTransition;
    }

    public int getTransitionStyle()
    {
        return mTransitionStyle;
    }

    public FragmentTransaction hide(Fragment fragment)
    {
        addOp(new Op(4, fragment));
        return this;
    }

    boolean interactsWith(int i)
    {
        int j = mOps.size();
        int l;
        for(int k = 0; k < j; k++)
        {
            Op op = (Op)mOps.get(k);
            if(op.fragment != null)
                l = op.fragment.mContainerId;
            else
                l = 0;
            if(l != 0 && l == i)
                return true;
        }

        return false;
    }

    boolean interactsWith(ArrayList arraylist, int i, int j)
    {
        if(j == i)
            return false;
        int k = mOps.size();
        int l = -1;
        for(int i1 = 0; i1 < k;)
        {
            Op op = (Op)mOps.get(i1);
            int j1;
            int k1;
            if(op.fragment != null)
                j1 = op.fragment.mContainerId;
            else
                j1 = 0;
            k1 = l;
            if(j1 != 0)
            {
                k1 = l;
                if(j1 != l)
                {
                    l = j1;
                    int l1 = i;
                    do
                    {
                        k1 = l;
                        if(l1 >= j)
                            break;
                        BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(l1);
                        int i2 = backstackrecord.mOps.size();
                        int j2;
                        for(k1 = 0; k1 < i2; k1++)
                        {
                            Op op1 = (Op)backstackrecord.mOps.get(k1);
                            if(op1.fragment != null)
                                j2 = op1.fragment.mContainerId;
                            else
                                j2 = 0;
                            if(j2 == j1)
                                return true;
                        }

                        l1++;
                    } while(true);
                }
            }
            i1++;
            l = k1;
        }

        return false;
    }

    public boolean isAddToBackStackAllowed()
    {
        return mAllowAddToBackStack;
    }

    public boolean isEmpty()
    {
        return mOps.isEmpty();
    }

    boolean isPostponed()
    {
        for(int i = 0; i < mOps.size(); i++)
            if(isFragmentPostponed((Op)mOps.get(i)))
                return true;

        return false;
    }

    public FragmentTransaction remove(Fragment fragment)
    {
        addOp(new Op(3, fragment));
        return this;
    }

    public FragmentTransaction replace(int i, Fragment fragment)
    {
        return replace(i, fragment, null);
    }

    public FragmentTransaction replace(int i, Fragment fragment, String s)
    {
        if(i == 0)
        {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        } else
        {
            doAddOp(i, fragment, s, 2);
            return this;
        }
    }

    public FragmentTransaction runOnCommit(Runnable runnable)
    {
        if(runnable == null)
            throw new IllegalArgumentException("runnable cannot be null");
        disallowAddToBackStack();
        if(mCommitRunnables == null)
            mCommitRunnables = new ArrayList();
        mCommitRunnables.add(runnable);
        return this;
    }

    public void runOnCommitRunnables()
    {
        if(mCommitRunnables != null)
        {
            int i = 0;
            for(int j = mCommitRunnables.size(); i < j; i++)
                ((Runnable)mCommitRunnables.get(i)).run();

            mCommitRunnables = null;
        }
    }

    public FragmentTransaction setBreadCrumbShortTitle(int i)
    {
        mBreadCrumbShortTitleRes = i;
        mBreadCrumbShortTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charsequence)
    {
        mBreadCrumbShortTitleRes = 0;
        mBreadCrumbShortTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int i)
    {
        mBreadCrumbTitleRes = i;
        mBreadCrumbTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence charsequence)
    {
        mBreadCrumbTitleRes = 0;
        mBreadCrumbTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int i, int j)
    {
        return setCustomAnimations(i, j, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int i, int j, int k, int l)
    {
        mEnterAnim = i;
        mExitAnim = j;
        mPopEnterAnim = k;
        mPopExitAnim = l;
        return this;
    }

    void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener onstartentertransitionlistener)
    {
        for(int i = 0; i < mOps.size(); i++)
        {
            Op op = (Op)mOps.get(i);
            if(isFragmentPostponed(op))
                op.fragment.setOnStartEnterTransitionListener(onstartentertransitionlistener);
        }

    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment)
    {
        addOp(new Op(8, fragment));
        return this;
    }

    public FragmentTransaction setReorderingAllowed(boolean flag)
    {
        mReorderingAllowed = flag;
        return this;
    }

    public FragmentTransaction setTransition(int i)
    {
        mTransition = i;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int i)
    {
        mTransitionStyle = i;
        return this;
    }

    public FragmentTransaction show(Fragment fragment)
    {
        addOp(new Op(5, fragment));
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("BackStackEntry{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if(mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if(mName != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mName);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    void trackAddedFragmentsInPop(ArrayList arraylist)
    {
        int i = 0;
_L2:
        Op op;
        if(i >= mOps.size())
            break MISSING_BLOCK_LABEL_102;
        op = (Op)mOps.get(i);
        switch(op.cmd)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        default:
            break;

        case 1: // '\001'
        case 7: // '\007'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
        case 6: // '\006'
            break;
        }
        break MISSING_BLOCK_LABEL_90;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        arraylist.remove(op.fragment);
          goto _L3
        arraylist.add(op.fragment);
          goto _L3
    }

    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SET_PRIMARY_NAV = 8;
    static final int OP_SHOW = 5;
    static final int OP_UNSET_PRIMARY_NAV = 9;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList mCommitRunnables;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    ArrayList mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;
}
