// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.location.Location;
import android.media.Image;
import android.os.SystemClock;
import android.util.Log;
import android.util.Size;
import java.io.*;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package android.hardware.camera2:
//            CameraCharacteristics, CaptureResult

public final class DngCreator
    implements AutoCloseable
{

    public DngCreator(CameraCharacteristics cameracharacteristics, CaptureResult captureresult)
    {
        if(cameracharacteristics == null || captureresult == null)
            throw new IllegalArgumentException("Null argument to DngCreator constructor");
        long l = System.currentTimeMillis();
        int i = ((Integer)cameracharacteristics.get(CameraCharacteristics.SENSOR_INFO_TIMESTAMP_SOURCE)).intValue();
        long l1;
        Object obj;
        if(i == 1)
            l1 = l - SystemClock.elapsedRealtime();
        else
        if(i == 0)
        {
            l1 = l - SystemClock.uptimeMillis();
        } else
        {
            Log.w("DngCreator", (new StringBuilder()).append("Sensor timestamp source is unexpected: ").append(i).toString());
            l1 = l - SystemClock.uptimeMillis();
        }
        obj = (Long)captureresult.get(CaptureResult.SENSOR_TIMESTAMP);
        if(obj != null)
            l = ((Long) (obj)).longValue() / 0xf4240L + l1;
        obj = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        ((DateFormat) (obj)).setTimeZone(TimeZone.getDefault());
        obj = ((DateFormat) (obj)).format(Long.valueOf(l));
        nativeInit(cameracharacteristics.getNativeCopy(), captureresult.getNativeCopy(), ((String) (obj)));
    }

    private static void colorToRgb(int i, int j, byte abyte0[])
    {
        abyte0[j] = (byte)Color.red(i);
        abyte0[j + 1] = (byte)Color.green(i);
        abyte0[j + 2] = (byte)Color.blue(i);
    }

    private static ByteBuffer convertToRGB(Bitmap bitmap)
    {
        int i = bitmap.getWidth();
        int j = bitmap.getHeight();
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(i * 3 * j);
        int ai[] = new int[i];
        byte abyte0[] = new byte[i * 3];
        for(int k = 0; k < j; k++)
        {
            bitmap.getPixels(ai, 0, i, 0, k, i, 1);
            for(int l = 0; l < i; l++)
                colorToRgb(ai[l], l * 3, abyte0);

            bytebuffer.put(abyte0);
        }

        bytebuffer.rewind();
        return bytebuffer;
    }

    private static ByteBuffer convertToRGB(Image image)
    {
        int i = image.getWidth();
        int j = image.getHeight();
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(i * 3 * j);
        android.media.Image.Plane plane = image.getPlanes()[0];
        android.media.Image.Plane plane1 = image.getPlanes()[1];
        android.media.Image.Plane plane2 = image.getPlanes()[2];
        ByteBuffer bytebuffer1 = plane.getBuffer();
        image = plane1.getBuffer();
        ByteBuffer bytebuffer2 = plane2.getBuffer();
        bytebuffer1.rewind();
        image.rewind();
        bytebuffer2.rewind();
        int k = plane.getRowStride();
        int l = plane2.getRowStride();
        int i1 = plane1.getRowStride();
        int j1 = plane.getPixelStride();
        int k1 = plane2.getPixelStride();
        int l1 = plane1.getPixelStride();
        byte abyte1[] = new byte[3];
        byte[] _tmp = abyte1;
        abyte1[0] = 0;
        abyte1[1] = 0;
        abyte1[2] = 0;
        byte abyte2[] = new byte[(i - 1) * j1 + 1];
        byte abyte3[] = new byte[(i / 2 - 1) * l1 + 1];
        byte abyte4[] = new byte[(i / 2 - 1) * k1 + 1];
        byte abyte0[] = new byte[i * 3];
        for(int i2 = 0; i2 < j; i2++)
        {
            int j2 = i2 / 2;
            bytebuffer1.position(k * i2);
            bytebuffer1.get(abyte2);
            image.position(i1 * j2);
            image.get(abyte3);
            bytebuffer2.position(l * j2);
            bytebuffer2.get(abyte4);
            for(int k2 = 0; k2 < i; k2++)
            {
                int l2 = k2 / 2;
                abyte1[0] = abyte2[j1 * k2];
                abyte1[1] = abyte3[l1 * l2];
                abyte1[2] = abyte4[k1 * l2];
                yuvToRgb(abyte1, k2 * 3, abyte0);
            }

            bytebuffer.put(abyte0);
        }

        bytebuffer1.rewind();
        image.rewind();
        bytebuffer2.rewind();
        bytebuffer.rewind();
        return bytebuffer;
    }

    private static native void nativeClassInit();

    private synchronized native void nativeDestroy();

    private synchronized native void nativeInit(CameraMetadataNative camerametadatanative, CameraMetadataNative camerametadatanative1, String s);

    private synchronized native void nativeSetDescription(String s);

    private synchronized native void nativeSetGpsTags(int ai[], String s, int ai1[], String s1, String s2, int ai2[]);

    private synchronized native void nativeSetOrientation(int i);

    private synchronized native void nativeSetThumbnail(ByteBuffer bytebuffer, int i, int j);

    private synchronized native void nativeWriteImage(OutputStream outputstream, int i, int j, ByteBuffer bytebuffer, int k, int l, long l1, boolean flag)
        throws IOException;

    private synchronized native void nativeWriteInputStream(OutputStream outputstream, InputStream inputstream, int i, int j, long l)
        throws IOException;

    private static int[] toExifLatLong(double d)
    {
        d = Math.abs(d);
        int i = (int)d;
        d = (d - (double)i) * 60D;
        int j = (int)d;
        return (new int[] {
            i, 1, j, 1, (int)((d - (double)j) * 6000D), 100
        });
    }

    private void writeByteBuffer(int i, int j, ByteBuffer bytebuffer, OutputStream outputstream, int k, int l, long l1)
        throws IOException
    {
        if(i <= 0 || j <= 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Image with invalid width, height: (").append(i).append(",").append(j).append(") passed to write").toString());
        long l2 = bytebuffer.capacity();
        long l3 = (long)l * (long)j + l1;
        if(l2 < l3)
            throw new IllegalArgumentException((new StringBuilder()).append("Image size ").append(l2).append(" is too small (must be larger than ").append(l3).append(")").toString());
        int i1 = k * i;
        if(i1 > l)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid image pixel stride, row byte width ").append(i1).append(" is too large, expecting ").append(l).toString());
        } else
        {
            bytebuffer.clear();
            nativeWriteImage(outputstream, i, j, bytebuffer, l, k, l1, bytebuffer.isDirect());
            bytebuffer.clear();
            return;
        }
    }

    private static void yuvToRgb(byte abyte0[], int i, byte abyte1[])
    {
        float f = abyte0[0] & 0xff;
        float f1 = abyte0[1] & 0xff;
        float f2 = abyte0[2] & 0xff;
        abyte1[i] = (byte)(int)Math.max(0.0F, Math.min(255F, f + (f2 - 128F) * 1.402F));
        abyte1[i + 1] = (byte)(int)Math.max(0.0F, Math.min(255F, f - (f1 - 128F) * 0.34414F - (f2 - 128F) * 0.71414F));
        abyte1[i + 2] = (byte)(int)Math.max(0.0F, Math.min(255F, f + (f1 - 128F) * 1.772F));
    }

    public void close()
    {
        nativeDestroy();
    }

    protected void finalize()
        throws Throwable
    {
        close();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public DngCreator setDescription(String s)
    {
        if(s == null)
        {
            throw new IllegalArgumentException("Null description passed to setDescription.");
        } else
        {
            nativeSetDescription(s);
            return this;
        }
    }

    public DngCreator setLocation(Location location)
    {
        if(location == null)
            throw new IllegalArgumentException("Null location passed to setLocation");
        double d = location.getLatitude();
        double d1 = location.getLongitude();
        long l = location.getTime();
        int ai[] = toExifLatLong(d);
        int ai1[] = toExifLatLong(d1);
        String s;
        String s1;
        if(d >= 0.0D)
            location = "N";
        else
            location = "S";
        if(d1 >= 0.0D)
            s = "E";
        else
            s = "W";
        s1 = sExifGPSDateStamp.format(Long.valueOf(l));
        mGPSTimeStampCalendar.setTimeInMillis(l);
        nativeSetGpsTags(ai, location, ai1, s, s1, new int[] {
            mGPSTimeStampCalendar.get(11), 1, mGPSTimeStampCalendar.get(12), 1, mGPSTimeStampCalendar.get(13), 1
        });
        return this;
    }

    public DngCreator setOrientation(int i)
    {
        if(i < 0 || i > 8)
            throw new IllegalArgumentException((new StringBuilder()).append("Orientation ").append(i).append(" is not a valid EXIF orientation value").toString());
        int j = i;
        if(i == 0)
            j = 9;
        nativeSetOrientation(j);
        return this;
    }

    public DngCreator setThumbnail(Bitmap bitmap)
    {
        if(bitmap == null)
            throw new IllegalArgumentException("Null argument to setThumbnail");
        int i = bitmap.getWidth();
        int j = bitmap.getHeight();
        if(i > 256 || j > 256)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Thumbnail dimensions width,height (").append(i).append(",").append(j).append(") too large, dimensions must be smaller than ").append(256).toString());
        } else
        {
            nativeSetThumbnail(convertToRGB(bitmap), i, j);
            return this;
        }
    }

    public DngCreator setThumbnail(Image image)
    {
        if(image == null)
            throw new IllegalArgumentException("Null argument to setThumbnail");
        int i = image.getFormat();
        if(i != 35)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported Image format ").append(i).toString());
        int j = image.getWidth();
        i = image.getHeight();
        if(j > 256 || i > 256)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Thumbnail dimensions width,height (").append(j).append(",").append(i).append(") too large, dimensions must be smaller than ").append(256).toString());
        } else
        {
            nativeSetThumbnail(convertToRGB(image), j, i);
            return this;
        }
    }

    public void writeByteBuffer(OutputStream outputstream, Size size, ByteBuffer bytebuffer, long l)
        throws IOException
    {
        if(outputstream == null)
            throw new IllegalArgumentException("Null dngOutput passed to writeByteBuffer");
        if(size == null)
            throw new IllegalArgumentException("Null size passed to writeByteBuffer");
        if(bytebuffer == null)
            throw new IllegalArgumentException("Null pixels passed to writeByteBuffer");
        if(l < 0L)
        {
            throw new IllegalArgumentException("Negative offset passed to writeByteBuffer");
        } else
        {
            int i = size.getWidth();
            writeByteBuffer(i, size.getHeight(), bytebuffer, outputstream, 2, i * 2, l);
            return;
        }
    }

    public void writeImage(OutputStream outputstream, Image image)
        throws IOException
    {
        if(outputstream == null)
            throw new IllegalArgumentException("Null dngOutput to writeImage");
        if(image == null)
            throw new IllegalArgumentException("Null pixels to writeImage");
        int i = image.getFormat();
        if(i != 32)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported image format ").append(i).toString());
        android.media.Image.Plane aplane[] = image.getPlanes();
        if(aplane == null || aplane.length <= 0)
        {
            throw new IllegalArgumentException("Image with no planes passed to writeImage");
        } else
        {
            ByteBuffer bytebuffer = aplane[0].getBuffer();
            writeByteBuffer(image.getWidth(), image.getHeight(), bytebuffer, outputstream, aplane[0].getPixelStride(), aplane[0].getRowStride(), 0L);
            return;
        }
    }

    public void writeInputStream(OutputStream outputstream, Size size, InputStream inputstream, long l)
        throws IOException
    {
        if(outputstream == null)
            throw new IllegalArgumentException("Null dngOutput passed to writeInputStream");
        if(size == null)
            throw new IllegalArgumentException("Null size passed to writeInputStream");
        if(inputstream == null)
            throw new IllegalArgumentException("Null pixels passed to writeInputStream");
        if(l < 0L)
            throw new IllegalArgumentException("Negative offset passed to writeInputStream");
        int i = size.getWidth();
        int j = size.getHeight();
        if(i <= 0 || j <= 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Size with invalid width, height: (").append(i).append(",").append(j).append(") passed to writeInputStream").toString());
        } else
        {
            nativeWriteInputStream(outputstream, inputstream, i, j, l);
            return;
        }
    }

    private static final int BYTES_PER_RGB_PIX = 3;
    private static final int DEFAULT_PIXEL_STRIDE = 2;
    private static final String GPS_DATE_FORMAT_STR = "yyyy:MM:dd";
    private static final String GPS_LAT_REF_NORTH = "N";
    private static final String GPS_LAT_REF_SOUTH = "S";
    private static final String GPS_LONG_REF_EAST = "E";
    private static final String GPS_LONG_REF_WEST = "W";
    public static final int MAX_THUMBNAIL_DIMENSION = 256;
    private static final String TAG = "DngCreator";
    private static final int TAG_ORIENTATION_UNKNOWN = 9;
    private static final String TIFF_DATETIME_FORMAT = "yyyy:MM:dd HH:mm:ss";
    private static final DateFormat sExifGPSDateStamp;
    private final Calendar mGPSTimeStampCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    private long mNativeContext;

    static 
    {
        sExifGPSDateStamp = new SimpleDateFormat("yyyy:MM:dd", Locale.US);
        sExifGPSDateStamp.setTimeZone(TimeZone.getTimeZone("UTC"));
        nativeClassInit();
    }
}
