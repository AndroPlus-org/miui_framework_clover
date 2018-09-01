// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2;

import android.util.AndroidException;

public class CameraAccessException extends AndroidException
{

    public CameraAccessException(int i)
    {
        super(getDefaultMessage(i));
        mReason = i;
    }

    public CameraAccessException(int i, String s)
    {
        super(getCombinedMessage(i, s));
        mReason = i;
    }

    public CameraAccessException(int i, String s, Throwable throwable)
    {
        super(getCombinedMessage(i, s), throwable);
        mReason = i;
    }

    public CameraAccessException(int i, Throwable throwable)
    {
        super(getDefaultMessage(i), throwable);
        mReason = i;
    }

    private static String getCombinedMessage(int i, String s)
    {
        return String.format("%s (%d): %s", new Object[] {
            getProblemString(i), Integer.valueOf(i), s
        });
    }

    public static String getDefaultMessage(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 4: // '\004'
            return "The camera device is in use already";

        case 5: // '\005'
            return "The system-wide limit for number of open cameras has been reached, and more camera devices cannot be opened until previous instances are closed.";

        case 2: // '\002'
            return "The camera device is removable and has been disconnected from the Android device, or the camera service has shut down the connection due to a higher-priority access request for the camera device.";

        case 1: // '\001'
            return "The camera is disabled due to a device policy, and cannot be opened.";

        case 3: // '\003'
            return "The camera device is currently in the error state; no further calls to it will succeed.";
        }
    }

    private static String getProblemString(int i)
    {
        i;
        JVM INSTR lookupswitch 6: default 60
    //                   1: 83
    //                   2: 77
    //                   3: 89
    //                   4: 65
    //                   5: 71
    //                   1000: 95;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        String s = "<UNKNOWN ERROR>";
_L9:
        return s;
_L5:
        s = "CAMERA_IN_USE";
        continue; /* Loop/switch isn't completed */
_L6:
        s = "MAX_CAMERAS_IN_USE";
        continue; /* Loop/switch isn't completed */
_L3:
        s = "CAMERA_DISCONNECTED";
        continue; /* Loop/switch isn't completed */
_L2:
        s = "CAMERA_DISABLED";
        continue; /* Loop/switch isn't completed */
_L4:
        s = "CAMERA_ERROR";
        continue; /* Loop/switch isn't completed */
_L7:
        s = "CAMERA_DEPRECATED_HAL";
        if(true) goto _L9; else goto _L8
_L8:
    }

    public final int getReason()
    {
        return mReason;
    }

    public static final int CAMERA_DEPRECATED_HAL = 1000;
    public static final int CAMERA_DISABLED = 1;
    public static final int CAMERA_DISCONNECTED = 2;
    public static final int CAMERA_ERROR = 3;
    public static final int CAMERA_IN_USE = 4;
    public static final int MAX_CAMERAS_IN_USE = 5;
    private static final long serialVersionUID = 0x4e22fb28f3a4e7dbL;
    private final int mReason;
}
