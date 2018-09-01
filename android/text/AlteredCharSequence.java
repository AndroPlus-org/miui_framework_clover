// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


// Referenced classes of package android.text:
//            GetChars, Spanned, TextUtils

public class AlteredCharSequence
    implements CharSequence, GetChars
{
    private static class AlteredSpanned extends AlteredCharSequence
        implements Spanned
    {

        public int getSpanEnd(Object obj)
        {
            return mSpanned.getSpanEnd(obj);
        }

        public int getSpanFlags(Object obj)
        {
            return mSpanned.getSpanFlags(obj);
        }

        public int getSpanStart(Object obj)
        {
            return mSpanned.getSpanStart(obj);
        }

        public Object[] getSpans(int i, int j, Class class1)
        {
            return mSpanned.getSpans(i, j, class1);
        }

        public int nextSpanTransition(int i, int j, Class class1)
        {
            return mSpanned.nextSpanTransition(i, j, class1);
        }

        private Spanned mSpanned;

        private AlteredSpanned(CharSequence charsequence, char ac[], int i, int j)
        {
            super(charsequence, ac, i, j, null);
            mSpanned = (Spanned)charsequence;
        }

        AlteredSpanned(CharSequence charsequence, char ac[], int i, int j, AlteredSpanned alteredspanned)
        {
            this(charsequence, ac, i, j);
        }
    }


    private AlteredCharSequence(CharSequence charsequence, char ac[], int i, int j)
    {
        mSource = charsequence;
        mChars = ac;
        mStart = i;
        mEnd = j;
    }

    AlteredCharSequence(CharSequence charsequence, char ac[], int i, int j, AlteredCharSequence alteredcharsequence)
    {
        this(charsequence, ac, i, j);
    }

    public static AlteredCharSequence make(CharSequence charsequence, char ac[], int i, int j)
    {
        if(charsequence instanceof Spanned)
            return new AlteredSpanned(charsequence, ac, i, j, null);
        else
            return new AlteredCharSequence(charsequence, ac, i, j);
    }

    public char charAt(int i)
    {
        if(i >= mStart && i < mEnd)
            return mChars[i - mStart];
        else
            return mSource.charAt(i);
    }

    public void getChars(int i, int j, char ac[], int k)
    {
        TextUtils.getChars(mSource, i, j, ac, k);
        i = Math.max(mStart, i);
        j = Math.min(mEnd, j);
        if(i > j)
            System.arraycopy(mChars, i - mStart, ac, k, j - i);
    }

    public int length()
    {
        return mSource.length();
    }

    public CharSequence subSequence(int i, int j)
    {
        return make(mSource.subSequence(i, j), mChars, mStart - i, mEnd - i);
    }

    public String toString()
    {
        int i = length();
        char ac[] = new char[i];
        getChars(0, i, ac, 0);
        return String.valueOf(ac);
    }

    void update(char ac[], int i, int j)
    {
        mChars = ac;
        mStart = i;
        mEnd = j;
    }

    private char mChars[];
    private int mEnd;
    private CharSequence mSource;
    private int mStart;
}
