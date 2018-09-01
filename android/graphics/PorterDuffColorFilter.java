// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            ColorFilter

public class PorterDuffColorFilter extends ColorFilter
{

    public PorterDuffColorFilter(int i, PorterDuff.Mode mode)
    {
        mColor = i;
        mMode = mode;
    }

    private static native long native_CreatePorterDuffFilter(int i, int j);

    long createNativeInstance()
    {
        return native_CreatePorterDuffFilter(mColor, mMode.nativeInt);
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (PorterDuffColorFilter)obj;
        if(mColor != ((PorterDuffColorFilter) (obj)).mColor || mMode.nativeInt != ((PorterDuffColorFilter) (obj)).mMode.nativeInt)
            flag = false;
        return flag;
    }

    public int getColor()
    {
        return mColor;
    }

    public PorterDuff.Mode getMode()
    {
        return mMode;
    }

    public int hashCode()
    {
        return mMode.hashCode() * 31 + mColor;
    }

    public void setColor(int i)
    {
        if(mColor != i)
        {
            mColor = i;
            discardNativeInstance();
        }
    }

    public void setMode(PorterDuff.Mode mode)
    {
        if(mode == null)
        {
            throw new IllegalArgumentException("mode must be non-null");
        } else
        {
            mMode = mode;
            discardNativeInstance();
            return;
        }
    }

    private int mColor;
    private PorterDuff.Mode mMode;
}
