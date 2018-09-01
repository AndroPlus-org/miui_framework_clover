// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import java.io.PrintWriter;
import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.graphics:
//            RectF

public class Matrix
{
    private static class NoImagePreloadHolder
    {

        public static final NativeAllocationRegistry sRegistry = new NativeAllocationRegistry(android/graphics/Matrix.getClassLoader(), Matrix._2D_wrap0(), 40L);


        private NoImagePreloadHolder()
        {
        }
    }

    public static final class ScaleToFit extends Enum
    {

        public static ScaleToFit valueOf(String s)
        {
            return (ScaleToFit)Enum.valueOf(android/graphics/Matrix$ScaleToFit, s);
        }

        public static ScaleToFit[] values()
        {
            return $VALUES;
        }

        private static final ScaleToFit $VALUES[];
        public static final ScaleToFit CENTER;
        public static final ScaleToFit END;
        public static final ScaleToFit FILL;
        public static final ScaleToFit START;
        final int nativeInt;

        static 
        {
            FILL = new ScaleToFit("FILL", 0, 0);
            START = new ScaleToFit("START", 1, 1);
            CENTER = new ScaleToFit("CENTER", 2, 2);
            END = new ScaleToFit("END", 3, 3);
            $VALUES = (new ScaleToFit[] {
                FILL, START, CENTER, END
            });
        }

        private ScaleToFit(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    static long _2D_wrap0()
    {
        return nGetNativeFinalizer();
    }

    public Matrix()
    {
        native_instance = nCreate(0L);
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, native_instance);
    }

    public Matrix(Matrix matrix)
    {
        long l;
        if(matrix != null)
            l = matrix.native_instance;
        else
            l = 0L;
        native_instance = nCreate(l);
        NoImagePreloadHolder.sRegistry.registerNativeAllocation(this, native_instance);
    }

    private static void checkPointArrays(float af[], int i, float af1[], int j, int k)
    {
        int l = i + (k << 1);
        for(int i1 = j + (k << 1); (k | i | j | l | i1) < 0 || l > af.length || i1 > af1.length;)
            throw new ArrayIndexOutOfBoundsException();

    }

    private static native long nCreate(long l);

    private static native boolean nEquals(long l, long l1);

    private static native long nGetNativeFinalizer();

    private static native void nGetValues(long l, float af[]);

    private static native boolean nInvert(long l, long l1);

    private static native boolean nIsAffine(long l);

    private static native boolean nIsIdentity(long l);

    private static native void nMapPoints(long l, float af[], int i, float af1[], int j, int k, boolean flag);

    private static native float nMapRadius(long l, float f);

    private static native boolean nMapRect(long l, RectF rectf, RectF rectf1);

    private static native void nPostConcat(long l, long l1);

    private static native void nPostRotate(long l, float f);

    private static native void nPostRotate(long l, float f, float f1, float f2);

    private static native void nPostScale(long l, float f, float f1);

    private static native void nPostScale(long l, float f, float f1, float f2, float f3);

    private static native void nPostSkew(long l, float f, float f1);

    private static native void nPostSkew(long l, float f, float f1, float f2, float f3);

    private static native void nPostTranslate(long l, float f, float f1);

    private static native void nPreConcat(long l, long l1);

    private static native void nPreRotate(long l, float f);

    private static native void nPreRotate(long l, float f, float f1, float f2);

    private static native void nPreScale(long l, float f, float f1);

    private static native void nPreScale(long l, float f, float f1, float f2, float f3);

    private static native void nPreSkew(long l, float f, float f1);

    private static native void nPreSkew(long l, float f, float f1, float f2, float f3);

    private static native void nPreTranslate(long l, float f, float f1);

    private static native boolean nRectStaysRect(long l);

    private static native void nReset(long l);

    private static native void nSet(long l, long l1);

    private static native void nSetConcat(long l, long l1, long l2);

    private static native boolean nSetPolyToPoly(long l, float af[], int i, float af1[], int j, int k);

    private static native boolean nSetRectToRect(long l, RectF rectf, RectF rectf1, int i);

