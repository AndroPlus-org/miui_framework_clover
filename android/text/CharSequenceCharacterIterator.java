// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import java.text.CharacterIterator;

public class CharSequenceCharacterIterator
    implements CharacterIterator
{

    public CharSequenceCharacterIterator(CharSequence charsequence, int i, int j)
    {
        mCharSeq = charsequence;
        mIndex = i;
        mBeginIndex = i;
        mEndIndex = j;
    }

    public Object clone()
    {
        Object obj;
        try
        {
            obj = super.clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            throw new InternalError();
        }
        return obj;
    }

    public char current()
    {
        char c1;
        if(mIndex == mEndIndex)
        {
            int i = 65535;
            c1 = i;
        } else
        {
            char c = mCharSeq.charAt(mIndex);
            c1 = c;
        }
        return c1;
    }

    public char first()
    {
        mIndex = mBeginIndex;
        return current();
    }

    public int getBeginIndex()
    {
        return mBeginIndex;
    }

    public int getEndIndex()
    {
        return mEndIndex;
    }

    public int getIndex()
    {
        return mIndex;
    }

    public char last()
    {
        if(mBeginIndex == mEndIndex)
        {
            mIndex = mEndIndex;
            return '\uFFFF';
        } else
        {
            mIndex = mEndIndex - 1;
            return mCharSeq.charAt(mIndex);
        }
    }

    public char next()
    {
        mIndex = mIndex + 1;
        if(mIndex >= mEndIndex)
        {
            mIndex = mEndIndex;
            return '\uFFFF';
        } else
        {
            return mCharSeq.charAt(mIndex);
        }
    }

    public char previous()
    {
        if(mIndex <= mBeginIndex)
        {
            return '\uFFFF';
        } else
        {
            mIndex = mIndex - 1;
            return mCharSeq.charAt(mIndex);
        }
    }

    public char setIndex(int i)
    {
        if(mBeginIndex <= i && i <= mEndIndex)
        {
            mIndex = i;
            return current();
        } else
        {
            throw new IllegalArgumentException("invalid position");
        }
    }

    private final int mBeginIndex;
    private final CharSequence mCharSeq;
    private final int mEndIndex;
    private int mIndex;
}
