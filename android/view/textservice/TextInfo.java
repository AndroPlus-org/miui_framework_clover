// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.SpellCheckSpan;

public final class TextInfo
    implements Parcelable
{

    public TextInfo(Parcel parcel)
    {
        mCharSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mCookie = parcel.readInt();
        mSequenceNumber = parcel.readInt();
    }

    public TextInfo(CharSequence charsequence, int i, int j, int k, int l)
    {
        if(TextUtils.isEmpty(charsequence))
            throw new IllegalArgumentException("charSequence is empty");
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(charsequence, i, j);
        charsequence = (SpellCheckSpan[])spannablestringbuilder.getSpans(0, spannablestringbuilder.length(), android/text/style/SpellCheckSpan);
        for(i = 0; i < charsequence.length; i++)
            spannablestringbuilder.removeSpan(charsequence[i]);

        mCharSequence = spannablestringbuilder;
        mCookie = k;
        mSequenceNumber = l;
    }

    public TextInfo(String s)
    {
        this(((CharSequence) (s)), 0, getStringLengthOrZero(s), 0, 0);
    }

    public TextInfo(String s, int i, int j)
    {
        this(((CharSequence) (s)), 0, getStringLengthOrZero(s), i, j);
    }

    private static int getStringLengthOrZero(String s)
    {
        int i;
        if(TextUtils.isEmpty(s))
            i = 0;
        else
            i = s.length();
        return i;
    }

    public int describeContents()
    {
        return 0;
    }

    public CharSequence getCharSequence()
    {
        return mCharSequence;
    }

    public int getCookie()
    {
        return mCookie;
    }

    public int getSequence()
    {
        return mSequenceNumber;
    }

    public String getText()
    {
        if(mCharSequence == null)
            return null;
        else
            return mCharSequence.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        TextUtils.writeToParcel(mCharSequence, parcel, i);
        parcel.writeInt(mCookie);
        parcel.writeInt(mSequenceNumber);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public TextInfo createFromParcel(Parcel parcel)
        {
            return new TextInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public TextInfo[] newArray(int i)
        {
            return new TextInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int DEFAULT_COOKIE = 0;
    private static final int DEFAULT_SEQUENCE_NUMBER = 0;
    private final CharSequence mCharSequence;
    private final int mCookie;
    private final int mSequenceNumber;

}
