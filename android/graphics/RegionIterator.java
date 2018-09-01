// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Region, Rect

public class RegionIterator
{

    public RegionIterator(Region region)
    {
        mNativeIter = nativeConstructor(region.ni());
    }

    private static native long nativeConstructor(long l);

    private static native void nativeDestructor(long l);

    private static native boolean nativeNext(long l, Rect rect);

    protected void finalize()
        throws Throwable
    {
        nativeDestructor(mNativeIter);
        mNativeIter = 0L;
    }

    public final boolean next(Rect rect)
    {
        if(rect == null)
            throw new NullPointerException("The Rect must be provided");
        else
            return nativeNext(mNativeIter, rect);
    }

    private long mNativeIter;
}
