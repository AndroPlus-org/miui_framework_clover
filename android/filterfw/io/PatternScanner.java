// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternScanner
{

    public PatternScanner(String s)
    {
        mOffset = 0;
        mLineNo = 0;
        mStartOfLine = 0;
        mInput = s;
    }

    public PatternScanner(String s, Pattern pattern)
    {
        mOffset = 0;
        mLineNo = 0;
        mStartOfLine = 0;
        mInput = s;
        mIgnorePattern = pattern;
        skip(mIgnorePattern);
    }

    public boolean atEnd()
    {
        boolean flag;
        if(mOffset >= mInput.length())
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String eat(Pattern pattern, String s)
    {
        pattern = tryEat(pattern);
        if(pattern == null)
            throw new RuntimeException(unexpectedTokenMessage(s));
        else
            return pattern;
    }

    public int lineNo()
    {
        return mLineNo;
    }

    public boolean peek(Pattern pattern)
    {
        if(mIgnorePattern != null)
            skip(mIgnorePattern);
        pattern = pattern.matcher(mInput);
        pattern.region(mOffset, mInput.length());
        return pattern.lookingAt();
    }

    public void skip(Pattern pattern)
    {
        pattern = pattern.matcher(mInput);
        pattern.region(mOffset, mInput.length());
        if(pattern.lookingAt())
        {
            updateLineCount(mOffset, pattern.end());
            mOffset = pattern.end();
        }
    }

    public String tryEat(Pattern pattern)
    {
        if(mIgnorePattern != null)
            skip(mIgnorePattern);
        Matcher matcher = pattern.matcher(mInput);
        matcher.region(mOffset, mInput.length());
        pattern = null;
        if(matcher.lookingAt())
        {
            updateLineCount(mOffset, matcher.end());
            mOffset = matcher.end();
            pattern = mInput.substring(matcher.start(), matcher.end());
        }
        if(pattern != null && mIgnorePattern != null)
            skip(mIgnorePattern);
        return pattern;
    }

    public String unexpectedTokenMessage(String s)
    {
        String s1 = mInput.substring(mStartOfLine, mOffset);
        return (new StringBuilder()).append("Unexpected token on line ").append(mLineNo + 1).append(" after '").append(s1).append("' <- Expected ").append(s).append("!").toString();
    }

    public void updateLineCount(int i, int j)
    {
        for(; i < j; i++)
            if(mInput.charAt(i) == '\n')
            {
                mLineNo = mLineNo + 1;
                mStartOfLine = i + 1;
            }

    }

    private Pattern mIgnorePattern;
    private String mInput;
    private int mLineNo;
    private int mOffset;
    private int mStartOfLine;
}
