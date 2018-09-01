// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;


// Referenced classes of package android.graphics:
//            Region, RectF, Matrix

public class Path
{
    public static final class Direction extends Enum
    {

        public static Direction valueOf(String s)
        {
            return (Direction)Enum.valueOf(android/graphics/Path$Direction, s);
        }

        public static Direction[] values()
        {
            return $VALUES;
        }

        private static final Direction $VALUES[];
        public static final Direction CCW;
        public static final Direction CW;
        final int nativeInt;

        static 
        {
            CW = new Direction("CW", 0, 0);
            CCW = new Direction("CCW", 1, 1);
            $VALUES = (new Direction[] {
                CW, CCW
            });
        }

        private Direction(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }

    public static final class FillType extends Enum
    {

        public static FillType valueOf(String s)
        {
            return (FillType)Enum.valueOf(android/graphics/Path$FillType, s);
        }

        public static FillType[] values()
        {
            return $VALUES;
        }

        private static final FillType $VALUES[];
        public static final FillType EVEN_ODD;
        public static final FillType INVERSE_EVEN_ODD;
        public static final FillType INVERSE_WINDING;
        public static final FillType WINDING;
        final int nativeInt;

        static 
        {
            WINDING = new FillType("WINDING", 0, 0);
            EVEN_ODD = new FillType("EVEN_ODD", 1, 1);
            INVERSE_WINDING = new FillType("INVERSE_WINDING", 2, 2);
            INVERSE_EVEN_ODD = new FillType("INVERSE_EVEN_ODD", 3, 3);
            $VALUES = (new FillType[] {
                WINDING, EVEN_ODD, INVERSE_WINDING, INVERSE_EVEN_ODD
            });
        }

        private FillType(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }

    public static final class Op extends Enum
    {

        public static Op valueOf(String s)
        {
            return (Op)Enum.valueOf(android/graphics/Path$Op, s);
        }

        public static Op[] values()
        {
            return $VALUES;
        }

        private static final Op $VALUES[];
        public static final Op DIFFERENCE;
        public static final Op INTERSECT;
        public static final Op REVERSE_DIFFERENCE;
        public static final Op UNION;
        public static final Op XOR;

        static 
        {
            DIFFERENCE = new Op("DIFFERENCE", 0);
            INTERSECT = new Op("INTERSECT", 1);
            UNION = new Op("UNION", 2);
            XOR = new Op("XOR", 3);
            REVERSE_DIFFERENCE = new Op("REVERSE_DIFFERENCE", 4);
            $VALUES = (new Op[] {
                DIFFERENCE, INTERSECT, UNION, XOR, REVERSE_DIFFERENCE
            });
        }

        private Op(String s, int i)
        {
            super(s, i);
        }
    }


    public Path()
    {
        isSimplePath = true;
        mLastDirection = null;
        mNativePath = nInit();
    }

    public Path(Path path)
    {
        isSimplePath = true;
        mLastDirection = null;
        long l = 0L;
        if(path != null)
        {
            long l1 = path.mNativePath;
            isSimplePath = path.isSimplePath;
            l = l1;
            if(path.rects != null)
            {
                rects = new Region(path.rects);
                l = l1;
            }
        }
        mNativePath = nInit(l);
    }

    private void detectSimplePath(float f, float f1, float f2, float f3, Direction direction)
    {
        if(mLastDirection == null)
            mLastDirection = direction;
        if(mLastDirection != direction)
        {
            isSimplePath = false;
        } else
        {
            if(rects == null)
                rects = new Region();
            rects.op((int)f, (int)f1, (int)f2, (int)f3, Region.Op.UNION);
        }
    }

    private static native void nAddArc(long l, float f, float f1, float f2, float f3, float f4, float f5);

    private static native void nAddCircle(long l, float f, float f1, float f2, int i);

    private static native void nAddOval(long l, float f, float f1, float f2, float f3, int i);

    private static native void nAddPath(long l, long l1);

    private static native void nAddPath(long l, long l1, float f, float f1);

    private static native void nAddPath(long l, long l1, long l2);

    private static native void nAddRect(long l, float f, float f1, float f2, float f3, int i);

    private static native void nAddRoundRect(long l, float f, float f1, float f2, float f3, float f4, float f5, 
            int i);

    private static native void nAddRoundRect(long l, float f, float f1, float f2, float f3, float af[], int i);

    private static native float[] nApproximate(long l, float f);

    private static native void nArcTo(long l, float f, float f1, float f2, float f3, float f4, float f5, 
            boolean flag);

    private static native void nClose(long l);

    private static native void nComputeBounds(long l, RectF rectf);

    private static native void nCubicTo(long l, float f, float f1, float f2, float f3, float f4, float f5);

    private static native void nFinalize(long l);

    private static native int nGetFillType(long l);

    private static native void nIncReserve(long l, int i);

    private static native long nInit();

    private static native long nInit(long l);

    private static native boolean nIsConvex(long l);

    private static native boolean nIsEmpty(long l);