    private static native void nSetRotate(long l, float f);

    private static native void nSetRotate(long l, float f, float f1, float f2);

    private static native void nSetScale(long l, float f, float f1);

    private static native void nSetScale(long l, float f, float f1, float f2, float f3);

    private static native void nSetSinCos(long l, float f, float f1);

    private static native void nSetSinCos(long l, float f, float f1, float f2, float f3);

    private static native void nSetSkew(long l, float f, float f1);

    private static native void nSetSkew(long l, float f, float f1, float f2, float f3);

    private static native void nSetTranslate(long l, float f, float f1);

    private static native void nSetValues(long l, float af[]);

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Matrix))
            return false;
        else
            return nEquals(native_instance, ((Matrix)obj).native_instance);
    }

    public void getValues(float af[])
    {
        if(af.length < 9)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            nGetValues(native_instance, af);
            return;
        }
    }

    public int hashCode()
    {
        return 44;
    }

    public boolean invert(Matrix matrix)
    {
        return nInvert(native_instance, matrix.native_instance);
    }

    public boolean isAffine()
    {
        return nIsAffine(native_instance);
    }

    public boolean isIdentity()
    {
        return nIsIdentity(native_instance);
    }

    public void mapPoints(float af[])
    {
        mapPoints(af, 0, af, 0, af.length >> 1);
    }

    public void mapPoints(float af[], int i, float af1[], int j, int k)
    {
        checkPointArrays(af1, j, af, i, k);
        nMapPoints(native_instance, af, i, af1, j, k, true);
    }

    public void mapPoints(float af[], float af1[])
    {
        if(af.length != af1.length)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            mapPoints(af, 0, af1, 0, af.length >> 1);
            return;
        }
    }

    public float mapRadius(float f)
    {
        return nMapRadius(native_instance, f);
    }

    public boolean mapRect(RectF rectf)
    {
        return mapRect(rectf, rectf);
    }

    public boolean mapRect(RectF rectf, RectF rectf1)
    {
        if(rectf == null || rectf1 == null)
            throw new NullPointerException();
        else
            return nMapRect(native_instance, rectf, rectf1);
    }

    public void mapVectors(float af[])
    {
        mapVectors(af, 0, af, 0, af.length >> 1);
    }

    public void mapVectors(float af[], int i, float af1[], int j, int k)
    {
        checkPointArrays(af1, j, af, i, k);
        nMapPoints(native_instance, af, i, af1, j, k, false);
    }

    public void mapVectors(float af[], float af1[])
    {
        if(af.length != af1.length)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            mapVectors(af, 0, af1, 0, af.length >> 1);
            return;
        }
    }

    public final long ni()
    {
        return native_instance;
    }

    public boolean postConcat(Matrix matrix)
    {
        nPostConcat(native_instance, matrix.native_instance);
        return true;
    }

    public boolean postRotate(float f)
    {
        nPostRotate(native_instance, f);
        return true;
    }

    public boolean postRotate(float f, float f1, float f2)
    {
        nPostRotate(native_instance, f, f1, f2);
        return true;
    }

    public boolean postScale(float f, float f1)
    {
        nPostScale(native_instance, f, f1);
        return true;
    }

    public boolean postScale(float f, float f1, float f2, float f3)
    {
        nPostScale(native_instance, f, f1, f2, f3);
        return true;
    }

    public boolean postSkew(float f, float f1)
    {
        nPostSkew(native_instance, f, f1);
        return true;
    }

    public boolean postSkew(float f, float f1, float f2, float f3)
    {
        nPostSkew(native_instance, f, f1, f2, f3);
        return true;
    }

    public boolean postTranslate(float f, float f1)
    {
        nPostTranslate(native_instance, f, f1);
        return true;
    }

    public boolean preConcat(Matrix matrix)
    {
        nPreConcat(native_instance, matrix.native_instance);
        return true;
    }

    public boolean preRotate(float f)
    {
        nPreRotate(native_instance, f);
        return true;
    }

    public boolean preRotate(float f, float f1, float f2)
    {
        nPreRotate(native_instance, f, f1, f2);
        return true;
    }

    public boolean preScale(float f, float f1)
    {
        nPreScale(native_instance, f, f1);
        return true;
    }

    public boolean preScale(float f, float f1, float f2, float f3)
    {
        nPreScale(native_instance, f, f1, f2, f3);
        return true;
    }

    public boolean preSkew(float f, float f1)
    {
        nPreSkew(native_instance, f, f1);
        return true;
    }

    public boolean preSkew(float f, float f1, float f2, float f3)
    {
        nPreSkew(native_instance, f, f1, f2, f3);
        return true;
    }

    public boolean preTranslate(float f, float f1)
    {
        nPreTranslate(native_instance, f, f1);
        return true;
    }

    public void printShortString(PrintWriter printwriter)
    {
        float af[] = new float[9];
        getValues(af);
        printwriter.print('[');
        printwriter.print(af[0]);
        printwriter.print(", ");
        printwriter.print(af[1]);
        printwriter.print(", ");
        printwriter.print(af[2]);
        printwriter.print("][");
        printwriter.print(af[3]);
        printwriter.print(", ");
        printwriter.print(af[4]);
        printwriter.print(", ");
        printwriter.print(af[5]);
        printwriter.print("][");
        printwriter.print(af[6]);
        printwriter.print(", ");
        printwriter.print(af[7]);
        printwriter.print(", ");
        printwriter.print(af[8]);
        printwriter.print(']');
    }

    public boolean rectStaysRect()
    {
        return nRectStaysRect(native_instance);
    }

    public void reset()
    {
        nReset(native_instance);
    }

    public void set(Matrix matrix)
    {
        if(matrix == null)
            reset();
        else
            nSet(native_instance, matrix.native_instance);
    }

    public boolean setConcat(Matrix matrix, Matrix matrix1)
    {
        nSetConcat(native_instance, matrix.native_instance, matrix1.native_instance);
        return true;
    }

    public boolean setPolyToPoly(float af[], int i, float af1[], int j, int k)
    {
        if(k > 4)
        {
            throw new IllegalArgumentException();
        } else
        {
            checkPointArrays(af, i, af1, j, k);
            return nSetPolyToPoly(native_instance, af, i, af1, j, k);
        }
    }

    public boolean setRectToRect(RectF rectf, RectF rectf1, ScaleToFit scaletofit)
    {
        if(rectf1 == null || rectf == null)
            throw new NullPointerException();
        else
            return nSetRectToRect(native_instance, rectf, rectf1, scaletofit.nativeInt);
    }

    public void setRotate(float f)
    {
        nSetRotate(native_instance, f);
    }

    public void setRotate(float f, float f1, float f2)
    {
        nSetRotate(native_instance, f, f1, f2);
    }

    public void setScale(float f, float f1)
    {
        nSetScale(native_instance, f, f1);
    }

    public void setScale(float f, float f1, float f2, float f3)
    {
        nSetScale(native_instance, f, f1, f2, f3);
    }

    public void setSinCos(float f, float f1)
    {
        nSetSinCos(native_instance, f, f1);
    }

    public void setSinCos(float f, float f1, float f2, float f3)
    {
        nSetSinCos(native_instance, f, f1, f2, f3);
    }

    public void setSkew(float f, float f1)
    {
        nSetSkew(native_instance, f, f1);
    }

    public void setSkew(float f, float f1, float f2, float f3)
    {
        nSetSkew(native_instance, f, f1, f2, f3);
    }

    public void setTranslate(float f, float f1)
    {
        nSetTranslate(native_instance, f, f1);
    }

    public void setValues(float af[])
    {
        if(af.length < 9)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            nSetValues(native_instance, af);
            return;
        }
    }

    public String toShortString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        toShortString(stringbuilder);
        return stringbuilder.toString();
    }

    public void toShortString(StringBuilder stringbuilder)
    {
        float af[] = new float[9];
        getValues(af);
        stringbuilder.append('[');
        stringbuilder.append(af[0]);
        stringbuilder.append(", ");
        stringbuilder.append(af[1]);
        stringbuilder.append(", ");
        stringbuilder.append(af[2]);
        stringbuilder.append("][");
        stringbuilder.append(af[3]);
        stringbuilder.append(", ");
        stringbuilder.append(af[4]);
        stringbuilder.append(", ");
        stringbuilder.append(af[5]);
        stringbuilder.append("][");
        stringbuilder.append(af[6]);
        stringbuilder.append(", ");
        stringbuilder.append(af[7]);
        stringbuilder.append(", ");
        stringbuilder.append(af[8]);
        stringbuilder.append(']');
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(64);
        stringbuilder.append("Matrix{");
        toShortString(stringbuilder);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public static final Matrix IDENTITY_MATRIX = new Matrix() {

        void oops()
        {
            throw new IllegalStateException("Matrix can not be modified");
        }

        public boolean postConcat(Matrix matrix)
        {
            oops();
            return false;
        }

        public boolean postRotate(float f)
        {
            oops();
            return false;
        }

        public boolean postRotate(float f, float f1, float f2)
        {
            oops();
            return false;
        }

        public boolean postScale(float f, float f1)
        {
            oops();
            return false;
        }

        public boolean postScale(float f, float f1, float f2, float f3)
        {
            oops();
            return false;
        }

        public boolean postSkew(float f, float f1)
        {
            oops();
            return false;
        }

        public boolean postSkew(float f, float f1, float f2, float f3)
        {
            oops();
            return false;
        }

        public boolean postTranslate(float f, float f1)
        {
            oops();
            return false;
        }

        public boolean preConcat(Matrix matrix)
        {
            oops();
            return false;
        }

        public boolean preRotate(float f)
        {
            oops();
            return false;
        }

        public boolean preRotate(float f, float f1, float f2)
        {
            oops();
            return false;
        }

        public boolean preScale(float f, float f1)
        {
            oops();
            return false;
        }

        public boolean preScale(float f, float f1, float f2, float f3)
        {
            oops();
            return false;
        }

        public boolean preSkew(float f, float f1)
        {
            oops();
            return false;
        }

        public boolean preSkew(float f, float f1, float f2, float f3)
        {
            oops();
            return false;
        }

        public boolean preTranslate(float f, float f1)
        {
            oops();
            return false;
        }

        public void reset()
        {
            oops();
        }

        public void set(Matrix matrix)
        {
            oops();
        }

        public boolean setConcat(Matrix matrix, Matrix matrix1)
        {
            oops();
            return false;
        }

        public boolean setPolyToPoly(float af[], int i, float af1[], int j, int k)
        {
            oops();
            return false;
        }

        public boolean setRectToRect(RectF rectf, RectF rectf1, ScaleToFit scaletofit)
        {
            oops();
            return false;
        }

        public void setRotate(float f)
        {
            oops();
        }

        public void setRotate(float f, float f1, float f2)
        {
            oops();
        }

        public void setScale(float f, float f1)
        {
            oops();
        }

        public void setScale(float f, float f1, float f2, float f3)
        {
            oops();
        }

        public void setSinCos(float f, float f1)
        {
            oops();
        }

        public void setSinCos(float f, float f1, float f2, float f3)
        {
            oops();
        }

        public void setSkew(float f, float f1)
        {
            oops();
        }

        public void setSkew(float f, float f1, float f2, float f3)
        {
            oops();
        }

        public void setTranslate(float f, float f1)
        {
            oops();
        }

        public void setValues(float af[])
        {
            oops();
        }

    }
;
    public static final int MPERSP_0 = 6;
    public static final int MPERSP_1 = 7;
    public static final int MPERSP_2 = 8;
    public static final int MSCALE_X = 0;
    public static final int MSCALE_Y = 4;
    public static final int MSKEW_X = 1;
    public static final int MSKEW_Y = 3;
    public static final int MTRANS_X = 2;
    public static final int MTRANS_Y = 5;
    private static final long NATIVE_ALLOCATION_SIZE = 40L;
    public final long native_instance;

}
