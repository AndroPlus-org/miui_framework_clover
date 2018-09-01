// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import java.io.IOException;

// Referenced classes of package android.text:
//            GetChars, Spannable, InputFilter, SpannableStringBuilder

public interface Editable
    extends CharSequence, GetChars, Spannable, Appendable
{
    public static class Factory
    {

        public static Factory getInstance()
        {
            return sInstance;
        }

        public Editable newEditable(CharSequence charsequence)
        {
            return new SpannableStringBuilder(charsequence);
        }

        private static Factory sInstance = new Factory();


        public Factory()
        {
        }
    }


    public abstract Editable append(char c);

    public abstract Editable append(CharSequence charsequence);

    public abstract Editable append(CharSequence charsequence, int i, int j);

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public abstract void clear();

    public abstract void clearSpans();

    public abstract Editable delete(int i, int j);

    public abstract InputFilter[] getFilters();

    public abstract Editable insert(int i, CharSequence charsequence);

    public abstract Editable insert(int i, CharSequence charsequence, int j, int k);

    public abstract Editable replace(int i, int j, CharSequence charsequence);

    public abstract Editable replace(int i, int j, CharSequence charsequence, int k, int l);

    public abstract void setFilters(InputFilter ainputfilter[]);
}