    private static native boolean nIsRect(long l, RectF rectf);

    private static native void nLineTo(long l, float f, float f1);

    private static native void nMoveTo(long l, float f, float f1);

    private static native void nOffset(long l, float f, float f1);

    private static native boolean nOp(long l, long l1, int i, long l2);

    private static native void nQuadTo(long l, float f, float f1, float f2, float f3);

    private static native void nRCubicTo(long l, float f, float f1, float f2, float f3, float f4, float f5);

    private static native void nRLineTo(long l, float f, float f1);

    private static native void nRMoveTo(long l, float f, float f1);

    private static native void nRQuadTo(long l, float f, float f1, float f2, float f3);

    private static native void nReset(long l);

    private static native void nRewind(long l);

    private static native void nSet(long l, long l1);

    private static native void nSetFillType(long l, int i);

    private static native void nSetLastPoint(long l, float f, float f1);

    private static native void nTransform(long l, long l1);

    private static native void nTransform(long l, long l1, long l2);

    public void addArc(float f, float f1, float f2, float f3, float f4, float f5)
    {
        isSimplePath = false;
        nAddArc(mNativePath, f, f1, f2, f3, f4, f5);
    }

    public void addArc(RectF rectf, float f, float f1)
    {
        addArc(rectf.left, rectf.top, rectf.right, rectf.bottom, f, f1);
    }

    public void addCircle(float f, float f1, float f2, Direction direction)
    {
        isSimplePath = false;
        nAddCircle(mNativePath, f, f1, f2, direction.nativeInt);
    }

    public void addOval(float f, float f1, float f2, float f3, Direction direction)
    {
        isSimplePath = false;
        nAddOval(mNativePath, f, f1, f2, f3, direction.nativeInt);
    }

    public void addOval(RectF rectf, Direction direction)
    {
        addOval(rectf.left, rectf.top, rectf.right, rectf.bottom, direction);
    }

    public void addPath(Path path)
    {
        isSimplePath = false;
        nAddPath(mNativePath, path.mNativePath);
    }

    public void addPath(Path path, float f, float f1)
    {
        isSimplePath = false;
        nAddPath(mNativePath, path.mNativePath, f, f1);
    }

    public void addPath(Path path, Matrix matrix)
    {
        if(!path.isSimplePath)
            isSimplePath = false;
        nAddPath(mNativePath, path.mNativePath, matrix.native_instance);
    }

    public void addRect(float f, float f1, float f2, float f3, Direction direction)
    {
        detectSimplePath(f, f1, f2, f3, direction);
        nAddRect(mNativePath, f, f1, f2, f3, direction.nativeInt);
    }

    public void addRect(RectF rectf, Direction direction)
    {
        addRect(rectf.left, rectf.top, rectf.right, rectf.bottom, direction);
    }

    public void addRoundRect(float f, float f1, float f2, float f3, float f4, float f5, Direction direction)
    {
        isSimplePath = false;
        nAddRoundRect(mNativePath, f, f1, f2, f3, f4, f5, direction.nativeInt);
    }

    public void addRoundRect(float f, float f1, float f2, float f3, float af[], Direction direction)
    {
        if(af.length < 8)
        {
            throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
        } else
        {
            isSimplePath = false;
            nAddRoundRect(mNativePath, f, f1, f2, f3, af, direction.nativeInt);
            return;
        }
    }

    public void addRoundRect(RectF rectf, float f, float f1, Direction direction)
    {
        addRoundRect(rectf.left, rectf.top, rectf.right, rectf.bottom, f, f1, direction);
    }

    public void addRoundRect(RectF rectf, float af[], Direction direction)
    {
        if(rectf == null)
        {
            throw new NullPointerException("need rect parameter");
        } else
        {
            addRoundRect(rectf.left, rectf.top, rectf.right, rectf.bottom, af, direction);
            return;
        }
    }

    public float[] approximate(float f)
    {
        if(f < 0.0F)
            throw new IllegalArgumentException("AcceptableError must be greater than or equal to 0");
        else
            return nApproximate(mNativePath, f);
    }

    public void arcTo(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        isSimplePath = false;
        nArcTo(mNativePath, f, f1, f2, f3, f4, f5, flag);
    }

    public void arcTo(RectF rectf, float f, float f1)
    {
        arcTo(rectf.left, rectf.top, rectf.right, rectf.bottom, f, f1, false);
    }

    public void arcTo(RectF rectf, float f, float f1, boolean flag)
    {
        arcTo(rectf.left, rectf.top, rectf.right, rectf.bottom, f, f1, flag);
    }

    public void close()
    {
        isSimplePath = false;
        nClose(mNativePath);
    }

    public void computeBounds(RectF rectf, boolean flag)
    {
        nComputeBounds(mNativePath, rectf);
    }

    public void cubicTo(float f, float f1, float f2, float f3, float f4, float f5)
    {
        isSimplePath = false;
        nCubicTo(mNativePath, f, f1, f2, f3, f4, f5);
    }

