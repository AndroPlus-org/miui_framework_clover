// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Parcel;
import android.text.ParcelableSpan;

public class SpellCheckSpan
    implements ParcelableSpan
{

    public SpellCheckSpan()
    {
        mSpellCheckInProgress = false;
    }

    public SpellCheckSpan(Parcel parcel)
    {
        boolean flag = false;
        super();
        if(parcel.readInt() != 0)
            flag = true;
        mSpellCheckInProgress = flag;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 20;
    }

    public boolean isSpellCheckInProgress()
    {
        return mSpellCheckInProgress;
    }

    public void setSpellCheckInProgress(boolean flag)
    {
        mSpellCheckInProgress = flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        if(mSpellCheckInProgress)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    private boolean mSpellCheckInProgress;
}
