// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import com.android.internal.util.Preconditions;
import java.util.Locale;

// Referenced classes of package android.text:
//            Spanned, TextUtils, SpannableString

public interface InputFilter
{
    public static class AllCaps
        implements InputFilter
    {

        public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
        {
            spanned = new CharSequenceWrapper(charsequence, i, j);
            boolean flag = false;
            k = 0;
label0:
            do
            {
label1:
                {
                    l = ((flag) ? 1 : 0);
                    if(k < j - i)
                    {
                        l = Character.codePointAt(spanned, k);
                        if(!Character.isLowerCase(l) && !Character.isTitleCase(l))
                            break label1;
                        l = 1;
                    }
                    if(l == 0)
                        return null;
                    break label0;
                }
                k += Character.charCount(l);
            } while(true);
            boolean flag1 = charsequence instanceof Spanned;
            charsequence = TextUtils.toUpperCase(mLocale, spanned, flag1);
            if(charsequence == spanned)
                return null;
            if(flag1)
                charsequence = new SpannableString(charsequence);
            else
                charsequence = charsequence.toString();
            return charsequence;
        }

        private final Locale mLocale;

        public AllCaps()
        {
            mLocale = null;
        }

        public AllCaps(Locale locale)
        {
            Preconditions.checkNotNull(locale);
            mLocale = locale;
        }
    }

    private static class AllCaps.CharSequenceWrapper
        implements CharSequence, Spanned
    {

        public char charAt(int i)
        {
            if(i < 0 || i >= mLength)
                throw new IndexOutOfBoundsException();
            else
                return mSource.charAt(mStart + i);
        }

        public int getSpanEnd(Object obj)
        {
            return ((Spanned)mSource).getSpanEnd(obj) - mStart;
        }

        public int getSpanFlags(Object obj)
        {
            return ((Spanned)mSource).getSpanFlags(obj);
        }

        public int getSpanStart(Object obj)
        {
            return ((Spanned)mSource).getSpanStart(obj) - mStart;
        }

        public Object[] getSpans(int i, int j, Class class1)
        {
            return ((Spanned)mSource).getSpans(mStart + i, mStart + j, class1);
        }

        public int length()
        {
            return mLength;
        }

        public int nextSpanTransition(int i, int j, Class class1)
        {
            return ((Spanned)mSource).nextSpanTransition(mStart + i, mStart + j, class1) - mStart;
        }

        public CharSequence subSequence(int i, int j)
        {
            while(i < 0 || j < 0 || j > mLength || i > j) 
                throw new IndexOutOfBoundsException();
            return new AllCaps.CharSequenceWrapper(mSource, mStart + i, mStart + j);
        }

        public String toString()
        {
            return mSource.subSequence(mStart, mEnd).toString();
        }

        private final int mEnd;
        private final int mLength;
        private final CharSequence mSource;
        private final int mStart;

        AllCaps.CharSequenceWrapper(CharSequence charsequence, int i, int j)
        {
            mSource = charsequence;
            mStart = i;
            mEnd = j;
            mLength = j - i;
        }
    }

    public static class LengthFilter
        implements InputFilter
    {

        public CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l)
        {
            k = mMax - (spanned.length() - (l - k));
            if(k <= 0)
                return "";
            if(k >= j - i)
                return null;
            k += i;
            j = k;
            if(Character.isHighSurrogate(charsequence.charAt(k - 1)))
            {
                j = --k;
                if(k == i)
                    return "";
            }
            return charsequence.subSequence(i, j);
        }

        public int getMax()
        {
            return mMax;
        }

        private final int mMax;

        public LengthFilter(int i)
        {
            mMax = i;
        }
    }


    public abstract CharSequence filter(CharSequence charsequence, int i, int j, Spanned spanned, int k, int l);
}
