// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;

// Referenced classes of package android.app:
//            BackStackRecord, Fragment, FragmentManagerImpl

final class BackStackState
    implements Parcelable
{

    public BackStackState(FragmentManagerImpl fragmentmanagerimpl, BackStackRecord backstackrecord)
    {
        int i = backstackrecord.mOps.size();
        mOps = new int[i * 6];
        if(!backstackrecord.mAddToBackStack)
            throw new IllegalStateException("Not on back stack");
        int j = 0;
        int k = 0;
        while(j < i) 
        {
            fragmentmanagerimpl = (BackStackRecord.Op)backstackrecord.mOps.get(j);
            int ai[] = mOps;
            int l = k + 1;
            ai[k] = ((BackStackRecord.Op) (fragmentmanagerimpl)).cmd;
            ai = mOps;
            int i1 = l + 1;
            if(((BackStackRecord.Op) (fragmentmanagerimpl)).fragment != null)
                k = ((BackStackRecord.Op) (fragmentmanagerimpl)).fragment.mIndex;
            else
                k = -1;
            ai[l] = k;
            ai = mOps;
            l = i1 + 1;
            ai[i1] = ((BackStackRecord.Op) (fragmentmanagerimpl)).enterAnim;
            ai = mOps;
            k = l + 1;
            ai[l] = ((BackStackRecord.Op) (fragmentmanagerimpl)).exitAnim;
            ai = mOps;
            i1 = k + 1;
            ai[k] = ((BackStackRecord.Op) (fragmentmanagerimpl)).popEnterAnim;
            ai = mOps;
            k = i1 + 1;
            ai[i1] = ((BackStackRecord.Op) (fragmentmanagerimpl)).popExitAnim;
            j++;
        }
        mTransition = backstackrecord.mTransition;
        mTransitionStyle = backstackrecord.mTransitionStyle;
        mName = backstackrecord.mName;
        mIndex = backstackrecord.mIndex;
        mBreadCrumbTitleRes = backstackrecord.mBreadCrumbTitleRes;
        mBreadCrumbTitleText = backstackrecord.mBreadCrumbTitleText;
        mBreadCrumbShortTitleRes = backstackrecord.mBreadCrumbShortTitleRes;
        mBreadCrumbShortTitleText = backstackrecord.mBreadCrumbShortTitleText;
        mSharedElementSourceNames = backstackrecord.mSharedElementSourceNames;
        mSharedElementTargetNames = backstackrecord.mSharedElementTargetNames;
        mReorderingAllowed = backstackrecord.mReorderingAllowed;
    }

    public BackStackState(Parcel parcel)
    {
        mOps = parcel.createIntArray();
        mTransition = parcel.readInt();
        mTransitionStyle = parcel.readInt();
        mName = parcel.readString();
        mIndex = parcel.readInt();
        mBreadCrumbTitleRes = parcel.readInt();
        mBreadCrumbTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mBreadCrumbShortTitleRes = parcel.readInt();
        mBreadCrumbShortTitleText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mSharedElementSourceNames = parcel.createStringArrayList();
        mSharedElementTargetNames = parcel.createStringArrayList();
        boolean flag;
        if(parcel.readInt() != 0)
            flag = true;
        else
            flag = false;
        mReorderingAllowed = flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManagerImpl fragmentmanagerimpl)
    {
        BackStackRecord backstackrecord = new BackStackRecord(fragmentmanagerimpl);
        int i = 0;
        int j = 0;
        while(i < mOps.length) 
        {
            BackStackRecord.Op op = new BackStackRecord.Op();
            int ai[] = mOps;
            int k = i + 1;
            op.cmd = ai[i];
            if(FragmentManagerImpl.DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Instantiate ").append(backstackrecord).append(" op #").append(j).append(" base fragment #").append(mOps[k]).toString());
            ai = mOps;
            i = k + 1;
            k = ai[k];
            if(k >= 0)
                op.fragment = (Fragment)fragmentmanagerimpl.mActive.get(k);
            else
                op.fragment = null;
            ai = mOps;
            k = i + 1;
            op.enterAnim = ai[i];
            ai = mOps;
            i = k + 1;
            op.exitAnim = ai[k];
            ai = mOps;
            k = i + 1;
            op.popEnterAnim = ai[i];
            ai = mOps;
            i = k + 1;
            op.popExitAnim = ai[k];
            backstackrecord.mEnterAnim = op.enterAnim;
            backstackrecord.mExitAnim = op.exitAnim;
            backstackrecord.mPopEnterAnim = op.popEnterAnim;
            backstackrecord.mPopExitAnim = op.popExitAnim;
            backstackrecord.addOp(op);
            j++;
        }
        backstackrecord.mTransition = mTransition;
        backstackrecord.mTransitionStyle = mTransitionStyle;
        backstackrecord.mName = mName;
        backstackrecord.mIndex = mIndex;
        backstackrecord.mAddToBackStack = true;
        backstackrecord.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        backstackrecord.mBreadCrumbTitleText = mBreadCrumbTitleText;
        backstackrecord.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        backstackrecord.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        backstackrecord.mSharedElementSourceNames = mSharedElementSourceNames;
        backstackrecord.mSharedElementTargetNames = mSharedElementTargetNames;
        backstackrecord.mReorderingAllowed = mReorderingAllowed;
        backstackrecord.bumpBackStackNesting(1);
        return backstackrecord;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 0;
        parcel.writeIntArray(mOps);
        parcel.writeInt(mTransition);
        parcel.writeInt(mTransitionStyle);
        parcel.writeString(mName);
        parcel.writeInt(mIndex);
        parcel.writeInt(mBreadCrumbTitleRes);
        TextUtils.writeToParcel(mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(mSharedElementSourceNames);
        parcel.writeStringList(mSharedElementTargetNames);
        if(mReorderingAllowed)
            i = 1;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public BackStackState createFromParcel(Parcel parcel)
        {
            return new BackStackState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public BackStackState[] newArray(int i)
        {
            return new BackStackState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int mOps[];
    final boolean mReorderingAllowed;
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;
    final int mTransitionStyle;

}