    protected void finalize()
        throws Throwable
    {
        nFinalize(mNativePath);
        mNativePath = 0L;
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public FillType getFillType()
    {
        return sFillTypeArray[nGetFillType(mNativePath)];
    }

    public void incReserve(int i)
    {
        nIncReserve(mNativePath, i);
    }

    public boolean isConvex()
    {
        return nIsConvex(mNativePath);
    }

    public boolean isEmpty()
    {
        return nIsEmpty(mNativePath);
    }

    public boolean isInverseFillType()
    {
        boolean flag = false;
        int i = nGetFillType(mNativePath);
        if((FillType.INVERSE_WINDING.nativeInt & i) != 0)
            flag = true;
        return flag;
    }

    public boolean isRect(RectF rectf)
    {
        return nIsRect(mNativePath, rectf);
    }

    public void lineTo(float f, float f1)
    {
        isSimplePath = false;
        nLineTo(mNativePath, f, f1);
    }

    public void moveTo(float f, float f1)
    {
        nMoveTo(mNativePath, f, f1);
    }

    final long mutateNI()
    {
        isSimplePath = false;
        return mNativePath;
    }

    public void offset(float f, float f1)
    {
        if(isSimplePath && rects == null)
            return;
        if(isSimplePath && (double)f == Math.rint(f) && (double)f1 == Math.rint(f1))
            rects.translate((int)f, (int)f1);
        else
            isSimplePath = false;
        nOffset(mNativePath, f, f1);
    }

    public void offset(float f, float f1, Path path)
    {
        if(path != null)
            path.set(this);
        else
            path = this;
        path.offset(f, f1);
    }

    public boolean op(Path path, Op op1)
    {
        return op(this, path, op1);
    }

    public boolean op(Path path, Path path1, Op op1)
    {
        if(nOp(path.mNativePath, path1.mNativePath, op1.ordinal(), mNativePath))
        {
            isSimplePath = false;
            rects = null;
            return true;
        } else
        {
            return false;
        }
    }

    public void quadTo(float f, float f1, float f2, float f3)
    {
        isSimplePath = false;
        nQuadTo(mNativePath, f, f1, f2, f3);
    }

    public void rCubicTo(float f, float f1, float f2, float f3, float f4, float f5)
    {
        isSimplePath = false;
        nRCubicTo(mNativePath, f, f1, f2, f3, f4, f5);
    }

    public void rLineTo(float f, float f1)
    {
        isSimplePath = false;
        nRLineTo(mNativePath, f, f1);
    }

    public void rMoveTo(float f, float f1)
    {
        nRMoveTo(mNativePath, f, f1);
    }

    public void rQuadTo(float f, float f1, float f2, float f3)
    {
        isSimplePath = false;
        nRQuadTo(mNativePath, f, f1, f2, f3);
    }

    public final long readOnlyNI()
    {
        return mNativePath;
    }

    public void reset()
    {
        isSimplePath = true;
        mLastDirection = null;
        if(rects != null)
            rects.setEmpty();
        FillType filltype = getFillType();
        nReset(mNativePath);
        setFillType(filltype);
    }

    public void rewind()
    {
        isSimplePath = true;
        mLastDirection = null;
        if(rects != null)
            rects.setEmpty();
        nRewind(mNativePath);
    }

    public void set(Path path)
    {
        if(this == path)
            return;
        isSimplePath = path.isSimplePath;
        nSet(mNativePath, path.mNativePath);
        if(!isSimplePath)
            return;
        if(rects == null || path.rects == null) goto _L2; else goto _L1
_L1:
        rects.set(path.rects);
_L4:
        return;
_L2:
        if(rects != null && path.rects == null)
            rects.setEmpty();
        else
        if(path.rects != null)
            rects = new Region(path.rects);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setFillType(FillType filltype)
    {
        nSetFillType(mNativePath, filltype.nativeInt);
    }

    public void setLastPoint(float f, float f1)
    {
        isSimplePath = false;
        nSetLastPoint(mNativePath, f, f1);
    }

    public void toggleInverseFillType()
    {
        int i = nGetFillType(mNativePath);
        int j = FillType.INVERSE_WINDING.nativeInt;
        nSetFillType(mNativePath, i ^ j);
    }

    public void transform(Matrix matrix)
    {
        isSimplePath = false;
        nTransform(mNativePath, matrix.native_instance);
    }

    public void transform(Matrix matrix, Path path)
    {
        long l = 0L;
        if(path != null)
        {
            path.isSimplePath = false;
            l = path.mNativePath;
        }
        nTransform(mNativePath, matrix.native_instance, l);
    }

    static final FillType sFillTypeArray[];
    public boolean isSimplePath;
    private Direction mLastDirection;
    public long mNativePath;
    public Region rects;

    static 
    {
        sFillTypeArray = (new FillType[] {
            FillType.WINDING, FillType.EVEN_ODD, FillType.INVERSE_WINDING, FillType.INVERSE_EVEN_ODD
        });
    }
}
