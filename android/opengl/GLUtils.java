// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.opengl;

import android.graphics.Bitmap;

public final class GLUtils
{

    private GLUtils()
    {
    }

    public static String getEGLErrorString(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("0x").append(Integer.toHexString(i)).toString();

        case 12288: 
            return "EGL_SUCCESS";

        case 12289: 
            return "EGL_NOT_INITIALIZED";

        case 12290: 
            return "EGL_BAD_ACCESS";

        case 12291: 
            return "EGL_BAD_ALLOC";

        case 12292: 
            return "EGL_BAD_ATTRIBUTE";

        case 12293: 
            return "EGL_BAD_CONFIG";

        case 12294: 
            return "EGL_BAD_CONTEXT";

        case 12295: 
            return "EGL_BAD_CURRENT_SURFACE";

        case 12296: 
            return "EGL_BAD_DISPLAY";

        case 12297: 
            return "EGL_BAD_MATCH";

        case 12298: 
            return "EGL_BAD_NATIVE_PIXMAP";

        case 12299: 
            return "EGL_BAD_NATIVE_WINDOW";

        case 12300: 
            return "EGL_BAD_PARAMETER";

        case 12301: 
            return "EGL_BAD_SURFACE";

        case 12302: 
            return "EGL_CONTEXT_LOST";
        }
    }

    public static int getInternalFormat(Bitmap bitmap)
    {
        if(bitmap == null)
            throw new NullPointerException("getInternalFormat can't be used with a null Bitmap");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("bitmap is recycled");
        int i = native_getInternalFormat(bitmap);
        if(i < 0)
            throw new IllegalArgumentException("Unknown internalformat");
        else
            return i;
    }

    public static int getType(Bitmap bitmap)
    {
        if(bitmap == null)
            throw new NullPointerException("getType can't be used with a null Bitmap");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("bitmap is recycled");
        int i = native_getType(bitmap);
        if(i < 0)
            throw new IllegalArgumentException("Unknown type");
        else
            return i;
    }

    private static native int native_getInternalFormat(Bitmap bitmap);

    private static native int native_getType(Bitmap bitmap);

    private static native int native_texImage2D(int i, int j, int k, Bitmap bitmap, int l, int i1);

    private static native int native_texSubImage2D(int i, int j, int k, int l, Bitmap bitmap, int i1, int j1);

    public static void texImage2D(int i, int j, int k, Bitmap bitmap, int l)
    {
        if(bitmap == null)
            throw new NullPointerException("texImage2D can't be used with a null Bitmap");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("bitmap is recycled");
        if(native_texImage2D(i, j, k, bitmap, -1, l) != 0)
            throw new IllegalArgumentException("invalid Bitmap format");
        else
            return;
    }

    public static void texImage2D(int i, int j, int k, Bitmap bitmap, int l, int i1)
    {
        if(bitmap == null)
            throw new NullPointerException("texImage2D can't be used with a null Bitmap");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("bitmap is recycled");
        if(native_texImage2D(i, j, k, bitmap, l, i1) != 0)
            throw new IllegalArgumentException("invalid Bitmap format");
        else
            return;
    }

    public static void texImage2D(int i, int j, Bitmap bitmap, int k)
    {
        if(bitmap == null)
            throw new NullPointerException("texImage2D can't be used with a null Bitmap");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("bitmap is recycled");
        if(native_texImage2D(i, j, -1, bitmap, -1, k) != 0)
            throw new IllegalArgumentException("invalid Bitmap format");
        else
            return;
    }

    public static void texSubImage2D(int i, int j, int k, int l, Bitmap bitmap)
    {
        if(bitmap == null)
            throw new NullPointerException("texSubImage2D can't be used with a null Bitmap");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("bitmap is recycled");
        if(native_texSubImage2D(i, j, k, l, bitmap, -1, getType(bitmap)) != 0)
            throw new IllegalArgumentException("invalid Bitmap format");
        else
            return;
    }

    public static void texSubImage2D(int i, int j, int k, int l, Bitmap bitmap, int i1, int j1)
    {
        if(bitmap == null)
            throw new NullPointerException("texSubImage2D can't be used with a null Bitmap");
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("bitmap is recycled");
        if(native_texSubImage2D(i, j, k, l, bitmap, i1, j1) != 0)
            throw new IllegalArgumentException("invalid Bitmap format");
        else
            return;
    }
}
