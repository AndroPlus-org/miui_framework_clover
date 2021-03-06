// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.style;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;

// Referenced classes of package android.text.style:
//            CharacterStyle, UpdateAppearance

public class ForegroundColorSpan extends CharacterStyle
    implements UpdateAppearance, ParcelableSpan
{

    public ForegroundColorSpan(int i)
    {
        mColor = i;
    }

    public ForegroundColorSpan(Parcel parcel)
    {
        mColor = parcel.readInt();
    }

    public int describeContents()
    {
        return 0;
    }

    public int getForegroundColor()
    {
        return mColor;
    }

    public int getSpanTypeId()
    {
        return getSpanTypeIdInternal();
    }

    public int getSpanTypeIdInternal()
    {
        return 2;
    }

    public void updateDrawState(TextPaint textpaint)
    {
        textpaint.setColor(mColor);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        writeToParcelInternal(parcel, i);
    }

    public void writeToParcelInternal(Parcel parcel, int i)
    {
        parcel.writeInt(mColor);
    }

    private final int mColor;
}
