// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.animation.Animator;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.os.*;
import android.transition.*;
import android.util.*;
import android.view.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Referenced classes of package android.app:
//            LoaderManagerImpl, FragmentManagerImpl, FragmentHostCallback, SharedElementCallback, 
//            Activity, FragmentManagerNonConfig, FragmentManager, LoaderManager, 
//            FragmentContainer

public class Fragment
    implements ComponentCallbacks2, android.view.View.OnCreateContextMenuListener
{
    static class AnimationInfo
    {

        static Boolean _2D_get0(AnimationInfo animationinfo)
        {
            return animationinfo.mAllowEnterTransitionOverlap;
        }

        static Boolean _2D_get1(AnimationInfo animationinfo)
        {
            return animationinfo.mAllowReturnTransitionOverlap;
        }

        static Transition _2D_get2(AnimationInfo animationinfo)
        {
            return animationinfo.mEnterTransition;
        }

        static Transition _2D_get3(AnimationInfo animationinfo)
        {
            return animationinfo.mExitTransition;
        }

        static Transition _2D_get4(AnimationInfo animationinfo)
        {
            return animationinfo.mReenterTransition;
        }

        static Transition _2D_get5(AnimationInfo animationinfo)
        {
            return animationinfo.mReturnTransition;
        }

        static Transition _2D_get6(AnimationInfo animationinfo)
        {
            return animationinfo.mSharedElementEnterTransition;
        }

        static Transition _2D_get7(AnimationInfo animationinfo)
        {
            return animationinfo.mSharedElementReturnTransition;
        }

        static Boolean _2D_set0(AnimationInfo animationinfo, Boolean boolean1)
        {
            animationinfo.mAllowEnterTransitionOverlap = boolean1;
            return boolean1;
        }

        static Boolean _2D_set1(AnimationInfo animationinfo, Boolean boolean1)
        {
            animationinfo.mAllowReturnTransitionOverlap = boolean1;
            return boolean1;
        }

        static Transition _2D_set2(AnimationInfo animationinfo, Transition transition)
        {
            animationinfo.mEnterTransition = transition;
            return transition;
        }

        static Transition _2D_set3(AnimationInfo animationinfo, Transition transition)
        {
            animationinfo.mExitTransition = transition;
            return transition;
        }

        static Transition _2D_set4(AnimationInfo animationinfo, Transition transition)
        {
            animationinfo.mReenterTransition = transition;
            return transition;
        }

        static Transition _2D_set5(AnimationInfo animationinfo, Transition transition)
        {
            animationinfo.mReturnTransition = transition;
            return transition;
        }

        static Transition _2D_set6(AnimationInfo animationinfo, Transition transition)
        {
            animationinfo.mSharedElementEnterTransition = transition;
            return transition;
        }

        static Transition _2D_set7(AnimationInfo animationinfo, Transition transition)
        {
            animationinfo.mSharedElementReturnTransition = transition;
            return transition;
        }

        private Boolean mAllowEnterTransitionOverlap;
        private Boolean mAllowReturnTransitionOverlap;
        Animator mAnimatingAway;
        private Transition mEnterTransition;
        SharedElementCallback mEnterTransitionCallback;
        boolean mEnterTransitionPostponed;
        private Transition mExitTransition;
        SharedElementCallback mExitTransitionCallback;
        boolean mIsHideReplaced;
        int mNextAnim;
        int mNextTransition;
        int mNextTransitionStyle;
        private Transition mReenterTransition;
        private Transition mReturnTransition;
        private Transition mSharedElementEnterTransition;
        private Transition mSharedElementReturnTransition;
        OnStartEnterTransitionListener mStartEnterTransitionListener;
        int mStateAfterAnimating;

        AnimationInfo()
        {
            mEnterTransition = null;
            mReturnTransition = Fragment._2D_get0();
            mExitTransition = null;
            mReenterTransition = Fragment._2D_get0();
            mSharedElementEnterTransition = null;
            mSharedElementReturnTransition = Fragment._2D_get0();
            mEnterTransitionCallback = SharedElementCallback.NULL_CALLBACK;
            mExitTransitionCallback = SharedElementCallback.NULL_CALLBACK;
        }
    }

    public static class InstantiationException extends AndroidRuntimeException
    {

        public InstantiationException(String s, Exception exception)
        {
            super(s, exception);
        }
    }

    static interface OnStartEnterTransitionListener
    {

        public abstract void onStartEnterTransition();

        public abstract void startListening();
    }

    public static class SavedState
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeBundle(mState);
        }

        public static final android.os.Parcelable.ClassLoaderCreator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
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
        final Bundle mState;


        SavedState(Bundle bundle)
        {
            mState = bundle;
        }

        SavedState(Parcel parcel, ClassLoader classloader)
        {
            mState = parcel.readBundle();
            if(classloader != null && mState != null)
                mState.setClassLoader(classloader);
        }
    }


    static Transition _2D_get0()
    {
        return USE_DEFAULT_TRANSITION;
    }

    public Fragment()
    {
        mState = 0;
        mIndex = -1;
        mTargetIndex = -1;
        mMenuVisible = true;
        mUserVisibleHint = true;
    }

    private void callStartTransitionListener()
    {
        OnStartEnterTransitionListener onstartentertransitionlistener;
        if(mAnimationInfo == null)
        {
            onstartentertransitionlistener = null;
        } else
        {
            mAnimationInfo.mEnterTransitionPostponed = false;
            onstartentertransitionlistener = mAnimationInfo.mStartEnterTransitionListener;
            mAnimationInfo.mStartEnterTransitionListener = null;
        }
        if(onstartentertransitionlistener != null)
            onstartentertransitionlistener.onStartEnterTransition();
    }

    private AnimationInfo ensureAnimationInfo()
    {
        if(mAnimationInfo == null)
            mAnimationInfo = new AnimationInfo();
        return mAnimationInfo;
    }

    public static Fragment instantiate(Context context, String s)
    {
        return instantiate(context, s, null);
    }

    public static Fragment instantiate(Context context, String s, Bundle bundle)
    {
        Class class1;
        Object obj;
        try
        {
            class1 = (Class)sClassMap.get(s);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": make sure class name exists, is public, and has an").append(" empty constructor that is public").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": could not find Fragment constructor").toString(), context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw new InstantiationException((new StringBuilder()).append("Unable to instantiate fragment ").append(s).append(": calling Fragment constructor caused an exception").toString(), context);
        }
        obj = class1;
        if(class1 != null)
            break MISSING_BLOCK_LABEL_137;
        obj = context.getClassLoader().loadClass(s);
        if(!android/app/Fragment.isAssignableFrom(((Class) (obj))))
        {
            context = JVM INSTR new #15  <Class Fragment$InstantiationException>;
            bundle = JVM INSTR new #184 <Class StringBuilder>;
            bundle.StringBuilder();
            bundle = bundle.append("Trying to instantiate a class ").append(s).append(" that is not a Fragment").toString();
            obj = JVM INSTR new #199 <Class ClassCastException>;
            ((ClassCastException) (obj)).ClassCastException();
            context.InstantiationException(bundle, ((Exception) (obj)));
            throw context;
        }
        sClassMap.put(s, obj);
        context = (Fragment)((Class) (obj)).getConstructor(new Class[0]).newInstance(new Object[0]);
        if(bundle == null)
            break MISSING_BLOCK_LABEL_177;
        bundle.setClassLoader(context.getClass().getClassLoader());
        context.setArguments(bundle);
        return context;
    }

    private static Transition loadTransition(Context context, TypedArray typedarray, Transition transition, Transition transition1, int i)
    {
        if(transition != transition1)
            return transition;
        i = typedarray.getResourceId(i, 0);
        typedarray = transition1;
        if(i != 0)
        {
            typedarray = transition1;
            if(i != 0x10f0000)
            {
                context = TransitionInflater.from(context).inflateTransition(i);
                typedarray = context;
                if(context instanceof TransitionSet)
                {
                    typedarray = context;
                    if(((TransitionSet)context).getTransitionCount() == 0)
                        typedarray = null;
                }
            }
        }
        return typedarray;
    }

    private boolean shouldChangeTransition(Transition transition, Transition transition1)
    {
        boolean flag = true;
        if(transition == transition1)
        {
            if(mAnimationInfo == null)
                flag = false;
            return flag;
        } else
        {
            return true;
        }
    }

    void _2D_android_app_Fragment_2D_mthref_2D_0()
    {
        callStartTransitionListener();
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.print(s);
        printwriter.print("mFragmentId=#");
        printwriter.print(Integer.toHexString(mFragmentId));
        printwriter.print(" mContainerId=#");
        printwriter.print(Integer.toHexString(mContainerId));
        printwriter.print(" mTag=");
        printwriter.println(mTag);
        printwriter.print(s);
        printwriter.print("mState=");
        printwriter.print(mState);
        printwriter.print(" mIndex=");
        printwriter.print(mIndex);
        printwriter.print(" mWho=");
        printwriter.print(mWho);
        printwriter.print(" mBackStackNesting=");
        printwriter.println(mBackStackNesting);
        printwriter.print(s);
        printwriter.print("mAdded=");
        printwriter.print(mAdded);
        printwriter.print(" mRemoving=");
        printwriter.print(mRemoving);
        printwriter.print(" mFromLayout=");
        printwriter.print(mFromLayout);
        printwriter.print(" mInLayout=");
        printwriter.println(mInLayout);
        printwriter.print(s);
        printwriter.print("mHidden=");
        printwriter.print(mHidden);
        printwriter.print(" mDetached=");
        printwriter.print(mDetached);
        printwriter.print(" mMenuVisible=");
        printwriter.print(mMenuVisible);
        printwriter.print(" mHasMenu=");
        printwriter.println(mHasMenu);
        printwriter.print(s);
        printwriter.print("mRetainInstance=");
        printwriter.print(mRetainInstance);
        printwriter.print(" mRetaining=");
        printwriter.print(mRetaining);
        printwriter.print(" mUserVisibleHint=");
        printwriter.println(mUserVisibleHint);
        if(mFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.print("mFragmentManager=");
            printwriter.println(mFragmentManager);
        }
        if(mHost != null)
        {
            printwriter.print(s);
            printwriter.print("mHost=");
            printwriter.println(mHost);
        }
        if(mParentFragment != null)
        {
            printwriter.print(s);
            printwriter.print("mParentFragment=");
            printwriter.println(mParentFragment);
        }
        if(mArguments != null)
        {
            printwriter.print(s);
            printwriter.print("mArguments=");
            printwriter.println(mArguments);
        }
        if(mSavedFragmentState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedFragmentState=");
            printwriter.println(mSavedFragmentState);
        }
        if(mSavedViewState != null)
        {
            printwriter.print(s);
            printwriter.print("mSavedViewState=");
            printwriter.println(mSavedViewState);
        }
        if(mTarget != null)
        {
            printwriter.print(s);
            printwriter.print("mTarget=");
            printwriter.print(mTarget);
            printwriter.print(" mTargetRequestCode=");
            printwriter.println(mTargetRequestCode);
        }
        if(getNextAnim() != 0)
        {
            printwriter.print(s);
            printwriter.print("mNextAnim=");
            printwriter.println(getNextAnim());
        }
        if(mContainer != null)
        {
            printwriter.print(s);
            printwriter.print("mContainer=");
            printwriter.println(mContainer);
        }
        if(mView != null)
        {
            printwriter.print(s);
            printwriter.print("mView=");
            printwriter.println(mView);
        }
        if(getAnimatingAway() != null)
        {
            printwriter.print(s);
            printwriter.print("mAnimatingAway=");
            printwriter.println(getAnimatingAway());
            printwriter.print(s);
            printwriter.print("mStateAfterAnimating=");
            printwriter.println(getStateAfterAnimating());
        }
        if(mLoaderManager != null)
        {
            printwriter.print(s);
            printwriter.println("Loader Manager:");
            mLoaderManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
        if(mChildFragmentManager != null)
        {
            printwriter.print(s);
            printwriter.println((new StringBuilder()).append("Child ").append(mChildFragmentManager).append(":").toString());
            mChildFragmentManager.dump((new StringBuilder()).append(s).append("  ").toString(), filedescriptor, printwriter, as);
        }
    }

    public final boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    Fragment findFragmentByWho(String s)
    {
        if(s.equals(mWho))
            return this;
        if(mChildFragmentManager != null)
            return mChildFragmentManager.findFragmentByWho(s);
        else
            return null;
    }

    public final Activity getActivity()
    {
        Activity activity = null;
        if(mHost != null)
            activity = mHost.getActivity();
        return activity;
    }

    public boolean getAllowEnterTransitionOverlap()
    {
        boolean flag;
        if(mAnimationInfo == null || AnimationInfo._2D_get0(mAnimationInfo) == null)
            flag = true;
        else
            flag = AnimationInfo._2D_get0(mAnimationInfo).booleanValue();
        return flag;
    }

    public boolean getAllowReturnTransitionOverlap()
    {
        boolean flag;
        if(mAnimationInfo == null || AnimationInfo._2D_get1(mAnimationInfo) == null)
            flag = true;
        else
            flag = AnimationInfo._2D_get1(mAnimationInfo).booleanValue();
        return flag;
    }

    Animator getAnimatingAway()
    {
        if(mAnimationInfo == null)
            return null;
        else
            return mAnimationInfo.mAnimatingAway;
    }

    public final Bundle getArguments()
    {
        return mArguments;
    }

    public final FragmentManager getChildFragmentManager()
    {
        if(mChildFragmentManager != null) goto _L2; else goto _L1
_L1:
        instantiateChildFragmentManager();
        if(mState < 5) goto _L4; else goto _L3
_L3:
        mChildFragmentManager.dispatchResume();
_L2:
        return mChildFragmentManager;
_L4:
        if(mState >= 4)
            mChildFragmentManager.dispatchStart();
        else
        if(mState >= 2)
            mChildFragmentManager.dispatchActivityCreated();
        else
        if(mState >= 1)
            mChildFragmentManager.dispatchCreate();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public Context getContext()
    {
        Context context = null;
        if(mHost != null)
            context = mHost.getContext();
        return context;
    }

    public Transition getEnterTransition()
    {
        if(mAnimationInfo == null)
            return null;
        else
            return AnimationInfo._2D_get2(mAnimationInfo);
    }

    SharedElementCallback getEnterTransitionCallback()
    {
        if(mAnimationInfo == null)
            return SharedElementCallback.NULL_CALLBACK;
        else
            return mAnimationInfo.mEnterTransitionCallback;
    }

    public Transition getExitTransition()
    {
        if(mAnimationInfo == null)
            return null;
        else
            return AnimationInfo._2D_get3(mAnimationInfo);
    }

    SharedElementCallback getExitTransitionCallback()
    {
        if(mAnimationInfo == null)
            return SharedElementCallback.NULL_CALLBACK;
        else
            return mAnimationInfo.mExitTransitionCallback;
    }

    public final FragmentManager getFragmentManager()
    {
        return mFragmentManager;
    }

    public final Object getHost()
    {
        Object obj = null;
        if(mHost != null)
            obj = mHost.onGetHost();
        return obj;
    }

    public final int getId()
    {
        return mFragmentId;
    }

    public final LayoutInflater getLayoutInflater()
    {
        if(mLayoutInflater == null)
            return performGetLayoutInflater(null);
        else
            return mLayoutInflater;
    }

    public LoaderManager getLoaderManager()
    {
        if(mLoaderManager != null)
            return mLoaderManager;
        if(mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, true);
            return mLoaderManager;
        }
    }

    int getNextAnim()
    {
        if(mAnimationInfo == null)
            return 0;
        else
            return mAnimationInfo.mNextAnim;
    }

    int getNextTransition()
    {
        if(mAnimationInfo == null)
            return 0;
        else
            return mAnimationInfo.mNextTransition;
    }

    int getNextTransitionStyle()
    {
        if(mAnimationInfo == null)
            return 0;
        else
            return mAnimationInfo.mNextTransitionStyle;
    }

    public final Fragment getParentFragment()
    {
        return mParentFragment;
    }

    public Transition getReenterTransition()
    {
        if(mAnimationInfo == null)
            return null;
        Transition transition;
        if(AnimationInfo._2D_get4(mAnimationInfo) == USE_DEFAULT_TRANSITION)
            transition = getExitTransition();
        else
            transition = AnimationInfo._2D_get4(mAnimationInfo);
        return transition;
    }

    public final Resources getResources()
    {
        if(mHost == null)
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        else
            return mHost.getContext().getResources();
    }

    public final boolean getRetainInstance()
    {
        return mRetainInstance;
    }

    public Transition getReturnTransition()
    {
        if(mAnimationInfo == null)
            return null;
        Transition transition;
        if(AnimationInfo._2D_get5(mAnimationInfo) == USE_DEFAULT_TRANSITION)
            transition = getEnterTransition();
        else
            transition = AnimationInfo._2D_get5(mAnimationInfo);
        return transition;
    }

    public Transition getSharedElementEnterTransition()
    {
        if(mAnimationInfo == null)
            return null;
        else
            return AnimationInfo._2D_get6(mAnimationInfo);
    }

    public Transition getSharedElementReturnTransition()
    {
        if(mAnimationInfo == null)
            return null;
        Transition transition;
        if(AnimationInfo._2D_get7(mAnimationInfo) == USE_DEFAULT_TRANSITION)
            transition = getSharedElementEnterTransition();
        else
            transition = AnimationInfo._2D_get7(mAnimationInfo);
        return transition;
    }

    int getStateAfterAnimating()
    {
        if(mAnimationInfo == null)
            return 0;
        else
            return mAnimationInfo.mStateAfterAnimating;
    }

    public final String getString(int i)
    {
        return getResources().getString(i);
    }

    public final transient String getString(int i, Object aobj[])
    {
        return getResources().getString(i, aobj);
    }

    public final String getTag()
    {
        return mTag;
    }

    public final Fragment getTargetFragment()
    {
        return mTarget;
    }

    public final int getTargetRequestCode()
    {
        return mTargetRequestCode;
    }

    public final CharSequence getText(int i)
    {
        return getResources().getText(i);
    }

    public boolean getUserVisibleHint()
    {
        return mUserVisibleHint;
    }

    public View getView()
    {
        return mView;
    }

    public final int hashCode()
    {
        return super.hashCode();
    }

    void initState()
    {
        mIndex = -1;
        mWho = null;
        mAdded = false;
        mRemoving = false;
        mFromLayout = false;
        mInLayout = false;
        mRestored = false;
        mBackStackNesting = 0;
        mFragmentManager = null;
        mChildFragmentManager = null;
        mHost = null;
        mFragmentId = 0;
        mContainerId = 0;
        mTag = null;
        mHidden = false;
        mDetached = false;
        mRetaining = false;
        mLoaderManager = null;
        mLoadersStarted = false;
        mCheckedForLoaderManager = false;
    }

    void instantiateChildFragmentManager()
    {
        mChildFragmentManager = new FragmentManagerImpl();
        mChildFragmentManager.attachController(mHost, new FragmentContainer() {

            public View onFindViewById(int i)
            {
                if(mView == null)
                    throw new IllegalStateException("Fragment does not have a view");
                else
                    return mView.findViewById(i);
            }

            public boolean onHasView()
            {
                boolean flag;
                if(mView != null)
                    flag = true;
                else
                    flag = false;
                return flag;
            }

            final Fragment this$0;

            
            {
                this$0 = Fragment.this;
                super();
            }
        }
, this);
    }

    public final boolean isAdded()
    {
        boolean flag;
        if(mHost != null)
            flag = mAdded;
        else
            flag = false;
        return flag;
    }

    public final boolean isDetached()
    {
        return mDetached;
    }

    public final boolean isHidden()
    {
        return mHidden;
    }

    boolean isHideReplaced()
    {
        if(mAnimationInfo == null)
            return false;
        else
            return mAnimationInfo.mIsHideReplaced;
    }

    final boolean isInBackStack()
    {
        boolean flag = false;
        if(mBackStackNesting > 0)
            flag = true;
        return flag;
    }

    public final boolean isInLayout()
    {
        return mInLayout;
    }

    boolean isPostponed()
    {
        if(mAnimationInfo == null)
            return false;
        else
            return mAnimationInfo.mEnterTransitionPostponed;
    }

    public final boolean isRemoving()
    {
        return mRemoving;
    }

    public final boolean isResumed()
    {
        boolean flag;
        if(mState >= 5)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public final boolean isStateSaved()
    {
        if(mFragmentManager == null)
            return false;
        else
            return mFragmentManager.isStateSaved();
    }

    public final boolean isVisible()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isAdded())
        {
            flag1 = flag;
            if(isHidden() ^ true)
            {
                flag1 = flag;
                if(mView != null)
                {
                    flag1 = flag;
                    if(mView.getWindowToken() != null)
                    {
                        flag1 = flag;
                        if(mView.getVisibility() == 0)
                            flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    void noteStateNotSaved()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.noteStateNotSaved();
    }

    public void onActivityCreated(Bundle bundle)
    {
        mCalled = true;
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
    }

    public void onAttach(Activity activity)
    {
        mCalled = true;
    }

    public void onAttach(Context context)
    {
        mCalled = true;
        if(mHost == null)
            context = null;
        else
            context = mHost.getActivity();
        if(context != null)
        {
            mCalled = false;
            onAttach(((Activity) (context)));
        }
    }

    public void onAttachFragment(Fragment fragment)
    {
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onCreate(Bundle bundle)
    {
        mCalled = true;
        Context context = getContext();
        int i;
        if(context != null)
            i = context.getApplicationInfo().targetSdkVersion;
        else
            i = 0;
        if(i >= 24)
        {
            restoreChildFragmentState(bundle, true);
            if(mChildFragmentManager != null && mChildFragmentManager.isStateAtLeast(1) ^ true)
                mChildFragmentManager.dispatchCreate();
        }
    }

    public Animator onCreateAnimator(int i, boolean flag, int j)
    {
        return null;
    }

    public void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        getActivity().onCreateContextMenu(contextmenu, view, contextmenuinfo);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return null;
    }

    public void onDestroy()
    {
        mCalled = true;
        if(!mCheckedForLoaderManager)
        {
            mCheckedForLoaderManager = true;
            mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, false);
        }
        if(mLoaderManager != null)
            mLoaderManager.doDestroy();
    }

    public void onDestroyOptionsMenu()
    {
    }

    public void onDestroyView()
    {
        mCalled = true;
    }

    public void onDetach()
    {
        mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle)
    {
        if(mHost == null)
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        bundle = mHost.onGetLayoutInflater();
        if(mHost.onUseFragmentManagerInflaterFactory())
        {
            getChildFragmentManager();
            bundle.setPrivateFactory(mChildFragmentManager.getLayoutInflaterFactory());
        }
        return bundle;
    }

    public void onHiddenChanged(boolean flag)
    {
    }

    public void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        mCalled = true;
    }

    public void onInflate(Context context, AttributeSet attributeset, Bundle bundle)
    {
        onInflate(attributeset, bundle);
        mCalled = true;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Fragment);
        setEnterTransition(loadTransition(context, typedarray, getEnterTransition(), null, 4));
        setReturnTransition(loadTransition(context, typedarray, getReturnTransition(), USE_DEFAULT_TRANSITION, 6));
        setExitTransition(loadTransition(context, typedarray, getExitTransition(), null, 3));
        setReenterTransition(loadTransition(context, typedarray, getReenterTransition(), USE_DEFAULT_TRANSITION, 8));
        setSharedElementEnterTransition(loadTransition(context, typedarray, getSharedElementEnterTransition(), null, 5));
        setSharedElementReturnTransition(loadTransition(context, typedarray, getSharedElementReturnTransition(), USE_DEFAULT_TRANSITION, 7));
        boolean flag;
        boolean flag1;
        if(mAnimationInfo == null)
        {
            flag = false;
            flag1 = false;
        } else
        {
            if(AnimationInfo._2D_get0(mAnimationInfo) != null)
                flag1 = true;
            else
                flag1 = false;
            if(AnimationInfo._2D_get1(mAnimationInfo) != null)
            {
                boolean flag2 = true;
                flag = flag1;
                flag1 = flag2;
            } else
            {
                boolean flag3 = false;
                flag = flag1;
                flag1 = flag3;
            }
        }
        if(!flag)
            setAllowEnterTransitionOverlap(typedarray.getBoolean(9, true));
        if(!flag1)
            setAllowReturnTransitionOverlap(typedarray.getBoolean(10, true));
        typedarray.recycle();
        if(mHost == null)
            context = null;
        else
            context = mHost.getActivity();
        if(context != null)
        {
            mCalled = false;
            onInflate(((Activity) (context)), attributeset, bundle);
        }
    }

    public void onInflate(AttributeSet attributeset, Bundle bundle)
    {
        mCalled = true;
    }

    public void onLowMemory()
    {
        mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean flag)
    {
    }

    public void onMultiWindowModeChanged(boolean flag, Configuration configuration)
    {
        onMultiWindowModeChanged(flag);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        return false;
    }

    public void onOptionsMenuClosed(Menu menu)
    {
    }

    public void onPause()
    {
        mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean flag)
    {
    }

    public void onPictureInPictureModeChanged(boolean flag, Configuration configuration)
    {
        onPictureInPictureModeChanged(flag);
    }

    public void onPrepareOptionsMenu(Menu menu)
    {
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
    }

    public void onResume()
    {
        mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
    }

    public void onStart()
    {
        mCalled = true;
        if(mLoadersStarted) goto _L2; else goto _L1
_L1:
        mLoadersStarted = true;
        if(mCheckedForLoaderManager) goto _L4; else goto _L3
_L3:
        mCheckedForLoaderManager = true;
        mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, false);
_L2:
        return;
_L4:
        if(mLoaderManager != null)
            mLoaderManager.doStart();
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void onStop()
    {
        mCalled = true;
    }

    public void onTrimMemory(int i)
    {
        mCalled = true;
    }

    public void onViewCreated(View view, Bundle bundle)
    {
    }

    public void onViewStateRestored(Bundle bundle)
    {
        mCalled = true;
    }

    void performActivityCreated(Bundle bundle)
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.noteStateNotSaved();
        mState = 2;
        mCalled = false;
        onActivityCreated(bundle);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onActivityCreated()").toString());
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchActivityCreated();
    }

    void performConfigurationChanged(Configuration configuration)
    {
        onConfigurationChanged(configuration);
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchConfigurationChanged(configuration);
    }

    boolean performContextItemSelected(MenuItem menuitem)
    {
        if(!mHidden)
        {
            if(onContextItemSelected(menuitem))
                return true;
            if(mChildFragmentManager != null && mChildFragmentManager.dispatchContextItemSelected(menuitem))
                return true;
        }
        return false;
    }

    void performCreate(Bundle bundle)
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.noteStateNotSaved();
        mState = 1;
        mCalled = false;
        onCreate(bundle);
        mIsCreated = true;
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onCreate()").toString());
        Context context = getContext();
        int i;
        if(context != null)
            i = context.getApplicationInfo().targetSdkVersion;
        else
            i = 0;
        if(i < 24)
            restoreChildFragmentState(bundle, false);
    }

    boolean performCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        boolean flag = false;
        boolean flag1 = false;
        if(!mHidden)
        {
            boolean flag2 = flag1;
            if(mHasMenu)
            {
                flag2 = flag1;
                if(mMenuVisible)
                {
                    flag2 = true;
                    onCreateOptionsMenu(menu, menuinflater);
                }
            }
            flag = flag2;
            if(mChildFragmentManager != null)
                flag = flag2 | mChildFragmentManager.dispatchCreateOptionsMenu(menu, menuinflater);
        }
        return flag;
    }

    View performCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.noteStateNotSaved();
        mPerformedCreateView = true;
        return onCreateView(layoutinflater, viewgroup, bundle);
    }

    void performDestroy()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchDestroy();
        mState = 0;
        mCalled = false;
        mIsCreated = false;
        onDestroy();
        if(!mCalled)
        {
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroy()").toString());
        } else
        {
            mChildFragmentManager = null;
            return;
        }
    }

    void performDestroyView()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchDestroyView();
        mState = 1;
        mCalled = false;
        onDestroyView();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDestroyView()").toString());
        if(mLoaderManager != null)
            mLoaderManager.doReportNextStart();
        mPerformedCreateView = false;
    }

    void performDetach()
    {
        mCalled = false;
        onDetach();
        mLayoutInflater = null;
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onDetach()").toString());
        if(mChildFragmentManager != null)
        {
            if(!mRetaining)
                throw new IllegalStateException((new StringBuilder()).append("Child FragmentManager of ").append(this).append(" was not ").append(" destroyed and this fragment is not retaining instance").toString());
            mChildFragmentManager.dispatchDestroy();
            mChildFragmentManager = null;
        }
    }

    LayoutInflater performGetLayoutInflater(Bundle bundle)
    {
        mLayoutInflater = onGetLayoutInflater(bundle);
        return mLayoutInflater;
    }

    void performLowMemory()
    {
        onLowMemory();
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchLowMemory();
    }

    void performMultiWindowModeChanged(boolean flag)
    {
        onMultiWindowModeChanged(flag);
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchMultiWindowModeChanged(flag);
    }

    void performMultiWindowModeChanged(boolean flag, Configuration configuration)
    {
        onMultiWindowModeChanged(flag, configuration);
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchMultiWindowModeChanged(flag, configuration);
    }

    boolean performOptionsItemSelected(MenuItem menuitem)
    {
        if(!mHidden)
        {
            if(mHasMenu && mMenuVisible && onOptionsItemSelected(menuitem))
                return true;
            if(mChildFragmentManager != null && mChildFragmentManager.dispatchOptionsItemSelected(menuitem))
                return true;
        }
        return false;
    }

    void performOptionsMenuClosed(Menu menu)
    {
        if(!mHidden)
        {
            if(mHasMenu && mMenuVisible)
                onOptionsMenuClosed(menu);
            if(mChildFragmentManager != null)
                mChildFragmentManager.dispatchOptionsMenuClosed(menu);
        }
    }

    void performPause()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchPause();
        mState = 4;
        mCalled = false;
        onPause();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onPause()").toString());
        else
            return;
    }

    void performPictureInPictureModeChanged(boolean flag)
    {
        onPictureInPictureModeChanged(flag);
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchPictureInPictureModeChanged(flag);
    }

    void performPictureInPictureModeChanged(boolean flag, Configuration configuration)
    {
        onPictureInPictureModeChanged(flag, configuration);
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchPictureInPictureModeChanged(flag, configuration);
    }

    boolean performPrepareOptionsMenu(Menu menu)
    {
        boolean flag = false;
        boolean flag1 = false;
        if(!mHidden)
        {
            boolean flag2 = flag1;
            if(mHasMenu)
            {
                flag2 = flag1;
                if(mMenuVisible)
                {
                    flag2 = true;
                    onPrepareOptionsMenu(menu);
                }
            }
            flag = flag2;
            if(mChildFragmentManager != null)
                flag = flag2 | mChildFragmentManager.dispatchPrepareOptionsMenu(menu);
        }
        return flag;
    }

    void performResume()
    {
        if(mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }
        mState = 5;
        mCalled = false;
        onResume();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onResume()").toString());
        if(mChildFragmentManager != null)
        {
            mChildFragmentManager.dispatchResume();
            mChildFragmentManager.execPendingActions();
        }
    }

    void performSaveInstanceState(Bundle bundle)
    {
        onSaveInstanceState(bundle);
        if(mChildFragmentManager != null)
        {
            Parcelable parcelable = mChildFragmentManager.saveAllState();
            if(parcelable != null)
                bundle.putParcelable("android:fragments", parcelable);
        }
    }

    void performStart()
    {
        if(mChildFragmentManager != null)
        {
            mChildFragmentManager.noteStateNotSaved();
            mChildFragmentManager.execPendingActions();
        }
        mState = 4;
        mCalled = false;
        onStart();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStart()").toString());
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchStart();
        if(mLoaderManager != null)
            mLoaderManager.doReportStart();
    }

    void performStop()
    {
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchStop();
        mState = 3;
        mCalled = false;
        onStop();
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onStop()").toString());
        if(mLoadersStarted)
        {
            mLoadersStarted = false;
            if(!mCheckedForLoaderManager)
            {
                mCheckedForLoaderManager = true;
                mLoaderManager = mHost.getLoaderManager(mWho, mLoadersStarted, false);
            }
            if(mLoaderManager != null)
                if(mHost.getRetainLoaders())
                    mLoaderManager.doRetain();
                else
                    mLoaderManager.doStop();
        }
    }

    void performTrimMemory(int i)
    {
        onTrimMemory(i);
        if(mChildFragmentManager != null)
            mChildFragmentManager.dispatchTrimMemory(i);
    }

    public void postponeEnterTransition()
    {
        ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public void registerForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(this);
    }

    public final void requestPermissions(String as[], int i)
    {
        if(mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onRequestPermissionsFromFragment(this, as, i);
            return;
        }
    }

    void restoreChildFragmentState(Bundle bundle, boolean flag)
    {
        if(bundle != null)
        {
            Parcelable parcelable = bundle.getParcelable("android:fragments");
            if(parcelable != null)
            {
                if(mChildFragmentManager == null)
                    instantiateChildFragmentManager();
                FragmentManagerImpl fragmentmanagerimpl = mChildFragmentManager;
                if(flag)
                    bundle = mChildNonConfig;
                else
                    bundle = null;
                fragmentmanagerimpl.restoreAllState(parcelable, bundle);
                mChildNonConfig = null;
                mChildFragmentManager.dispatchCreate();
            }
        }
    }

    final void restoreViewState(Bundle bundle)
    {
        if(mSavedViewState != null)
        {
            mView.restoreHierarchyState(mSavedViewState);
            mSavedViewState = null;
        }
        mCalled = false;
        onViewStateRestored(bundle);
        if(!mCalled)
            throw new SuperNotCalledException((new StringBuilder()).append("Fragment ").append(this).append(" did not call through to super.onViewStateRestored()").toString());
        else
            return;
    }

    public void setAllowEnterTransitionOverlap(boolean flag)
    {
        AnimationInfo._2D_set0(ensureAnimationInfo(), Boolean.valueOf(flag));
    }

    public void setAllowReturnTransitionOverlap(boolean flag)
    {
        AnimationInfo._2D_set1(ensureAnimationInfo(), Boolean.valueOf(flag));
    }

    void setAnimatingAway(Animator animator)
    {
        ensureAnimationInfo().mAnimatingAway = animator;
    }

    public void setArguments(Bundle bundle)
    {
        if(mIndex >= 0 && isStateSaved())
        {
            throw new IllegalStateException("Fragment already active");
        } else
        {
            mArguments = bundle;
            return;
        }
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        SharedElementCallback sharedelementcallback1 = sharedelementcallback;
        if(sharedelementcallback == null)
        {
            if(mAnimationInfo == null)
                return;
            sharedelementcallback1 = SharedElementCallback.NULL_CALLBACK;
        }
        ensureAnimationInfo().mEnterTransitionCallback = sharedelementcallback1;
    }

    public void setEnterTransition(Transition transition)
    {
        if(shouldChangeTransition(transition, null))
            AnimationInfo._2D_set2(ensureAnimationInfo(), transition);
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        SharedElementCallback sharedelementcallback1 = sharedelementcallback;
        if(sharedelementcallback == null)
        {
            if(mAnimationInfo == null)
                return;
            sharedelementcallback1 = SharedElementCallback.NULL_CALLBACK;
        }
        ensureAnimationInfo().mExitTransitionCallback = sharedelementcallback1;
    }

    public void setExitTransition(Transition transition)
    {
        if(shouldChangeTransition(transition, null))
            AnimationInfo._2D_set3(ensureAnimationInfo(), transition);
    }

    public void setHasOptionsMenu(boolean flag)
    {
        if(mHasMenu != flag)
        {
            mHasMenu = flag;
            if(isAdded() && isHidden() ^ true)
                mFragmentManager.invalidateOptionsMenu();
        }
    }

    void setHideReplaced(boolean flag)
    {
        ensureAnimationInfo().mIsHideReplaced = flag;
    }

    final void setIndex(int i, Fragment fragment)
    {
        mIndex = i;
        if(fragment != null)
            mWho = (new StringBuilder()).append(fragment.mWho).append(":").append(mIndex).toString();
        else
            mWho = (new StringBuilder()).append("android:fragment:").append(mIndex).toString();
    }

    public void setInitialSavedState(SavedState savedstate)
    {
        Object obj = null;
        if(mIndex >= 0)
            throw new IllegalStateException("Fragment already active");
        Bundle bundle = obj;
        if(savedstate != null)
        {
            bundle = obj;
            if(savedstate.mState != null)
                bundle = savedstate.mState;
        }
        mSavedFragmentState = bundle;
    }

    public void setMenuVisibility(boolean flag)
    {
        if(mMenuVisible != flag)
        {
            mMenuVisible = flag;
            if(mHasMenu && isAdded() && isHidden() ^ true)
                mFragmentManager.invalidateOptionsMenu();
        }
    }

    void setNextAnim(int i)
    {
        if(mAnimationInfo == null && i == 0)
        {
            return;
        } else
        {
            ensureAnimationInfo().mNextAnim = i;
            return;
        }
    }

    void setNextTransition(int i, int j)
    {
        if(mAnimationInfo == null && i == 0 && j == 0)
        {
            return;
        } else
        {
            ensureAnimationInfo();
            mAnimationInfo.mNextTransition = i;
            mAnimationInfo.mNextTransitionStyle = j;
            return;
        }
    }

    void setOnStartEnterTransitionListener(OnStartEnterTransitionListener onstartentertransitionlistener)
    {
        ensureAnimationInfo();
        if(onstartentertransitionlistener == mAnimationInfo.mStartEnterTransitionListener)
            return;
        if(onstartentertransitionlistener != null && mAnimationInfo.mStartEnterTransitionListener != null)
            throw new IllegalStateException((new StringBuilder()).append("Trying to set a replacement startPostponedEnterTransition on ").append(this).toString());
        if(mAnimationInfo.mEnterTransitionPostponed)
            mAnimationInfo.mStartEnterTransitionListener = onstartentertransitionlistener;
        if(onstartentertransitionlistener != null)
            onstartentertransitionlistener.startListening();
    }

    public void setReenterTransition(Transition transition)
    {
        if(shouldChangeTransition(transition, USE_DEFAULT_TRANSITION))
            AnimationInfo._2D_set4(ensureAnimationInfo(), transition);
    }

    public void setRetainInstance(boolean flag)
    {
        mRetainInstance = flag;
    }

    public void setReturnTransition(Transition transition)
    {
        if(shouldChangeTransition(transition, USE_DEFAULT_TRANSITION))
            AnimationInfo._2D_set5(ensureAnimationInfo(), transition);
    }

    public void setSharedElementEnterTransition(Transition transition)
    {
        if(shouldChangeTransition(transition, null))
            AnimationInfo._2D_set6(ensureAnimationInfo(), transition);
    }

    public void setSharedElementReturnTransition(Transition transition)
    {
        if(shouldChangeTransition(transition, USE_DEFAULT_TRANSITION))
            AnimationInfo._2D_set7(ensureAnimationInfo(), transition);
    }

    void setStateAfterAnimating(int i)
    {
        ensureAnimationInfo().mStateAfterAnimating = i;
    }

    public void setTargetFragment(Fragment fragment, int i)
    {
        FragmentManager fragmentmanager = getFragmentManager();
        FragmentManager fragmentmanager1;
        if(fragment != null)
            fragmentmanager1 = fragment.getFragmentManager();
        else
            fragmentmanager1 = null;
        if(fragmentmanager != null && fragmentmanager1 != null && fragmentmanager != fragmentmanager1)
            throw new IllegalArgumentException((new StringBuilder()).append("Fragment ").append(fragment).append(" must share the same FragmentManager to be set as a target fragment").toString());
        for(Fragment fragment1 = fragment; fragment1 != null; fragment1 = fragment1.getTargetFragment())
            if(fragment1 == this)
                throw new IllegalArgumentException((new StringBuilder()).append("Setting ").append(fragment).append(" as the target of ").append(this).append(" would create a target cycle").toString());

        mTarget = fragment;
        mTargetRequestCode = i;
    }

    public void setUserVisibleHint(boolean flag)
    {
        boolean flag1 = false;
        Context context = getContext();
        Context context1 = context;
        if(mFragmentManager != null)
        {
            context1 = context;
            if(mFragmentManager.mHost != null)
                context1 = mFragmentManager.mHost.getContext();
        }
        boolean flag2;
        if(context1 != null)
            if(context1.getApplicationInfo().targetSdkVersion <= 23)
                flag1 = true;
            else
                flag1 = false;
        if(flag1)
        {
            if(!mUserVisibleHint && flag && mState < 4)
            {
                if(mFragmentManager != null)
                    flag2 = true;
                else
                    flag2 = false;
            } else
            {
                flag2 = false;
            }
        } else
        if(!mUserVisibleHint && flag && mState < 4 && mFragmentManager != null)
            flag2 = isAdded();
        else
            flag2 = false;
        if(flag2)
            mFragmentManager.performPendingDeferredStart(this);
        mUserVisibleHint = flag;
        if(mState < 4)
            flag ^= true;
        else
            flag = false;
        mDeferStart = flag;
    }

    public boolean shouldShowRequestPermissionRationale(String s)
    {
        if(mHost != null)
            return mHost.getContext().getPackageManager().shouldShowRequestPermissionRationale(s);
        else
            return false;
    }

    public void startActivity(Intent intent)
    {
        startActivity(intent, null);
    }

    public void startActivity(Intent intent, Bundle bundle)
    {
        if(mHost == null)
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        if(bundle != null)
            mHost.onStartActivityFromFragment(this, intent, -1, bundle);
        else
            mHost.onStartActivityFromFragment(this, intent, -1, null);
    }

    public void startActivityForResult(Intent intent, int i)
    {
        startActivityForResult(intent, i, null);
    }

    public void startActivityForResult(Intent intent, int i, Bundle bundle)
    {
        if(mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onStartActivityFromFragment(this, intent, i, bundle);
            return;
        }
    }

    public void startActivityForResultAsUser(Intent intent, int i, Bundle bundle, UserHandle userhandle)
    {
        if(mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onStartActivityAsUserFromFragment(this, intent, i, bundle, userhandle);
            return;
        }
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        if(mHost == null)
        {
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(this).append(" not attached to Activity").toString());
        } else
        {
            mHost.onStartIntentSenderFromFragment(this, intentsender, i, intent, j, k, l, bundle);
            return;
        }
    }

    public void startPostponedEnterTransition()
    {
        if(mFragmentManager == null || mFragmentManager.mHost == null)
            ensureAnimationInfo().mEnterTransitionPostponed = false;
        else
        if(Looper.myLooper() != mFragmentManager.mHost.getHandler().getLooper())
            mFragmentManager.mHost.getHandler().postAtFrontOfQueue(new _.Lambda.aS31cHIhRx41653CMnd4gZqshIQ((byte)2, this));
        else
            callStartTransitionListener();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        DebugUtils.buildShortClassTag(this, stringbuilder);
        if(mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if(mFragmentId != 0)
        {
            stringbuilder.append(" id=0x");
            stringbuilder.append(Integer.toHexString(mFragmentId));
        }
        if(mTag != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mTag);
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void unregisterForContextMenu(View view)
    {
        view.setOnCreateContextMenuListener(null);
    }

    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int INVALID_STATE = -1;
    static final int RESUMED = 5;
    static final int STARTED = 4;
    static final int STOPPED = 3;
    private static final Transition USE_DEFAULT_TRANSITION = new TransitionSet();
    private static final ArrayMap sClassMap = new ArrayMap();
    boolean mAdded;
    AnimationInfo mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    FragmentManagerImpl mChildFragmentManager;
    FragmentManagerNonConfig mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManagerImpl mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    FragmentHostCallback mHost;
    boolean mInLayout;
    int mIndex;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mMenuVisible;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    SparseArray mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    String mWho;

}
