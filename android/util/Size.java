// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.Preconditions;

public final class Size
{

    public Size(int i, int j)
    {
        mWidth = i;
        mHeight = j;
    }

    private static NumberFormatException invalidSize(String s)
    {
        throw new NumberFormatException((new StringBuilder()).append("Invalid Size: \"").append(s).append("\"").toString());
    }

    public static Size parseSize(String s)
        throws NumberFormatException
    {
        Preconditions.checkNotNull(s, "string must not be null");
        int i = s.indexOf('*');
        int j = i;
        if(i < 0)
            j = s.indexOf('x');
        if(j < 0)
            throw invalidSize(s);
        Size size;
        try
        {
            size = new Size(Integer.parseInt(s.substring(0, j)), Integer.parseInt(s.substring(j + 1)));
        }
        catch(NumberFormatException numberformatexception)
        {
            throw invalidSize(s);
        }
        return size;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof Size)
        {
            obj = (Size)obj;
            if(mWidth != ((Size) (obj)).mWidth || mHeight != ((Size) (obj)).mHeight)
                flag = false;
            return flag;
        } else
        {
            return false;
        }
    }

    public int getHeight()
    {
        return mHeight;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int hashCode()
    {
        return mHeight ^ (mWidth << 16 | mWidth >>> 16);
    }

    public String toString()
    {
        return (new StringBuilder()).append(mWidth).append("x").append(mHeight).toString();
    }

    private final int mHeight;
    private final int mWidth;
}
