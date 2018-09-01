// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import com.android.internal.util.Preconditions;

public final class SizeF
{

    public SizeF(float f, float f1)
    {
        mWidth = Preconditions.checkArgumentFinite(f, "width");
        mHeight = Preconditions.checkArgumentFinite(f1, "height");
    }

    private static NumberFormatException invalidSizeF(String s)
    {
        throw new NumberFormatException((new StringBuilder()).append("Invalid SizeF: \"").append(s).append("\"").toString());
    }

    public static SizeF parseSizeF(String s)
        throws NumberFormatException
    {
        Preconditions.checkNotNull(s, "string must not be null");
        int i = s.indexOf('*');
        int j = i;
        if(i < 0)
            j = s.indexOf('x');
        if(j < 0)
            throw invalidSizeF(s);
        SizeF sizef;
        try
        {
            sizef = new SizeF(Float.parseFloat(s.substring(0, j)), Float.parseFloat(s.substring(j + 1)));
        }
        catch(NumberFormatException numberformatexception)
        {
            throw invalidSizeF(s);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            throw invalidSizeF(s);
        }
        return sizef;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(obj instanceof SizeF)
        {
            obj = (SizeF)obj;
            if(mWidth != ((SizeF) (obj)).mWidth || mHeight != ((SizeF) (obj)).mHeight)
                flag = false;
            return flag;
        } else
        {
            return false;
        }
    }

    public float getHeight()
    {
        return mHeight;
    }

    public float getWidth()
    {
        return mWidth;
    }

    public int hashCode()
    {
        return Float.floatToIntBits(mWidth) ^ Float.floatToIntBits(mHeight);
    }

    public String toString()
    {
        return (new StringBuilder()).append(mWidth).append("x").append(mHeight).toString();
    }

    private final float mHeight;
    private final float mWidth;
}
