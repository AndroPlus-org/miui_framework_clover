// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.transition;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

public class MiuiAppTransitionAnimationSpec
    implements Parcelable
{

    public MiuiAppTransitionAnimationSpec(Bitmap bitmap, Rect rect)
    {
        mBitmap = bitmap;
        mRect = rect;
    }

    public MiuiAppTransitionAnimationSpec(Parcel parcel)
    {
        mBitmap = (Bitmap)parcel.readParcelable(null);
        mRect = (Rect)parcel.readParcelable(null);
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mBitmap, i);
        parcel.writeParcelable(mRect, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MiuiAppTransitionAnimationSpec createFromParcel(Parcel parcel)
        {
            return new MiuiAppTransitionAnimationSpec(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MiuiAppTransitionAnimationSpec[] newArray(int i)
        {
            return new MiuiAppTransitionAnimationSpec[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final Bitmap mBitmap;
    public final Rect mRect;

}
