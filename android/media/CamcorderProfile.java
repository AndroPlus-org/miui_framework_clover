// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.hardware.Camera;

public class CamcorderProfile
{

    private CamcorderProfile(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, int i2, int j2, int k2, int l2)
    {
        duration = i;
        quality = j;
        fileFormat = k;
        videoCodec = l;
        videoBitRate = i1;
        videoFrameRate = j1;
        videoFrameWidth = k1;
        videoFrameHeight = l1;
        audioCodec = i2;
        audioBitRate = j2;
        audioSampleRate = k2;
        audioChannels = l2;
    }

    public static CamcorderProfile get(int i)
    {
        int j = Camera.getNumberOfCameras();
        android.hardware.Camera.CameraInfo camerainfo = new android.hardware.Camera.CameraInfo();
        for(int k = 0; k < j; k++)
        {
            Camera.getCameraInfo(k, camerainfo);
            if(camerainfo.facing == 0)
                return get(k, i);
        }

        return null;
    }

    public static CamcorderProfile get(int i, int j)
    {
        if((j < 0 || j > 8) && (j < 1000 || j > 1008) && (j < 2000 || j > 2005) && (j < 10000 || j > 10010))
            throw new IllegalArgumentException((new StringBuilder()).append("Unsupported quality level: ").append(j).toString());
        else
            return native_get_camcorder_profile(i, j);
    }

    public static boolean hasProfile(int i)
    {
        int j = Camera.getNumberOfCameras();
        android.hardware.Camera.CameraInfo camerainfo = new android.hardware.Camera.CameraInfo();
        for(int k = 0; k < j; k++)
        {
            Camera.getCameraInfo(k, camerainfo);
            if(camerainfo.facing == 0)
                return hasProfile(k, i);
        }

        return false;
    }

    public static boolean hasProfile(int i, int j)
    {
        return native_has_camcorder_profile(i, j);
    }

    private static final native CamcorderProfile native_get_camcorder_profile(int i, int j);

    private static final native boolean native_has_camcorder_profile(int i, int j);

    private static final native void native_init();

    public static final int QUALITY_1080P = 6;
    public static final int QUALITY_2160P = 8;
    public static final int QUALITY_2k = 10008;
    public static final int QUALITY_480P = 4;
    public static final int QUALITY_4KDCI = 10001;
    public static final int QUALITY_720P = 5;
    public static final int QUALITY_CIF = 3;
    public static final int QUALITY_HIGH = 1;
    public static final int QUALITY_HIGH_SPEED_1080P = 2004;
    public static final int QUALITY_HIGH_SPEED_2160P = 2005;
    public static final int QUALITY_HIGH_SPEED_480P = 2002;
    public static final int QUALITY_HIGH_SPEED_4KDCI = 10006;
    public static final int QUALITY_HIGH_SPEED_720P = 2003;
    public static final int QUALITY_HIGH_SPEED_CIF = 10004;
    public static final int QUALITY_HIGH_SPEED_HIGH = 2001;
    private static final int QUALITY_HIGH_SPEED_LIST_END = 2005;
    private static final int QUALITY_HIGH_SPEED_LIST_START = 2000;
    public static final int QUALITY_HIGH_SPEED_LOW = 2000;
    public static final int QUALITY_HIGH_SPEED_VGA = 10005;
    private static final int QUALITY_LIST_END = 8;
    private static final int QUALITY_LIST_START = 0;
    public static final int QUALITY_LOW = 0;
    public static final int QUALITY_QCIF = 2;
    public static final int QUALITY_QHD = 10007;
    public static final int QUALITY_QVGA = 7;
    public static final int QUALITY_TIME_LAPSE_1080P = 1006;
    public static final int QUALITY_TIME_LAPSE_2160P = 1008;
    public static final int QUALITY_TIME_LAPSE_2k = 10010;
    public static final int QUALITY_TIME_LAPSE_480P = 1004;
    public static final int QUALITY_TIME_LAPSE_4KDCI = 10003;
    public static final int QUALITY_TIME_LAPSE_720P = 1005;
    public static final int QUALITY_TIME_LAPSE_CIF = 1003;
    public static final int QUALITY_TIME_LAPSE_HIGH = 1001;
    private static final int QUALITY_TIME_LAPSE_LIST_END = 1008;
    private static final int QUALITY_TIME_LAPSE_LIST_START = 1000;
    public static final int QUALITY_TIME_LAPSE_LOW = 1000;
    public static final int QUALITY_TIME_LAPSE_QCIF = 1002;
    public static final int QUALITY_TIME_LAPSE_QHD = 10009;
    public static final int QUALITY_TIME_LAPSE_QVGA = 1007;
    public static final int QUALITY_TIME_LAPSE_VGA = 10002;
    private static final int QUALITY_VENDOR_LIST_END = 10010;
    private static final int QUALITY_VENDOR_LIST_START = 10000;
    public static final int QUALITY_VGA = 10000;
    public int audioBitRate;
    public int audioChannels;
    public int audioCodec;
    public int audioSampleRate;
    public int duration;
    public int fileFormat;
    public int quality;
    public int videoBitRate;
    public int videoCodec;
    public int videoFrameHeight;
    public int videoFrameRate;
    public int videoFrameWidth;

    static 
    {
        System.loadLibrary("media_jni");
        native_init();
    }
}
