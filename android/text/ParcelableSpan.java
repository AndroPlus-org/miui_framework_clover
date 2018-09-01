// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.os.Parcel;
import android.os.Parcelable;

public interface ParcelableSpan
    extends Parcelable
{

    public abstract int getSpanTypeId();

    public abstract int getSpanTypeIdInternal();

    public abstract void writeToParcelInternal(Parcel parcel, int i);
}
