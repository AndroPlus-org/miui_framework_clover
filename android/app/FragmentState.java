// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.app:
//            Fragment, FragmentHostCallback, FragmentContainer, FragmentManagerImpl, 
//            FragmentManagerNonConfig

final class FragmentState
    implements Parcelable
{

    FragmentState(Fragment fragment)
    {
        mClassName = fragment.getClass().getName();
        mIndex = fragment.mIndex;
        mFromLayout = fragment.mFromLayout;
        mFragmentId = fragment.mFragmentId;
        mContainerId = fragment.mContainerId;
        mTag = fragment.mTag;
        mRetainInstance = fragment.mRetainInstance;
        mDetached = fragment.mDetached;
        mArguments = fragment.mArguments;
        mHidden = fragment.mHidden;
    }

    FragmentState(Parcel parcel)
    {
        boolean flag = true;
        super();
        mClassName = parcel.readString();
        mIndex = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mFromLayout = flag1;
        mFragmentId = parcel.readInt();
        mContainerId = parcel.readInt();
        mTag = parcel.readString();
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mRetainInstance = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mDetached = flag1;
        mArguments = parcel.readBundle();
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mHidden = flag1;
        mSavedFragmentState = parcel.readBundle();
    }

    public int describeContents()
    {
        return 0;
    }

    public Fragment instantiate(FragmentHostCallback fragmenthostcallback, FragmentContainer fragmentcontainer, Fragment fragment, FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        if(mInstance == null)
        {
            Context context = fragmenthostcallback.getContext();
            if(mArguments != null)
                mArguments.setClassLoader(context.getClassLoader());
            if(fragmentcontainer != null)
                mInstance = fragmentcontainer.instantiate(context, mClassName, mArguments);
            else
                mInstance = Fragment.instantiate(context, mClassName, mArguments);
            if(mSavedFragmentState != null)
            {
                mSavedFragmentState.setClassLoader(context.getClassLoader());
                mInstance.mSavedFragmentState = mSavedFragmentState;
            }
            mInstance.setIndex(mIndex, fragment);
            mInstance.mFromLayout = mFromLayout;
            mInstance.mRestored = true;
            mInstance.mFragmentId = mFragmentId;
            mInstance.mContainerId = mContainerId;
            mInstance.mTag = mTag;
            mInstance.mRetainInstance = mRetainInstance;
            mInstance.mDetached = mDetached;
            mInstance.mHidden = mHidden;
            mInstance.mFragmentManager = fragmenthostcallback.mFragmentManager;
            if(FragmentManagerImpl.DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Instantiated fragment ").append(mInstance).toString());
        }
        mInstance.mChildNonConfig = fragmentmanagernonconfig;
        return mInstance;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(mClassName);
        parcel.writeInt(mIndex);
        if(mFromLayout)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mFragmentId);
        parcel.writeInt(mContainerId);
        parcel.writeString(mTag);
        if(mRetainInstance)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mDetached)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeBundle(mArguments);
        if(mHidden)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeBundle(mSavedFragmentState);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FragmentState createFromParcel(Parcel parcel)
        {
            return new FragmentState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FragmentState[] newArray(int i)
        {
            return new FragmentState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

}
