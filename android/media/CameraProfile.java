// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.hardware.Camera;
import java.util.Arrays;
import java.util.HashMap;

public class CameraProfile
{

    public CameraProfile()
    {
    }

    private static int[] getImageEncodingQualityLevels(int i)
    {
        int j = native_get_num_image_encoding_quality_levels(i);
        if(j != 3)
            throw new RuntimeException((new StringBuilder()).append("Unexpected Jpeg encoding quality levels ").append(j).toString());
        int ai[] = new int[j];
        for(int k = 0; k < j; k++)
            ai[k] = native_get_image_encoding_quality_level(i, k);

        Arrays.sort(ai);
        return ai;
    }

    public static int getJpegEncodingQualityParameter(int i)
    {
        int j = Camera.getNumberOfCameras();
        android.hardware.Camera.CameraInfo camerainfo = new android.hardware.Camera.CameraInfo();
        for(int k = 0; k < j; k++)
        {
            Camera.getCameraInfo(k, camerainfo);
            if(camerainfo.facing == 0)
                return getJpegEncodingQualityParameter(k, i);
        }

        return 0;
    }

    public static int getJpegEncodingQualityParameter(int i, int j)
    {
        if(j < 0 || j > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported quality level: ").append(j).toString());
        HashMap hashmap = sCache;
        hashmap;
        JVM INSTR monitorenter ;
        int ai[] = (int[])sCache.get(Integer.valueOf(i));
        int ai1[];
        ai1 = ai;
        if(ai != null)
            break MISSING_BLOCK_LABEL_82;
        ai1 = getImageEncodingQualityLevels(i);
        sCache.put(Integer.valueOf(i), ai1);
        i = ai1[j];
        hashmap;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    private static final native int native_get_image_encoding_quality_level(int i, int j);

    private static final native int native_get_num_image_encoding_quality_levels(int i);

    private static final native void native_init();

    public static final int QUALITY_HIGH = 2;
    public static final int QUALITY_LOW = 0;
    public static final int QUALITY_MEDIUM = 1;
    private static final HashMap sCache = new HashMap();

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
