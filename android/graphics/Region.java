// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.graphics:
//            Rect, Path

public class Region
    implements Parcelable
{
    public static final class Op extends Enum
    {

        public static Op valueOf(String s)
        {
            return (Op)Enum.valueOf(android/graphics/Region$Op, s);
        }

        public static Op[] values()
        {
            return $VALUES;
        }

        private static final Op $VALUES[];
        public static final Op DIFFERENCE;
        public static final Op INTERSECT;
        public static final Op REPLACE;
        public static final Op REVERSE_DIFFERENCE;
        public static final Op UNION;
        public static final Op XOR;
        public final int nativeInt;

        static 
        {
            DIFFERENCE = new Op("DIFFERENCE", 0, 0);
            INTERSECT = new Op("INTERSECT", 1, 1);
            UNION = new Op("UNION", 2, 2);
            XOR = new Op("XOR", 3, 3);
            REVERSE_DIFFERENCE = new Op("REVERSE_DIFFERENCE", 4, 4);
            REPLACE = new Op("REPLACE", 5, 5);
            $VALUES = (new Op[] {
                DIFFERENCE, INTERSECT, UNION, XOR, REVERSE_DIFFERENCE, REPLACE
            });
        }

        private Op(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    static long _2D_wrap0(Parcel parcel)
    {
        return nativeCreateFromParcel(parcel);
    }

    public Region()
    {
        this(nativeConstructor());
    }

    public Region(int i, int j, int k, int l)
    {
        mNativeRegion = nativeConstructor();
        nativeSetRect(mNativeRegion, i, j, k, l);
    }

    Region(long l)
    {
        if(l == 0L)
        {
            throw new RuntimeException();
        } else
        {
            mNativeRegion = l;
            return;
        }
    }

    private Region(long l, int i)
    {
        this(l);
    }

    public Region(Rect rect)
    {
        mNativeRegion = nativeConstructor();
        nativeSetRect(mNativeRegion, rect.left, rect.top, rect.right, rect.bottom);
    }

    public Region(Region region)
    {
        this(nativeConstructor());
        nativeSetRegion(mNativeRegion, region.mNativeRegion);
    }

    private static native long nativeConstructor();

    private static native long nativeCreateFromParcel(Parcel parcel);

    private static native void nativeDestructor(long l);

    private static native boolean nativeEquals(long l, long l1);

    private static native boolean nativeGetBoundaryPath(long l, long l1);

    private static native boolean nativeGetBounds(long l, Rect rect);

    private static native boolean nativeOp(long l, int i, int j, int k, int i1, int j1);

    private static native boolean nativeOp(long l, long l1, long l2, int i);

    private static native boolean nativeOp(long l, Rect rect, long l1, int i);

    private static native boolean nativeSetPath(long l, long l1, long l2);

    private static native boolean nativeSetRect(long l, int i, int j, int k, int i1);

    private static native void nativeSetRegion(long l, long l1);

    private static native String nativeToString(long l);

    private static native boolean nativeWriteToParcel(long l, Parcel parcel);

    public static Region obtain()
    {
        Region region = (Region)sPool.acquire();
        if(region == null)
            region = new Region();
        return region;
    }

    public static Region obtain(Region region)
    {
        Region region1 = obtain();
        region1.set(region);
        return region1;
    }

    public native boolean contains(int i, int j);

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == null || (obj instanceof Region) ^ true)
        {
            return false;
        } else
        {
            obj = (Region)obj;
            return nativeEquals(mNativeRegion, ((Region) (obj)).mNativeRegion);
        }
    }

    protected void finalize()
        throws Throwable
    {
        nativeDestructor(mNativeRegion);
        mNativeRegion = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public Path getBoundaryPath()
    {
        Path path = new Path();
        nativeGetBoundaryPath(mNativeRegion, path.mutateNI());
        return path;
    }

    public boolean getBoundaryPath(Path path)
    {
        return nativeGetBoundaryPath(mNativeRegion, path.mutateNI());
    }

    public Rect getBounds()
    {
        Rect rect = new Rect();
        nativeGetBounds(mNativeRegion, rect);
        return rect;
    }

    public boolean getBounds(Rect rect)
    {
        if(rect == null)
            throw new NullPointerException();
        else
            return nativeGetBounds(mNativeRegion, rect);
    }

    public native boolean isComplex();

    public native boolean isEmpty();

    public native boolean isRect();

    final long ni()
    {
        return mNativeRegion;
    }

    public boolean op(int i, int j, int k, int l, Op op1)
    {
        return nativeOp(mNativeRegion, i, j, k, l, op1.nativeInt);
    }

    public boolean op(Rect rect, Op op1)
    {
        return nativeOp(mNativeRegion, rect.left, rect.top, rect.right, rect.bottom, op1.nativeInt);
    }

    public boolean op(Rect rect, Region region, Op op1)
    {
        return nativeOp(mNativeRegion, rect, region.mNativeRegion, op1.nativeInt);
    }

    public boolean op(Region region, Op op1)
    {
        return op(this, region, op1);
    }

    public boolean op(Region region, Region region1, Op op1)
    {
        return nativeOp(mNativeRegion, region.mNativeRegion, region1.mNativeRegion, op1.nativeInt);
    }

    public native boolean quickContains(int i, int j, int k, int l);

    public boolean quickContains(Rect rect)
    {
        return quickContains(rect.left, rect.top, rect.right, rect.bottom);
    }

    public native boolean quickReject(int i, int j, int k, int l);

    public boolean quickReject(Rect rect)
    {
        return quickReject(rect.left, rect.top, rect.right, rect.bottom);
    }

    public native boolean quickReject(Region region);

    public void recycle()
    {
        setEmpty();
        sPool.release(this);
    }

    public void scale(float f)
    {
        scale(f, null);
    }

    public native void scale(float f, Region region);

    public boolean set(int i, int j, int k, int l)
    {
        return nativeSetRect(mNativeRegion, i, j, k, l);
    }

    public boolean set(Rect rect)
    {
        return nativeSetRect(mNativeRegion, rect.left, rect.top, rect.right, rect.bottom);
    }

    public boolean set(Region region)
    {
        nativeSetRegion(mNativeRegion, region.mNativeRegion);
        return true;
    }

    public void setEmpty()
    {
        nativeSetRect(mNativeRegion, 0, 0, 0, 0);
    }

    public boolean setPath(Path path, Region region)
    {
        return nativeSetPath(mNativeRegion, path.readOnlyNI(), region.mNativeRegion);
    }

    public String toString()
    {
        return nativeToString(mNativeRegion);
    }

    public void translate(int i, int j)
    {
        translate(i, j, null);
    }

    public native void translate(int i, int j, Region region);

    public final boolean union(Rect rect)
    {
        return op(rect, Op.UNION);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(!nativeWriteToParcel(mNativeRegion, parcel))
            throw new RuntimeException();
        else
            return;
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Region createFromParcel(Parcel parcel)
        {
            long l = Region._2D_wrap0(parcel);
            if(l == 0L)
                throw new RuntimeException();
            else
                return new Region(l);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Region[] newArray(int i)
        {
            return new Region[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_POOL_SIZE = 10;
    private static final android.util.Pools.SynchronizedPool sPool = new android.util.Pools.SynchronizedPool(10);
    public long mNativeRegion;

}
