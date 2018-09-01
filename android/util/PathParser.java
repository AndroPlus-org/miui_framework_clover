// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.graphics.Path;

public class PathParser
{
    public static class PathData
    {

        protected void finalize()
            throws Throwable
        {
            if(mNativePathData != 0L)
            {
                PathParser._2D_wrap3(mNativePathData);
                mNativePathData = 0L;
            }
            super.finalize();
        }

        public long getNativePtr()
        {
            return mNativePathData;
        }

        public void setPathData(PathData pathdata)
        {
            PathParser._2D_wrap4(mNativePathData, pathdata.mNativePathData);
        }

        long mNativePathData;

        public PathData()
        {
            mNativePathData = 0L;
            mNativePathData = PathParser._2D_wrap0();
        }

        public PathData(PathData pathdata)
        {
            mNativePathData = 0L;
            mNativePathData = PathParser._2D_wrap2(pathdata.mNativePathData);
        }

        public PathData(String s)
        {
            mNativePathData = 0L;
            mNativePathData = PathParser._2D_wrap1(s, s.length());
            if(mNativePathData == 0L)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid pathData: ").append(s).toString());
            else
                return;
        }
    }


    static long _2D_wrap0()
    {
        return nCreateEmptyPathData();
    }

    static long _2D_wrap1(String s, int i)
    {
        return nCreatePathDataFromString(s, i);
    }

    static long _2D_wrap2(long l)
    {
        return nCreatePathData(l);
    }

    static void _2D_wrap3(long l)
    {
        nFinalize(l);
    }

    static void _2D_wrap4(long l, long l1)
    {
        nSetPathData(l, l1);
    }

    public PathParser()
    {
    }

    public static boolean canMorph(PathData pathdata, PathData pathdata1)
    {
        return nCanMorph(pathdata.mNativePathData, pathdata1.mNativePathData);
    }

    public static Path createPathFromPathData(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Path string can not be null.");
        } else
        {
            Path path = new Path();
            nParseStringForPath(path.mNativePath, s, s.length());
            return path;
        }
    }

    public static void createPathFromPathData(Path path, PathData pathdata)
    {
        nCreatePathFromPathData(path.mNativePath, pathdata.mNativePathData);
    }

    public static boolean interpolatePathData(PathData pathdata, PathData pathdata1, PathData pathdata2, float f)
    {
        return nInterpolatePathData(pathdata.mNativePathData, pathdata1.mNativePathData, pathdata2.mNativePathData, f);
    }

    private static native boolean nCanMorph(long l, long l1);

    private static native long nCreateEmptyPathData();

    private static native long nCreatePathData(long l);

    private static native long nCreatePathDataFromString(String s, int i);

    private static native void nCreatePathFromPathData(long l, long l1);

    private static native void nFinalize(long l);

    private static native boolean nInterpolatePathData(long l, long l1, long l2, float f);

    private static native void nParseStringForPath(long l, String s, int i);

    private static native void nSetPathData(long l, long l1);

    static final String LOGTAG = android/util/PathParser.getSimpleName();

}
