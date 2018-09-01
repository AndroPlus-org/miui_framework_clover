// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.graphics.Rect;
import android.text.*;
import android.view.View;

// Referenced classes of package android.text.method:
//            TransformationMethod

public abstract class ReplacementTransformationMethod
    implements TransformationMethod
{
    private static class ReplacementCharSequence
        implements CharSequence, GetChars
    {

        public char charAt(int i)
        {
            char c = mSource.charAt(i);
            int j = mOriginal.length;
            i = 0;
            char c1;
            for(c1 = c; i < j; c1 = c)
            {
                c = c1;
                if(c1 == mOriginal[i])
                    c = mReplacement[i];
                i++;
            }

            return c1;
        }

        public void getChars(int i, int j, char ac[], int k)
        {
            TextUtils.getChars(mSource, i, j, ac, k);
            int l = mOriginal.length;
            for(int i1 = k; i1 < (j - i) + k; i1++)
            {
                char c = ac[i1];
                for(int j1 = 0; j1 < l; j1++)
                    if(c == mOriginal[j1])
                        ac[i1] = mReplacement[j1];

            }

        }

        public int length()
        {
            return mSource.length();
        }

        public CharSequence subSequence(int i, int j)
        {
            char ac[] = new char[j - i];
            getChars(i, j, ac, 0);
            return new String(ac);
        }

        public String toString()
        {
            char ac[] = new char[length()];
            getChars(0, length(), ac, 0);
            return new String(ac);
        }

        private char mOriginal[];
        private char mReplacement[];
        private CharSequence mSource;

        public ReplacementCharSequence(CharSequence charsequence, char ac[], char ac1[])
        {
            mSource = charsequence;
            mOriginal = ac;
            mReplacement = ac1;
        }
    }

    private static class SpannedReplacementCharSequence extends ReplacementCharSequence
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

        public CharSequence subSequence(int i, int j)
        {
            return (new SpannedString(this)).subSequence(i, j);
        }

        private Spanned mSpanned;

        public SpannedReplacementCharSequence(Spanned spanned, char ac[], char ac1[])
        {
            super(spanned, ac, ac1);
            mSpanned = spanned;
        }
    }


    public ReplacementTransformationMethod()
    {
    }

    protected abstract char[] getOriginal();

    protected abstract char[] getReplacement();

    public CharSequence getTransformation(CharSequence charsequence, View view)
    {
        char ac[];
label0:
        {
            ac = getOriginal();
            view = getReplacement();
            if(charsequence instanceof Editable)
                break label0;
            boolean flag = true;
            int i = ac.length;
            int j = 0;
label1:
            do
            {
label2:
                {
                    boolean flag1 = flag;
                    if(j < i)
                    {
                        if(TextUtils.indexOf(charsequence, ac[j]) < 0)
                            break label2;
                        flag1 = false;
                    }
                    if(flag1)
                        return charsequence;
                    break label1;
                }
                j++;
            } while(true);
            if(!(charsequence instanceof Spannable))
                if(charsequence instanceof Spanned)
                    return new SpannedString(new SpannedReplacementCharSequence((Spanned)charsequence, ac, view));
                else
                    return (new ReplacementCharSequence(charsequence, ac, view)).toString();
        }
        if(charsequence instanceof Spanned)
            return new SpannedReplacementCharSequence((Spanned)charsequence, ac, view);
        else
            return new ReplacementCharSequence(charsequence, ac, view);
    }

    public void onFocusChanged(View view, CharSequence charsequence, boolean flag, int i, Rect rect)
    {
    }
}
