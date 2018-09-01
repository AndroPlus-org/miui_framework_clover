// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class AbsSavedState
    implements Parcelable
{

    private AbsSavedState()
    {
        mSuperState = null;
    }

    protected AbsSavedState(Parcel parcel)
    {
        this(parcel, null);
    }

    protected AbsSavedState(Parcel parcel, ClassLoader classloader)
    {
        parcel = parcel.readParcelable(classloader);
        if(parcel == null)
            parcel = EMPTY_STATE;
        mSuperState = parcel;
    }

    protected AbsSavedState(Parcelable parcelable)
    {
        if(parcelable == null)
            throw new IllegalArgumentException("superState must not be null");
        if(parcelable == EMPTY_STATE)
            parcelable = null;
        mSuperState = parcelable;
    }

    AbsSavedState(AbsSavedState abssavedstate)
    {
        this();
    }

    public int describeContents()
    {
        return 0;
    }

    public final Parcelable getSuperState()
    {
        return mSuperState;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mSuperState, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.ClassLoaderCreator() {

        public AbsSavedState createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel, null);
        }

        public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            if(parcel.readParcelable(classloader) != null)
                throw new IllegalStateException("superState must be null");
            else
                return AbsSavedState.EMPTY_STATE;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
        {
            return createFromParcel(parcel, classloader);
        }

        public AbsSavedState[] newArray(int i)
        {
            return new AbsSavedState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final AbsSavedState EMPTY_STATE = new AbsSavedState() {

    }
;
    private final Parcelable mSuperState;

}
