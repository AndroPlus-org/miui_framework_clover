// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;


// Referenced classes of package android.text:
//            SpannableStringInternal, GetChars, Spanned

public final class SpannedString extends SpannableStringInternal
    implements CharSequence, GetChars, Spanned
{

    public SpannedString(CharSequence charsequence)
    {
        super(charsequence, 0, charsequence.length());
    }

    private SpannedString(CharSequence charsequence, int i, int j)
    {
        super(charsequence, i, j);
    }

    public static SpannedString valueOf(CharSequence charsequence)
    {
        if(charsequence instanceof SpannedString)
            return (SpannedString)charsequence;
        else
            return new SpannedString(charsequence);
    }

    public volatile boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public volatile int getSpanEnd(Object obj)
    {
        return super.getSpanEnd(obj);
    }

    public volatile int getSpanFlags(Object obj)
    {
        return super.getSpanFlags(obj);
    }

    public volatile int getSpanStart(Object obj)
    {
        return super.getSpanStart(obj);
    }

    public volatile Object[] getSpans(int i, int j, Class class1)
    {
        return super.getSpans(i, j, class1);
    }

    public volatile int hashCode()
    {
        return super.hashCode();
    }

    public volatile int nextSpanTransition(int i, int j, Class class1)
    {
        return super.nextSpanTransition(i, j, class1);
    }

    public CharSequence subSequence(int i, int j)
    {
        return new SpannedString(this, i, j);
    }
}
