// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text.util;


public class Rfc822Token
{

    public Rfc822Token(String s, String s1, String s2)
    {
        mName = s;
        mAddress = s1;
        mComment = s2;
    }

    public static String quoteComment(String s)
    {
        int i;
        StringBuilder stringbuilder;
        int j;
        i = s.length();
        stringbuilder = new StringBuilder();
        j = 0;
_L2:
        char c;
        if(j >= i)
            break MISSING_BLOCK_LABEL_71;
        c = s.charAt(j);
        break MISSING_BLOCK_LABEL_27;
        if(c == '(' || c == ')' || c == '\\')
            stringbuilder.append('\\');
        stringbuilder.append(c);
        j++;
        continue; /* Loop/switch isn't completed */
        return stringbuilder.toString();
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static String quoteName(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            char c = s.charAt(j);
            if(c == '\\' || c == '"')
                stringbuilder.append('\\');
            stringbuilder.append(c);
        }

        return stringbuilder.toString();
    }

    public static String quoteNameIfNecessary(String s)
    {
        int i = s.length();
        for(int j = 0; j < i; j++)
        {
            char c = s.charAt(j);
            if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && c != ' ' && (c < '0' || c > '9'))
                return (new StringBuilder()).append('"').append(quoteName(s)).append('"').toString();
        }

        return s;
    }

    private static boolean stringEquals(String s, String s1)
    {
        if(s == null)
        {
            boolean flag;
            if(s1 == null)
                flag = true;
            else
                flag = false;
            return flag;
        } else
        {
            return s.equals(s1);
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof Rfc822Token))
            return false;
        obj = (Rfc822Token)obj;
        boolean flag1 = flag;
        if(stringEquals(mName, ((Rfc822Token) (obj)).mName))
        {
            flag1 = flag;
            if(stringEquals(mAddress, ((Rfc822Token) (obj)).mAddress))
                flag1 = stringEquals(mComment, ((Rfc822Token) (obj)).mComment);
        }
        return flag1;
    }

    public String getAddress()
    {
        return mAddress;
    }

    public String getComment()
    {
        return mComment;
    }

    public String getName()
    {
        return mName;
    }

    public int hashCode()
    {
        int i = 17;
        if(mName != null)
            i = mName.hashCode() + 527;
        int j = i;
        if(mAddress != null)
            j = i * 31 + mAddress.hashCode();
        i = j;
        if(mComment != null)
            i = j * 31 + mComment.hashCode();
        return i;
    }

    public void setAddress(String s)
    {
        mAddress = s;
    }

    public void setComment(String s)
    {
        mComment = s;
    }

    public void setName(String s)
    {
        mName = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(mName != null && mName.length() != 0)
        {
            stringbuilder.append(quoteNameIfNecessary(mName));
            stringbuilder.append(' ');
        }
        if(mComment != null && mComment.length() != 0)
        {
            stringbuilder.append('(');
            stringbuilder.append(quoteComment(mComment));
            stringbuilder.append(") ");
        }
        if(mAddress != null && mAddress.length() != 0)
        {
            stringbuilder.append('<');
            stringbuilder.append(mAddress);
            stringbuilder.append('>');
        }
        return stringbuilder.toString();
    }

    private String mAddress;
    private String mComment;
    private String mName;
}
