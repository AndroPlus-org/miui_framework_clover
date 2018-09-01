// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.method;

import android.icu.lang.UCharacter;
import android.icu.text.BreakIterator;
import android.text.CharSequenceCharacterIterator;
import java.util.Locale;

public class WordIterator
    implements android.text.Selection.PositionIterator
{

    public WordIterator()
    {
        this(Locale.getDefault());
    }

    public WordIterator(Locale locale)
    {
        mIterator = BreakIterator.getWordInstance(locale);
    }

    private void checkOffsetIsValid(int i)
    {
        if(mStart > i || i > mEnd)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid offset: ").append(i).append(". Valid range is [").append(mStart).append(", ").append(mEnd).append("]").toString());
        else
            return;
    }

    private int getBeginning(int i, boolean flag)
    {
        checkOffsetIsValid(i);
        if(isOnLetterOrDigit(i))
            if(mIterator.isBoundary(i) && (!isAfterLetterOrDigit(i) || flag ^ true))
                return i;
            else
                return mIterator.preceding(i);
        if(isAfterLetterOrDigit(i))
            return mIterator.preceding(i);
        else
            return -1;
    }

    private int getEnd(int i, boolean flag)
    {
        checkOffsetIsValid(i);
        if(isAfterLetterOrDigit(i))
            if(mIterator.isBoundary(i) && (!isOnLetterOrDigit(i) || flag ^ true))
                return i;
            else
                return mIterator.following(i);
        if(isOnLetterOrDigit(i))
            return mIterator.following(i);
        else
            return -1;
    }

    private boolean isAfterLetterOrDigit(int i)
    {
        return mStart < i && i <= mEnd && Character.isLetterOrDigit(Character.codePointBefore(mCharSeq, i));
    }

    public static boolean isMidWordPunctuation(Locale locale, int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        i = UCharacter.getIntPropertyValue(i, 4116);
        flag1 = flag;
        if(i == 4) goto _L2; else goto _L1
_L1:
        if(i != 11) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 15)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean isOnLetterOrDigit(int i)
    {
        return mStart <= i && i < mEnd && Character.isLetterOrDigit(Character.codePointAt(mCharSeq, i));
    }

    private static boolean isPunctuation(int i)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        i = Character.getType(i);
        flag1 = flag;
        if(i == 23) goto _L2; else goto _L1
_L1:
        if(i != 20) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 22)
        {
            flag1 = flag;
            if(i != 30)
            {
                flag1 = flag;
                if(i != 29)
                {
                    flag1 = flag;
                    if(i != 24)
                    {
                        flag1 = flag;
                        if(i != 21)
                            flag1 = false;
                    }
                }
            }
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean isPunctuationEndBoundary(int i)
    {
        boolean flag;
        if(!isOnPunctuation(i))
            flag = isAfterPunctuation(i);
        else
            flag = false;
        return flag;
    }

    private boolean isPunctuationStartBoundary(int i)
    {
        boolean flag;
        if(isOnPunctuation(i))
            flag = isAfterPunctuation(i) ^ true;
        else
            flag = false;
        return flag;
    }

    public int following(int i)
    {
        checkOffsetIsValid(i);
        int j;
        do
        {
            j = mIterator.following(i);
            if(j == -1)
                break;
            i = j;
        } while(!isAfterLetterOrDigit(j));
        return j;
    }

    public int getBeginning(int i)
    {
        return getBeginning(i, false);
    }

    public int getEnd(int i)
    {
        return getEnd(i, false);
    }

    public int getNextWordEndOnTwoWordBoundary(int i)
    {
        return getEnd(i, true);
    }

    public int getPrevWordBeginningOnTwoWordsBoundary(int i)
    {
        return getBeginning(i, true);
    }

    public int getPunctuationBeginning(int i)
    {
        checkOffsetIsValid(i);
        for(; i != -1 && isPunctuationStartBoundary(i) ^ true; i = prevBoundary(i));
        return i;
    }

    public int getPunctuationEnd(int i)
    {
        checkOffsetIsValid(i);
        for(; i != -1 && isPunctuationEndBoundary(i) ^ true; i = nextBoundary(i));
        return i;
    }

    public boolean isAfterPunctuation(int i)
    {
        if(mStart < i && i <= mEnd)
            return isPunctuation(Character.codePointBefore(mCharSeq, i));
        else
            return false;
    }

    public boolean isBoundary(int i)
    {
        checkOffsetIsValid(i);
        return mIterator.isBoundary(i);
    }

    public boolean isOnPunctuation(int i)
    {
        if(mStart <= i && i < mEnd)
            return isPunctuation(Character.codePointAt(mCharSeq, i));
        else
            return false;
    }

    public int nextBoundary(int i)
    {
        checkOffsetIsValid(i);
        return mIterator.following(i);
    }

    public int preceding(int i)
    {
        checkOffsetIsValid(i);
        int j;
        do
        {
            j = mIterator.preceding(i);
            if(j == -1)
                break;
            i = j;
        } while(!isOnLetterOrDigit(j));
        return j;
    }

    public int prevBoundary(int i)
    {
        checkOffsetIsValid(i);
        return mIterator.preceding(i);
    }

    public void setCharSequence(CharSequence charsequence, int i, int j)
    {
        if(i >= 0 && j <= charsequence.length())
        {
            mCharSeq = charsequence;
            mStart = Math.max(0, i - 50);
            mEnd = Math.min(charsequence.length(), j + 50);
            mIterator.setText(new CharSequenceCharacterIterator(charsequence, mStart, mEnd));
            return;
        } else
        {
            throw new IndexOutOfBoundsException("input indexes are outside the CharSequence");
        }
    }

    private static final int WINDOW_WIDTH = 50;
    private CharSequence mCharSeq;
    private int mEnd;
    private final BreakIterator mIterator;
    private int mStart;
}
