// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.content.res.ResourcesImpl;
import android.os.*;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.OutputStream;
import java.nio.*;
import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.graphics:
//            Matrix, Rect, RectF, Canvas, 
//            Paint, ColorSpace, GraphicBuffer

public final class Bitmap
    implements Parcelable
{
    public static final class CompressFormat extends Enum
    {

        public static CompressFormat valueOf(String s)
        {
            return (CompressFormat)Enum.valueOf(android/graphics/Bitmap$CompressFormat, s);
        }

        public static CompressFormat[] values()
        {
            return $VALUES;
        }

        private static final CompressFormat $VALUES[];
        public static final CompressFormat JPEG;
        public static final CompressFormat PNG;
        public static final CompressFormat WEBP;
        final int nativeInt;

        static 
        {
            JPEG = new CompressFormat("JPEG", 0, 0);
            PNG = new CompressFormat("PNG", 1, 1);
            WEBP = new CompressFormat("WEBP", 2, 2);
            $VALUES = (new CompressFormat[] {
                JPEG, PNG, WEBP
            });
        }

        private CompressFormat(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }

    public static final class Config extends Enum
    {

        static Config nativeToConfig(int i)
        {
            return sConfigs[i];
        }

        public static Config valueOf(String s)
        {
            return (Config)Enum.valueOf(android/graphics/Bitmap$Config, s);
        }

        public static Config[] values()
        {
            return $VALUES;
        }

        private static final Config $VALUES[];
        public static final Config ALPHA_8;
        public static final Config ARGB_4444;
        public static final Config ARGB_8888;
        public static final Config HARDWARE;
        public static final Config RGBA_F16;
        public static final Config RGB_565;
        private static Config sConfigs[];
        final int nativeInt;

        static 
        {
            ALPHA_8 = new Config("ALPHA_8", 0, 1);
            RGB_565 = new Config("RGB_565", 1, 3);
            ARGB_4444 = new Config("ARGB_4444", 2, 4);
            ARGB_8888 = new Config("ARGB_8888", 3, 5);
            RGBA_F16 = new Config("RGBA_F16", 4, 6);
            HARDWARE = new Config("HARDWARE", 5, 7);
            $VALUES = (new Config[] {
                ALPHA_8, RGB_565, ARGB_4444, ARGB_8888, RGBA_F16, HARDWARE
            });
            sConfigs = (new Config[] {
                null, ALPHA_8, null, RGB_565, ARGB_4444, ARGB_8888, RGBA_F16, HARDWARE
            });
        }

        private Config(String s, int i, int j)
        {
            super(s, i);
            nativeInt = j;
        }
    }


    private static int[] _2D_getandroid_2D_graphics_2D_Bitmap$ConfigSwitchesValues()
    {
        if(_2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues != null)
            return _2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues;
        int ai[] = new int[Config.values().length];
        try
        {
            ai[Config.ALPHA_8.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[Config.ARGB_4444.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Config.ARGB_8888.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Config.HARDWARE.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Config.RGBA_F16.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Config.RGB_565.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues = ai;
        return ai;
    }

    static Bitmap _2D_wrap0(Parcel parcel)
    {
        return nativeCreateFromParcel(parcel);
    }

    Bitmap(long l, int i, int j, int k, boolean flag, boolean flag1, 
            byte abyte0[], NinePatch.InsetStruct insetstruct)
    {
        mDensity = getDefaultDensity();
        if(l == 0L)
            throw new RuntimeException("internal error: native bitmap is 0");
        mWidth = i;
        mHeight = j;
        mIsMutable = flag;
        mRequestPremultiplied = flag1;
        mNinePatchChunk = abyte0;
        mNinePatchInsets = insetstruct;
        if(k >= 0)
            mDensity = k;
        mNativePtr = l;
        long l1 = 32L + (long)getAllocationByteCount();
        (new NativeAllocationRegistry(android/graphics/Bitmap.getClassLoader(), nativeGetNativeFinalizer(), l1)).registerNativeAllocation(this, l);
        if(ResourcesImpl.TRACE_FOR_DETAILED_PRELOAD)
        {
            sPreloadTracingNumInstantiatedBitmaps++;
            sPreloadTracingTotalBitmapsSize += l1;
        }
    }

    private void checkHardware(String s)
    {
        if(getConfig() == Config.HARDWARE)
            throw new IllegalStateException(s);
        else
            return;
    }

    private void checkPixelAccess(int i, int j)
    {
        checkXYSign(i, j);
        if(i >= getWidth())
            throw new IllegalArgumentException("x must be < bitmap.width()");
        if(j >= getHeight())
            throw new IllegalArgumentException("y must be < bitmap.height()");
        else
            return;
    }

    private void checkPixelsAccess(int i, int j, int k, int l, int i1, int j1, int ai[])
    {
        checkXYSign(i, j);
        if(k < 0)
            throw new IllegalArgumentException("width must be >= 0");
        if(l < 0)
            throw new IllegalArgumentException("height must be >= 0");
        if(i + k > getWidth())
            throw new IllegalArgumentException("x + width must be <= bitmap.width()");
        if(j + l > getHeight())
            throw new IllegalArgumentException("y + height must be <= bitmap.height()");
        if(Math.abs(j1) < k)
            throw new IllegalArgumentException("abs(stride) must be >= width");
        j = i1 + (l - 1) * j1;
        for(i = ai.length; i1 < 0 || i1 + k > i || j < 0 || j + k > i;)
            throw new ArrayIndexOutOfBoundsException();

    }

    private void checkRecycled(String s)
    {
        if(mRecycled)
            throw new IllegalStateException(s);
        else
            return;
    }

    private static void checkWidthHeight(int i, int j)
    {
        if(i <= 0)
            throw new IllegalArgumentException("width must be > 0");
        if(j <= 0)
            throw new IllegalArgumentException("height must be > 0");
        else
            return;
    }

    private static void checkXYSign(int i, int j)
    {
        if(i < 0)
            throw new IllegalArgumentException("x must be >= 0");
        if(j < 0)
            throw new IllegalArgumentException("y must be >= 0");
        else
            return;
    }

    public static Bitmap createBitmap(int i, int j, Config config)
    {
        return createBitmap(i, j, config, true);
    }

    public static Bitmap createBitmap(int i, int j, Config config, boolean flag)
    {
        return createBitmap(((DisplayMetrics) (null)), i, j, config, flag);
    }

    public static Bitmap createBitmap(int i, int j, Config config, boolean flag, ColorSpace colorspace)
    {
        return createBitmap(((DisplayMetrics) (null)), i, j, config, flag, colorspace);
    }

    public static Bitmap createBitmap(Bitmap bitmap)
    {
        return createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    public static Bitmap createBitmap(Bitmap bitmap, int i, int j, int k, int l)
    {
        return createBitmap(bitmap, i, j, k, l, ((Matrix) (null)), false);
    }

    public static Bitmap createBitmap(Bitmap bitmap, int i, int j, int k, int l, Matrix matrix, boolean flag)
    {
        Bitmap bitmap1;
        RectF rectf;
        RectF rectf1;
        checkXYSign(i, j);
        checkWidthHeight(k, l);
        if(i + k > bitmap.getWidth())
            throw new IllegalArgumentException("x + width must be <= bitmap.width()");
        if(j + l > bitmap.getHeight())
            throw new IllegalArgumentException("y + height must be <= bitmap.height()");
        if(!bitmap.isMutable() && i == 0 && j == 0 && k == bitmap.getWidth() && l == bitmap.getHeight() && (matrix == null || matrix.isIdentity()))
            return bitmap;
        boolean flag1;
        Rect rect;
        Config config;
        Canvas canvas;
        if(bitmap.getConfig() == Config.HARDWARE)
            flag1 = true;
        else
            flag1 = false;
        bitmap1 = bitmap;
        if(flag1)
        {
            bitmap.noteHardwareBitmapSlowCall();
            bitmap1 = nativeCopyPreserveInternalConfig(bitmap.mNativePtr);
        }
        rect = new Rect(i, j, i + k, j + l);
        rectf = new RectF(0.0F, 0.0F, k, l);
        rectf1 = new RectF();
        bitmap = Config.ARGB_8888;
        config = bitmap1.getConfig();
        if(config == null) goto _L2; else goto _L1
_L1:
        _2D_getandroid_2D_graphics_2D_Bitmap$ConfigSwitchesValues()[config.ordinal()];
        JVM INSTR tableswitch 1 5: default 236
    //                   1 393
    //                   2 236
    //                   3 236
    //                   4 400
    //                   5 386;
           goto _L3 _L4 _L3 _L3 _L5 _L6
_L5:
        break MISSING_BLOCK_LABEL_400;
_L3:
        bitmap = Config.ARGB_8888;
_L2:
        Object obj;
        if(matrix == null || matrix.isIdentity())
        {
            obj = createBitmap(k, l, ((Config) (bitmap)), bitmap1.hasAlpha());
            bitmap = null;
        } else
        {
            k = matrix.rectStaysRect() ^ true;
            matrix.mapRect(rectf1, rectf);
            j = Math.round(rectf1.width());
            i = Math.round(rectf1.height());
            obj = bitmap;
            if(k != 0)
            {
                obj = bitmap;
                if(bitmap != Config.ARGB_8888)
                {
                    obj = bitmap;
                    if(bitmap != Config.RGBA_F16)
                        obj = Config.ARGB_8888;
                }
            }
            Bitmap bitmap2;
            boolean flag2;
            Paint paint;
            if(k == 0)
                flag2 = bitmap1.hasAlpha();
            else
                flag2 = true;
            bitmap2 = createBitmap(j, i, ((Config) (obj)), flag2);
            paint = new Paint();
            paint.setFilterBitmap(flag);
            obj = bitmap2;
            bitmap = paint;
            if(k != 0)
            {
                paint.setAntiAlias(true);
                obj = bitmap2;
                bitmap = paint;
            }
        }
        nativeCopyColorSpace(bitmap1.mNativePtr, ((Bitmap) (obj)).mNativePtr);
        obj.mDensity = bitmap1.mDensity;
        ((Bitmap) (obj)).setHasAlpha(bitmap1.hasAlpha());
        ((Bitmap) (obj)).setPremultiplied(bitmap1.mRequestPremultiplied);
        canvas = new Canvas(((Bitmap) (obj)));
        canvas.translate(-rectf1.left, -rectf1.top);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap1, rect, rectf, bitmap);
        canvas.setBitmap(null);
        if(flag1)
            return ((Bitmap) (obj)).copy(Config.HARDWARE, false);
        else
            return ((Bitmap) (obj));
_L6:
        bitmap = Config.RGB_565;
          goto _L2
_L4:
        bitmap = Config.ALPHA_8;
          goto _L2
        bitmap = Config.RGBA_F16;
          goto _L2
    }

    public static Bitmap createBitmap(DisplayMetrics displaymetrics, int i, int j, Config config)
    {
        return createBitmap(displaymetrics, i, j, config, true);
    }

    public static Bitmap createBitmap(DisplayMetrics displaymetrics, int i, int j, Config config, boolean flag)
    {
        return createBitmap(displaymetrics, i, j, config, flag, ColorSpace.get(ColorSpace.Named.SRGB));
    }

    public static Bitmap createBitmap(DisplayMetrics displaymetrics, int i, int j, Config config, boolean flag, ColorSpace colorspace)
    {
        if(i <= 0 || j <= 0)
            throw new IllegalArgumentException("width and height must be > 0");
        if(config == Config.HARDWARE)
            throw new IllegalArgumentException("can't create mutable bitmap with Config.HARDWARE");
        if(colorspace == null)
            throw new IllegalArgumentException("can't create bitmap without a color space");
        if(config != Config.ARGB_8888 || colorspace == ColorSpace.get(ColorSpace.Named.SRGB))
        {
            colorspace = nativeCreate(null, 0, i, i, j, config.nativeInt, true, null, null);
        } else
        {
            if(!(colorspace instanceof ColorSpace.Rgb))
                throw new IllegalArgumentException("colorSpace must be an RGB color space");
            ColorSpace.Rgb rgb = (ColorSpace.Rgb)colorspace;
            colorspace = rgb.getTransferParameters();
            if(colorspace == null)
                throw new IllegalArgumentException("colorSpace must use an ICC parametric transfer function");
            rgb = (ColorSpace.Rgb)ColorSpace.adapt(rgb, ColorSpace.ILLUMINANT_D50);
            colorspace = nativeCreate(null, 0, i, i, j, config.nativeInt, true, rgb.getTransform(), colorspace);
        }
        if(displaymetrics != null)
            colorspace.mDensity = displaymetrics.densityDpi;
        colorspace.setHasAlpha(flag);
        if((config == Config.ARGB_8888 || config == Config.RGBA_F16) && flag ^ true)
            nativeErase(((Bitmap) (colorspace)).mNativePtr, 0xff000000);
        return colorspace;
    }

    public static Bitmap createBitmap(DisplayMetrics displaymetrics, int ai[], int i, int j, int k, int l, Config config)
    {
        checkWidthHeight(k, l);
        if(Math.abs(j) < k)
            throw new IllegalArgumentException("abs(stride) must be >= width");
        int i1 = i + (l - 1) * j;
        for(int j1 = ai.length; i < 0 || i + k > j1 || i1 < 0 || i1 + k > j1;)
            throw new ArrayIndexOutOfBoundsException();

        if(k <= 0 || l <= 0)
            throw new IllegalArgumentException("width and height must be > 0");
        ai = nativeCreate(ai, i, j, k, l, config.nativeInt, false, null, null);
        if(displaymetrics != null)
            ai.mDensity = displaymetrics.densityDpi;
        return ai;
    }

    public static Bitmap createBitmap(DisplayMetrics displaymetrics, int ai[], int i, int j, Config config)
    {
        return createBitmap(displaymetrics, ai, 0, i, i, j, config);
    }

    public static Bitmap createBitmap(int ai[], int i, int j, int k, int l, Config config)
    {
        return createBitmap(((DisplayMetrics) (null)), ai, i, j, k, l, config);
    }

    public static Bitmap createBitmap(int ai[], int i, int j, Config config)
    {
        return createBitmap(((DisplayMetrics) (null)), ai, 0, i, i, j, config);
    }

    public static Bitmap createHardwareBitmap(GraphicBuffer graphicbuffer)
    {
        return nativeCreateHardwareBitmap(graphicbuffer);
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int i, int j, boolean flag)
    {
        Matrix matrix = new Matrix();
        int k = bitmap.getWidth();
        int l = bitmap.getHeight();
        if(k != i || l != j)
            matrix.setScale((float)i / (float)k, (float)j / (float)l);
        return createBitmap(bitmap, 0, 0, k, l, matrix, flag);
    }

    static int getDefaultDensity()
    {
        if(sDefaultDensity >= 0)
        {
            return sDefaultDensity;
        } else
        {
            sDefaultDensity = DisplayMetrics.DENSITY_DEVICE;
            return sDefaultDensity;
        }
    }

    private static native boolean nativeCompress(long l, int i, int j, OutputStream outputstream, byte abyte0[]);

    private static native int nativeConfig(long l);

    private static native Bitmap nativeCopy(long l, int i, boolean flag);

    private static native Bitmap nativeCopyAshmem(long l);

    private static native Bitmap nativeCopyAshmemConfig(long l, int i);

    private static native void nativeCopyColorSpace(long l, long l1);

    private static native void nativeCopyPixelsFromBuffer(long l, Buffer buffer);

    private static native void nativeCopyPixelsToBuffer(long l, Buffer buffer);

    private static native Bitmap nativeCopyPreserveInternalConfig(long l);

    private static native Bitmap nativeCreate(int ai[], int i, int j, int k, int l, int i1, boolean flag, float af[], 
            ColorSpace.Rgb.TransferParameters transferparameters);

    private static native Bitmap nativeCreateFromParcel(Parcel parcel);

    private static native GraphicBuffer nativeCreateGraphicBufferHandle(long l);

    private static native Bitmap nativeCreateHardwareBitmap(GraphicBuffer graphicbuffer);

    private static native void nativeErase(long l, int i);

    private static native Bitmap nativeExtractAlpha(long l, long l1, int ai[]);

    private static native int nativeGenerationId(long l);

    private static native int nativeGetAllocationByteCount(long l);

    private static native boolean nativeGetColorSpace(long l, float af[], float af1[]);

    private static native long nativeGetNativeFinalizer();

    private static native int nativeGetPixel(long l, int i, int j);

    private static native void nativeGetPixels(long l, int ai[], int i, int j, int k, int i1, int j1, 
            int k1);

    private static native boolean nativeHasAlpha(long l);

    private static native boolean nativeHasMipMap(long l);

    private static native boolean nativeIsPremultiplied(long l);

    private static native boolean nativeIsSRGB(long l);

    private static native void nativePrepareToDraw(long l);

    private static native void nativeReconfigure(long l, int i, int j, int k, boolean flag);

    private static native boolean nativeRecycle(long l);

    private static native int nativeRowBytes(long l);

    private static native boolean nativeSameAs(long l, long l1);

    private static native void nativeSetHasAlpha(long l, boolean flag, boolean flag1);

    private static native void nativeSetHasMipMap(long l, boolean flag);

    private static native void nativeSetPixel(long l, int i, int j, int k);

    private static native void nativeSetPixels(long l, int ai[], int i, int j, int k, int i1, int j1, 
            int k1);

    private static native void nativeSetPremultiplied(long l, boolean flag);

    private static native boolean nativeWriteToParcel(long l, boolean flag, int i, Parcel parcel);

    private void noteHardwareBitmapSlowCall()
    {
        if(getConfig() == Config.HARDWARE)
            StrictMode.noteSlowCall("Warning: attempt to read pixels from hardware bitmap, which is very slow operation");
    }

    public static int scaleFromDensity(int i, int j, int k)
    {
        while(j == 0 || k == 0 || j == k) 
            return i;
        return (i * k + (j >> 1)) / j;
    }

    public static void setDefaultDensity(int i)
    {
        sDefaultDensity = i;
    }

    public boolean compress(CompressFormat compressformat, int i, OutputStream outputstream)
    {
        checkRecycled("Can't compress a recycled bitmap");
        if(outputstream == null)
            throw new NullPointerException();
        if(i < 0 || i > 100)
        {
            throw new IllegalArgumentException("quality must be 0..100");
        } else
        {
            StrictMode.noteSlowCall("Compression of a bitmap is slow");
            Trace.traceBegin(8192L, "Bitmap.compress");
            boolean flag = nativeCompress(mNativePtr, compressformat.nativeInt, i, outputstream, new byte[4096]);
            Trace.traceEnd(8192L);
            return flag;
        }
    }

    public Bitmap copy(Config config, boolean flag)
    {
        checkRecycled("Can't copy a recycled bitmap");
        if(config == Config.HARDWARE && flag)
            throw new IllegalArgumentException("Hardware bitmaps are always immutable");
        noteHardwareBitmapSlowCall();
        config = nativeCopy(mNativePtr, config.nativeInt, flag);
        if(config != null)
        {
            config.setPremultiplied(mRequestPremultiplied);
            config.mDensity = mDensity;
        }
        return config;
    }

    public void copyPixelsFromBuffer(Buffer buffer)
    {
        checkRecycled("copyPixelsFromBuffer called on recycled bitmap");
        checkHardware("unable to copyPixelsFromBuffer, Config#HARDWARE bitmaps are immutable");
        int i = buffer.remaining();
        int j;
        long l;
        long l1;
        if(buffer instanceof ByteBuffer)
            j = 0;
        else
        if(buffer instanceof ShortBuffer)
            j = 1;
        else
        if(buffer instanceof IntBuffer)
            j = 2;
        else
            throw new RuntimeException("unsupported Buffer subclass");
        l = i;
        l1 = getByteCount();
        if(l << j < l1)
        {
            throw new RuntimeException("Buffer not large enough for pixels");
        } else
        {
            nativeCopyPixelsFromBuffer(mNativePtr, buffer);
            buffer.position((int)((long)buffer.position() + (l1 >> j)));
            return;
        }
    }

    public void copyPixelsToBuffer(Buffer buffer)
    {
        checkHardware("unable to copyPixelsToBuffer, pixel access is not supported on Config#HARDWARE bitmaps");
        int i = buffer.remaining();
        int j;
        long l;
        long l1;
        if(buffer instanceof ByteBuffer)
            j = 0;
        else
        if(buffer instanceof ShortBuffer)
            j = 1;
        else
        if(buffer instanceof IntBuffer)
            j = 2;
        else
            throw new RuntimeException("unsupported Buffer subclass");
        l = i;
        l1 = getByteCount();
        if(l << j < l1)
        {
            throw new RuntimeException("Buffer not large enough for pixels");
        } else
        {
            nativeCopyPixelsToBuffer(mNativePtr, buffer);
            buffer.position((int)((long)buffer.position() + (l1 >> j)));
            return;
        }
    }

    public Bitmap createAshmemBitmap()
    {
        checkRecycled("Can't copy a recycled bitmap");
        noteHardwareBitmapSlowCall();
        Bitmap bitmap = nativeCopyAshmem(mNativePtr);
        if(bitmap != null)
        {
            bitmap.setPremultiplied(mRequestPremultiplied);
            bitmap.mDensity = mDensity;
        }
        return bitmap;
    }

    public Bitmap createAshmemBitmap(Config config)
    {
        checkRecycled("Can't copy a recycled bitmap");
        noteHardwareBitmapSlowCall();
        config = nativeCopyAshmemConfig(mNativePtr, config.nativeInt);
        if(config != null)
        {
            config.setPremultiplied(mRequestPremultiplied);
            config.mDensity = mDensity;
        }
        return config;
    }

    public GraphicBuffer createGraphicBufferHandle()
    {
        return nativeCreateGraphicBufferHandle(mNativePtr);
    }

    public int describeContents()
    {
        return 0;
    }

    public void eraseColor(int i)
    {
        checkRecycled("Can't erase a recycled bitmap");
        if(!isMutable())
        {
            throw new IllegalStateException("cannot erase immutable bitmaps");
        } else
        {
            nativeErase(mNativePtr, i);
            return;
        }
    }

    public Bitmap extractAlpha()
    {
        return extractAlpha(null, null);
    }

    public Bitmap extractAlpha(Paint paint, int ai[])
    {
        checkRecycled("Can't extractAlpha on a recycled bitmap");
        long l;
        if(paint != null)
            l = paint.getNativeInstance();
        else
            l = 0L;
        noteHardwareBitmapSlowCall();
        paint = nativeExtractAlpha(mNativePtr, l, ai);
        if(paint == null)
        {
            throw new RuntimeException("Failed to extractAlpha on Bitmap");
        } else
        {
            paint.mDensity = mDensity;
            return paint;
        }
    }

    public final int getAllocationByteCount()
    {
        if(mRecycled)
        {
            Log.w("Bitmap", "Called getAllocationByteCount() on a recycle()'d bitmap! This is undefined behavior!");
            return 0;
        } else
        {
            return nativeGetAllocationByteCount(mNativePtr);
        }
    }

    public final int getByteCount()
    {
        if(mRecycled)
        {
            Log.w("Bitmap", "Called getByteCount() on a recycle()'d bitmap! This is undefined behavior!");
            return 0;
        } else
        {
            return getRowBytes() * getHeight();
        }
    }

    public final ColorSpace getColorSpace()
    {
        if(getConfig() == Config.RGBA_F16)
        {
            mColorSpace = null;
            return ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
        }
        if(mColorSpace != null) goto _L2; else goto _L1
_L1:
        if(!nativeIsSRGB(mNativePtr)) goto _L4; else goto _L3
_L3:
        mColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
_L2:
        return mColorSpace;
_L4:
        float af[] = new float[9];
        float af1[] = new float[7];
        if(nativeGetColorSpace(mNativePtr, af, af1))
        {
            ColorSpace.Rgb.TransferParameters transferparameters = new ColorSpace.Rgb.TransferParameters(af1[0], af1[1], af1[2], af1[3], af1[4], af1[5], af1[6]);
            ColorSpace colorspace = ColorSpace.match(af, transferparameters);
            if(colorspace != null)
                mColorSpace = colorspace;
            else
                mColorSpace = new ColorSpace.Rgb("Unknown", af, transferparameters);
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    public final Config getConfig()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called getConfig() on a recycle()'d bitmap! This is undefined behavior!");
        return Config.nativeToConfig(nativeConfig(mNativePtr));
    }

    public int getDensity()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called getDensity() on a recycle()'d bitmap! This is undefined behavior!");
        return mDensity;
    }

    public int getGenerationId()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called getGenerationId() on a recycle()'d bitmap! This is undefined behavior!");
        return nativeGenerationId(mNativePtr);
    }

    public final int getHeight()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called getHeight() on a recycle()'d bitmap! This is undefined behavior!");
        return mHeight;
    }

    public long getNativeInstance()
    {
        return mNativePtr;
    }

    public byte[] getNinePatchChunk()
    {
        return mNinePatchChunk;
    }

    public NinePatch.InsetStruct getNinePatchInsets()
    {
        return mNinePatchInsets;
    }

    public void getOpticalInsets(Rect rect)
    {
        if(mNinePatchInsets == null)
            rect.setEmpty();
        else
            rect.set(mNinePatchInsets.opticalRect);
    }

    public int getPixel(int i, int j)
    {
        checkRecycled("Can't call getPixel() on a recycled bitmap");
        checkHardware("unable to getPixel(), pixel access is not supported on Config#HARDWARE bitmaps");
        checkPixelAccess(i, j);
        return nativeGetPixel(mNativePtr, i, j);
    }

    public void getPixels(int ai[], int i, int j, int k, int l, int i1, int j1)
    {
        checkRecycled("Can't call getPixels() on a recycled bitmap");
        checkHardware("unable to getPixels(), pixel access is not supported on Config#HARDWARE bitmaps");
        if(i1 == 0 || j1 == 0)
        {
            return;
        } else
        {
            checkPixelsAccess(k, l, i1, j1, i, j, ai);
            nativeGetPixels(mNativePtr, ai, i, j, k, l, i1, j1);
            return;
        }
    }

    public final int getRowBytes()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called getRowBytes() on a recycle()'d bitmap! This is undefined behavior!");
        return nativeRowBytes(mNativePtr);
    }

    public int getScaledHeight(int i)
    {
        return scaleFromDensity(getHeight(), mDensity, i);
    }

    public int getScaledHeight(Canvas canvas)
    {
        return scaleFromDensity(getHeight(), mDensity, canvas.mDensity);
    }

    public int getScaledHeight(DisplayMetrics displaymetrics)
    {
        return scaleFromDensity(getHeight(), mDensity, displaymetrics.densityDpi);
    }

    public int getScaledWidth(int i)
    {
        return scaleFromDensity(getWidth(), mDensity, i);
    }

    public int getScaledWidth(Canvas canvas)
    {
        return scaleFromDensity(getWidth(), mDensity, canvas.mDensity);
    }

    public int getScaledWidth(DisplayMetrics displaymetrics)
    {
        return scaleFromDensity(getWidth(), mDensity, displaymetrics.densityDpi);
    }

    public final int getWidth()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called getWidth() on a recycle()'d bitmap! This is undefined behavior!");
        return mWidth;
    }

    public final boolean hasAlpha()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called hasAlpha() on a recycle()'d bitmap! This is undefined behavior!");
        return nativeHasAlpha(mNativePtr);
    }

    public final boolean hasMipMap()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called hasMipMap() on a recycle()'d bitmap! This is undefined behavior!");
        return nativeHasMipMap(mNativePtr);
    }

    public final boolean isMutable()
    {
        return mIsMutable;
    }

    public final boolean isPremultiplied()
    {
        if(mRecycled)
            Log.w("Bitmap", "Called isPremultiplied() on a recycle()'d bitmap! This is undefined behavior!");
        return nativeIsPremultiplied(mNativePtr);
    }

    public final boolean isRecycled()
    {
        return mRecycled;
    }

    public void prepareToDraw()
    {
        checkRecycled("Can't prepareToDraw on a recycled bitmap!");
        nativePrepareToDraw(mNativePtr);
    }

    public void reconfigure(int i, int j, Config config)
    {
        checkRecycled("Can't call reconfigure() on a recycled bitmap");
        if(i <= 0 || j <= 0)
            throw new IllegalArgumentException("width and height must be > 0");
        if(!isMutable())
        {
            throw new IllegalStateException("only mutable bitmaps may be reconfigured");
        } else
        {
            nativeReconfigure(mNativePtr, i, j, config.nativeInt, mRequestPremultiplied);
            mWidth = i;
            mHeight = j;
            mColorSpace = null;
            return;
        }
    }

    public void recycle()
    {
        if(!mRecycled && mNativePtr != 0L)
        {
            if(nativeRecycle(mNativePtr))
                mNinePatchChunk = null;
            mRecycled = true;
        }
    }

    void reinit(int i, int j, boolean flag)
    {
        mWidth = i;
        mHeight = j;
        mRequestPremultiplied = flag;
        mColorSpace = null;
    }

    public boolean sameAs(Bitmap bitmap)
    {
        checkRecycled("Can't call sameAs on a recycled bitmap!");
        noteHardwareBitmapSlowCall();
        if(this == bitmap)
            return true;
        if(bitmap == null)
            return false;
        bitmap.noteHardwareBitmapSlowCall();
        if(bitmap.isRecycled())
            throw new IllegalArgumentException("Can't compare to a recycled bitmap!");
        else
            return nativeSameAs(mNativePtr, bitmap.mNativePtr);
    }

    public void setConfig(Config config)
    {
        reconfigure(getWidth(), getHeight(), config);
    }

    public void setDensity(int i)
    {
        mDensity = i;
    }

    public void setHasAlpha(boolean flag)
    {
        checkRecycled("setHasAlpha called on a recycled bitmap");
        nativeSetHasAlpha(mNativePtr, flag, mRequestPremultiplied);
    }

    public final void setHasMipMap(boolean flag)
    {
        checkRecycled("setHasMipMap called on a recycled bitmap");
        nativeSetHasMipMap(mNativePtr, flag);
    }

    public void setHeight(int i)
    {
        reconfigure(getWidth(), i, getConfig());
    }

    public void setNinePatchChunk(byte abyte0[])
    {
        mNinePatchChunk = abyte0;
    }

    public void setPixel(int i, int j, int k)
    {
        checkRecycled("Can't call setPixel() on a recycled bitmap");
        if(!isMutable())
        {
            throw new IllegalStateException();
        } else
        {
            checkPixelAccess(i, j);
            nativeSetPixel(mNativePtr, i, j, k);
            return;
        }
    }

    public void setPixels(int ai[], int i, int j, int k, int l, int i1, int j1)
    {
        checkRecycled("Can't call setPixels() on a recycled bitmap");
        if(!isMutable())
            throw new IllegalStateException();
        if(i1 == 0 || j1 == 0)
        {
            return;
        } else
        {
            checkPixelsAccess(k, l, i1, j1, i, j, ai);
            nativeSetPixels(mNativePtr, ai, i, j, k, l, i1, j1);
            return;
        }
    }

    public final void setPremultiplied(boolean flag)
    {
        checkRecycled("setPremultiplied called on a recycled bitmap");
        mRequestPremultiplied = flag;
        nativeSetPremultiplied(mNativePtr, flag);
    }

    public void setWidth(int i)
    {
        reconfigure(i, getHeight(), getConfig());
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        checkRecycled("Can't parcel a recycled bitmap");
        noteHardwareBitmapSlowCall();
        if(!nativeWriteToParcel(mNativePtr, mIsMutable, mDensity, parcel))
            throw new RuntimeException("native writeToParcel failed");
        else
            return;
    }

    private static final int _2D_android_2D_graphics_2D_Bitmap$ConfigSwitchesValues[];
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Bitmap createFromParcel(Parcel parcel)
        {
            parcel = Bitmap._2D_wrap0(parcel);
            if(parcel == null)
                throw new RuntimeException("Failed to unparcel Bitmap");
            else
                return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Bitmap[] newArray(int i)
        {
            return new Bitmap[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DENSITY_NONE = 0;
    private static final long NATIVE_ALLOCATION_SIZE = 32L;
    private static final String TAG = "Bitmap";
    private static final int WORKING_COMPRESS_STORAGE = 4096;
    private static volatile int sDefaultDensity = -1;
    public static volatile int sPreloadTracingNumInstantiatedBitmaps;
    public static volatile long sPreloadTracingTotalBitmapsSize;
    public byte mBuffer[];
    private ColorSpace mColorSpace;
    public int mDensity;
    private int mHeight;
    private final boolean mIsMutable;
    private final long mNativePtr;
    private byte mNinePatchChunk[];
    private NinePatch.InsetStruct mNinePatchInsets;
    public boolean mRecycled;
    private boolean mRequestPremultiplied;
    private int mWidth;

}
