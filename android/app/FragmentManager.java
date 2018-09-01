// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

// Referenced classes of package android.app:
//            FragmentManagerImpl, FragmentTransaction, Fragment

public abstract class FragmentManager
{
    public static interface BackStackEntry
    {

        public abstract CharSequence getBreadCrumbShortTitle();

        public abstract int getBreadCrumbShortTitleRes();

        public abstract CharSequence getBreadCrumbTitle();

        public abstract int getBreadCrumbTitleRes();

        public abstract int getId();

        public abstract String getName();
    }

    public static abstract class FragmentLifecycleCallbacks
    {

        public void onFragmentActivityCreated(FragmentManager fragmentmanager, Fragment fragment, Bundle bundle)
        {
        }

        public void onFragmentAttached(FragmentManager fragmentmanager, Fragment fragment, Context context)
        {
        }

        public void onFragmentCreated(FragmentManager fragmentmanager, Fragment fragment, Bundle bundle)
        {
        }

        public void onFragmentDestroyed(FragmentManager fragmentmanager, Fragment fragment)
        {
        }

        public void onFragmentDetached(FragmentManager fragmentmanager, Fragment fragment)
        {
        }

        public void onFragmentPaused(FragmentManager fragmentmanager, Fragment fragment)
        {
        }

        public void onFragmentPreAttached(FragmentManager fragmentmanager, Fragment fragment, Context context)
        {
        }

        public void onFragmentPreCreated(FragmentManager fragmentmanager, Fragment fragment, Bundle bundle)
        {
        }

        public void onFragmentResumed(FragmentManager fragmentmanager, Fragment fragment)
        {
        }

        public void onFragmentSaveInstanceState(FragmentManager fragmentmanager, Fragment fragment, Bundle bundle)
        {
        }

        public void onFragmentStarted(FragmentManager fragmentmanager, Fragment fragment)
        {
        }

        public void onFragmentStopped(FragmentManager fragmentmanager, Fragment fragment)
        {
        }

        public void onFragmentViewCreated(FragmentManager fragmentmanager, Fragment fragment, View view, Bundle bundle)
        {
        }

        public void onFragmentViewDestroyed(FragmentManager fragmentmanager, Fragment fragment)
        {
        }

        public FragmentLifecycleCallbacks()
        {
        }
    }

    public static interface OnBackStackChangedListener
    {

        public abstract void onBackStackChanged();
    }


    public FragmentManager()
    {
    }

    public static void enableDebugLogging(boolean flag)
    {
        FragmentManagerImpl.DEBUG = flag;
    }

    public abstract void addOnBackStackChangedListener(OnBackStackChangedListener onbackstackchangedlistener);

    public abstract FragmentTransaction beginTransaction();

    public abstract void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[]);

    public abstract boolean executePendingTransactions();

    public abstract Fragment findFragmentById(int i);

    public abstract Fragment findFragmentByTag(String s);

    public abstract BackStackEntry getBackStackEntryAt(int i);

    public abstract int getBackStackEntryCount();

    public abstract Fragment getFragment(Bundle bundle, String s);

    public abstract List getFragments();

    public abstract Fragment getPrimaryNavigationFragment();

    public void invalidateOptionsMenu()
    {
    }

    public abstract boolean isDestroyed();

    public abstract boolean isStateSaved();

    public FragmentTransaction openTransaction()
    {
        return beginTransaction();
    }

    public abstract void popBackStack();

    public abstract void popBackStack(int i, int j);

    public abstract void popBackStack(String s, int i);

    public abstract boolean popBackStackImmediate();

    public abstract boolean popBackStackImmediate(int i, int j);

    public abstract boolean popBackStackImmediate(String s, int i);

    public abstract void putFragment(Bundle bundle, String s, Fragment fragment);

    public abstract void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentlifecyclecallbacks, boolean flag);

    public abstract void removeOnBackStackChangedListener(OnBackStackChangedListener onbackstackchangedlistener);

    public abstract Fragment.SavedState saveFragmentInstanceState(Fragment fragment);

    public abstract void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentlifecyclecallbacks);

    public static final int POP_BACK_STACK_INCLUSIVE = 1;
}
