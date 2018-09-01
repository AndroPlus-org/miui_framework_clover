// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;

public final class Vr2dDisplayProperties
    implements Parcelable
{
    public static class Builder
    {

        public Builder addFlags(int i)
        {
            mAddedFlags = mAddedFlags | i;
            mRemovedFlags = mRemovedFlags & i;
            return this;
        }

        public Vr2dDisplayProperties build()
        {
            return new Vr2dDisplayProperties(mWidth, mHeight, mDpi, mAddedFlags, mRemovedFlags, null);
        }

        public Builder removeFlags(int i)
        {
            mRemovedFlags = mRemovedFlags | i;
            mAddedFlags = mAddedFlags & i;
            return this;
        }

        public Builder setDimensions(int i, int j, int k)
        {
            mWidth = i;
            mHeight = j;
            mDpi = k;
            return this;
        }

        public Builder setEnabled(boolean flag)
        {
            if(flag)
                addFlags(1);
            else
                removeFlags(1);
            return this;
        }

        private int mAddedFlags;
        private int mDpi;
        private int mHeight;
        private int mRemovedFlags;
        private int mWidth;

        public Builder()
        {
            mAddedFlags = 0;
            mRemovedFlags = 0;
            mWidth = -1;
            mHeight = -1;
            mDpi = -1;
        }
    }


    public Vr2dDisplayProperties(int i, int j, int k)
    {
        this(i, j, k, 0, 0);
    }

    private Vr2dDisplayProperties(int i, int j, int k, int l, int i1)
    {
        mWidth = i;
        mHeight = j;
        mDpi = k;
        mAddedFlags = l;
        mRemovedFlags = i1;
    }

    Vr2dDisplayProperties(int i, int j, int k, int l, int i1, Vr2dDisplayProperties vr2ddisplayproperties)
    {
        this(i, j, k, l, i1);
    }

    private Vr2dDisplayProperties(Parcel parcel)
    {
        mWidth = parcel.readInt();
        mHeight = parcel.readInt();
        mDpi = parcel.readInt();
        mAddedFlags = parcel.readInt();
        mRemovedFlags = parcel.readInt();
    }

    Vr2dDisplayProperties(Parcel parcel, Vr2dDisplayProperties vr2ddisplayproperties)
    {
        this(parcel);
    }

    private static String toReadableFlags(int i)
    {
        String s = "{";
        if((i & 1) == 1)
            s = (new StringBuilder()).append("{").append("enabled").toString();
        return (new StringBuilder()).append(s).append("}").toString();
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(PrintWriter printwriter, String s)
    {
        printwriter.println((new StringBuilder()).append(s).append(toString()).toString());
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (Vr2dDisplayProperties)obj;
        if(getFlags() != ((Vr2dDisplayProperties) (obj)).getFlags())
            return false;
        if(getRemovedFlags() != ((Vr2dDisplayProperties) (obj)).getRemovedFlags())
            return false;
        if(getWidth() != ((Vr2dDisplayProperties) (obj)).getWidth())
            return false;
        if(getHeight() != ((Vr2dDisplayProperties) (obj)).getHeight())
            return false;
        if(getDpi() != ((Vr2dDisplayProperties) (obj)).getDpi())
            flag = false;
        return flag;
    }

    public int getDpi()
    {
        return mDpi;
    }

    public int getFlags()
    {
        return mAddedFlags;
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getRemovedFlags()
    {
        return mRemovedFlags;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int hashCode()
    {
        return (getWidth() * 31 + getHeight()) * 31 + getDpi();
    }

    public String toString()
    {
        return (new StringBuilder()).append("Vr2dDisplayProperties{mWidth=").append(mWidth).append(", mHeight=").append(mHeight).append(", mDpi=").append(mDpi).append(", flags=").append(toReadableFlags(mAddedFlags)).append(", removed_flags=").append(toReadableFlags(mRemovedFlags)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mWidth);
        parcel.writeInt(mHeight);
        parcel.writeInt(mDpi);
        parcel.writeInt(mAddedFlags);
        parcel.writeInt(mRemovedFlags);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Vr2dDisplayProperties createFromParcel(Parcel parcel)
        {
            return new Vr2dDisplayProperties(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Vr2dDisplayProperties[] newArray(int i)
        {
            return new Vr2dDisplayProperties[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_VIRTUAL_DISPLAY_ENABLED = 1;
    private final int mAddedFlags;
    private final int mDpi;
    private final int mHeight;
    private final int mRemovedFlags;
    private final int mWidth;

}
